package com.spring.msvc.catalogo.repositories.categoria;

import com.spring.msvc.catalogo.entity.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {

  Optional<Categoria> findByNombre (String nombre);
}
