package com.spring.msvc.catalogo.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Categorias")
public class Categoria {

  @Id
  private String id;

  @NotNull(message = "El Nombre de la Categoria no puede ser NULL")
  @NotBlank(message = "El Nombre de la Categoria no puede estar vacio")
  @Size(max = 50)
  private String nombre;

  @NotNull(message = "La Descripción de la Categoria no puede ser NULL")
  @NotBlank(message = "La Descripción de la Categoria no puede estar vacia")
  @Size(max = 500)
  private String descripcion;

  private String fechaAlta;

  private String fechaModificacion;

  @DBRef
  private Set<Producto> productos = new HashSet<>();
}
