package com.marceloserpa.hibernatemap.bidirectional;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "BiPostComment")
@Table(name = "bipost_comment")
public class BiPostComment {

  @Id
  @GeneratedValue
  private Long id;
  private String review;

  @ManyToOne(fetch = FetchType.LAZY)
  private BiPost bipost;

  public BiPostComment(String review) {
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

  public BiPost getBipost() {
    return bipost;
  }

  public void setBipost(BiPost bipost) {
    this.bipost = bipost;
  }

  @Override
  public String toString() {
    return "PostComment{" +
        "id=" + id +
        ", review='" + review + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BiPostComment )) return false;
    return id != null && id.equals(((BiPostComment) o).getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

}
