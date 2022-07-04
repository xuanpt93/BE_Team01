package com.itsol.recruit.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "STATUS_JOB", schema = "TEAM01", catalog = "")
public class StatusJob {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private short id;
    @Basic
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "IS_DELETE")
    private BigInteger isDelete;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(BigInteger isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusJob statusJob = (StatusJob) o;
        return id == statusJob.id && Objects.equals(code, statusJob.code) && Objects.equals(description, statusJob.description) && Objects.equals(isDelete, statusJob.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, description, isDelete);
    }
}
