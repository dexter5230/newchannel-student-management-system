package com.newchannel.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentServiceTest {
    @Autowired
    private StudentRepository studentRepository;

    private StudentService underTest;

    public StudentServiceTest() {
    }


    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);

    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    void getAllStudent() {
        //given
        Student jamila = new Student("Jamila", "Emilio", Date.valueOf("1990-02-10"),"Jamila.Emiloo@newChannel.edu.com");
        Student elly = new Student("Elly","Lumia", Date.valueOf("1991-03-11"),"Elly.Lumia@newChannel.edu.com");
        studentRepository.save(jamila);
        studentRepository.save(elly);
        //when
        List<Student> students = underTest.getAllStudent();

        //then
        assertEquals(2,students.size());

    }

    @Test
    void findStudentByStudentEmail() {
        //given
        Student jamila = new Student("Jamila", "Emilio", Date.valueOf("1990-02-10"),"Jamila.Emiloo@newChannel.edu.com");
        studentRepository.save(jamila);

        //when
        Student s = underTest.findStudentByStudentEmail("Jamila.Emiloo@newChannel.edu.com");

        //then
        assertEquals("Jamila", s.getFirstName());
        assertEquals("Emilio", s.getLastName());
        assertEquals(Date.valueOf("1990-02-10"),s.getDate_of_birth());
        assertEquals("Jamila.Emiloo@newChannel.edu.com", s.getEmail());

    }

    @Test
    void addNewStudent() {
        //given
        Student jamila = new Student("Jamila", "Emilio", Date.valueOf("1990-02-10"),"Jamila.Emiloo@newChannel.edu.com");
        underTest.addNewStudent(jamila);

        //when
        Student s = underTest.findStudentByStudentEmail("Jamila.Emiloo@newChannel.edu.com");

        //then
        assertEquals("Jamila", s.getFirstName());
        assertEquals("Emilio", s.getLastName());
        assertEquals(Date.valueOf("1990-02-10"),s.getDate_of_birth());
        assertEquals("Jamila.Emiloo@newChannel.edu.com", s.getEmail());

    }

    @Test
    void gRandomAlphabeticString() {
        //given
        String temp = underTest.gRandomAlphabeticString();

        //then
        assertEquals(temp.length(), underTest.gRandomAlphabeticString().length());
    }

    @Test
    void removeStudent() {
        //given
        Student jamila = new Student("Jamila", "Emilio", Date.valueOf("1990-02-10"),"Jamila.Emiloo@newChannel.edu.com");
        Student elly = new Student("Elly","Lumia", Date.valueOf("1991-03-11"),"Elly.Lumia@newChannel.edu.com");
        studentRepository.save(jamila);
        studentRepository.save(elly);
        underTest.removeStudent(jamila.getEmail());

        //when
        List<Student> students = underTest.getAllStudent();

        //then
        assertEquals(1, students.size());

    }

    @Test
    void updateStudent() {
        //given
        Student jamila = new Student("Jamila", "Emilio", Date.valueOf("1990-02-10"),"Jamila.Emiloo@newChannel.edu.com");
        studentRepository.save(jamila);
        underTest.updateStudent("Jamila.Emiloo@newChannel.edu.com", "Jiaxuan", "Wu", "wujiaxuan@newchannel.edu.com", Date.valueOf("1991-02-10"));

        //when
        Student s = underTest.findStudentByStudentEmail("wujiaxuan@newchannel.edu.com");

        //then
        assertEquals("Jiaxuan", s.getFirstName());
        assertEquals("Wu", s.getLastName());
        assertEquals(Date.valueOf("1991-02-10"),s.getDate_of_birth());
        assertEquals("wujiaxuan@newchannel.edu.com", s.getEmail());

    }
}