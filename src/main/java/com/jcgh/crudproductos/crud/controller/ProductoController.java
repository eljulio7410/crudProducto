package com.jcgh.crudproductos.crud.controller;

import com.jcgh.crudproductos.crud.dto.Mensaje;
import com.jcgh.crudproductos.crud.dto.ProductoDto;
import com.jcgh.crudproductos.crud.entity.Productos;
import com.jcgh.crudproductos.crud.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {


    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Productos>> list(){
        List<Productos> list = productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Productos> getById(@PathVariable("id") Long id){
        if (!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Productos productos = productoService.getOne(id).get();
        return new ResponseEntity(productos,HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Productos> getByNombre(@PathVariable("nombre") String nombre){
        if (!productoService.existeByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Productos productos = productoService.getByNombre(nombre).get();
        return new ResponseEntity(productos,HttpStatus.OK);
    }
    @PostMapping("/create")
    public  ResponseEntity<?> create(@RequestBody ProductoDto productoDto){
        if (StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (productoDto.getPrecio()<0)
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if (productoService.existeByNombre(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"),HttpStatus.BAD_REQUEST);

        Productos productos = new Productos(productoDto.getNombre(),productoDto.getPrecio());
        productoService.save(productos);
        return new ResponseEntity(new Mensaje("Producto creado"), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public  ResponseEntity<?> update(@PathVariable("id")Long id, @RequestBody ProductoDto productoDto){
        if (!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (productoService.existeByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"),HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (productoDto.getPrecio()<0)
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Productos productos = productoService.getOne(id).get();
        productos.setNombre(productoDto.getNombre());
        productos.setPrecio(productoDto.getPrecio());
        productoService.save(productos);
        return new ResponseEntity(new Mensaje("Producto actualizado"), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if (!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity(new Mensaje("Producto Eliminado"), HttpStatus.OK);
    }
}

