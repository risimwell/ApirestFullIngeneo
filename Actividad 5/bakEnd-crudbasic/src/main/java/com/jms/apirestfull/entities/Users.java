package com.jms.apirestfull.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Setter;


@Entity
@Table(name="users")
@Data
@Setter
public class Users implements Serializable {

@Id
@Column(name="user_id")
private int id;
@Column(name="firstname")
private String firstname;
@Column(name="lastname")
private String lastname;
@Column(name="username")
private String username;
@Column(name="password")
private String password;
@Column(name="created_at")
@Temporal(TemporalType.TIMESTAMP)
private Date createdAT;
@Column(name="updated_at")
@Temporal(TemporalType.TIMESTAMP)
private Date updatedAt;

// Constructor sin argumentos
public Users() {
}

// Constructor con argumentos de tipo int y String
public Users(int id, String firstname, String lastname, String username, String password) {
this.id = id;
this.firstname = firstname;
this.lastname = lastname;
this.username = username;
this.password = password;
}

@PrePersist
public void prePersit() {
this.createdAT = new Date();


}

@PreUpdate
public void preUpdate() {
this.updatedAt = new Date();
}

}