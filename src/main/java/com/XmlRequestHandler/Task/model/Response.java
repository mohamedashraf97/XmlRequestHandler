package com.XmlRequestHandler.Task.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "response")
public class Response {

  @Id
  @Column(name="id")
  private long id;

  @Column(name = "function")
  private String function;

  @Column(name = "status")
  private String status;

  @CreatedDate
  @Column(name = "created_at", updatable = false)
  @NotNull
  private Date createdAt;

  public Response(long id, String function, String status,Date createdAt) {
    this.id = id;
    this.function = function;
    this.status = status;
    this.createdAt = createdAt;
  }

  public Response() {

  }

  public long getId() {
    return id;
  }


  public String getFunction() {
    return function;
  }

  public void setFunction(String function) {
    this.function = function;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "Response [id=" + id + ", function=" + function + ", status=" + status  + "]";
  }
}