import service.BookService;
import service.LibraryManagementService;
import service.impl.Authentication;
import service.impl.BookServiceImpl;
import service.impl.LibraryManagementServiceImpl;
import service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        while (true){
            Authentication authentication=new Authentication();

//            authentication.register();
//            authentication.login();
            UserServiceImpl userService=new UserServiceImpl(authentication);
            userService.sortUsers();
//            userService.update();
        //    userService.delete();
          //  BookServiceImpl bookService=new BookServiceImpl();
//            bookService.add();
//            bookService.add();
//            bookService.searchBook();
//            bookService.sortBooks();
//            LibraryManagementService libraryManagementService=new LibraryManagementServiceImpl(authentication,bookService);
//            libraryManagementService.borrowBook();
//            libraryManagementService.returnBook();
//            libraryManagementService.displayLibraryTransactions();
        }


    }
}