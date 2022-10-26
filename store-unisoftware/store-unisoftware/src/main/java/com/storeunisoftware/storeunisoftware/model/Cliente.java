package com.storeunisoftware.storeunisoftware.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {
    

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private Integer dni;

    @Column
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference(value = "cliente")
    private List<Venta> ventas;
}
