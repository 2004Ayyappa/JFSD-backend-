package klu.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import klu.model.Event;
import klu.model.Student;
import klu.model.StudentManager;
@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentManager SM;
	
	 
	 @PostMapping("/save")
	    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
	        try {
	            SM.saveStudent(student);
	            // This line is never reached since the service always throws an exception
	            return ResponseEntity.status(HttpStatus.CREATED).body("Student created successfully");
	        } catch (Exception e) {
	            // Return the exception message as a response with appropriate HTTP status
	            if ("Username already exists!".equals(e.getMessage())) {
	                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	            } else if ("New Student has been added".equals(e.getMessage())) {
	                return ResponseEntity.status(HttpStatus.CREATED).body(e.getMessage());
	            } else {
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
	            }
	        }
	    }
	@GetMapping("/login/{uname}/{pwd}")
	public ResponseEntity<?>  login(@PathVariable("uname") String un, @PathVariable("pwd") String pw)
	{
		return SM.loginStudent(un, pw);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Student S) {
	    return SM.loginStudent(S.getUsername(), S.getPassword());
	}
	
	  // Endpoint to update student details
    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody Student updatedStudent) {
        Optional<Student> existingStudent = SM.getStudentByUsername(updatedStudent.getUsername());

        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();

            // Update fields
            student.setEmail(updatedStudent.getEmail());
            if (updatedStudent.getPassword() != null && !updatedStudent.getPassword().isEmpty()) {
                student.setPassword(updatedStudent.getPassword());
            }

            // Save updated student
            Student savedStudent = SM.updateStudentDetails(student);
            savedStudent.setPassword(null); // Mask password
            return ResponseEntity.ok(savedStudent);
        } else {
            return ResponseEntity.badRequest().body("Student not found");
        }
    }
    
    @GetMapping("/get/{username}")
    public ResponseEntity<Optional<Student>> getStudents(@PathVariable String username) {
        Optional<Student> students = SM.getStudents(username);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/get")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = SM.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            SM.deleteStudent(id);
            return ResponseEntity.ok("Student deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting student: " + e.getMessage());
        }
    }
    

	
}

