package service.impl;

import entity.Book;
import entity.Transaction;
import entity.User;
import enums.TransactionType;
import service.LibraryManagementService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class LibraryManagementServiceImpl implements LibraryManagementService {
    private final Authentication authentication;
    private final BookServiceImpl bookService;
    Scanner scanner = new Scanner(System.in);
    List<Transaction> transactions=new ArrayList<>();
    Transaction transaction;

    public LibraryManagementServiceImpl(Authentication authentication, BookServiceImpl bookService) {
        this.authentication = authentication;
        this.bookService = bookService;
    }


    @Override
    public void borrowBook() {
        User user = authentication.getCurrentUser();
        System.out.println("Which book do you want to borrow?" +
                "Please enter book name");
        for (Book book : bookService.library) {
            System.out.println(book.toString());
        }
        String name=scanner.next();
        Book book=getBookById(name);
        if(checkStockQuantity(book.getStockQuantity())){
            book.setStockQuantity(book.getStockQuantity()-1);
        }//todo:exceptiona ehtiyac

        LocalDate borrowDate=LocalDate.now();
        transaction=new Transaction(transaction.getTransactionID()+1,user.getUserID(),
                book.getBookID(), borrowDate,null, TransactionType.BORROW);
        transactions.add(transaction);
        System.out.println(transaction.toString());

    }

    @Override
    public void returnBook() {
        User user = authentication.getCurrentUser();
        System.out.println("Which book do you want to return?" +
                "Please enter book name");
        for (Book book : bookService.library) {
            System.out.println(book.toString());
        }
        String name=scanner.next();
        Book book=getBookById(name);
        assert book != null;
        book.setStockQuantity(book.getStockQuantity()+1);
        LocalDate returnDate=LocalDate.now();
        transaction.setReturnDate(returnDate);
        transaction.setTransactionType(TransactionType.RETURN);
        transaction.setUserID(user.getUserID());
//        transactions.add(transaction);
        System.out.println(transaction.toString());

    }

    @Override
    public void displayLibraryTransactions() {
        for (Transaction transaction :
                transactions) {
            System.out.println(transaction.toString());
        }
    }

    private Book getBookById(String title){
        for (Book book :
                bookService.library) {
            if(Objects.equals(book.getTitle(),title)&& book.getAvailable()){
                return book;
            }
        }
        return null;
    }
    private boolean checkStockQuantity(int stockQuantity){
        return stockQuantity >= 0;
    }
}
