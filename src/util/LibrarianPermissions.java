package util;

import service.LibraryManagementService;
import service.impl.BookServiceImpl;
import service.impl.ReportServiceImpl;
import service.impl.UserServiceImpl;

import java.util.Scanner;

public class LibrarianPermissions {
    private final BookServiceImpl bookService;
    private final UserServiceImpl userService;
    private final ReportServiceImpl reportService;
    private final LibraryManagementService libraryManagementService;
    Scanner scanner = new Scanner(System.in);

    public LibrarianPermissions(BookServiceImpl bookService, UserServiceImpl userService, ReportServiceImpl reportService, LibraryManagementService libraryManagementService) {
        this.bookService = bookService;
        this.userService = userService;
        this.reportService = reportService;
        this.libraryManagementService = libraryManagementService;
    }


    public void getMyPermissions() {
        try {
            System.out.println("""
                     Welcome Librarian
                     Choose Operation and enter number
                     1.Book Operations
                     2.Search or Sort in Library
                     3.Search or Sort Users
                     4.Display all transactions
                     5.Quick report
                     6.My profile
                    """);
            switch (scanner.nextInt()) {
                case 1 -> {
                    getBookMenu();
                    goBack();
                }
                case 2 -> {
                    getReportMenuAboutBook();
                    goBack();
                }
                case 3 -> {
                    getSearchOrSortUsers();
                    goBack();
                }
                case 4 -> {
                    libraryManagementService.displayLibraryTransactions();
                    goBack();
                }
                case 5 -> {
                    getReportMenu();
                    goBack();
                }
                case 6 -> {
                    userService.getMyProfile();
                    goBack();
                }
                default -> throw new IllegalArgumentException("Please enter number from menu");

            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            getMyPermissions();
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

    public void getBookMenu() {
        System.out.println("""
                Choose Operation(number)
                1.Add
                2.Update
                3.Delete
                4.Go Back
                """);
        try {
            switch (scanner.nextInt()) {
                case 1 -> bookService.add();
                case 2 -> bookService.update();
                case 3 -> bookService.delete();
                case 4-> goBack();
                default -> throw new IllegalArgumentException("Invalid choice");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void getReportMenuAboutBook() {
        System.out.println("""
                Choose Operation(number)
                1.Search book
                2.Sort books
                3.Go Back""");
        try {
            switch (scanner.nextInt()) {
                case 1 -> bookService.searchBook();
                case 2 -> bookService.sortBooks();
                case 3->goBack();
                default -> throw new IllegalArgumentException("Invalid choice");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void getSearchOrSortUsers() {
        try {
            System.out.println("""
                    Choose Operation(number)
                    1.Search User
                    2.Sort Users
                    3.Go Back""");
            switch (scanner.nextInt()) {
                case 1 -> userService.searchUser();
                case 2 -> userService.sortUsers();
                case 3->goBack();
                default -> throw new IllegalArgumentException("Invalid option selected");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void getReportMenu() {
        try {
            System.out.println("""
                    Choose Operation(number)
                    1.Currently Borrowed Books
                    2.Overdue Books
                    3.Transaction for specific user
                    4.Books by Genre or Author
                    5.Go Back
                    """);
            switch (scanner.nextInt()) {
                case 1 -> reportService.getCurrentlyBorrowedBooks();
                case 2 -> reportService.getOverdueBooks();
                case 3 -> reportService.getTransactionsForSpecificUser();
                case 4 -> reportService.getBooksByGenreOrAuthor();
                case 5-> goBack();
                default -> throw new IllegalArgumentException("Invalid option selected");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }


}
