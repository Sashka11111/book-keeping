package com.lopit.bookkeeping.domain.model;

public class Book {

  private int id;
  private String title;
  private String author;
  private String category;
  private int yearPublished;

  public Book() {
    this.id = id;
    this.title = title;
    this.author = author;
    this.category = category;
    this.yearPublished = yearPublished;
  }
  public int getId(){
    return id;
  }
  public void setId(int id){
    this.id = id;
  }
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getYearPublished() {
    return yearPublished;
  }

  public void setYearPublished(int yearPublished) {
    this.yearPublished = yearPublished;
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", category=" + category +
        ", yearPublished=" + yearPublished +
        '}';
  }
}
