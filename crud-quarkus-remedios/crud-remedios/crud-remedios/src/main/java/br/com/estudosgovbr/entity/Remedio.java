package br.com.estudosgovbr.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import br.com.estudosgovbr.dto.RemedioDTO;
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
public class Remedio extends PanacheEntityBase
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50, nullable = false)
	private String nome;

	/**
	 * U - Medicamento em uso 
	 * P - Medicamento pausado 
	 * F - Medicamento finalizado 
	 * I - Medicamento interrompido
	 */
	@Column(length = 1)
	private String statusRemedio;
	
	@Column(nullable = false)
    private Date deadLine;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

	
	public RemedioDTO getDTO()
	{
		return new RemedioDTO(
			this.getId(),
			this.getNome(),
			this.getStatusRemedio(),
			this.getDeadLine(),
			this.getCreatedAt()
		);
	}
    
}
