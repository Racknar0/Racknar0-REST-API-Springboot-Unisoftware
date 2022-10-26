package com.storeunisoftware.storeunisoftware.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data // Lombok annotation to auto generate getters and setters
@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "fecha_venta")
    private Date fechaVenta;

    @Column
    private Double total;

   /*  @ManyToOne //! Relacion muchos a uno
    @JoinColumn(referencedColumnName = "id", name = "id_cliente") //! se declara la columna que hace referencia a la tabla cliente
    private Cliente cliente;  //! se declara un objeto de tipo Cliente
 */


    @JsonDeserialize
    @ManyToOne(targetEntity = Cliente.class)
    @JoinColumn(name = "id_cliente")
    @JsonBackReference(value = "cliente")
    private Cliente cliente;



    /* @JsonProperty("cliente")
    private void unpackNested(Integer cliente) {
        Venta venta = new Venta();
        venta.setId(cliente);
    } */

}
