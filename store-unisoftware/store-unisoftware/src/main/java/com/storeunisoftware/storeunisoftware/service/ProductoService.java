package com.storeunisoftware.storeunisoftware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storeunisoftware.storeunisoftware.model.Producto;
import com.storeunisoftware.storeunisoftware.repository.ProductoRepository;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return this.productoRepository.findAll();
    }

    public Producto findById(Integer id) {
        return this.productoRepository.findById(id).orElse(null);
    }

    public Producto save(Producto producto) {
        return this.productoRepository.save(producto);
    }

    public void deleteById(Integer id) {
        this.productoRepository.deleteById(id);
    }

    public Producto update(Producto producto) {
        return this.productoRepository.save(producto);
    }

}
