package service;

public interface ReportService {
    void getCurrentlyBorrowedBooks();
    void getOverdueBooks();
    void getTransactionsForSpecificUser();
    void getBooksByGenreOrAuthor();
}
