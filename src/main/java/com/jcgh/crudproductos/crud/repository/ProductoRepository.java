package com.jcgh.crudproductos.crud.repository;

import com.jcgh.crudproductos.crud.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Productos,Long> {
    Optional<Productos> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
