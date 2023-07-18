package com.spring.msvc.catalogo.services.producto;

import com.spring.common.tools.entity.RestResponse;
import com.spring.common.tools.service.UtilService;
import com.spring.msvc.catalogo.entity.Producto;
import com.spring.msvc.catalogo.repositories.producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

  private static final String EXITO = "Proceso ejecutado correctamente";
  private static final String ISSUE = "Ocurrio un problema en la ejecucion del proceso";

  @Autowired
  private ProductoRepository productoRepository;

  @Autowired
  private UtilService utilService;

  @Override
  public ResponseEntity<RestResponse<Object>> nuevoProducto (Producto producto) {

    Optional<Producto> productoOptional = productoRepository.findByNombre(producto.getNombre());

    if (productoOptional.isPresent()) {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ISSUE,
              "El producto que se requiere crear ya existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
    else {
      producto.setFechaAlta(utilService.obtenerFechaActual());
      producto.setFechaModificacion(utilService.obtenerFechaActual());

      productoRepository.save(producto);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Producto creado de forma correcta",
              false,
              null,
              HttpStatus.OK
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> actualizarProducto (String id, Producto producto) {

    Optional<Producto> productoOptional = productoRepository.findById(id);

    if (productoOptional.isPresent()) {
      Producto productoUpdate = productoOptional.get();

      productoUpdate.setNombre(producto.getNombre());
      productoUpdate.setDescripcion(producto.getDescripcion());
      productoUpdate.setTipo(producto.getTipo());
      productoUpdate.setImagen(producto.getImagen());
      productoUpdate.setSubcategoria(producto.getSubcategoria());
      productoUpdate.setFechaModificacion(utilService.obtenerFechaActual());

      productoRepository.save(productoUpdate);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Producto actualizado de forma correcta",
              false,
              null,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ISSUE,
              "El producto que se requiere actualizar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> eliminarProducto (String id) {
    if (productoRepository.existsById(id)) {
      productoRepository.deleteById(id);

      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Producto eliminado de forma correcta",
              false,
              null,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ISSUE,
              "El producto que se requiere eliminar no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarProductos () {
    List<Producto> productoList = productoRepository.findAll();

    return new UtilService().armarRespuesta(
            HttpStatus.OK.value(),
            EXITO,
            "Se consultan productos de forma correcta",
            false,
            productoList,
            HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarProductoById (String id) {

    Optional<Producto> productoOptional = productoRepository.findById(id);

    if (productoOptional.isPresent()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulta producto por ID de forma correcta",
              false,
              productoOptional.stream().collect(Collectors.toList()),
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ISSUE,
              "El producto consultado por ID no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarProductoByNombre (String nombre) {

    Optional<Producto> productoOptional = productoRepository.findByNombre(nombre);

    if (productoOptional.isPresent()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulta producto por nombre de forma correcta",
              false,
              productoOptional.stream().collect(Collectors.toList()),
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ISSUE,
              "El producto consultado por nombre no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }

  @Override
  public ResponseEntity<RestResponse<Object>> consultarProductoByTipo (String tipo) {

    List<Producto> productoList = productoRepository.findByTipo(tipo);

    if (!productoList.isEmpty()) {
      return new UtilService().armarRespuesta(
              HttpStatus.OK.value(),
              EXITO,
              "Se consulta producto por tipo de forma correcta",
              false,
              productoList,
              HttpStatus.OK
      );
    }
    else {
      return new UtilService().armarRespuesta(
              HttpStatus.BAD_REQUEST.value(),
              ISSUE,
              "El producto consultado por tipo no existe",
              true,
              null,
              HttpStatus.BAD_REQUEST
      );
    }
  }
}
