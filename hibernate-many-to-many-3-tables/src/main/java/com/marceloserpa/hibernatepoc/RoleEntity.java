package com.marceloserpa.hibernatepoc;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import java.util.List;

@Entity(name="RoleEntity")
@Table(name = "roles")
public class RoleEntity {

  @Id
  @GeneratedValue
  @Column(name = "role_id")
  private Long id;
  private String description;

  @OneToMany(mappedBy = "role"
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
}
