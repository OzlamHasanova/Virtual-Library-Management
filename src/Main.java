import entity.Employee;
import entity.User;
import enums.UserRole;
import service.impl.*;
import util.LibrarianPermissions;
import util.MemberPermissions;

public class Main {

    public static void main(String[] args) {

        Authentication authentication = new Authentication();
        UserServiceImpl userService = new UserServiceImpl(authentication);
        BookServiceImpl bookService = new BookServiceImpl();
        LibraryManagementServiceImpl libraryManagementService = new LibraryManagementServiceImpl(authentication, bookService);
        ReportServiceImpl reportService = new ReportServiceImpl(bookService, libraryManagementService);
        authentication.register();
        User currentUser = authentication.login();
        if (currentUser.getUserRole() == UserRole.LIBRARIAN) {
            LibrarianPermissions librarianPermissions=new LibrarianPermissions(bookService,userService,reportService,libraryManagementService);
            librarianPermissions.getMyPermissions();
        } else if (currentUser.getUserRole() == UserRole.MEMBER) {
            MemberPermissions memberPermissions = new MemberPermissions(userService, libraryManagementService);
            memberPermissions.getMyPermissions();
        }

    }
}