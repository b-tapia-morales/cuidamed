package com.bairontapia.projects.cuidamed.localization;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "residence", name = "commune")
@Immutable
public class Commune {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, updatable = false, nullable = false)
  private Short id;

  @Column(name = "commune_name", unique = true, updatable = false, nullable = false)
  private String name;

  @JoinColumn(name = "province_id", unique = true, updatable = false, nullable = false)
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  private Province province;

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof Commune commune){
      return Objects.equals(id, commune.id);
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

  public void setName(final String communeName) {
    this.name = communeName;
  }

  public Province getProvince() {
    return province;
  }

  public void setProvince(final Province province) {
    this.province = province;
  }
}
