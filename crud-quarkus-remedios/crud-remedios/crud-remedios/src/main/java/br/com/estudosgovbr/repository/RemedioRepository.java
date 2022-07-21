package br.com.estudosgovbr.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.estudosgovbr.entity.Remedio;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class RemedioRepository implements PanacheRepositoryBase<Remedio, Integer>
{
    
}
