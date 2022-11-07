package com.newchannel.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID>{
    @Query("SELECT s FROM Student s WHERE s.studentId = :studentId")
    Optional<Student> findStudentByStudentId(@Param("studentId") UUID studentId);


}
