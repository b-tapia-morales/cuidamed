package com.bairontapia.projects.cuidamed.localization;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "residence", name = "region")
@Immutable
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

  public Short getId() {
    return id;
  }

  public void setId(Short id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String regionName) {
    this.name = regionName;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(final String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public String getCapital() {
    return capital;
  }

  public void setCapital(final String capital) {
    this.capital = capital;
  }
}
