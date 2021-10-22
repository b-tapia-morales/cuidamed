package com.bairontapia.projects.cuidamed.person;

import javax.persistence.*;
;

@Entity
@Table(schema = "residence", name = "responsible")
@PrimaryKeyJoinColumn(name = "rut")
public class Responsible extends Person {
    //atributos correspondientes


    //conexion con clase Elder
    @OneToOne(mappedBy = "responsible")
    private Elder elder;
}
