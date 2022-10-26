package com.storeunisoftware.storeunisoftware.controller;

import java.time.LocalDate;
import java.util.List;

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
import com.storeunisoftware.storeunisoftware.service.ClienteService;

@RequestMapping("/clientes")
@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /* GET ALL */
    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> clientes = this.clienteService.findAll();
        /* System.out.println(clientes); */
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    /* GET X ID */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        if (this.clienteService.findById(id) == null) {
            /* Enviar mensaje; */
            return new ResponseEntity<>("El producto no existe", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(this.clienteService.findById(id));
    }

    /* POST */
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Cliente cliente) {

        //! validaciones
        if (cliente.getNombre() == null || cliente.getApellido() == null || cliente.getDni() == null
                || cliente.getFechaNacimiento() == null) {
            return new ResponseEntity<>("Faltan datos", HttpStatus.BAD_REQUEST);
        }
        if (cliente.getNombre().length() < 3 || cliente.getApellido().length() < 3) {
            return ResponseEntity.badRequest().body("Los campos nombre y apellido deben tener al menos 3 caracteres");
        }
        if (cliente.getFechaNacimiento().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de nacimiento no puede ser mayor a la fecha actual");
        }
        

        try {
            String nombre = cliente.getNombre();
            String apellido = cliente.getApellido();
            Integer dni = cliente.getDni();
            LocalDate fechaNacimiento = cliente.getFechaNacimiento();

            Cliente clienteNuevo = new Cliente();
            clienteNuevo.setNombre(nombre);
            clienteNuevo.setApellido(apellido);
            clienteNuevo.setDni(dni);
            clienteNuevo.setFechaNacimiento(fechaNacimiento);

            this.clienteService.save(clienteNuevo);

            /* retornar una respuesta con el objeto en formato JSON */
            return ResponseEntity.ok().body(clienteNuevo);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    /* DELETE */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Integer id) {
        try {
            this.clienteService.deleteById(id);
            return ResponseEntity.ok().body("El cliente con id " + id + " fue eliminado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("El cliente con id " + id + " no existe");
        }
    }

    /* UPDATE */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Integer id, @RequestBody Cliente cliente) {

        //! validaciones
        if (cliente.getNombre() == null || cliente.getApellido() == null || cliente.getDni() == null
                || cliente.getFechaNacimiento() == null) {
            return new ResponseEntity<>("Faltan datos", HttpStatus.BAD_REQUEST);
        }
        if (cliente.getNombre().length() < 3 || cliente.getApellido().length() < 3) {
            return ResponseEntity.badRequest().body("Los campos nombre y apellido deben tener al menos 3 caracteres");
        }
        if (cliente.getFechaNacimiento().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de nacimiento no puede ser mayor a la fecha actual");
        }

        try {
            Cliente clienteActual = this.clienteService.findById(id);
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setDni(cliente.getDni());
            clienteActual.setFechaNacimiento(cliente.getFechaNacimiento());
            this.clienteService.update(clienteActual);
            return ResponseEntity.ok().body("El cliente con id " + id + " fue actualizado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("El cliente con id " + id + " no existe");
        }
    }

    
}
