package com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention;

import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecord;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "residence", name = "surgical_intervention")
@Getter
@Setter
public class SurgicalIntervention {
    @EmbeddedId
    @Setter(AccessLevel.PRIVATE)
    private SurgicalInterventionID id;

    @Column(name = "hospital", nullable = false)
    private String hospital;

    @Column(name = "severity", nullable = false)
    private Integer severity;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "elder_rut", insertable = false,nullable = false, updatable = false)
    @Setter(AccessLevel.PROTECTED)
    private MedicalRecord medicalRecord;

    @Override
    public boolean equals(final Object object){
        if (this == object) return true;
        if(object instanceof final SurgicalIntervention surgicalIntervention){
            return Objects.equals(id,surgicalIntervention.id);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}
