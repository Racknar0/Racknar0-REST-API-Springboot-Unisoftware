package com.storeunisoftware.storeunisoftware.model;

import java.time.LocalDate;
import java.util.Date;
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
@Table(name = "productos")
public class Producto {
    

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String sku;

    @Column
    private String descripcion;

    @Column
    private Double precio_compra;

    @Column
    private Double precio_venta;

    @Column
    private Integer stock;

    @Column
    private LocalDate ultima_compra;

    /* @OneToMany
    private List<DetalleVenta> detalleVentaList;
 */

    /* @OneToMany(mappedBy = "producto")
    @JsonManagedReference(value = "producto")
    private List<DetalleVenta> detalleVentaList; */

}
