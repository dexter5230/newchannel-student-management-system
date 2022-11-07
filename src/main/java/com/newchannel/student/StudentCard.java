package com.newchannel.student;

import javax.persistence.*;

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

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "student_id", referencedColumnName = "student_id")
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
}
