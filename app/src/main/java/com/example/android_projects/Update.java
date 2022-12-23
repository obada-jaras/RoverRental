package com.example.android_projects;

public class Update {

    private String name ;
    private String email ;
    private String contact ;
    private String city ;



    public Update()
    {

    }
    public Update(String name, String email, String contact, String city) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "You make update in his/here Info {" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
