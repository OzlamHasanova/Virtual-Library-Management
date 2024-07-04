package util;

import service.LibraryManagementService;
import service.impl.BookServiceImpl;
import service.impl.ReportServiceImpl;
import service.impl.UserServiceImpl;

import java.util.Scanner;

public class LibrarianPermissions{
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
                case 1 -> bookService.getBookMenu();
                case 2 -> bookService.getReportMenuAboutBook();
                case 3 -> userService.getSearchOrSortUsers();
                case 4 -> libraryManagementService.displayLibraryTransactions();
                case 5 -> reportService.getReportMenu();
                case 6 -> userService.getMyProfile();
                default -> throw new IllegalArgumentException("Please enter number from menu");

            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            getMyPermissions();
        }
    }




}
