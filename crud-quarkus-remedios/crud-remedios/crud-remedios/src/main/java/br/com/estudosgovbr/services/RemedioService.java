package br.com.estudosgovbr.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.estudosgovbr.dto.RemedioDTO;
import br.com.estudosgovbr.entity.Remedio;
import br.com.estudosgovbr.repository.RemedioRepository;

@ApplicationScoped
public class RemedioService
{
    @Inject
    RemedioRepository remedioRepository;

    public List<RemedioDTO> listAll()
    {
        List<Remedio> listaRemedios = remedioRepository.findAll().list();
        return listaRemedios.stream()
                             .map(entity -> new RemedioDTO(
                                                    entity.getId(), 
                                                    entity.getNome(),
                                                    entity.getStatusRemedio(),
                                                    entity.getDeadLine(),
                                                    entity.getCreatedAt()
                                )).collect(Collectors.toList());
    }
    
    public List<RemedioDTO> getAllByNomeOrdered() 
    {
        return remedioRepository.list("order by nome")
                                .stream()
                                .map(Remedio::getDTO)
                                .collect(Collectors.toList());
	}  

    public RemedioDTO getById(Integer id) throws Exception
    {
        return remedioRepository.findByIdOptional(id).orElseThrow(() -> new Exception("Id não encontrado.")).getDTO();
    }
    
    @Transactional
    public RemedioDTO post(RemedioDTO entity)
    {
        Remedio remedio = entity.convertToEntity();
        remedioRepository.persist(remedio);
        return remedio.getDTO();
    }

    @Transactional
    public RemedioDTO update(RemedioDTO dto)
    {
        Remedio entity = this.remedioRepository.findById(dto.getIdRemedio());    
        entity.setNome(dto.getNomeRemedio());
        entity.setStatusRemedio(dto.getStatusRemedioUso());
        entity.setDeadLine(dto.getDeadLineRemedio());
        entity.setCreatedAt(dto.getCreatedAtRemedio());

        return entity.getDTO();
    }

    @Transactional
    public boolean delete(Integer id)
    {
        return remedioRepository.deleteById(id);
    }  
}
