package entity;

import enums.UserRole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private static Long lastUserID=0L;

    private Long userID;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String password;
    private UserRole userRole;
    private double deposit=0;
    private List<Transaction> transactions=new ArrayList<>();
    private Set<Book> myFavoriteBooks=new HashSet<>();
    private Set<Book> borrowBooks=new HashSet<>();
    private Set<Book> myReservedBooks=new HashSet<>();



    public User(Long userID, String name, String address, String phone, String email, String password, UserRole userRole) {
        this.userID = ++lastUserID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public User() {
        this.userID = ++lastUserID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID=userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Set<Book> getMyFavoriteBooks() {
        return myFavoriteBooks;
    }

    public void setMyFavoriteBooks(Set<Book> myFavoriteBooks) {
        this.myFavoriteBooks = myFavoriteBooks;
    }

    public Set<Book> getBorrowBooks() {
        return borrowBooks;
    }

    public void setBorrowBooks(Set<Book> borrowBooks) {
        this.borrowBooks = borrowBooks;
    }

    public Set<Book> getMyReservedBooks() {
        return myReservedBooks;
    }

    public void setMyReservedBooks(Set<Book> myReservedBooks) {
        this.myReservedBooks = myReservedBooks;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
