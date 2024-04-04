package com.jcgh.crudproductos.crud.service.imp;

import com.jcgh.crudproductos.crud.entity.Productos;
import com.jcgh.crudproductos.crud.repository.ProductoRepository;
import com.jcgh.crudproductos.crud.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoImp implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<Productos> list() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Productos> getOne(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Optional<Productos> getByNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    @Override
    public void save(Productos productos) {
        productoRepository.save(productos);
    }

    @Override
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return productoRepository.existsById(id);
    }

    @Override
    public boolean existeByNombre(String nombre) {
        return productoRepository.existsByNombre(nombre);
    }
}
