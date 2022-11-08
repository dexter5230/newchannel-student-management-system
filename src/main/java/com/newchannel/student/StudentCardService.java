package com.newchannel.student;

import org.springframework.stereotype.Service;

@Service
public class StudentCardService {
    private final StudentCardRepository studentCardRepository;

    public StudentCardService(StudentCardRepository studentCardRepository) {
        this.studentCardRepository = studentCardRepository;
    }

   // public void createNewCard(Long )

    public boolean isCardNumberExist(Long cardNumber) {
        return studentCardRepository.isCardNumberExist(cardNumber).isPresent();
    }
}
