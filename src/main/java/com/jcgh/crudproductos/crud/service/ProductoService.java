package com.jcgh.crudproductos.crud.service;

import com.jcgh.crudproductos.crud.entity.Productos;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    public List<Productos> list();

    public Optional<Productos> getOne(Long id);

    public Optional<Productos> getByNombre(String nombre);

    public void save(Productos productos);

    public void delete(Long id);

    public boolean existsById(Long id);

    public boolean existeByNombre(String nombre);

}
