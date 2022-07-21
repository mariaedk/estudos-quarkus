package br.com.estudosgovbr.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.estudosgovbr.dto.PacienteDTO;
import br.com.estudosgovbr.entity.Paciente;
import br.com.estudosgovbr.repository.PacienteRepository;

@ApplicationScoped
public class PacienteService
{
    @Inject
    PacienteRepository pacienteRepository;

    public List<PacienteDTO> listAll()
    {
        List<Paciente> listaPacientes = pacienteRepository.findAll().list();
        return listaPacientes.stream()
                             .map(entity -> new PacienteDTO(
                                                    entity.getId(), 
                                                    entity.getTelefone(),
                                                    entity.getNome(),
                                                    entity.getGenero(),
                                                    entity.getRemedio().getDTO()
                                )).collect(Collectors.toList());
    }

    
    
    public List<PacienteDTO> getPacienteByStatus(String status)
	{
        return this.getAllByNomeOrdered().stream()
                                  .filter(paciente -> paciente.getRemedioDto().getStatusRemedioUso().equals(status))
                                  .collect(Collectors.toList());
	}

    public List<PacienteDTO> getAllByNomeOrdered() 
    {
        return pacienteRepository.list("order by nome")
               .stream()
               .map(Paciente::getDTO)
               .collect(Collectors.toList());
	}

    public PacienteDTO getById(Integer id) throws Exception
    {
        return pacienteRepository.findByIdOptional(id).orElseThrow(() -> new Exception("Id não encontrado.")).getDTO();
    }
    
    @Transactional
    public PacienteDTO post(PacienteDTO entity)
    {
        Paciente paciente = entity.convertToEntity();
        pacienteRepository.persist(paciente);
        return paciente.getDTO();
    }

    @Transactional
    public PacienteDTO update(PacienteDTO dto)
    {
        Paciente entity = this.pacienteRepository.findById(dto.getIdPaciente());    
        entity.setTelefone(dto.getTelefonePaciente());
        entity.setNome(dto.getNomePaciente());
        entity.setGenero(dto.getGeneroPaciente());
        entity.setRemedio(dto.getRemedioDto().convertToEntity());

        return entity.getDTO();
    }

    @Transactional
    public boolean delete(Integer id)
    {
        return pacienteRepository.deleteById(id);
    }

}
