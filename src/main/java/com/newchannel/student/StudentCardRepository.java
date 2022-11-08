package com.newchannel.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentCardRepository extends JpaRepository <StudentCard, UUID>{
    @Query("SELECT s FROM StudentCard s WHERE s.studentCardNumber = :CardNumber ")
    Optional<StudentCard> isCardNumberExist(@Param("CardNumber")Long cardNumber);
}
