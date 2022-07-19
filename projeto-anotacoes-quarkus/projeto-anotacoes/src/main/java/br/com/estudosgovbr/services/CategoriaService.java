package br.com.estudosgovbr.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import br.com.estudosgovbr.data.dto.CategoriaDTO;
import br.com.estudosgovbr.data.entity.Categoria;
import br.com.estudosgovbr.repository.CategoriaRepository;

@ApplicationScoped
public class CategoriaService 
{
    @Inject
    CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> getAllCategorias()
    {
        List<Categoria> listCategoriasEntity = categoriaRepository.findAll().list();
        return listCategoriasEntity.stream().map(categoria -> 
                                                    {
                                                        return new CategoriaDTO(
                                                            categoria.getId(), 
                                                            categoria.getDescricao(),
                                                            categoria.getFlAtivo());
                                                    }).collect(Collectors.toList());
    }
    
    public CategoriaDTO getById(Integer id)
    {
        Optional<Categoria> optionalCategoria = categoriaRepository.findByIdOptional(id);
        Categoria entity = optionalCategoria.orElseThrow(() -> new NotFoundException());
        return new CategoriaDTO(entity.getId(), entity.getDescricao(), entity.getFlAtivo());
    }

    @Transactional
    public CategoriaDTO postCategoria(CategoriaDTO dto)
    {
        Categoria entity = dto.convertToEntity();
        categoriaRepository.persist(entity);
        return entity.getDTO();
    }

    @Transactional
    public Boolean deleteCategoria(Integer id)
    {
        return this.categoriaRepository.deleteById(id);
    }

    @Transactional
    public Boolean toggle(Integer id)
    {
        Optional<Categoria> optionalCategoria = categoriaRepository.findByIdOptional(id);
        Categoria entity = optionalCategoria.orElseThrow(() -> new NotFoundException());
        entity.setFlAtivo(!entity.getFlAtivo());

        return entity.getId() > 0;
    }

    

    
}
