package com.marceloserpa.hibernatepoc;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity(name="DepartmentEntity")
@Table(name = "departments")
public class DepartmentEntity {

  @Id
  @GeneratedValue
  @Column(name = "department_id")
  private Long id;
  private String description;

  @OneToMany(mappedBy = "department"
  //    , cascade = {CascadeType.PERSIST, CascadeType.MERGE}
  )
  private List<UserDepartmentRoleLinkEntity> links;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<UserDepartmentRoleLinkEntity> getLinks() {
    return links;
  }

  public void setLinks(
      List<UserDepartmentRoleLinkEntity> links) {
    this.links = links;
  }
}
