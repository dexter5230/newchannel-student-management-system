package com.newchannel.student;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity(name = "StudentAccount")
@Table(name = "student_account", uniqueConstraints = {@UniqueConstraint(name = "unique_account_name", columnNames = "account_name")})
public class StudentAccount {
    @Id
    @Column(name = "student_account_id",updatable = false)
    @Type(type = "uuid-char")
    private Long id;

    @Column (name = "account_name", updatable = false, columnDefinition = "TEXT", nullable = false)
    @Email
    private String accountName;

    @Column (name = "account_password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "student_id", referencedColumnName = "student_id")
    private Student student;

    public StudentAccount(Long id, String accountName, String password) {
        this.id = id;
        this.accountName = accountName;
        this.password = password;
    }

    public StudentAccount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

