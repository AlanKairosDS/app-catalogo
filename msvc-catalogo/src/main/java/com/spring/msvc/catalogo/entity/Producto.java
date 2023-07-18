package com.spring.msvc.catalogo.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Productos")
public class Producto {

  @Id
  private String id;

  @NotNull(message = "El Nombre del Producto no puede ser NULL")
  @NotBlank(message = "El Nombre del Producto no puede estar vacio")
  @Size(max = 50)
  private String nombre;

  @NotNull(message = "La Descripción del Producto no puede ser NULL")
  @NotBlank(message = "La Descripción del Producto no puede estar vacia")
  @Size(max = 500)
  private String descripcion;

  @NotNull(message = "El Tipo del Producto no puede ser NULL")
  @NotBlank(message = "El Tipo del Producto no puede estar vacio")
  @Size(max = 50)
  private String tipo;

  @NotNull(message = "El Producto debe de tener al menos una Subcategoria")
  @NotEmpty(message = "El Producto debe de tener al menos una Subcategoria")
  private Set<String> subcategoria;

  private String imagen;

  private String fechaAlta;

  private String fechaModificacion;
}
