package com.newchannel.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "StudentCard")
@Table (name = "student_card")
public class StudentCard {
    @Id
    @SequenceGenerator (name = "student_card_generator", sequenceName = "student_card_generator", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.IDENTITY, generator = "student_card_generator")
    @Column (name = "student_card_id")
    private Long studentCardId;

    @Column (name = "student_card_number", nullable = false, updatable = false)
    private Long studentCardNumber;

    @JsonIgnore
    @OneToOne (cascade = {}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn (name = "student_id", referencedColumnName = "student_id", foreignKey = @ForeignKey(name = "student_id_fk"))
    private Student student;

    public StudentCard(Long studentCardId, Long studentCardNumber) {
        this.studentCardId = studentCardId;
        this.studentCardNumber = studentCardNumber;
    }

    public StudentCard() {
    }

    public StudentCard(Long studentCardNumber) {
        this.studentCardNumber = studentCardNumber;
    }

    public Long getStudentCardId() {
        return studentCardId;
    }

    public void setStudentCardId(Long studentCardId) {
        this.studentCardId = studentCardId;
    }

    public Long getStudentCardNumber() {
        return studentCardNumber;
    }

    public void setStudentCardNumber(Long studentCardNumber) {
        this.studentCardNumber = studentCardNumber;
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
        StudentCard that = (StudentCard) o;
        return Objects.equals(studentCardId, that.studentCardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentCardId);
    }
}
