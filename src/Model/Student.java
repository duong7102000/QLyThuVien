package Model;

import java.sql.Date;

public class Student {
    private String username;
    private String fullName;
    private Date birth;
    private String address;
    private String major;
    private String class_;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public Student(String username, String fullName, Date birth, String address, String major, String class_) {
        this.username = username;
        this.fullName = fullName;
        this.birth = birth;
        this.address = address;
        this.major = major;
        this.class_ = class_;
    }
}
