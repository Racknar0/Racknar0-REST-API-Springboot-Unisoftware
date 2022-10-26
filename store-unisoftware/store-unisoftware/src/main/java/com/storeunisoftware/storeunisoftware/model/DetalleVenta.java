package com.storeunisoftware.storeunisoftware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer cantidad;

    @Column
    private Double subtotal;
    

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    /* @JsonDeserialize
    @ManyToOne(targetEntity = Producto.class)
    @JoinColumn(name = "id_producto")
    @JsonBackReference(value = "producto")
    private Producto producto; */


}
