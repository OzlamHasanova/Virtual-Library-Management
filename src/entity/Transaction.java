package entity;

import enums.TransactionType;

import java.time.LocalDate;

public class Transaction {
    private static Long lastTransactionId=0L;
    private Long transactionID;
    private Long userID;
    private Long bookID;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private TransactionType transactionType;

    public Transaction(Long transactionID, Long userID, Long bookID, LocalDate borrowDate, LocalDate returnDate, TransactionType transactionType) {
        this.transactionID = ++lastTransactionId;
        this.userID = userID;
        this.bookID = bookID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.transactionType = transactionType;
    }

    public Transaction() {
        this.transactionID = ++lastTransactionId;
    }

    public Long getTransactionID() {
        return lastTransactionId;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", userID=" + userID +
                ", bookID=" + bookID +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", transactionType=" + transactionType +
                '}';
    }
}
