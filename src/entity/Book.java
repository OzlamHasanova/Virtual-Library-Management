package entity;

import enums.BookGenre;

import java.time.LocalDate;

public class Book {
    private static Long lastBookID=0L;
    private Long bookID;
    private String title;
    private String author;
    private int stockQuantity;
    private BookGenre genre;
    private LocalDate publicationDate;
    private Boolean isAvailable;

    public Book(Long bookID, String title, String author, BookGenre genre, LocalDate publicationDate, Boolean isAvailable,int stockQuantity) {
        this.bookID = ++lastBookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.isAvailable = isAvailable;
        this.stockQuantity=stockQuantity;
    }

    public Book() {
        this.bookID = ++lastBookID;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID=bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", genre=" + genre +
                ", publicationDate=" + publicationDate +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
