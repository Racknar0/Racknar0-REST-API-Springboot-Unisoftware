package com.storeunisoftware.storeunisoftware.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.storeunisoftware.storeunisoftware.model.Producto;
import com.storeunisoftware.storeunisoftware.service.ProductoService;

@RequestMapping("/productos")
@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /* GET ALL */
    @GetMapping
    public Object getAll() {
        return this.productoService.findAll();
    }

    /* GET BY ID */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {

        if (this.productoService.findById(id) == null) {
            /* Enviar mensaje; */
            return new ResponseEntity<>("El producto no existe", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(this.productoService.findById(id));
    }

    /* SAVE */
    @PostMapping
    public Object createClient(@RequestBody Producto producto) {

        // ! validaciones
        if (producto.getDescripcion() == null || producto.getPrecio_compra() == null || producto.getStock() == null
                || producto.getStock() == null || producto.getUltima_compra() == null
                || producto.getPrecio_venta() == null) {
            return ResponseEntity.badRequest().body("Faltan datos");
        }

        String sku = producto.getSku();
        String descripcion = producto.getDescripcion();
        Double precio_compra = producto.getPrecio_compra();
        Double precio_venta = producto.getPrecio_venta();
        Integer stock = producto.getStock();
        LocalDate ultima_compra = producto.getUltima_compra();

        Producto productoNuevo = new Producto();
        productoNuevo.setSku(sku);
        productoNuevo.setDescripcion(descripcion);
        productoNuevo.setPrecio_compra(precio_compra);
        productoNuevo.setPrecio_venta(precio_venta);
        productoNuevo.setStock(stock);
        productoNuevo.setUltima_compra(ultima_compra);

        this.productoService.save(productoNuevo);
        /* retornar una respuesta con el objeto en formato JSON */
        return ResponseEntity.ok()
                .body("{ \"sku\": \"" + sku + "\", \"descripcion\": \"" + descripcion + "\", \"precio_compra\": \""
                        + precio_compra + "\", \"precio_venta\": \"" + precio_venta + "\", \"stock\": \"" + stock
                        + "\", \"ultima_compra\": \"" + ultima_compra + "\" }");
    }

    /* UPDATE */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Integer id, @RequestBody Producto producto) {

        // ! validaciones
        if (producto.getDescripcion() == null || producto.getPrecio_compra() == null || producto.getStock() == null
                || producto.getStock() == null || producto.getUltima_compra() == null
                || producto.getPrecio_venta() == null) {
            return ResponseEntity.badRequest().body("Faltan datos");
        }

        try {
            Producto productoActual = this.productoService.findById(id);
            productoActual.setSku(producto.getSku());
            productoActual.setDescripcion(producto.getDescripcion());
            productoActual.setPrecio_compra(producto.getPrecio_compra());
            productoActual.setPrecio_venta(producto.getPrecio_venta());
            productoActual.setStock(producto.getStock());
            productoActual.setUltima_compra(producto.getUltima_compra());
            this.productoService.update(productoActual);
            return ResponseEntity.ok().body("El cliente con id " + id + " fue actualizado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("El cliente con id " + id + " no existe");
        }
    }

    /* DELETE BY ID */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id) {
        try {
            this.productoService.deleteById(id);
            return ResponseEntity.ok().body("El cliente con id " + id + " fue eliminado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("El cliente con id " + id + " no existe");
        }
    }

}
