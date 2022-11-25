package com.marceloserpa.hibernatemap.bidirectional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity(name="BiPost")
@Table(name = "bipost")
public class BiPost {

  @Id
  @GeneratedValue
  private Long id;
  private String title;

 // @JoinColumn(name = "bipost_id")
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "bipost")
  private List<BiPostComment> comments = new ArrayList<>();

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

  public List<BiPostComment> getComments() {
    return comments;
  }

  public void addComment(BiPostComment comment) {
    comments.add(comment);
    comment.setBipost(this);
  }

  public void removeComment(BiPostComment comment){
    comments.remove(comment);
    comment.setBipost(null);
  }


  public void setComments(
      List<BiPostComment> comments) {
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
