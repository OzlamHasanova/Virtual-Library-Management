package util;

import service.LibraryManagementService;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;

import java.util.Scanner;

public class MemberPermissions {
    private final UserServiceImpl userService;
    private final LibraryManagementService libraryManagementService;
    Scanner scanner=new Scanner(System.in);

    public MemberPermissions(UserServiceImpl userService, LibraryManagementService libraryManagementService) {
        this.userService = userService;
        this.libraryManagementService = libraryManagementService;
    }

    public void getMyPermissions(){
        System.out.println("""
                Welcome Library Member
                Choose Operation and enter number
                1.Borrow Book
                2.Return Book
                3.Add Book My Favorite List
                4.Show My Favorite List
                5.My profile
               """);
        switch (scanner.nextInt()){
            case 1-> libraryManagementService.borrowBook();
            case 2->libraryManagementService.returnBook();
            case 3->libraryManagementService.addBookFavoriteList();
            case 4->libraryManagementService.showFavoriteList();
            case 5->userService.getMyProfile();
            default -> System.out.println("Invalid data");
        }
    }
}
