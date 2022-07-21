package br.com.estudosgovbr.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.estudosgovbr.entity.Paciente;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class PacienteRepository implements PanacheRepositoryBase<Paciente, Integer>
{
    
}
