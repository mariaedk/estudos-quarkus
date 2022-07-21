package br.com.estudosgovbr.dto;

import java.io.Serializable;
import java.sql.Date;
import br.com.estudosgovbr.entity.Remedio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemedioDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

	private Integer idRemedio;

	private String nomeRemedio;

	private String statusRemedioUso;

	private Date deadLineRemedio;

	private Date createdAtRemedio;

	public Remedio convertToEntity()
	{
		return new Remedio(
			this.getIdRemedio(),
			this.getNomeRemedio(),
			this.getStatusRemedioUso(),
			this.getDeadLineRemedio(),
			this.getCreatedAtRemedio()
		);
	}

}
