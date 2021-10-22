package com.bairontapia.projects.cuidamed.person;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;


@Entity
@Table(schema = "residence", name = "elder")
@PrimaryKeyJoinColumn(name = "rut")
public class Elder extends Person {
    @Column(name = "is_active", unique = false, updatable = true, nullable = false)
    private boolean isActive;

    @Column(name = "admission_date", unique = false, updatable = false, nullable = false)
    private Date admissionDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rut_responsible", referencedColumnName = "rut", unique = false, updatable = false, nullable = false)
    private Responsible responsible;

    @Override
    public String toString() {
        return "Elder{" +
                "isActive=" + isActive +
                ", admissionDate=" + admissionDate +
                ", responsible=" + responsible +
                '}';
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Elder elder) {
            return Objects.equals(getRut(), elder.getRut());
        }
        return false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Responsible getResponsible() {
        return responsible;
    }

    public void setResponsible(Responsible responsible) {
        this.responsible = responsible;
    }
}
