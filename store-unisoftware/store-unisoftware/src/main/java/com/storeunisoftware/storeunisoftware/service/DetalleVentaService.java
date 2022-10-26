package com.storeunisoftware.storeunisoftware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storeunisoftware.storeunisoftware.model.DetalleVenta;
import com.storeunisoftware.storeunisoftware.repository.DetalleVentaRepository;

@Service
public class DetalleVentaService {
    
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> findAll() {
        return this.detalleVentaRepository.findAll();
    }

    public DetalleVenta findById(Integer id) {
        return this.detalleVentaRepository.findById(id).orElse(null);
    }

    public DetalleVenta save(DetalleVenta detalleVenta) {
        return this.detalleVentaRepository.save(detalleVenta);
    }

    public void deleteById(Integer id) {
        this.detalleVentaRepository.deleteById(id);
    }

    public DetalleVenta update(DetalleVenta detalleVenta) {
        return this.detalleVentaRepository.save(detalleVenta);
    }

}
