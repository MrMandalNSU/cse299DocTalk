package com.example.doctalk;

public class PatientHelperClass {


    String name, age, location, phone, addiSymptoms;

    public PatientHelperClass() {

    }

    public PatientHelperClass(String name, String age, String location, String phone, String addiSymptoms) {
        this.name = name;
        this.age = age;
        this.location = location;
        this.phone = phone;
        this.addiSymptoms = addiSymptoms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddiSymptoms() {
        return addiSymptoms;
    }

    public void setAddiSymptoms(String addiSymptoms) {
        this.addiSymptoms = addiSymptoms;
    }
}
