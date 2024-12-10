package klu.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import klu.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query("select count(S) from Student S where S.username=:uname")
	public int validateUsername(@Param("uname") String uname);
	
	@Query("select count(S) from Student S where S.username=:uname and S.password=:pwd")
	public int validateCredentials(@Param("uname") String uname, @Param("pwd") String pwd);

	  Optional<Student> findById(Long id);

	Optional<Student> findByUsername(String username);

}
