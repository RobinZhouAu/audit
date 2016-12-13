package com.zhb.bean;

import com.zhb.core.ObjectBase;

/**
 * Created by zhouhaibin on 2016/9/19.
 */
public class Center extends ObjectBase {
    String firstChar;
    String type;
    String province = "";
    String city = "";
    String town = "";
    String website;
    String address;
    String contact;
    String department;
    String certificate;


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {
        Center center = (Center)object;
        this.name = center.getName();
        this.type = center.getType();
        this.province = center.getProvince();
        this.city = center.getCity();
        this.town = center.getTown();
        this.website = center.getWebsite();
        this.address = center.getAddress();
        this.contact = center.getContact();
        this.certificate = center.getCertificate();
        this.department = center.getDepartment();
    }
}
