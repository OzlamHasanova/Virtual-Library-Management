package service.impl;

import entity.User;
import exception.UserAlreadyExistsException;
import exception.UserNotFoundException;
import service.UserService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    private final Authentication authentication;
    Scanner scanner = new Scanner(System.in);


    public UserServiceImpl(Authentication authentication) {
        this.authentication = authentication;
    }



    public void getMyProfile() {
        User user = authentication.getCurrentUser();
        System.out.println(user.toString());
        try {
            System.out.println("""
                    Choose Operation(number)
                    1.Update
                    2.Delete""");
            switch (scanner.nextInt()) {
                case 1 -> update();
                case 2 -> delete();
                default -> throw new IllegalArgumentException("Invalid option selected");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update() {
        try {
            User user = authentication.getCurrentUser();
            String email = Authentication.getInfoFromUser(scanner, user);
            if (authentication.checkSameUser(email)) {
                System.out.println("Update is success \n" + user.toString());
            } else {
                throw new UserAlreadyExistsException("This email use another user");
            }
        } catch (IllegalArgumentException | UserAlreadyExistsException | NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void delete() {
        try {
            User user = authentication.getCurrentUser();
            System.out.println("""
                    Are you sure you want to delete the account?
                    1.No
                    2.Yes""");
            int deleteSure = scanner.nextInt();
            if (deleteSure == 2) {
                user = null;
                System.out.println("Account is deleted");
            } else if (deleteSure != 1) {
                throw new IllegalArgumentException("Invalid data");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void searchUser() {
        try {
            System.out.println("""
                     Searching in Users for:
                     1.UserId
                     2.Name
                     3.UserRole
                    """);
            int choose = scanner.nextInt();
            switch (choose) {
                case 1 -> searchUserForUserID();
                case 2 -> searchUserForName();
                case 3 -> searchUserForUserRole();
                default -> throw new IllegalArgumentException("Invalid option selected");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private void searchUserForUserRole() {
        try {
            System.out.println("Enter user role: ");
            String role = scanner.next().toUpperCase();
            boolean found = false;
            for (User user : authentication.users) {
                if (user.getUserRole().name().equals(role)) {
                    System.out.println(user);
                    found = true;
                }
            }
            if (!found) {
                throw new UserNotFoundException("No users found with role: " + role);
            }
        } catch (UserNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }

    private void searchUserForName() {
        try {
            System.out.println("Enter user Name: ");
            String name = scanner.next();
            boolean found = false;
            for (User user : authentication.users) {
                if (user.getName().equalsIgnoreCase(name)) {
                    System.out.println(user);
                    found = true;
                }
            }
            if (!found) {
                throw new UserNotFoundException("No users found with name: " + name);
            }
        } catch (UserNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private void searchUserForUserID() {
        try {
            System.out.println("Enter user id: ");
            Long userId = scanner.nextLong();
            boolean found = false;
            for (User user : authentication.users) {
                if (user.getUserID().equals(userId)) {
                    System.out.println(user);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new UserNotFoundException("No users found with ID: " + userId);
            }
        } catch (UserNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void sortUsers() {
        try {
            System.out.println("""
                     Sorting in Users for:
                     1.User Id
                     2.User Name
                    """);
            switch (scanner.nextInt()) {
                case 1 -> sortUsersForUserId();
                case 2 -> sortUsersForUserName();
                default -> throw new IllegalArgumentException("Invalid option selected");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
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
