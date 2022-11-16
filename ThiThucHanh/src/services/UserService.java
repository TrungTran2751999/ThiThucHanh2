package services;

import models.User;

import java.util.ArrayList;
import java.util.Date;

public class UserService {
    private User user;
    public UserService(){
        user = new User();
    }
    public void addUser(User user){
        User.getListUser().add(user);
    }
    public void removeUser(int id){
        ArrayList<User> listUser = User.getListUser();
        for(int i=0; i<listUser.size(); i++){
            if(id == User.getListUser().get(i).getId()){
                User.getListUser().remove(User.getListUser().get(i));
            }
        }
    }
//    public void editeUser(int id, String name, String phone, String email, String date, String createDate){
//        ArrayList<User> listUser = User.getListUser();
//        for(int i=0; i<listUser.size(); i++){
//            if(id == User.getListUser().get(i).getId()){
//                User.getListUser().get(i).setName(name);
//                User.getListUser().get(i).setEmail(email);
//                User.getListUser().get(i).setPhone(phone);
//                User.getListUser().get(i).setDate(date);
//                User.getListUser().get(i).setCreateDate(createDate);
//            }
//        }
//    }
}
