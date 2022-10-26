package com.storeunisoftware.storeunisoftware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storeunisoftware.storeunisoftware.model.DetalleVenta;
import com.storeunisoftware.storeunisoftware.service.DetalleVentaService;

@RequestMapping("/detalleventa")
@RestController
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    /* Get all */
    @GetMapping
    public List<DetalleVenta> findAll() {
        return this.detalleVentaService.findAll();
    }

    /* Get by Id */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        if (this.detalleVentaService.findById(id) == null) {
            return new ResponseEntity<>("El detalle-venta no existe", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.detalleVentaService.findById(id), HttpStatus.OK);
    }

    /* Delete by id */
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id) {
        try {
            this.detalleVentaService.deleteById(id);
        } catch (Exception e) {
            return "Error al eliminar el detalle-venta";
        }

        return "Detalle-venta eliminado";
    }
}
