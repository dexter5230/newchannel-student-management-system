package com.newchannel.student;

import javax.persistence.*;

@Entity(name = "StudentCard")
@Table (name = "student_card")
public class StudentCard {
    @Id
    @SequenceGenerator (name = "student_card_generator", sequenceName = "student_card_generator", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.IDENTITY, generator = "student_card_generator")
    private Long studentCardId;

    @Column (name = "student_card_number", nullable = false, updatable = false)
    private Long studentCardNumber;

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
}
