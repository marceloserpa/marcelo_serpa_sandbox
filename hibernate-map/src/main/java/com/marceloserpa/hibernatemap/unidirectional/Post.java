package com.marceloserpa.hibernatemap.unidirectional;

import jakarta.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity(name="Post")
@Table(name = "post")
public class Post {

  @Id
  @GeneratedValue
  private Long id;
  private String title;

  @JoinColumn(name = "post_id")
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PostComment> comments = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<PostComment> getComments() {
    return comments;
  }

  public void setComments(
      List<PostComment> comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "Post{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", comments=" + comments +
        '}';
  }
}
