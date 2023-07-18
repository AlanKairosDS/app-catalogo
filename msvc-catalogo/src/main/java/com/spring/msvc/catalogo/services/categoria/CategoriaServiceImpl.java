package com.spring.msvc.catalogo.services.categoria;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.catalogo.entity.Categoria;
import com.spring.msvc.catalogo.entity.Producto;
import com.spring.msvc.catalogo.repositories.categoria.CategoriaRepository;
import com.spring.msvc.catalogo.repositories.producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

  private static final String EXITO = "Proceso ejecutado correctamente";
  private static final String ISSUE = "Ocurrio un problema en la ejecucion del proceso";

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private ProductoRepository productoRepository;

  @Autowired
  private UtilService utilService;

  @Override
  public ResponseEntity<RestResponse<Object>> nuevaCategoria (Categoria categoria) {

    Optional<Categoria> categoriaOptional = categoriaRepository.findByNombre(categoria.getNombre());

    if (categoriaOptional.isPresent()) {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ISSUE,
              "La categoria que se requiere crear ya existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
    else {
      categoria.setFechaAlta(utilService.obtenerFechaActual());
      categoria.setFechaModificacion(utilService.obtenerFechaActual());

      categoriaRepository.save(categoria);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Categoria creado de forma correcta",
              false,
              null,
              HttpStatus.OK
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> actualizarCategoria (String id, Categoria categoria) {

    Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

    if (categoriaOptional.isPresent()) {
      Categoria categoriaUpdate = categoriaOptional.get();

      categoriaUpdate.setNombre(categoria.getNombre());
      categoriaUpdate.setDescripcion(categoria.getDescripcion());
      categoriaUpdate.setFechaModificacion(utilService.obtenerFechaActual());

      categoriaRepository.save(categoriaUpdate);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se actualiza categoria de forma correcta",
              false,
              null,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ISSUE,
              "La categoria que se requiere acualizar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> eliminarCategoria (String id) {
    if (categoriaRepository.existsById(id)) {
      categoriaRepository.deleteById(id);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Categoria eliminada de forma correcta",
              false,
              null,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ISSUE,
              "La categoria que se requiere eliminar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarCategorias () {
    List<Categoria> categoriaList = categoriaRepository.findAll();

    return new UtilService().armarRespuesta(
            HttpStatus.OK.value(),
            EXITO,
            "Se consultan categorias de forma correcta",
            false,
            categoriaList,
            HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarCategoriaById (String id) {

    Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

    if (categoriaOptional.isPresent()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulta categoria por ID de forma correcta",
              false,
              categoriaOptional.stream().collect(Collectors.toList()),
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ISSUE,
              "La categoria consultada por ID no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarCategoriaByNombre (String nombre) {

    Optional<Categoria> categoriaOptional = categoriaRepository.findByNombre(nombre);

    if (categoriaOptional.isPresent()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulta categoria por nombre de forma correcta",
              false,
              categoriaOptional.stream().collect(Collectors.toList()),
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ISSUE,
              "La categoria consultada por nombre no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> agregarProducto (String idCategoria, String idProducto) {

    Optional<Producto> productoOptional = productoRepository.findById(idProducto);

    if (productoOptional.isPresent()) {
      Optional<Categoria> categoriaOptional = categoriaRepository.findById(idCategoria);

      if (categoriaOptional.isPresent()) {
        Categoria categoriaUpdate = categoriaOptional.get();

        if (this.validarProducto(categoriaUpdate.getProductos(), idProducto)) {
          return new UtilService().armarRespuesta(
                  HttpStatus.BAD_REQUEST.value(),
                  ISSUE,
                  "El producto ya existe en la categoria",
                  true,
                  null,
                  HttpStatus.BAD_REQUEST
          );
        }
        else {
          categoriaUpdate.getProductos().add(productoOptional.get());
          categoriaUpdate.setFechaModificacion(utilService.obtenerFechaActual());

          categoriaRepository.save(categoriaUpdate);

          return new UtilService().armarRespuesta(
                  HttpStatus.OK.value(),
                  EXITO,
                  "Se agrega producto en categoria de forma correcta",
                  false,
                  null,
                  HttpStatus.OK
          );
        }
      }
      else {
        return new UtilService().armarRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                ISSUE,
                "La categoria no existe",
                true,
                null,
                HttpStatus.BAD_REQUEST
        );
      }
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ISSUE,
              "El producto no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  public boolean validarProducto (Set<Producto> productos, String idProducto) {
    boolean existe = false;

    for (Producto prod : productos) {
      if (prod.getId().equals(idProducto)) {
        existe = true;
        break;
      }
    }

    return existe;
  }
}
