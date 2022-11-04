package com.newchannel.student;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Course")
@Table(name = "course")
public class Course {
    @Id
    @Type(type = "uuid_char")
    @Column(name = "book_id", updatable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "book_name", nullable = false, columnDefinition = "TEXT")
    private String courseName;

    @Column(name = "department", nullable = false, columnDefinition = "TEXT")
    private String department;

    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    List<Enrolment> enrolments = new ArrayList<>();

    public Course(UUID uuid, String courseName, String department, List<Enrolment> enrolments) {
        this.uuid = uuid;
        this.courseName = courseName;
        this.department = department;
        this.enrolments = enrolments;
    }

    public Course() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
    public String toString() {
        return "Course{" +
                "uuid=" + uuid +
                ", courseName='" + courseName + '\'' +
                ", department='" + department + '\'' +
                ", enrolments=" + enrolments +
                '}';
    }
}
