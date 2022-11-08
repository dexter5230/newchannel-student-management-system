package com.newchannel.student;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Book")
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "book_id", columnDefinition = "CHAR(36)")
    private UUID bookId;

    @Column(name = "book_name", nullable = false, columnDefinition = "TEXT")
    private String bookName;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", foreignKey = @ForeignKey(name = "student_id_fk"))
    private Student student;

    public Book(UUID uuid, String bookName, LocalDateTime createAt, Student student) {
        this.bookId = uuid;
        this.bookName = bookName;
        this.createAt = createAt;
        this.student = student;
    }

    public Book(String bookName) {
        this.bookName = bookName;
        this.createAt = LocalDateTime.now();
    }

    public Book(String bookName, LocalDateTime createAt, Student student) {
        this.bookName = bookName;
        this.createAt = createAt;
        this.student = student;
    }

    public Book() {

    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID uuid) {
        this.bookId = uuid;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }

    @Override
    public String toString() {
        return "Book{" +
                "uuid=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", createAt=" + createAt +
                ", student=" + student +
                '}';
    }
}
