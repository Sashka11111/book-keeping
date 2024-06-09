package com.lopit.bookkeeping.domain;
public class User {
  private String username; // Ім'я користувача
  private String password; // Пароль користувача
  private String email; // Електронна пошта користувача
  private int phoneNumber; // Номер телефону користувача
  private String role; // Роль користувача (наприклад, "Бібліотекар" або "Читач")

  // Конструктор за замовчуванням
  public User() {
  }
  public User(String username, String password, String email, int phoneNumber, String role) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.role = role;
  }

  // Методи доступу до полів

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
  @Override
  public String toString() {
    return "User{" +
        "username='" + username + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", password='" + password + '\'' +
        ", role='" + role + '\'' +
        '}';
  }
}
