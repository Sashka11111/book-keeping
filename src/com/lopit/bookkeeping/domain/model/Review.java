package com.lopit.bookkeeping.domain;

public class Review {

  private String comment;
  private int mark;
  private User author;
  private int book;

  public Review(String comment, int mark, User author) {
    this.comment = comment;
    this.mark = mark;
    this.author = author;
    this.book = book;
  }

  public Review() {
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public int getMark() {
    return mark;
  }

  public void setMark(int mark) {
    this.mark = mark;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public int getbook() {
    return book;
  }

  public void setbook(int book) {
    this.book = book;
  }
  @Override
  public String toString() {
    return "Review{" +
        "comment='" + comment + '\'' +
        ", mark=" + mark +
        ", author=" + author +
        ", book=" + book +
        '}';
  }
}
