package br.com.estudosgovbr.repository;

import javax.enterprise.context.ApplicationScoped;
import br.com.estudosgovbr.data.entity.Nota;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class NotaRepository implements PanacheRepositoryBase<Nota, Integer>
{ }
