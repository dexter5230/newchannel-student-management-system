package com.newchannel.student;

import javax.persistence.*;

@Entity(name = "Enrolment")
@Table(name = "enrolment")
public class Enrolment {
    @EmbeddedId
    private EnrolmentId enrolmentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId ("courseId")
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", foreignKey = @ForeignKey(name = "course_id_fk"))
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("studentId")
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", foreignKey = @ForeignKey(name = "student_id_fk"))
    private Student student;

    public Enrolment(EnrolmentId enrolmentId, Course course, Student student) {
        this.enrolmentId = enrolmentId;
        this.course = course;
        this.student = student;
    }

    public Enrolment() {
    }

    public EnrolmentId getEnrolmentId() {
        return enrolmentId;
    }

    public void setEnrolmentId(EnrolmentId enrolmentId) {
        this.enrolmentId = enrolmentId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "enrolmentId=" + enrolmentId +
                ", course=" + course +
                ", student=" + student +
                '}';
    }
}
