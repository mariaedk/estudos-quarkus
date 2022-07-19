package br.com.estudosgovbr.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.estudosgovbr.entity.ItemEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ItemRepository implements PanacheRepositoryBase<ItemEntity, Long>
{ }
