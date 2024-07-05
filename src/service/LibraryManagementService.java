package service;

public interface LibraryManagementService {
    void borrowBook();
    void returnBook();
    void displayLibraryTransactions();
    void addBookFavoriteList();
    void showFavoriteList();
    void showReservedList();
}
