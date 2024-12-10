package klu.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="student")
@Entity
public class Student {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "id") 
	    Long id;
	    
	   
	    @Column(name = "email")
	    String email;
	    
	    @Column(name = "username")
	    String username;
	    
	    @Column(name = "password")
	    String password;

	    // Add a constructor accepting the ID
	    public Student(Long id) {
	        this.id = id;
	    }

	    // Default constructor
	    public Student() {
	    }

	public Long getId() {
	    return id;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	 

	 
	  

	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
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
	    return "User [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password + "]";
	  }
}