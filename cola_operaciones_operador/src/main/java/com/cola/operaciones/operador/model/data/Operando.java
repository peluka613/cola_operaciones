package com.cola.operaciones.operador.model.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "operandos")
public class Operando implements Serializable {

    private static final long serialVersionUID = -8201547993903070418L;

    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false)
    private String sesionId;

    @Column(nullable = false)
    private Integer valor;
}
