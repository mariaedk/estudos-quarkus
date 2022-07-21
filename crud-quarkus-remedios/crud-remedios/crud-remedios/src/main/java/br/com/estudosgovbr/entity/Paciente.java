package br.com.estudosgovbr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import br.com.estudosgovbr.dto.PacienteDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paciente extends PanacheEntityBase
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 25)
    private String telefone;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false)
    private String genero;

    @OneToOne
    @JoinColumn(name = "id_remedio")
    private Remedio remedio;

    public PacienteDTO getDTO()
	{
		return new PacienteDTO(
			this.getId(),
			this.getTelefone(),
			this.getNome(),
			this.getGenero(),
			this.getRemedio().getDTO()
		);
	}
}
