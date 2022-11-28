package com.marceloserpa.hibernatepoc;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import java.util.List;

@Entity(name="UserEntity")
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue
  @Column(name = "user_id")
  private Long id;
  private String name;

  @OneToMany(mappedBy = "user"
  //    , cascade = {CascadeType.PERSIST, CascadeType.MERGE}
  )
  private List<UserDepartmentRoleLinkEntity> links;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
