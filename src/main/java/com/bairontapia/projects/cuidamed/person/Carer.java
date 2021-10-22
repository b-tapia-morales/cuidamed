package com.bairontapia.projects.cuidamed.person;

import javax.persistence.*;


@Entity
@Table(schema = "residence", name = "carer")
@PrimaryKeyJoinColumn(name = "rut")
public class Carer extends Person {

}
