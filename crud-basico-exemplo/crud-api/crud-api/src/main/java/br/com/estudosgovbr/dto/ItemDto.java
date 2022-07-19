package br.com.estudosgovbr.dto;

import java.io.Serializable;
import br.com.estudosgovbr.entity.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ItemDto implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String count;
	private String status;

	public ItemEntity convertToEntity()
	{
		return new ItemEntity(getId(), getName(), getCount(), getStatus());
	}
}
