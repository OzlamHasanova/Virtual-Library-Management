package service.impl;

import service.ReportService;

public class ReportServiceImpl implements ReportService {
    private final BookServiceImpl bookService;
    private final LibraryManagementServiceImpl libraryManagementService;
    private final UserServiceImpl userService;

    public ReportServiceImpl(BookServiceImpl bookService, LibraryManagementServiceImpl libraryManagementService, UserServiceImpl userService) {
        this.bookService = bookService;
        this.libraryManagementService = libraryManagementService;
        this.userService = userService;
    }

    @Override
    public void getCurrentlyBorrowedBooks() {

    }

    @Override
    public void getOverdueBooks() {

    }

    @Override
    public void getTransactionsForSpecificUser() {

    }

    @Override
    public void getBooksByGenreOrAuthor() {

    }
}
