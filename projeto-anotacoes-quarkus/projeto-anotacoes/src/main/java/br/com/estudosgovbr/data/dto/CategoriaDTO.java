package br.com.estudosgovbr.data.dto;

import java.io.Serializable;
import br.com.estudosgovbr.data.entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer idCategoria;

    private String descricaoCategoria;

    private Boolean flAtivo;

    public Categoria convertToEntity()
    {
        return new Categoria(this.getIdCategoria(), this.getDescricaoCategoria(), this.getFlAtivo());
    }
}
