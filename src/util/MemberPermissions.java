package util;

import service.LibraryManagementService;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;

import java.util.Scanner;

public class MemberPermissions {
    private final UserServiceImpl userService;
    private final LibraryManagementService libraryManagementService;
    Scanner scanner = new Scanner(System.in);

    public MemberPermissions(UserServiceImpl userService, LibraryManagementService libraryManagementService) {
        this.userService = userService;
        this.libraryManagementService = libraryManagementService;
    }

    public void getMyPermissions() {
        System.out.println("""
                 Welcome Library Member
                 Choose Operation and enter number
                 1.Borrow Book
                 2.Return Book
                 3.Add Book My Favorite List
                 4.Show My Favorite List
                 5.Show Reserved Book List
                 6.My profile
                """);
        switch (scanner.nextInt()) {
            case 1 -> {
                libraryManagementService.borrowBook();
                goBack();
            }
            case 2 -> {
                libraryManagementService.returnBook();
                goBack();
            }
            case 3 -> {
                libraryManagementService.addBookFavoriteList();
                goBack();
            }
            case 4 -> {
                libraryManagementService.showFavoriteList();
                goBack();
            }
            case 5->{
                libraryManagementService.showReservedList();
                goBack();
            }
            case 6 -> {
                userService.getMyProfile();
                goBack();
            }
            default -> System.out.println("Invalid data");
        }
    }

    public void goBack() {
        System.out.println("""
                You want to return to the main menu?
                1.Yes
                2.No
                """);
        switch (scanner.nextInt()) {
            case 1 -> getMyPermissions();
            case 2 -> System.exit(0);
        }
    }
}
