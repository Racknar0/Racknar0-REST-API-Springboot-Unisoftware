package com.storeunisoftware.storeunisoftware.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.storeunisoftware.storeunisoftware.model.Cliente;
import com.storeunisoftware.storeunisoftware.model.Venta;
import com.storeunisoftware.storeunisoftware.service.VentaService;

@RequestMapping("/ventas")
@RestController
public class VentaController {

    @Autowired
    private VentaService ventaService;

    /* GET ALL */
    @GetMapping
    public Object getAll() {
        return this.ventaService.findAll();
    }

    /* GET X ID */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {

        if (this.ventaService.findById(id) == null) {
            return new ResponseEntity<>("El la venta no existe", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.ventaService.findById(id), HttpStatus.OK);
    }

    /* POST */
    @PostMapping
    public ResponseEntity<?> createVenta(@RequestBody Venta venta) {
        if (venta.getFechaVenta() == null || venta.getTotal() == null || venta.getCliente() == null) {
            return new ResponseEntity<>("Faltan datos", HttpStatus.BAD_REQUEST);
        }
        if (venta.getTotal() < 0) {
            return new ResponseEntity<>("El total no puede ser negativo", HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(this.ventaService.save(venta), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } 
    }

    /* DELETE */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id) {
        try {
            this.ventaService.deleteById(id);
            return ResponseEntity.ok().body("Venta eliminada");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /* UPDATE */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVenta(@PathVariable(name = "id") Integer id, @RequestBody Venta venta) {
        if (venta.getFechaVenta() == null || venta.getTotal() == null) {
            return new ResponseEntity<>("Faltan datos", HttpStatus.BAD_REQUEST);
        }
        if (venta.getTotal() < 0) {
            return new ResponseEntity<>("El total no puede ser negativo", HttpStatus.BAD_REQUEST);
        }

        try {
            Venta ventaActual = this.ventaService.findById(id);

            if (ventaActual == null) {
                return new ResponseEntity<>("La venta no existe", HttpStatus.NOT_FOUND);
            }

            ventaActual.setFechaVenta(venta.getFechaVenta());
            ventaActual.setTotal(venta.getTotal());

            this.ventaService.save(ventaActual);
            return ResponseEntity.ok().body("La venta se actualizó correctamente");
            
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    
}
