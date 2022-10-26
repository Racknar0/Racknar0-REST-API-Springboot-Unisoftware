package com.storeunisoftware.storeunisoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storeunisoftware.storeunisoftware.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}