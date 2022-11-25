package com.marceloserpa.hibernatemap.unidirectional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "PostComment")
@Table(name = "post_comment")
public class PostComment {

  @Id
  @GeneratedValue
  private Long id;
  private String review;

  public PostComment(String review) {
    this.review = review;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  @Override
  public String toString() {
    return "PostComment{" +
        "id=" + id +
        ", review='" + review + '\'' +
        '}';
  }
}
