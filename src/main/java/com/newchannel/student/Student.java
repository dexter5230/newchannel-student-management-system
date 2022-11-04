package com.newchannel.student;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "Student")
@Table(name = "student", uniqueConstraints = @UniqueConstraint(name = "unique_email_constrains", columnNames = "email"))
public class Student {
    @Id
    @Column (name = "student_id",updatable = false)
    @Type(type = "uuid-char")
    private UUID uuid = UUID.randomUUID();
    @Column (name = "first_name",nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column (name = "last_name", columnDefinition = "TEXT")
    private String lastName;
    @Column (name = "date_of_birth", nullable = false)
    private Date date_of_birth;
    @Column (name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;
    @Column (name = "email", nullable = false, unique = true)
    @Email
    private String email;
    @OneToOne (mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private StudentCard studentCard;
    @OneToOne (mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private StudentAccount studentAccount;
    @OneToMany (mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    List<Book> books = new ArrayList<>();
    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    List<Enrolment> enrolments = new ArrayList<>();

    public Student() {
    }

    public Student(UUID uuid, String firstName, String lastName, Date date_of_birth, LocalDateTime createAt, String email) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date_of_birth = date_of_birth;
        this.createAt = createAt;
        this.email = email;
    }

    public Student(String firstName, String lastName, Date date_of_birth, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date_of_birth = date_of_birth;
        this.createAt = LocalDateTime.now();
        this.email = email;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addStudentCard(StudentCard studentCard) {
        this.studentCard = studentCard;
        studentCard.setStudent(this);
    }

    public void addStudentAccount(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
        studentAccount.setStudent(this);
    }

    public void addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
        }
    }

    public void addCourse(Enrolment enrolment) {
        if (!enrolments.contains(enrolment)) {
            enrolments.add(enrolment);
            enrolment.setStudent(this);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "uuid=" + uuid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", createAt=" + createAt +
                ", email='" + email + '\'' +
                '}';
    }
}
