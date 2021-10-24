package com.bairontapia.projects.cuidamed.localization;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "residence", name = "region")
@Immutable
@Getter
@Setter(AccessLevel.PRIVATE)
public class Region {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, updatable = false, nullable = false)
  private Short id;

  @Column(name = "region_name", unique = true, updatable = false, nullable = false)
  private String name;

  @Column(name = "abbreviation", unique = true, updatable = false, nullable = false)
  private String abbreviation;

  @Column(name = "capital", unique = true, updatable = false, nullable = false)
  private String capital;

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof Region region){
      return Objects.equals(id, region.id);
      /*
      return new EqualsBuilder()
              .append(id, region.id)
              .append(regionName, region.regionName)
              .append(abbreviation, region.abbreviation)
              .append(capital, region.capital)
              .isEquals();
              */
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
    /*
    return new HashCodeBuilder()
            .append(id)
            .append(regionName)
            .append(abbreviation)
            .append(capital)
            .hashCode();
            */
  }

  @Override
  public String toString() {
    return name;
  }
}
