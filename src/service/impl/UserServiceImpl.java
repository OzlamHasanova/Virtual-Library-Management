package service.impl;

import entity.Book;
import entity.User;
import enums.UserRole;
import service.UserService;

import java.io.InvalidObjectException;
import java.util.*;

public class UserServiceImpl implements UserService {
    private final Authentication authentication;
    Scanner scanner = new Scanner(System.in);


    public UserServiceImpl(Authentication authentication) {
        this.authentication = authentication;
    }


    @Override
    public void update() {
        User user = authentication.getCurrentUser();
        String name = scanner.next();
        user.setName(name);
        String address = scanner.next();
        user.setAddress(address);
        String phone = scanner.next();
        user.setPhone(phone);
        String password = scanner.next();
        user.setPassword(password);
        String email = scanner.next();
        user.setEmail(email);
        authentication.checkSameUser(email);
        System.out.println("""
                Enter your role number
                1.Librarian
                2.Member""");
        int roleNumber = scanner.nextInt();
        UserRole role;
        if (roleNumber == 0) {
            role = UserRole.LIBRARIAN;
        } else if (roleNumber == 1) {
            role = UserRole.MEMBER;
        } else {
            throw new IllegalArgumentException("Invalid data");
        }
        user.setUserRole(role);
        if (authentication.checkSameUser(email)) {
            System.out.println("Update is success \n" + user.toString());
        } else {
            System.err.println("You have an account");
        }


    }

    @Override
    public void delete() {
        User user = authentication.getCurrentUser();
        System.out.println("""
                Are you sure you want to delete the account?
                1.No
                2.Yes""");
        int deleteSure= scanner.nextInt();
        if(deleteSure==2){
            user=null;
            System.out.println("Account is deleted");
        } else if (deleteSure==1) {

        }else {
            System.out.println("Invalid data");
        }

    }

    @Override
    public void searchUser() {
        System.out.println("""
                Searching in Users for:
                1.UserId
                2.Name
                3.UserRole
               """);
        int choose= scanner.nextInt();
        switch (choose){
            case 1->searchUserForUserID();
            case 2->searchUserForName();
            case 3->searchUserForUserRole();
            default -> System.err.println("Invalid data");// TODO: exception
        }
    }

    private void searchUserForUserRole() {
        System.out.println("Enter user role: ");
        String role=scanner.next().toLowerCase();
        for (User user :
                authentication.users) {
            if(Objects.equals(user.getUserRole().name(),role)){
                System.out.println(user.toString());
            }
        }
    }

    private void searchUserForName() {
        System.out.println("Enter user Name: ");
        String name=scanner.next();
        for (User user :
                authentication.users) {
            if(Objects.equals(user.getName(),name)){
                System.out.println(user.toString());
            }
        }
    }

    private void searchUserForUserID() {
        System.out.println("Enter user id: ");
        Long userId=scanner.nextLong();
        for (User user :
                authentication.users) {
            if(Objects.equals(user.getUserID(), userId)){
                System.out.println(user.toString());
                break;
            }
        }
    }

    @Override
    public void sortUsers() {
        System.out.println("""
                Sorting in Users for:
                1.User Id
                2.User Name
               """);
        int choose=scanner.nextInt();
        switch (choose){
            case 1->sortUsersForUserId();
            case 2->sortUsersForUserName();
            default -> System.err.println("Invalid data");// TODO: exception
        }
    }

    private void sortUsersForUserName() {
        List<User> sortedLibrary = new ArrayList<>(authentication.users);
        sortedLibrary.sort(Comparator.comparing(User::getName));

        for (User user : sortedLibrary) {
            System.out.println(user);
        }
    }

    private void sortUsersForUserId() {
        List<User> sortedLibrary = new ArrayList<>(authentication.users);
        sortedLibrary.sort(Comparator.comparing(User::getUserID));

        for (User user : sortedLibrary) {
            System.out.println(user);
        }
    }

}
