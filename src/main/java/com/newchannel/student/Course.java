package com.newchannel.student;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Course")
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "course_id", columnDefinition = "CHAR(36)")
    private UUID courseId;

    @Column(name = "course_name", nullable = false, columnDefinition = "TEXT")
    private String courseName;

    @Column(name = "department", nullable = false, columnDefinition = "TEXT")
    private String department;

    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    List<Enrolment> enrolments = new ArrayList<>();

    public Course(UUID uuid, String courseName, String department, List<Enrolment> enrolments) {
        this.courseId = uuid;
        this.courseName = courseName;
        this.department = department;
        this.enrolments = enrolments;
    }

    public Course(String courseName, String department) {
        this.courseName = courseName;
        this.department = department;
    }

    public Course() {
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID uuid) {
        this.courseId = uuid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void setEnrolments(List<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }

    public void removeEnrolment(Enrolment e) {
        if (enrolments.contains(e)) {
            enrolments.remove(e);
        }
    }

    public void addEnrolment(Enrolment e) {
        if (!enrolments.contains(e)) {
            enrolments.add(e);
            e.setCourse(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }

    @Override
    public String toString() {
        return "Course{" +
                "uuid=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", department='" + department + '\'' +
                ", enrolments=" + enrolments +
                '}';
    }
}
