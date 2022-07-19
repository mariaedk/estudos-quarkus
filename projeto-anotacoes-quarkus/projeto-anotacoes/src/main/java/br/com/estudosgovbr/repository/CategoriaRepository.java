package br.com.estudosgovbr.repository;

import javax.enterprise.context.ApplicationScoped;
import br.com.estudosgovbr.data.entity.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepositoryBase<Categoria, Integer>
{ }
