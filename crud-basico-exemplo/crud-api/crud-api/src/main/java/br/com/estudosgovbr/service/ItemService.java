package br.com.estudosgovbr.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import br.com.estudosgovbr.dto.ItemDto;
import br.com.estudosgovbr.entity.ItemEntity;
import br.com.estudosgovbr.repository.ItemRepository;

@ApplicationScoped
public class ItemService 
{
	@Inject
	ItemRepository itemRepository;
	
	public List<ItemDto> get() 
    {
		List<ItemEntity> listAll = itemRepository.findAll().list();
		return listAll.stream().map(
						ie -> 
						{
							return new ItemDto(ie.getId(), ie.getName(), ie.getCount(), ie.getStatus());
						}
					).collect(Collectors.toList());
	}

    public ItemDto getById(Long id)
    {
		Optional<ItemEntity> optional = itemRepository.findByIdOptional(id);
		ItemEntity entity = optional.orElseThrow(() -> new NotFoundException());
        return new ItemDto(entity.getId(), entity.getName(), entity.getCount(), entity.getStatus());
    }
	
	@Transactional
	public ItemDto create(ItemDto item) 
	{
		ItemEntity ie = item.convertToEntity();
		ie.persist();
		return ie.getDto();
	}
	
	@Transactional
	public ItemDto update(ItemDto item) 
	{
		ItemEntity entity = itemRepository.findById(item.getId());
		entity.setName(item.getName());
		entity.setCount(item.getCount());
		entity.setStatus(item.getStatus());
		return entity.getDto();
	}
	
	@Transactional
	public boolean delete(Long id) 
	{
		return itemRepository.deleteById(id);
	}
}
