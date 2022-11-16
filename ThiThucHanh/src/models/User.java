package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String date;
    private String createDate;
    private static ArrayList<User> listUser;

    public User(int id, String name, String phone, String email, String date, String createDate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.date = date;
        this.createDate = createDate;
    }
    public User(){
        listUser = new ArrayList<>();
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static ArrayList<User> getListUser() {
        return listUser;
    }

    public static void setListUser(ArrayList<User> listUser) {
        User.listUser = listUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
