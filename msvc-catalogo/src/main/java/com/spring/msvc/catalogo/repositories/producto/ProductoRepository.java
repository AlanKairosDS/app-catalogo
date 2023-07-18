package com.spring.msvc.catalogo.repositories.producto;

import com.spring.msvc.catalogo.entity.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends MongoRepository<Producto, String> {

  Optional<Producto> findByNombre (String nombre);

  List<Producto> findByTipo (String tipo);
}
