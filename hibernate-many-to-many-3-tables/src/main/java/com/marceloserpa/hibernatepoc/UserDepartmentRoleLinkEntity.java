package com.marceloserpa.hibernatepoc;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name="UserDepartmentRoleLinkEntity")
@Table(name = "user_department_role_link")
public class UserDepartmentRoleLinkEntity {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(/*cascade = {CascadeType.PERSIST, CascadeType.MERGE}*/)
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @ManyToOne(/*cascade = {CascadeType.PERSIST, CascadeType.MERGE}*/)
  @JoinColumn(name = "department_id")
  private DepartmentEntity department;

  @ManyToOne(/*cascade = {CascadeType.PERSIST, CascadeType.MERGE}*/)
  @JoinColumn(name = "role_id")
  private RoleEntity role;

  public Long getId() {
    return id;

  }
  public void setId(Long id) {
    this.id = id;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public DepartmentEntity getDepartment() {
    return department;
  }

  public void setDepartment(DepartmentEntity department) {
    this.department = department;
  }

  public RoleEntity getRole() {
    return role;
  }

  public void setRole(RoleEntity role) {
    this.role = role;
  }
}
