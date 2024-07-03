package service.impl;

import dto.LoginDto;
import dto.RegisterDto;
import entity.User;
import enums.UserRole;
import service.AuthenticationService;

import java.util.*;

public class Authentication implements AuthenticationService {
    Scanner scanner = new Scanner(System.in);
    List<User> users=new ArrayList<>();
     User user;

    public Authentication() {
        user=new User();
        user.setUserID(user.getUserID());
        user.setName("Ozlem");
        user.setAddress("Baku");
        user.setPhone("0507776655");
        user.setEmail("ozlamhasanova@gmail.com");
        user.setPassword("1234");
        user.setUserRole(UserRole.MEMBER);
        users.add(user);
        System.out.println(user);

        user=new User();
        user.setUserID(user.getUserID());
        user.setName("Ozcan");
        user.setAddress("Baku");
        user.setPhone("0507776655");
        user.setEmail("ozlamhasanova@gmail.com");
        user.setPassword("1234");
        user.setUserRole(UserRole.MEMBER);
        users.add(user);
        System.out.println(user);
//        user=new User(user.getUserID(), "Ozcan","Baku","0507776655","ozlamhasanova@gmail.com","1234",UserRole.MEMBER);

    }

    @Override
    public void register() {
        System.out.println("---Register---");
        user=new User();
        user.setUserID(user.getUserID());
        String name=scanner.next();
        user.setName(name);
        String address=scanner.next();
        user.setAddress(address);
        String phone=scanner.next();
        user.setPhone(phone);
        String password=scanner.next();
        user.setPassword(password);
        String email=scanner.next();
        user.setEmail(email);
        checkSameUser(email);
        System.out.println("""
                Enter your role number
                1.Librarian
                2.Member""");
        int roleNumber=scanner.nextInt();
        UserRole role;
        if(roleNumber==0){
            role=UserRole.LIBRARIAN;
        } else if (roleNumber==1) {
            role=UserRole.MEMBER;
        }else{
            throw new IllegalArgumentException("Invalid data");
        }
        user.setUserRole(role);
        if(checkSameUser(email)) {
            users.add(user);
            System.out.println("Register is success \n"+user.toString());
        }else {
            System.err.println("You have an account");
        }
    }

    @Override
    public void login() {
        System.out.println("---Login---");
        System.out.print("Email: ");
        String email=scanner.next();
        System.out.print("Password: ");
        String password=scanner.next();
        User findUser=getUser(email);
        if(Objects.equals(findUser.getPassword(),password)){
            System.out.println("success");
        }else {
            System.out.println("qaqa sehvdi nese");
        }
    }
    private User getUser(String email){
        for (User findUser :
                users) {
            if(Objects.equals(findUser.getEmail(), email)){
                return findUser;
            }
        }
        return null;
    }
    public User getCurrentUser(){
        return users.get(users.size()-1);
    }

    public boolean checkSameUser(String email){//TODO: BU METHOD ISLMEIR OVLAD
        for (User userItem :
                users) {
            System.out.println(Objects.equals(userItem.getEmail(),email));
            if(!Objects.equals(userItem.getEmail(),email)){
                return false;
            }
        }
        return true;
    }
}
