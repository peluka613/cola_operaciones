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
@Table(name = "operaciones")
public class Operacion implements Serializable {

    private static final long serialVersionUID = 5488218850501279591L;

    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false)
    private String sesionId;

    @Column(nullable = false)
    private String operacion;

    @Column(nullable = false)
    private Double primerOperando;

    @Column(nullable = false)
    private Double segundoOperando;

    @Column(nullable = false)
    private Double resultado;
}
