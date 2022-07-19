package br.com.estudosgovbr.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import br.com.estudosgovbr.data.dto.NotaDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nota extends PanacheEntityBase
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String titulo;

    @Column(length = 400)
    private String anotacao;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    public NotaDTO getDTO()
    {
        return new NotaDTO(this.getId(), this.getTitulo(), this.getAnotacao(), this.getCategoria().getDTO());
    }
    
}
