package com.itsol.recruit.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Company {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private BigInteger id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "EMAIL")
    private String email;
    @Basic
    @Column(name = "HOTLINE")
    private String hotline;
    @Basic
    @Column(name = "DATE_INCOPORATION")
    private Date dateIncoporation;
    @Basic
    @Column(name = "TAX_CODE")
    private String taxCode;
    @Basic
    @Column(name = "TAX_DATE")
    private Date taxDate;
    @Basic
    @Column(name = "TAX_PLACE")
    private String taxPlace;
    @Basic
    @Column(name = "HEAD_OFFICE")
    private String headOffice;
    @Basic
    @Column(name = "NUMBER_STAFF")
    private BigInteger numberStaff;
    @Basic
    @Column(name = "LINK_WEB")
    private String linkWeb;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "AVATAR")
    private String avatar;
    @Basic
    @Column(name = "BACKDROP_IMG")
    private String backdropImg;
    @Basic
    @Column(name = "IS_DELETE")
    private BigInteger isDelete;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public Date getDateIncoporation() {
        return dateIncoporation;
    }

    public void setDateIncoporation(Date dateIncoporation) {
        this.dateIncoporation = dateIncoporation;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Date getTaxDate() {
        return taxDate;
    }

    public void setTaxDate(Date taxDate) {
        this.taxDate = taxDate;
    }

    public String getTaxPlace() {
        return taxPlace;
    }

    public void setTaxPlace(String taxPlace) {
        this.taxPlace = taxPlace;
    }

    public String getHeadOffice() {
        return headOffice;
    }

    public void setHeadOffice(String headOffice) {
        this.headOffice = headOffice;
    }

    public BigInteger getNumberStaff() {
        return numberStaff;
    }

    public void setNumberStaff(BigInteger numberStaff) {
        this.numberStaff = numberStaff;
    }

    public String getLinkWeb() {
        return linkWeb;
    }

    public void setLinkWeb(String linkWeb) {
        this.linkWeb = linkWeb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBackdropImg() {
        return backdropImg;
    }

    public void setBackdropImg(String backdropImg) {
        this.backdropImg = backdropImg;
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
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(name, company.name) && Objects.equals(email, company.email) && Objects.equals(hotline, company.hotline) && Objects.equals(dateIncoporation, company.dateIncoporation) && Objects.equals(taxCode, company.taxCode) && Objects.equals(taxDate, company.taxDate) && Objects.equals(taxPlace, company.taxPlace) && Objects.equals(headOffice, company.headOffice) && Objects.equals(numberStaff, company.numberStaff) && Objects.equals(linkWeb, company.linkWeb) && Objects.equals(description, company.description) && Objects.equals(avatar, company.avatar) && Objects.equals(backdropImg, company.backdropImg) && Objects.equals(isDelete, company.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, hotline, dateIncoporation, taxCode, taxDate, taxPlace, headOffice, numberStaff, linkWeb, description, avatar, backdropImg, isDelete);
    }
}
