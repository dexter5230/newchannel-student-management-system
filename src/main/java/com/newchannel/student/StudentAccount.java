package com.newchannel.student;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;

@Entity(name = "StudentAccount")
@Table(name = "student_account", uniqueConstraints = {@UniqueConstraint(name = "unique_account_name", columnNames = "account_name")})
public class StudentAccount {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    @Column (name = "account_name", updatable = false, columnDefinition = "TEXT", nullable = false)
    @Email
    private String accountName;

    @Column (name = "account_password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "student_id", referencedColumnName = "student_id")
    private Student student;

    public StudentAccount(UUID id, String accountName, String password) {
        this.id = id;
        this.accountName = accountName;
        this.password = password;
    }

    public StudentAccount(String accountName, String password) {
        this.accountName = accountName;
        this.password = password;
    }

    public StudentAccount() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

