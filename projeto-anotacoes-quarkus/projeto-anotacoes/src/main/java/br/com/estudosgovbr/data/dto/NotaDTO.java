package br.com.estudosgovbr.data.dto;

import java.io.Serializable;
import br.com.estudosgovbr.data.entity.Nota;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotaDTO implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private Integer idNota;

    private String tituloNota;

    private String textoAnotacao;

    private CategoriaDTO categoria;

    public Nota convertToEntity()
    {
        return new Nota(this.getIdNota(), this.getTituloNota(), this.getTextoAnotacao(), this.getCategoria().convertToEntity());
    }
    
}
