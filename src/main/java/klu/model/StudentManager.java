package klu.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import klu.repository.EventRepository;
import klu.repository.RegistrationRepository;
import klu.repository.StudentRepository;

@Service
public class StudentManager {
    
    @Autowired
    StudentRepository SR;
    
    @Autowired
    RegistrationRepository registrationRepository;
    
    @Autowired
    EventRepository ER;
    
    public void saveStudent(Student S) throws Exception {
        try {
            if (SR.validateUsername(S.getUsername()) > 0) {
                throw new Exception("Username already exists!");
            }

            SR.save(S);
            // Throwing an exception for the success message
            throw new Exception("New Student has been added");
        } catch (Exception e) {
            throw e; // Rethrowing the caught exception
        }
    }

    public ResponseEntity<String> loginStudent(String uname, String pwd) {
        try {
            if (SR.validateCredentials(uname, pwd) == 0) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"status\":401,\"message\":\"Invalid username or password\"}");
            }
            return ResponseEntity.ok("{\"status\":200,\"message\":\"Login successful\",\"username\":\"" + uname + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"status\":500,\"message\":\"An error occurred\",\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    public Optional<Student> getStudentByUsername(String username) {
        return SR.findByUsername(username);
    }

    public Student updateStudentDetails(Student student) {
        return SR.save(student);
    }
    
    public Optional<Student> getStudents(String username) {
        // Assuming you have a method in the repository to find students by username
        return SR.findByUsername(username);
    }
    public List<Student> getAllStudents() {
        // Assuming you have a method in the repository to find students by username
        return SR.findAll();
    }
    public void deleteStudent(Long id) throws Exception {
        if (!SR.existsById(id)) {
            throw new Exception("Student with ID " + id + " does not exist");
        }
        SR.deleteById(id);
    }
}
