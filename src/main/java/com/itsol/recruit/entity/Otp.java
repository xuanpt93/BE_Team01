package com.itsol.recruit.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Otp {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private short id;
    @Basic
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "ISSUE_AT")
    private Date issueAt;
    @Basic
    @Column(name = "USER_ID")
    private short userId;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getIssueAt() {
        return issueAt;
    }

    public void setIssueAt(Date issueAt) {
        this.issueAt = issueAt;
    }

    public short getUserId() {
        return userId;
    }

    public void setUserId(short userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Otp otp = (Otp) o;
        return id == otp.id && userId == otp.userId && Objects.equals(code, otp.code) && Objects.equals(issueAt, otp.issueAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, issueAt, userId);
    }
}
