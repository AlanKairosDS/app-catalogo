package com.spring.msvc.catalogo.controllers.categoria;

import com.spring.common.tools.entity.RestResponse;
import com.spring.msvc.catalogo.entity.Categoria;
import com.spring.msvc.catalogo.services.categoria.CategoriaService;
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
@RequestMapping("/v1/api/categoria")
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  @PostMapping(
          value = "/nueva-categoria",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> nuevaCategoria (@Valid @RequestBody Categoria categoria) {
    return categoriaService.nuevaCategoria(categoria);
  }

  @PutMapping(
          value = "/actualizar-categoria/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE},
          consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> actualizarCategoria (@PathVariable String id,
          @Valid @RequestBody Categoria categoria) {
    return categoriaService.actualizarCategoria(id, categoria);
  }

  @DeleteMapping(
          value = "/eliminar-categoria/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> eliminarCategoria (@PathVariable String id) {
    return categoriaService.eliminarCategoria(id);
  }

  @GetMapping(
          value = "/consultar-categorias",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarCategorias () {
    return categoriaService.consultarCategorias();
  }

  @GetMapping(
          value = "/consultar-categoria-id/{id}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarCategoriaById (@PathVariable String id) {
    return categoriaService.consultarCategoriaById(id);
  }

  @GetMapping(
          value = "/consultar-categoria-nombre/{nombre}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> consultarCategoriaByNombre (@PathVariable String nombre) {
    return categoriaService.consultarCategoriaByNombre(nombre);
  }

  @GetMapping(
          value = "/agregar-producto/{idCategoria}/{idProducto}",
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RestResponse<Object>> agregarProducto (@PathVariable String idCategoria,
          @PathVariable String idProducto) {
    return categoriaService.agregarProducto(idCategoria, idProducto);
  }
}
