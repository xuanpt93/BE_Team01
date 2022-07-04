package com.itsol.recruit.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
public class Profiles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USER_ID")
    private short userId;
    @Basic
    @Column(name = "SKILL")
    private String skill;
    @Basic
    @Column(name = "NUMBER_YEARS_EXPERIENCE")
    private Short numberYearsExperience;
    @Basic
    @Column(name = "ACADEMIC_NAME_ID")
    private Short academicNameId;
    @Basic
    @Column(name = "DESIRED_SALARY")
    private String desiredSalary;
    @Basic
    @Column(name = "DESIRED_WORKING_ADDRESS")
    private String desiredWorkingAddress;
    @Basic
    @Column(name = "DESIRED_WORKING_FORM")
    private String desiredWorkingForm;
    @Basic
    @Column(name = "IS_DELETE")
    private BigInteger isDelete;

    public short getUserId() {
        return userId;
    }

    public void setUserId(short userId) {
        this.userId = userId;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Short getNumberYearsExperience() {
        return numberYearsExperience;
    }

    public void setNumberYearsExperience(Short numberYearsExperience) {
        this.numberYearsExperience = numberYearsExperience;
    }

    public Short getAcademicNameId() {
        return academicNameId;
    }

    public void setAcademicNameId(Short academicNameId) {
        this.academicNameId = academicNameId;
    }

    public String getDesiredSalary() {
        return desiredSalary;
    }

    public void setDesiredSalary(String desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    public String getDesiredWorkingAddress() {
        return desiredWorkingAddress;
    }

    public void setDesiredWorkingAddress(String desiredWorkingAddress) {
        this.desiredWorkingAddress = desiredWorkingAddress;
    }

    public String getDesiredWorkingForm() {
        return desiredWorkingForm;
    }

    public void setDesiredWorkingForm(String desiredWorkingForm) {
        this.desiredWorkingForm = desiredWorkingForm;
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
        Profiles profiles = (Profiles) o;
        return userId == profiles.userId && Objects.equals(skill, profiles.skill) && Objects.equals(numberYearsExperience, profiles.numberYearsExperience) && Objects.equals(academicNameId, profiles.academicNameId) && Objects.equals(desiredSalary, profiles.desiredSalary) && Objects.equals(desiredWorkingAddress, profiles.desiredWorkingAddress) && Objects.equals(desiredWorkingForm, profiles.desiredWorkingForm) && Objects.equals(isDelete, profiles.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, skill, numberYearsExperience, academicNameId, desiredSalary, desiredWorkingAddress, desiredWorkingForm, isDelete);
    }
}
