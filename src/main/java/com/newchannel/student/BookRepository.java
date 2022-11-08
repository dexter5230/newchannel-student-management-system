package com.newchannel.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Book s WHERE s.student = :student")
    void deleteBookByStudentId(@Param("student") Student student);
}
