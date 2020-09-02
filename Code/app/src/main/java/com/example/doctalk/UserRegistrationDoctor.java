package com.example.doctalk;

public class UserRegistrationDoctor {

    public String fullname, email, phone, userType,licenseNumber;


    public UserRegistrationDoctor() {

    }

    public UserRegistrationDoctor(String fullname,String email,String phone,String userType,String licenseNumber)
    {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
        this.licenseNumber = licenseNumber;
    }
}
