package com.cola.operaciones.sesion.model.data;

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
@Table(name = "sesiones")
public class Sesion implements Serializable {

    private static final long serialVersionUID = -6910634427941415966L;

    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false)
    private String sesionId;
}
