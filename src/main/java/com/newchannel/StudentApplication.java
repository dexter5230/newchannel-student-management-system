package com.newchannel;

import com.github.javafaker.Faker;
import com.newchannel.student.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, StudentController studentController) {
		return args -> {
//            generateRandomStudents(studentRepository);
//            //sorting(studentRepository);
//            PageRequest pageRequest1 = PageRequest.of(0, 10, Sort.by("lastName").descending());
//            Page<Student> page1 = studentRepository.findAll(pageRequest1);
//            System.out.println(page1);
//            Sort sort = Sort.by("dateOfBirth").ascending().and(Sort.by("lastName").descending());
//            studentRepository.findAll(sort).forEach(com.example.demo.student -> System.out.println(com.example.demo.student.getDateOfBirth()));
//            PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("firstName").ascending());
//            Page<Student> page = studentRepository.findAll(pageRequest);
//            System.out.println(page);
			Faker faker =  new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@newchannel.edu", firstName, lastName);
			Date date =  faker.date().birthday();
			Student student =  new Student (
					firstName,
					lastName,
					date,
					email
			);
			student.addBook(new Book("I want to be a better person"));
			student.addBook(new Book("I want to have a job"));
			//student.addStudentCard(new StudentCard(123456789L));
			//student.addStudentAccount(new StudentAccount(student.getEmail(),"qwer1234"));
			Course course1 =  new Course("Computer Science","IT");
			Course course2 = new Course("Spring Date JPA","IT");
			EnrolmentId en1 = new EnrolmentId(student.getStudentId(), course1.getCourseId());
			EnrolmentId en2 = new EnrolmentId(student.getStudentId(), course2.getCourseId());
			Enrolment e1 = new Enrolment(en1,course1, student);
			Enrolment w2 = new Enrolment(en2,course2, student);
			student.addCourse(e1);
			student.addCourse(w2);

//            com.example.demo.student.enrolToCourse(new Course("Computer Science", "IT"));
//            com.example.demo.student.enrolToCourse(new Course("Spring Date JPA", "IT"));
			//StudentIdCard studentIdCard =  new StudentIdCard("123456789",com.example.demo.student);
			// StudentAccount studentAccount = new StudentAccount("qwer1234",com.example.demo.student);
			studentController.addNewStudent(student);
			//studentController.getAllStudent();
//			studentRepository.findStudentByStudentId(student.getStudentId()).ifPresent(s-> {
//				System.out.println("FetchType is lazy...");
//				List<Book> books = student.getBooks();
//				books.forEach(book -> {
//					System.out.println(book.getBookName());
//				});
//			});
//            bookRepository.save(new Book("how to be a jerk", com.example.demo.student));

//            studentAccountRepository.save(studentAccount);
//            studentCardRepository.save(studentIdCard);
			//studentRepository.updateStudentById("wujiaxuanau@gmail.com", 1l);
			//studentAccountRepository.updateStudentAccountById(studentRepository.findById(1l).get().email, 1l);
		};
	}



	private void generateRandomStudents(StudentRepository studentRepository) {
		Faker faker = new Faker();
		for (int i = 0; i < 20; i++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@newchannel.edu", firstName, lastName);
			Date date = faker.date().birthday();
			Student student = new Student(
					firstName,
					lastName,
					date,
					email
			);
			studentRepository.save(student);

		}
	}}