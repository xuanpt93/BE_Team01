package com.itsol.recruit.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "JOBS_REGISTER", schema = "TEAM01", catalog = "")
@IdClass(JobsRegisterPK.class)
public class JobsRegister {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "JOB_ID")
    private BigInteger jobId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USER_ID")
    private BigInteger userId;
    @Basic
    @Column(name = "DATE_REGISTER")
    private Date dateRegister;
    @Basic
    @Column(name = "DATE_INTERVIEW")
    private Date dateInterview;
    @Basic
    @Column(name = "METHOD_INTERVIEW")
    private String methodInterview;
    @Basic
    @Column(name = "ADDRESS_INTERVIEW")
    private String addressInterview;
    @Basic
    @Column(name = "STATUS_ID")
    private BigInteger statusId;
    @Basic
    @Column(name = "REASON")
    private String reason;
    @Basic
    @Column(name = "CV_FILE")
    private String cvFile;
    @Basic
    @Column(name = "MEDIA_TYPE")
    private String mediaType;
    @Basic
    @Column(name = "IS_DELETE")
    private BigInteger isDelete;

    public BigInteger getJobId() {
        return jobId;
    }

    public void setJobId(BigInteger jobId) {
        this.jobId = jobId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Date getDateInterview() {
        return dateInterview;
    }

    public void setDateInterview(Date dateInterview) {
        this.dateInterview = dateInterview;
    }

    public String getMethodInterview() {
        return methodInterview;
    }

    public void setMethodInterview(String methodInterview) {
        this.methodInterview = methodInterview;
    }

    public String getAddressInterview() {
        return addressInterview;
    }

    public void setAddressInterview(String addressInterview) {
        this.addressInterview = addressInterview;
    }

    public BigInteger getStatusId() {
        return statusId;
    }

    public void setStatusId(BigInteger statusId) {
        this.statusId = statusId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCvFile() {
        return cvFile;
    }

    public void setCvFile(String cvFile) {
        this.cvFile = cvFile;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
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
        JobsRegister that = (JobsRegister) o;
        return Objects.equals(jobId, that.jobId) && Objects.equals(userId, that.userId) && Objects.equals(dateRegister, that.dateRegister) && Objects.equals(dateInterview, that.dateInterview) && Objects.equals(methodInterview, that.methodInterview) && Objects.equals(addressInterview, that.addressInterview) && Objects.equals(statusId, that.statusId) && Objects.equals(reason, that.reason) && Objects.equals(cvFile, that.cvFile) && Objects.equals(mediaType, that.mediaType) && Objects.equals(isDelete, that.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, userId, dateRegister, dateInterview, methodInterview, addressInterview, statusId, reason, cvFile, mediaType, isDelete);
    }
}
