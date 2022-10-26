package com.storeunisoftware.storeunisoftware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storeunisoftware.storeunisoftware.model.Venta;
import com.storeunisoftware.storeunisoftware.repository.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> findAll() {
        return this.ventaRepository.findAll();
    }

    public Venta findById(Integer id) {
        return this.ventaRepository.findById(id).orElse(null);
    }

    public Venta save(Venta venta) {
        return this.ventaRepository.save(venta);
    }

    public void deleteById(Integer id) {
        this.ventaRepository.deleteById(id);
    }

    public Venta update(Venta venta) {
        return this.ventaRepository.save(venta);
    }
    
}
