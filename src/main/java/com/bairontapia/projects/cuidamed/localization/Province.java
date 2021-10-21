package com.bairontapia.projects.cuidamed.localization;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "residence", name = "province")
@Immutable
public class Province {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, updatable = false, nullable = false)
  private Short id;

  @Column(name = "province_name", unique = true, updatable = false, nullable = false)
  private String name;

  @JoinColumn(name = "region_id", unique = true, updatable = false, nullable = false)
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

  public Short getId() {
    return id;
  }

  public void setId(final Short id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String provinceName) {
    this.name = provinceName;
  }

  public Region getRegion() {
    return region;
  }

  public void setRegion(final Region region) {
    this.region = region;
  }
}
