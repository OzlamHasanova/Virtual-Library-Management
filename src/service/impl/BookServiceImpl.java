package service.impl;

import entity.Book;
import enums.BookGenre;
import service.BookService;

import java.time.LocalDate;
import java.util.*;

public class BookServiceImpl implements BookService {
    Set<Book> library = new HashSet<>();
    Book book;
    Scanner scanner=new Scanner(System.in);
    public BookServiceImpl() {

        book = new Book();
        book.setBookID(book.getBookID());
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        book.setStockQuantity(10);
        book.setGenre(BookGenre.FICTION);
        book.setPublicationDate(LocalDate.of(1925, 4, 10));
        book.setAvailable(true);
        library.add(book);
        System.out.println(book.toString());

        book = new Book();
        book.setBookID(book.getBookID());
        book.setTitle("Moby Dick");
        book.setAuthor("Herman Melville");
        book.setStockQuantity(5);
        book.setGenre(BookGenre.FICTION);
        book.setPublicationDate(LocalDate.of(1851, 11, 14));
        book.setAvailable(true);
        library.add(book);
        System.out.println(book.toString());
    }

    @Override
    public void add() {
        System.out.println("Add Books to the Library");
        book=new Book();
        book.setBookID(book.getBookID());
        getInfoFromUser(book);
        library.add(book);
        System.out.println(book.toString());
        System.out.println("Book added success");
    }

    private void getInfoFromUser(Book book) {
        System.out.println("Title: ");
        String title=scanner.next();
        book.setTitle(title);
        System.out.println("Author: ");
        String author=scanner.next();
        book.setAuthor(author);
        System.out.println("Stock Quantity: ");
        int stockCount=scanner.nextInt();
        book.setStockQuantity(stockCount);
        System.out.println("""
                Genre:
                 1.Fiction
                 2.Nonfiction
                 3.Science
                 4.Art
                 5.Romance
                 6.Mystery""");
        int genreEnumNumber=scanner.nextInt();
        BookGenre genre = null;
        switch (genreEnumNumber){
            case 1->genre= BookGenre.FICTION;
            case 2->genre=BookGenre.NONFICTION;
            case 3->genre=BookGenre.SCIENCE;
            case 4->genre=BookGenre.ART;
            case 5->genre=BookGenre.ROMANCE;
            case 6->genre=BookGenre.MYSTERY;
            default -> System.out.println();
        }
        book.setGenre(genre);
        System.out.print("Publication date(yyyy-mm-dd): ");
        String date=scanner.next();
        LocalDate publicationDate= LocalDate.parse(date);
        book.setPublicationDate(publicationDate);
        System.out.println("""
                Book is available?
                1.Yes
                2.No""");
        boolean isAvailable = false;
        switch (scanner.nextInt()){
            case 1->isAvailable=true;
            case 2->isAvailable=false;
            default -> System.out.println();
        }
        book.setAvailable(isAvailable);
    }

    @Override
    public void update() {
        System.out.println("Book id: ");
        Long id=scanner.nextLong();
        Book book=getBookById(id);
        getInfoFromUser(book);
        System.out.println("Update is success");
    }

    @Override
    public void delete() {
        System.out.println("Book id: ");
        Long id=scanner.nextLong();
        Book book=getBookById(id);
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
        int choose= scanner.nextInt();
        switch (choose){
            case 1->searchBookForTitle();
            case 2->searchBookForAuthor();
            case 3->searchBookForGenre();
            default -> System.err.println("Invalid data");// TODO: exception
        }
    }

    private void searchBookForGenre() {
        System.out.println("Enter book Genre: ");
        String genre=scanner.next().toUpperCase();
        for (Book book :
                library) {
            if(Objects.equals(book.getGenre().name(), genre)){
                System.out.println(book);
            }
        }//todo exception bele kitab yoxdur
    }

    private void searchBookForAuthor() {
        System.out.println("Enter book Author: ");
        String author=scanner.next();
        for (Book book :
                library) {
            if(Objects.equals(book.getAuthor(), author)){
                System.out.println(book);
            }
        }
    }

    private void searchBookForTitle() {
        System.out.println("Enter book title: ");
        String title=scanner.next();
        for (Book book :
                library) {
            if(Objects.equals(book.getTitle(), title)){
                System.out.println(book.toString());
            }
        }
        System.out.println("Kitab tapilmadi");
    }

    @Override
    public void sortBooks() {
        System.out.println("""
                Choose element for sort library books
                1.Title(A-Z)
                2.Author(A-Z)""");
        int choose=scanner.nextInt();
        switch (choose){
            case 1->sortBooksForTitle();
            case 2->sortBooksForAuthor();
            default -> System.err.println("Invalid data");// TODO: exception
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

    private Book getBookById(Long id){
        for (Book book :
                library) {
            if(Objects.equals(book.getBookID(),id)){
                return book;
            }
        }
        return null;
    }


}
