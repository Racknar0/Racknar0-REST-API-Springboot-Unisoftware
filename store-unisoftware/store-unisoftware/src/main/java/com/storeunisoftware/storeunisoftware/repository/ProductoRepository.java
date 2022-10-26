package com.storeunisoftware.storeunisoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storeunisoftware.storeunisoftware.model.Producto;

public interface ProductoRepository  extends JpaRepository<Producto, Integer> {
 
}
