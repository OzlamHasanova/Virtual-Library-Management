package service.impl;

import entity.Transaction;
import enums.TransactionType;
import exception.BookNotFoundException;
import service.ReportService;

import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ReportServiceImpl implements ReportService {
    private final BookServiceImpl bookService;
    private final LibraryManagementServiceImpl libraryManagementService;

    Scanner scanner=new Scanner(System.in);


    public ReportServiceImpl(BookServiceImpl bookService, LibraryManagementServiceImpl libraryManagementService) {
        this.bookService = bookService;
        this.libraryManagementService = libraryManagementService;
    }


    @Override
    public void getCurrentlyBorrowedBooks() {
        try {
            boolean found = false;
            for (Transaction transaction : libraryManagementService.transactions) {
                if (transaction.getTransactionType() == TransactionType.BORROW) {
                    System.out.println(transaction);
                    found = true;
                }
            }
            if (!found) {
                throw new BookNotFoundException("No currently borrowed books found");
            }
        } catch (BookNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void getOverdueBooks() {
        try {
            boolean found = false;
            for (Transaction transaction : libraryManagementService.transactions) {
                if (transaction.getReturnDate() == null && ChronoUnit.DAYS.between(
                        transaction.getBorrowDate(),transaction.getBorrowDate().plusDays(10)) > 10) {
                    System.out.println(transaction);
                    found = true;
                }
            }
            if (!found) {
                throw new BookNotFoundException("No overdue books found");
            }
        } catch (BookNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void getTransactionsForSpecificUser() {
        try {
            System.out.println("Enter User Id:");
            int userId = scanner.nextInt();
            boolean found = false;
            for (Transaction transaction : libraryManagementService.transactions) {
                if (transaction.getUserID() == userId) {
                    System.out.println(transaction);
                    found = true;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("No transactions found for user ID: " + userId);
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void getBooksByGenreOrAuthor() {
        try {
            System.out.println("""
                What do you want to search for?
                1.Genre
                2.Author
                """);
            switch (scanner.nextInt()) {
                case 1 -> bookService.searchBookForGenre();
                case 2 -> bookService.searchBookForAuthor();
                default -> throw new IllegalArgumentException("Invalid option selected");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
