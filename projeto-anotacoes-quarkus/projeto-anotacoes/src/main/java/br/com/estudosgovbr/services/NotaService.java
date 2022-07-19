package br.com.estudosgovbr.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import br.com.estudosgovbr.data.dto.NotaDTO;
import br.com.estudosgovbr.data.entity.Nota;
import br.com.estudosgovbr.repository.NotaRepository;

@ApplicationScoped
public class NotaService 
{
    @Inject
    NotaRepository notaRepository;

    public List<NotaDTO> getAllNotas()
    {
        List<Nota> listEntity = notaRepository.findAll().list();
        return listEntity.stream()
                         .map(
                            entity -> {
                                return new NotaDTO(entity.getId(), 
                                                  entity.getTitulo(), 
                                                  entity.getAnotacao(),
                                                  entity.getCategoria().getDTO());
                            }
                         ).collect(Collectors.toList()); 
    }

    public NotaDTO getById(Integer id)
    {
		Optional<Nota> optionalNota = this.notaRepository.findByIdOptional(id);
		Nota entity = optionalNota.orElseThrow(() -> new NotFoundException());
        return new NotaDTO(entity.getId(), entity.getTitulo(), entity.getAnotacao(), entity.getCategoria().getDTO());
    }

    @Transactional
    public NotaDTO post(NotaDTO dto)
    {
        Nota entity = dto.convertToEntity();
        notaRepository.persist(entity);
        return entity.getDTO(); 
    }

    @Transactional
    public NotaDTO update(NotaDTO dto)
    {
        Nota entity = this.notaRepository.findById(dto.getIdNota());    
        entity.setTitulo(dto.getTituloNota());
        entity.setAnotacao(dto.getTextoAnotacao());
        entity.setCategoria(dto.getCategoria().convertToEntity());

        return entity.getDTO();
    }

    @Transactional
    public Boolean deleteNota(Integer id)
    {
        return this.notaRepository.deleteById(id);
    }



}
