package klu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="admin")
@Entity
public class Admin {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") 
    Long id;
    

	@Column(name = "username")
    String username;
    
    @Column(name = "password")
    String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

    @Override
  	public String toString() {
  		return "Admin [id=" + id + ", username=" + username + ", password=" + password + "]";
  	}
}
