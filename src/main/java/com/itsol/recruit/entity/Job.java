package com.itsol.recruit.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Job {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private short id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "JOB_POSITION_ID")
    private short jobPositionId;
    @Basic
    @Column(name = "NUMBER_EXPERIENCE")
    private String numberExperience;
    @Basic
    @Column(name = "WORKING_FORM_ID")
    private short workingFormId;
    @Basic
    @Column(name = "ADDRESS_WORK")
    private String addressWork;
    @Basic
    @Column(name = "ACADEMIC_LEVEL_ID")
    private short academicLevelId;
    @Basic
    @Column(name = "RANK_ID")
    private short rankId;
    @Basic
    @Column(name = "QTY_PERSON")
    private BigInteger qtyPerson;
    @Basic
    @Column(name = "START_RECRUITMENT_DATE")
    private Date startRecruitmentDate;
    @Basic
    @Column(name = "DUE_DATE")
    private Date dueDate;
    @Basic
    @Column(name = "SKILLS")
    private String skills;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "INTEREST")
    private String interest;
    @Basic
    @Column(name = "JOB_REQUIREMENT")
    private String jobRequirement;
    @Basic
    @Column(name = "SALARY_MAX")
    private BigInteger salaryMax;
    @Basic
    @Column(name = "SALARY_MIN")
    private BigInteger salaryMin;
    @Basic
    @Column(name = "CONTACT_ID")
    private short contactId;
    @Basic
    @Column(name = "CREATE_ID")
    private short createId;
    @Basic
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Basic
    @Column(name = "UPDATE_ID")
    private short updateId;
    @Basic
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
    @Basic
    @Column(name = "STATUS_ID")
    private short statusId;
    @Basic
    @Column(name = "VIEWS")
    private BigInteger views;
    @Basic
    @Column(name = "IS_DELETE")
    private BigInteger isDelete;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(short jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    public String getNumberExperience() {
        return numberExperience;
    }

    public void setNumberExperience(String numberExperience) {
        this.numberExperience = numberExperience;
    }

    public short getWorkingFormId() {
        return workingFormId;
    }

    public void setWorkingFormId(short workingFormId) {
        this.workingFormId = workingFormId;
    }

    public String getAddressWork() {
        return addressWork;
    }

    public void setAddressWork(String addressWork) {
        this.addressWork = addressWork;
    }

    public short getAcademicLevelId() {
        return academicLevelId;
    }

    public void setAcademicLevelId(short academicLevelId) {
        this.academicLevelId = academicLevelId;
    }

    public short getRankId() {
        return rankId;
    }

    public void setRankId(short rankId) {
        this.rankId = rankId;
    }

    public BigInteger getQtyPerson() {
        return qtyPerson;
    }

    public void setQtyPerson(BigInteger qtyPerson) {
        this.qtyPerson = qtyPerson;
    }

    public Date getStartRecruitmentDate() {
        return startRecruitmentDate;
    }

    public void setStartRecruitmentDate(Date startRecruitmentDate) {
        this.startRecruitmentDate = startRecruitmentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getJobRequirement() {
        return jobRequirement;
    }

    public void setJobRequirement(String jobRequirement) {
        this.jobRequirement = jobRequirement;
    }

    public BigInteger getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(BigInteger salaryMax) {
        this.salaryMax = salaryMax;
    }

    public BigInteger getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(BigInteger salaryMin) {
        this.salaryMin = salaryMin;
    }

    public short getContactId() {
        return contactId;
    }

    public void setContactId(short contactId) {
        this.contactId = contactId;
    }

    public short getCreateId() {
        return createId;
    }

    public void setCreateId(short createId) {
        this.createId = createId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public short getUpdateId() {
        return updateId;
    }

    public void setUpdateId(short updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public short getStatusId() {
        return statusId;
    }

    public void setStatusId(short statusId) {
        this.statusId = statusId;
    }

    public BigInteger getViews() {
        return views;
    }

    public void setViews(BigInteger views) {
        this.views = views;
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
        Job job = (Job) o;
        return id == job.id && jobPositionId == job.jobPositionId && workingFormId == job.workingFormId && academicLevelId == job.academicLevelId && rankId == job.rankId && contactId == job.contactId && createId == job.createId && updateId == job.updateId && statusId == job.statusId && Objects.equals(name, job.name) && Objects.equals(numberExperience, job.numberExperience) && Objects.equals(addressWork, job.addressWork) && Objects.equals(qtyPerson, job.qtyPerson) && Objects.equals(startRecruitmentDate, job.startRecruitmentDate) && Objects.equals(dueDate, job.dueDate) && Objects.equals(skills, job.skills) && Objects.equals(description, job.description) && Objects.equals(interest, job.interest) && Objects.equals(jobRequirement, job.jobRequirement) && Objects.equals(salaryMax, job.salaryMax) && Objects.equals(salaryMin, job.salaryMin) && Objects.equals(createDate, job.createDate) && Objects.equals(updateDate, job.updateDate) && Objects.equals(views, job.views) && Objects.equals(isDelete, job.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, jobPositionId, numberExperience, workingFormId, addressWork, academicLevelId, rankId, qtyPerson, startRecruitmentDate, dueDate, skills, description, interest, jobRequirement, salaryMax, salaryMin, contactId, createId, createDate, updateId, updateDate, statusId, views, isDelete);
    }
}
