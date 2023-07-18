package com.spring.msvc.catalogo.controllers.producto;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.catalogo.entity.Producto;
import com.spring.msvc.catalogo.services.producto.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "${msvccatalogo.app.origin}", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/v1/api/producto")
public class ProductoController {

  @Autowired
  private ProductoService productoService;

  @PostMapping(
          value = "/nuevo-producto",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> nuevoProducto (@Valid @RequestBody Producto producto) {
    return productoService.nuevoProducto(producto);
  }

  @PutMapping(
          value = "/actualizar-producto/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> actualizarProducto (@PathVariable String id,
          @Valid @RequestBody Producto producto) {
    return productoService.actualizarProducto(id, producto);
  }

  @DeleteMapping(
          value = "/eliminar-producto/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> eliminarProducto (@PathVariable String id) {
    return productoService.eliminarProducto(id);
  }

  @GetMapping(
          value = "/consultar-productos",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarProductos () {
    return productoService.consultarProductos();
  }

  @GetMapping(
          value = "/consultar-producto-id/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarProductoById (@PathVariable String id) {
    return productoService.consultarProductoById(id);
  }

  @GetMapping(
          value = "/consultar-producto-nombre/{nombre}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarProductoByNombre (@PathVariable String nombre) {
    return productoService.consultarProductoByNombre(nombre);
  }

  @GetMapping(
          value = "/consultar-producto-tipo/{tipo}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarProductoByTipo (@PathVariable String tipo) {
    return productoService.consultarProductoByTipo(tipo);
  }
}
