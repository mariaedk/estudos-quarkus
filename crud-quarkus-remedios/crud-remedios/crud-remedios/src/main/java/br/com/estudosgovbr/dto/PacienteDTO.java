package br.com.estudosgovbr.dto;

import java.io.Serializable;
import br.com.estudosgovbr.entity.Paciente;
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
public class PacienteDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

	private Integer idPaciente;
	private String telefonePaciente;
	private String nomePaciente;
	private String generoPaciente;
	private RemedioDTO remedioDto;

	public Paciente convertToEntity()
	{
		return new Paciente(
			this.getIdPaciente(),
			this.getTelefonePaciente(),
			this.getNomePaciente(),
			this.getGeneroPaciente(),
			this.getRemedioDto().convertToEntity()
		);
	}

}
