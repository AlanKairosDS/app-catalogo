package com.spring.msvc.catalogo.services.categoria;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.catalogo.entity.Categoria;
import org.springframework.http.ResponseEntity;

public interface CategoriaService {

  ResponseEntity<RestResponse<Object>> nuevaCategoria (Categoria categoria);

  ResponseEntity<RestResponse<Object>> actualizarCategoria (String id, Categoria categoria);

  ResponseEntity<RestResponse<Object>> eliminarCategoria (String id);

  ResponseEntity<RestResponse<Object>> consultarCategorias ();

  ResponseEntity<RestResponse<Object>> consultarCategoriaById (String id);

  ResponseEntity<RestResponse<Object>> consultarCategoriaByNombre (String nombre);

  ResponseEntity<RestResponse<Object>> agregarProducto (String idCategoria, String idProducto);
}
