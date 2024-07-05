package service.impl;

import entity.Book;
import entity.Transaction;
import entity.User;
import enums.TransactionType;
import exception.BookNotFoundException;
import exception.InsufficientStockException;
import service.LibraryManagementService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class LibraryManagementServiceImpl implements LibraryManagementService {
    private final Authentication authentication;
    private final BookServiceImpl bookService;
    Scanner scanner = new Scanner(System.in);
    List<Transaction> transactions = new ArrayList<>();
    Transaction transaction;

    public LibraryManagementServiceImpl(Authentication authentication, BookServiceImpl bookService) {
        this.authentication = authentication;
        this.bookService = bookService;

        transaction=new Transaction();
    }

    @Override
    public void borrowBook() {
        try {
            transaction=new Transaction();
            User user = authentication.getCurrentUser();
            System.out.println("Which book do you want to borrow? Please enter book name");
            showAllBooks();
            String name = scanner.nextLine();
            Book book = getBookByName(name);
            if (book == null) {
                throw new BookNotFoundException("Book not found: " + name);
            }
            if (checkStockQuantity(book.getStockQuantity())) {
                book.setStockQuantity(book.getStockQuantity() - 1);
            } else {
                throw new InsufficientStockException("Insufficient stock for the book: " + name);
            }
            if(!book.getAvailable()){
                System.out.println("Book added your reserved book list. Please wait until the book is available");
                user.getMyReservedBooks().add(book);
            }
            user.getBorrowBooks().add(book);

            LocalDate borrowDate = LocalDate.now();
            transaction = new Transaction(transaction.getTransactionID() + 1, user.getUserID(),
                    book.getBookID(), borrowDate, null, TransactionType.BORROW);
            transactions.add(transaction);
            user.getTransactions().add(transaction);
            System.out.println(book);
            System.out.println(transaction);
        } catch (BookNotFoundException | InsufficientStockException | DateTimeException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void returnBook() {
        try {
            User user = authentication.getCurrentUser();
            System.out.println("Which book do you want to return? " +
                    "Please enter book name");
            showAllBooks();
            String name = scanner.nextLine();
            Book book = getBookByName(name);
            if (book == null) {
                throw new BookNotFoundException("Book not found: " + name);
            }
            book.setStockQuantity(book.getStockQuantity() + 1);
            LocalDate returnDate = LocalDate.now();
            transaction.setReturnDate(returnDate);
            transaction.setTransactionType(TransactionType.RETURN);
            transaction.setUserID(user.getUserID());
            user.getTransactions().add(transaction);
            System.out.println(transaction);
        } catch (BookNotFoundException |DateTimeException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void displayLibraryTransactions() {
        for (Transaction transaction :
                transactions) {
            System.out.println(transaction.toString());
        }
    }

    @Override
    public void addBookFavoriteList() {
        try {
            User user = authentication.getCurrentUser();

            showAllBooks();
            System.out.println("Enter the name of the book you want to add to the favorite list: ");
            String name = scanner.next();
            Book book = bookService.searchBookForTitle(name);
            if (book == null) {
                throw new BookNotFoundException("Book not found: " + name);
            }
            user.getMyFavoriteBooks().add(book);
        } catch (BookNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void showFavoriteList() {
        User user = authentication.getCurrentUser();
        System.out.println("Your favorite list❤️ ");
        for (Book book :
                user.getMyFavoriteBooks()) {
            System.out.println(book);
        }
    }
    public void showReservedList() {
        User user = authentication.getCurrentUser();
        System.out.println("Your reserved books list ");
        for (Book book :
                user.getMyReservedBooks()) {
            System.out.println(book);
            if(book.getAvailable()){
                System.out.println("""
            Is already available ->"+book.getTitle()
            
            Do you want borrow this book?(enter Yes or No)
            """);
                if(scanner.next().equalsIgnoreCase("Yes")){
                    borrowBook();
                }

            }

        }
    }

    private void showAllBooks() {
        for (Book book : bookService.library) {
            System.out.println(book.toString());
        }
    }

    private Book getBookByName(String title) {
        for (Book book :
                bookService.library) {
            if (Objects.equals(book.getTitle(), title) && book.getAvailable()) {
                return book;
            } else if (Objects.equals(book.getTitle(), title) && !book.getAvailable()) {
                System.out.println("""
                This book is not available now, do you want to reserve the book?
                1.Yes
                2.No""");
                switch (scanner.nextInt()){
                    case 1 -> {
                        return book;
                    }
                    case 2-> {
                        return null;
                    }

                }
            }
        }
        return null;
    }

    private boolean checkStockQuantity(int stockQuantity) {
        return stockQuantity >= 0;
    }
}
