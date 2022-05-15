package com.XmlRequestHandler.Task.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "request")
public class Request {

  public Request(long id ,String function, String param1, String param2,Date createdAt) {
    this.id = id;
    this.function = function;
    this.param1 = param1;
    this.param2 = param2;
    this.createdAt = createdAt;
  }

  @Id
  @Column(name="id")
  private long id;

  @Column(name = "function")
  private String function;

  @Column(name = "param1")
  @NotNull
  private String param1;

  @Column(name = "param2")
  @NotNull
  private String param2;

  @CreatedDate
  @Column(name = "created_at" ,updatable = false)
  @NotNull
  private Date createdAt;

  public long getId() {
    return id;
  }
  public String getFunction() {
    return function;
  }
  public String getParam1() {
    return param1;
  }
  public String getParam2() {
    return param2;
  }


  public Request() {
  }
  @Override
  public String toString() {
    return "Request [id=" + id + ", function=" + function + ", param1=" + param1 + ", param2=" + param2 + "]";
  }
}