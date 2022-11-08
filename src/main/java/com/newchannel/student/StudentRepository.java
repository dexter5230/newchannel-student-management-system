package com.newchannel.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID>{
    @Query("SELECT s FROM Student s WHERE s.studentId = :studentId")
    Optional<Student> findStudentByStudentId(@Param("studentId") UUID studentId);

    @Query("SELECT s FROM Student s WHERE s.email = :email")
    Optional<Student> findStudentByStudentEmail(@Param("email")String email);

    @Query("SELECT s FROM Student s")
    List<Student> getAllStudent();


    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.email = :email")
    void removeStudentByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.firstName = :first_name,s.lastName = :last_name, s.email = :email, s.date_of_birth = :date_of_birth WHERE s.studentId = :student_id")
    void updateStudent(@Param("student_id")UUID studentId, @Param("first_name") String firstName, @Param("last_name") String lastName, @Param("email") String email,@Param("date_of_birth") Date dateOfBirth);
}
