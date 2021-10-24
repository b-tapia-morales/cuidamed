package com.bairontapia.projects.cuidamed.localization;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "residence", name = "province")
@Immutable
@Getter
@Setter(AccessLevel.PRIVATE)
public class Province {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, updatable = false, nullable = false)
  private Short id;

  @Column(name = "province_name", unique = true, updatable = false, nullable = false)
  private String name;

  @JoinColumn(name = "region_id", updatable = false, nullable = false)
  @OneToOne(cascade = CascadeType.ALL)
  private Region region;

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof Province province){
      return Objects.equals(id, province.id);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return name;
  }
}
