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
    Set<Book> myFavorites = new HashSet<>();
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
            Long name = scanner.nextLong();
            Book book = getBookById(name);
            if (book == null) {
                throw new BookNotFoundException("Book not found: " + name);
            }
            if (checkStockQuantity(book.getStockQuantity())) {
                book.setStockQuantity(book.getStockQuantity() - 1);
            } else {
                throw new InsufficientStockException("Insufficient stock for the book: " + name);
            }

            LocalDate borrowDate = LocalDate.now();
            transaction = new Transaction(transaction.getTransactionID() + 1, user.getUserID(),
                    book.getBookID(), borrowDate, null, TransactionType.BORROW);
            transactions.add(transaction);
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
            Long name = scanner.nextLong();
            Book book = getBookById(name);
            if (book == null) {
                throw new BookNotFoundException("Book not found: " + name);
            }
            book.setStockQuantity(book.getStockQuantity() + 1);
            LocalDate returnDate = LocalDate.now();
            transaction.setReturnDate(returnDate);
            transaction.setTransactionType(TransactionType.RETURN);
            transaction.setUserID(user.getUserID());
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
            showAllBooks();
            System.out.println("Enter the name of the book you want to add to the favorite list: ");
            String name = scanner.next();
            Book book = bookService.searchBookForTitle(name);
            if (book == null) {
                throw new BookNotFoundException("Book not found: " + name);
            }
            myFavorites.add(book);
        } catch (BookNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void showFavoriteList() {
        System.out.println("Your favorite list❤️ ");
        for (Book book :
                myFavorites) {
            System.out.println(book);
        }
    }

    private void showAllBooks() {
        for (Book book : bookService.library) {
            System.out.println(book.toString());
        }
    }

    private Book getBookById(Long id) {
        for (Book book :
                bookService.library) {
            if (Objects.equals(book.getBookID(), id) && book.getAvailable()) {
                return book;
            }
        }
        return null;
    }

    private boolean checkStockQuantity(int stockQuantity) {
        return stockQuantity >= 0;
    }
}
