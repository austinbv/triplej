package com.pivotal.triplej;

public class Account {
  private int id;
  private String name;

  public Account() {
  }

  public Account(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
