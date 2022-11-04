package com.newchannel.student;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Book")
@Table(name = "book")
public class Book {
    @Id
    @Type(type = "uuid_char")
    @Column(name = "book_id", updatable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "book_name", nullable = false, columnDefinition = "TEXT")
    private String bookName;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", foreignKey = @ForeignKey(name = "student_id_fk"))
    Student student;

    public Book(UUID uuid, String bookName, LocalDateTime createAt, Student student) {
        this.uuid = uuid;
        this.bookName = bookName;
        this.createAt = createAt;
        this.student = student;
    }

    public Book(String bookName) {
        this.bookName = bookName;
        this.createAt = LocalDateTime.now();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Book{" +
                "uuid=" + uuid +
                ", bookName='" + bookName + '\'' +
                ", createAt=" + createAt +
                ", student=" + student +
                '}';
    }
}
