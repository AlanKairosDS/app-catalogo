package com.spring.msvc.catalogo.services.producto;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.catalogo.entity.Producto;
import org.springframework.http.ResponseEntity;

public interface ProductoService {

  ResponseEntity<RestResponse<Object>> nuevoProducto (Producto producto);

  ResponseEntity<RestResponse<Object>> actualizarProducto (String id, Producto producto);

  ResponseEntity<RestResponse<Object>> eliminarProducto (String id);

  ResponseEntity<RestResponse<Object>> consultarProductos ();

  ResponseEntity<RestResponse<Object>> consultarProductoById (String id);

  ResponseEntity<RestResponse<Object>> consultarProductoByNombre (String nombre);

  ResponseEntity<RestResponse<Object>> consultarProductoByTipo (String tipo);
}
