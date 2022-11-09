package com.newchannel.student;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudent() {
        return studentRepository.getAllStudent();
    }

    public Optional<Student> findStudentByStudentEmail(String email) {
      return  studentRepository.findStudentByStudentEmail(email);
    }
    @Transactional
    @Modifying
    public void addNewStudent(Student student) {
        Random rnd = new Random();
        long cardNumber = (100000 + rnd.nextInt(900000));
//        while (studentCardRepository.isCardNumberExist(cardNumber).isPresent()) {
//            cardNumber = (100000 + rnd.nextInt(900000));
//        }

        student.setStudentCard(new StudentCard(cardNumber));
        student.setStudentAccount(new StudentAccount(student.getEmail(), gRandomAlphabeticString()));
        studentRepository.save(student);
    }


    public String gRandomAlphabeticString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
    @Transactional
    @Modifying
    public void removeStudent(String email) {
        Student s = studentRepository.findStudentByStudentEmail(email).orElseThrow(() -> new RuntimeException(
                        "student with email" + email + " does not exist"
                )
        );
//        s.setStudentCard(null);
//        s.setStudentAccount(null);
//          s.setBooks(null);
        //bookRepository.deleteBookByStudentId(s);
        studentRepository.removeStudentByEmail(email);
    }
    @Transactional
    @Modifying
    public void updateStudent(String email, String firstName, String lastName, String updatedEmail, Date date) {
        Student s = studentRepository.findStudentByStudentEmail(email).orElseThrow(() -> new RuntimeException(
                "student with email" + email + " does not exist"));
        if (firstName != null) {
            s.setFirstName(firstName);
        }
        if (lastName != null) {
            s.setLastName(lastName);
        }
        if (updatedEmail != null) {
            s.setEmail(updatedEmail);
        }
        if (date != null) {
            s.setDate_of_birth(date);
        }
       studentRepository.updateStudent(s.getStudentId(),s.getFirstName(), s.getLastName(), s.getEmail(), s.getDate_of_birth());
    }
}
