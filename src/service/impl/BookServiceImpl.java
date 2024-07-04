package service.impl;

import entity.Book;
import enums.BookGenre;
import exception.BookNotFoundException;
import service.BookService;

import java.time.LocalDate;
import java.util.*;

public class BookServiceImpl implements BookService {
    Set<Book> library = new HashSet<>();
    Book book;
    Scanner scanner = new Scanner(System.in);


    public BookServiceImpl() {

        book = new Book();
        book.setBookID(book.getBookID());
        book.setTitle("Les Misérables");
        book.setAuthor("Victor Hugo");
        book.setStockQuantity(10);
        book.setGenre(BookGenre.NONFICTION);
        book.setPublicationDate(LocalDate.of(1862, 1, 1));
        book.setAvailable(true);
        library.add(book);
        System.out.println(book.toString());

        book = new Book();
        book.setBookID(book.getBookID());
        book.setTitle("Crime and Punishment");
        book.setAuthor("Fyodor Dostoevsky");
        book.setStockQuantity(5);
        book.setGenre(BookGenre.NONFICTION);
        book.setPublicationDate(LocalDate.of(1866, 1, 1));
        book.setAvailable(true);
        library.add(book);
        System.out.println(book.toString());
    }

    public void getBookMenu() {
        System.out.println("""
                Choose Operation(number)
                1.Add
                2.Update
                3.Delete
                """);
        try {
            switch (scanner.nextInt()) {
                case 1 -> add();
                case 2 -> update();
                case 3 -> delete();
                default -> throw new IllegalArgumentException("Invalid choice");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void getReportMenuAboutBook() {
        System.out.println("""
                Choose Operation(number)
                1.Search book
                2.Sort books""");
        try {
            switch (scanner.nextInt()) {
                case 1 -> searchBook();
                case 2 -> sortBooks();
                default -> throw new IllegalArgumentException("Invalid choice");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        System.out.println("Add Books to the Library");
        book = new Book();
        book.setBookID(book.getBookID());
        getInfoFromUser(book);
        library.add(book);
        System.out.println(book.toString());
        System.out.println("Book added success");
    }

    private void getInfoFromUser(Book book) {
        System.out.println("Title: ");
        String title = scanner.next();
        book.setTitle(title);
        System.out.println("Author: ");
        String author = scanner.next();
        book.setAuthor(author);
        System.out.println("Stock Quantity: ");
        int stockCount = scanner.nextInt();
        book.setStockQuantity(stockCount);
        System.out.println("""
                Genre:
                 1.Fiction
                 2.Nonfiction
                 3.Science
                 4.Art
                 5.Romance
                 6.Mystery""");
        BookGenre genre = null;
        try {
            switch (scanner.nextInt()) {
                case 1 -> book.setGenre(BookGenre.FICTION);
                case 2 -> book.setGenre(BookGenre.NONFICTION);
                case 3 -> book.setGenre(BookGenre.SCIENCE);
                case 4 -> book.setGenre(BookGenre.ART);
                case 5 -> book.setGenre(BookGenre.ROMANCE);
                case 6 -> book.setGenre(BookGenre.MYSTERY);
                default -> throw new IllegalArgumentException("Invalid genre choice");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Publication date (yyyy-mm-dd): ");
        book.setPublicationDate(LocalDate.parse(scanner.next()));

        System.out.println("""
                Book is available?
                1. Yes
                2. No
                """);
        try {
            switch (scanner.nextInt()) {
                case 1 -> book.setAvailable(true);
                case 2 -> book.setAvailable(false);
                default -> throw new IllegalArgumentException("Invalid availability choice");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update() {
        System.out.println("Book id: ");
        Long id = scanner.nextLong();
        Book book = getBookById(id);
        getInfoFromUser(book);
        System.out.println("Update is success");
    }

    @Override
    public void delete() {
        System.out.println("Book id: ");
        Long id = scanner.nextLong();
        Book book = getBookById(id);
        library.remove(book);
        System.out.println("Delete is success");
    }

    @Override
    public void searchBook() {
        System.out.println("""
                Searching in Library for:
                1.Title
                2.Author
                3.Genre""");
        try {
            switch (scanner.nextInt()) {
                case 1 -> searchBookForTitle();
                case 2 -> searchBookForAuthor();
                case 3 -> searchBookForGenre();
                default -> throw new IllegalArgumentException("Invalid search choice");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    protected void searchBookForGenre() {
        System.out.println("Enter book Genre" +
                "Genre: Fiction, Nonfiction, Science, Art, Romance, Mystery: ");

        String genre = scanner.next().toUpperCase();
        try {
            for (Book book :
                    library) {
                if (Objects.equals(book.getGenre().name(), genre)) {
                    System.out.println(book);
                }
            }
        }catch (BookNotFoundException ex){
            System.err.println("Books not found with genre: " + genre);

        }

    }

    void searchBookForAuthor() {
        System.out.println("Enter book Author: ");
        String author = scanner.next();
        try {
            boolean found = false;
            for (Book book :
                    library) {
                if (Objects.equals(book.getAuthor(), author)) {
                    System.out.println(book);
                    found=true;
                }
            }
            if (!found) {
                throw new BookNotFoundException("No Book found with author: " + author);
            }
        }catch (BookNotFoundException ex){
            System.err.println("Books not found with author name: " + author);

        }

    }

    private void searchBookForTitle() {
        System.out.println("Enter book title: ");
        String title = scanner.next();
        try {
            boolean found=false;
            for (Book book :
                    library) {
                if (Objects.equals(book.getTitle(), title)) {
                    System.out.println(book);
                    found=true;
                }
            }
            if (!found) {
                throw new BookNotFoundException("No book found with title: " + title);
            }
        }catch (BookNotFoundException ex){
            System.err.println("Books not found with title: " + title);

        }
    }

    protected Book searchBookForTitle(String bookName) {
        for (Book book :
                library) {
            if (Objects.equals(book.getTitle(), bookName)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void sortBooks() {
        System.out.println("""
                Choose element for sort library books
                1.Title(A-Z)
                2.Author(A-Z)""");

        try {
            switch (scanner.nextInt()) {
                case 1 -> sortBooksForTitle();
                case 2 -> sortBooksForAuthor();
                default -> throw new IllegalArgumentException("Invalid sort choice");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }

    private void sortBooksForAuthor() {
        List<Book> sortedLibrary = new ArrayList<>(library);
        sortedLibrary.sort(Comparator.comparing(Book::getAuthor));

        for (Book book : sortedLibrary) {
            System.out.println(book);
        }
    }

    private void sortBooksForTitle() {
        List<Book> sortedLibrary = new ArrayList<>(library);
        sortedLibrary.sort(Comparator.comparing(Book::getTitle));

        for (Book book : sortedLibrary) {
            System.out.println(book.toString());
        }
    }

    private Book getBookById(Long id) {
        for (Book book :
                library) {
            if (Objects.equals(book.getBookID(), id)) {
                return book;
            }
        }
        return null;
    }


}
