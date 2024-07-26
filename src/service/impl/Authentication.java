package service.impl;

import entity.User;
import enums.UserRole;
import exception.InvalidPasswordException;
import exception.UserAlreadyExistsException;
import exception.UserNotFoundException;
import service.AuthenticationService;

import java.util.*;
import java.util.function.Predicate;

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

    }

    @Override
    public void register() {
        System.out.println("---Register---");
        user=new User();
        user.setUserID(user.getUserID());
        getInfoFromUser(scanner, user);
        System.out.println("""
                Enter your role number
                1.Librarian
                2.Member""");
        int roleNumber=scanner.nextInt();
        UserRole role;
        if(roleNumber==1){
            role=UserRole.LIBRARIAN;
        } else if (roleNumber==2) {
            role=UserRole.MEMBER;
        }else{
            throw new IllegalArgumentException("Invalid data");
        }
        user.setUserRole(role);

        user.setUserRole(role);
        try {
            if (roleNumber == 1) {
                role = UserRole.LIBRARIAN;
            } else if (roleNumber == 2) {
                role = UserRole.MEMBER;
            } else {
                throw new IllegalArgumentException("Invalid data");
            }
            if (!checkSameUser(user.getEmail())) {
                users.add(user);
                System.out.println("Register is success \n" + user.toString());
            } else {
                throw new UserAlreadyExistsException("You have an account with this email.");
            }
        } catch (UserAlreadyExistsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    protected static String getInfoFromUser(Scanner scanner, User user) {
        System.out.println("Name: ");
        String name= scanner.next();
        user.setName(name);
        System.out.println("Address: ");
        String address= scanner.next();
        user.setAddress(address);
        System.out.println("Phone: ");
        String phone= scanner.next();
        user.setPhone(phone);
        System.out.println("Password");
        String password= scanner.next();
        user.setPassword(password);
        System.out.println("Email");
        boolean isContinue=true;
        while (isContinue) {
            String email = scanner.next();
            if (email.contains("@")) {
                user.setEmail(email);
                isContinue=false;
                return email;
            } else {
                System.err.println("E-poçtda @ simvolu olmalıdır");
            }
        }
        return null;

    }

    @Override
    public User login() {
        int count=0;
        try {
            System.out.println("---Login---");
            System.out.print("Email: ");
            String email=scanner.next();
            System.out.print("Password: ");
            String password=scanner.next();
            User findUser=getUser(email);
            if(Objects.equals(findUser.getPassword(),password)&&Objects.equals(findUser.getEmail(),email)){
                System.out.println("Login is success");
                return findUser;
            } else if (Objects.equals(findUser.getPassword(), password) && !Objects.equals(findUser.getEmail(), email)) {
                throw new UserNotFoundException("User not found this email "+email);
            } else if (!Objects.equals(findUser.getPassword(),password)&&Objects.equals(findUser.getEmail(),email)) {
                throw new InvalidPasswordException("Invalid password");
            }
        }catch (UserNotFoundException|InvalidPasswordException e){
            System.err.println(e.getMessage());
            System.out.println("Try again");
            count++;
            if(count==3){
                System.out.println("You have exceeded the limit");
                System.exit(0);
            }
            login();
        }

        return null;
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

    public boolean checkSameUser(String email){
        for (User userItem :
                users) {
            if(!Objects.equals(userItem.getEmail(),email)){
                return false;
            }
        }
        return true;
    }
}
