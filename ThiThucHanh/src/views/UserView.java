package views;

import models.User;
import pattern.PatternString;
import services.UserService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserView {
    private UserService userService;
    private Scanner scanner;
    private boolean isContinue = true;
    private String name;
    private String email;
    private String phone;
    private String dateString;
    private String createDate;
    private PatternString patternString;
    private static int hhh;

    public UserView(){
        userService = new UserService();
        scanner = new Scanner(System.in);
        patternString = new PatternString();
    }

    public void showUser(){
        System.out.printf("%-15s %-15s %-30s %-30s %-30s %-30s", "id", "name", "phone", "email", "date", "createDate");
        System.out.println("");
        ArrayList<User> listUser = User.getListUser();
        for(int i=0; i<listUser.size(); i++){
            int id = listUser.get(i).getId();
            String name = listUser.get(i).getName();
            String phone = listUser.get(i).getPhone();
            String email = listUser.get(i).getEmail();
            String date = listUser.get(i).getDate();
            String createDate = listUser.get(i).getCreateDate();
            System.out.printf("%-15s %-15s %-30s %-30s %-30s %-30s", id, name, phone, email, date, createDate);
            System.out.println("");
        }
        System.out.println("");
    }
    public void showHead(){
       System.out.println("[1]: Show Users");
       System.out.println("[2]: Add Users");
       System.out.println("[3]: Remove User");
       System.out.println("[4]: Edit User");
       System.out.println("[5]: Exit");
    }
    public void showBody(){
        boolean flag = true;
        int number = 0;
        do{
            try{
                System.out.print("Nhap number: ");
                number = Integer.parseInt(scanner.nextLine());
                flag = false;
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
        }while (flag);
        switch (number){
            case 1:{
                showUser();
                break;
            }
            case 2:{
                addUser();
                break;
            }
            case 3:{
                removeUser();
                break;
            }
            case 4:{
                editUser();
                break;
            }
            case 5:
                isContinue = false;
        }
    }
    public void show(){
        do{
            showHead();
            showBody();
        }while (isContinue);
    }
    public void inputUser(){
        boolean flag = true;
        do{
            try{
                System.out.print("Nhap name: ");
                name = scanner.nextLine();
                flag = false;
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
            System.out.println("");
        }while (flag);
        do{
            try{
                System.out.println("Email phai bao gom 8-12 ki tu");
                System.out.print("Nhap email: ");
                email = scanner.nextLine();
                if(patternString.valindateEmail(email)){
                    flag = false;
                }else{
                    System.out.println("Nhap sai dinh dang. Vui long nhap lai");
                    flag=true;
                }
                System.out.println("");
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
        }while (flag);
        do{
            try{
                System.out.println("Phone format: 08.9845.3345");
                System.out.print("Nhap phone: ");
                phone = scanner.nextLine();
                flag = false;
                if(patternString.validatePhone(phone)){
                    flag = false;
                }else{
                    System.out.println("Nhap sai dinh dang. Vui long nhap lai");
                    flag=true;
                }
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
        }while (flag);
    }
    public void addUser(){
        int id = (int) System.currentTimeMillis()/1000;
        inputUser();
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dateString = date.format(timestamp);
        createDate = dateString;
        User user = new User(id,name, phone, email, dateString, createDate);
        userService.addUser(user);
    }
    public void removeUser(){
        boolean flag = true;
        int id = 0;
        do{
            try{
                System.out.print("Nhap id: ");
                id = Integer.parseInt(scanner.nextLine());
                if(checkId(id) == false){
                    flag = true;
                }else{
                    flag = false;
                }
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
        }while (flag);
        for(int i=0; i<User.getListUser().size(); i++){
            if(id==User.getListUser().get(i).getId()){
                User.getListUser().remove(i);
            }
        }
    }
    public void editUser(){
        boolean flag = true;
        int id = 0;
        do{
            try{
                System.out.print("Nhap id: ");
                id = Integer.parseInt(scanner.nextLine());
                if(checkId(id) == false){
                    flag = true;
                }else{
                    flag = false;
                }
            }catch (Exception e){
                flag = true;
                scanner.reset();
            }
        }while (flag);

        inputEditUser(id);

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        createDate = date.format(timestamp);

        for(int i=0; i<User.getListUser().size(); i++){
            if(id == User.getListUser().get(i).getId()){
                User.getListUser().get(i).setCreateDate(createDate);
            }
        }
    }
    public void inputEditUser(int id){
        boolean flag = true;
        boolean isContinue = true;
        int number = 0;
        do{
            System.out.println("[1]: Edit Name");
            System.out.println("[2]: Edit Phone");
            System.out.println("[3]: Edit Email");
            System.out.println("[4]: Exit");
            do{
                try{
                    number = Integer.parseInt(scanner.nextLine());
                    flag = false;
                }catch (Exception e){
                    flag = true;
                    scanner.reset();
                }
            }while (flag);
            switch (number){
                case 1:{
                    optionHandleEdit("name", id);
                    break;
                }
                case 2:{
                    optionHandleEdit("phone", id);
                    break;
                }
                case 3:{
                    optionHandleEdit("email", id);
                    break;
                }
                case 4:{
                    isContinue = false;
                }
            }
        }while (isContinue);
    }
    public void optionHandleEdit(String str, int id){
        boolean flag = true;
        switch (str){
            case "name":{
                do{
                    try{
                        System.out.print("Nhap name: ");
                        name = scanner.nextLine();
                        if(name.equals("")){
                            flag = true;
                        }else{
                            flag= false;
                        }
                    }catch (Exception e){
                        flag = true;
                        scanner.reset();
                    }
                }while (flag);
                for(int i=0; i<User.getListUser().size(); i++){
                    if(id == User.getListUser().get(i).getId()){
                        User.getListUser().get(i).setName(name);
                    }
                }
                break;
            }
            case "email":{
                do{
                    try{
                        System.out.print("Nhap email: ");
                        email = scanner.nextLine();
                        if(!patternString.valindateEmail(email)){
                            flag = true;
                        }else{
                            flag= false;
                        }
                    }catch (Exception e){
                        flag = true;
                        scanner.reset();
                    }
                }while (flag);
                for(int i=0; i<User.getListUser().size(); i++){
                    if(id == User.getListUser().get(i).getId()){
                        User.getListUser().get(i).setEmail(email);
                    }
                }
                break;
            }
            case "phone":{
                do{
                    try{
                        System.out.print("Nhap phone: ");
                        phone = scanner.nextLine();
                        if(!patternString.validatePhone(phone)){
                            flag = true;
                        }else{
                            flag= false;
                        }
                    }catch (Exception e){
                        flag = true;
                        scanner.reset();
                    }
                }while (flag);
                for(int i=0; i<User.getListUser().size(); i++){
                    if(id == User.getListUser().get(i).getId()){
                        User.getListUser().get(i).setPhone(phone);
                    }
                }
                break;
            }
        }

    }
    public boolean checkId(int id){
        for(int i=0; i<User.getListUser().size(); i++){
            if(id == User.getListUser().get(i).getId()){
                return true;
            }
        }
        return false;
    }
}
