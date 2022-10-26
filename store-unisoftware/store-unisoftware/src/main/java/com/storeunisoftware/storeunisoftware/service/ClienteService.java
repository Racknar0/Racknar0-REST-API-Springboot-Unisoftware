package com.storeunisoftware.storeunisoftware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storeunisoftware.storeunisoftware.model.Cliente;
import com.storeunisoftware.storeunisoftware.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;


    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    public Cliente findById(Integer id) {
        return this.clienteRepository.findById(id).orElse(null);
    }

    public Cliente save(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public void deleteById(Integer id) {
        this.clienteRepository.deleteById(id);
    }

    public Cliente update(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    
}
