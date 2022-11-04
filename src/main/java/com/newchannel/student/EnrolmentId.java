package com.newchannel.student;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrolmentId implements Serializable {

    private Long StudentId;
    private Long CourseId;

    public EnrolmentId(Long studentId, Long courseId) {
        StudentId = studentId;
        CourseId = courseId;
    }

    public EnrolmentId() {
    }

    public Long getStudentId() {
        return StudentId;
    }

    public void setStudentId(Long studentId) {
        StudentId = studentId;
    }

    public Long getCourseId() {
        return CourseId;
    }

    public void setCourseId(Long courseId) {
        CourseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrolmentId that = (EnrolmentId) o;
        return Objects.equals(StudentId, that.StudentId) && Objects.equals(CourseId, that.CourseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StudentId, CourseId);
    }
}
