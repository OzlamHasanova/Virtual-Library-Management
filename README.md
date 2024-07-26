# Library Management System

## Welcome!

This Library Management System project is all about creating a practical, console-based application in Java. It covers user management, book management, transactions, and more to help you master Java core concepts like OOP, Collections, Enums, Exception Handling, and File I/O.

## Features

### 1. User Registration and Login
- **User Roles:** LIBRARIAN and MEMBER (enum `UserRole`).
- **User Class:** Attributes include `userID`, `name`, `address`, `phone`, `email`, `password`, and `userRole`.
- **Register & Login:** Ensure unique `userID` and validate credentials.

### 2. User Management
- **Add, Delete, Update Users:** Manage user information with unique `userID`.

### 3. Book Management
- **Book Class:** Attributes include `bookID`, `title`, `author`, `genre`, `publicationDate`, and `isAvailable`.
- **Book Genres:** FICTION, NONFICTION, SCIENCE, ART (enum `BookGenre`).
- **Add, Delete, Update Books:** Ensure unique `bookID`.

### 4. Borrowing and Returning Books
- **Borrow & Return Books:** Track availability and user borrowing status.

### 5. Transaction Management
- **Transaction Class:** Attributes include `transactionID`, `userID`, `bookID`, `borrowDate`, `returnDate`, and `transactionType` (enum `TransactionType`: BORROW, RETURN).
- **Record Transactions:** Unique `transactionID` and history tracking.

### 6. Searching and Sorting
- **Search Books & Users:** By attributes like `title`, `author`, `genre`, `name`, `userID`.
- **Sort Functionality:** Based on various attributes.

### 7. Generating Reports
- **Reports:** 
  - Currently borrowed books.
  - Overdue books.
  - User transactions.
  - Books by genre/author.

### 8. Exception Handling
- **Custom Exceptions:** `BookNotAvailableException`, `UserNotFoundException`, etc.
- **Error Management:** `try-catch` blocks and user-friendly messages.

### 9. Favorites Feature
- **Add to Favorites:** Users can favorite books and view their list even after restart.

### 10. Interactive Menu
- **User-Friendly Menu:** Text-based navigation for all operations.

### 11. Reservation System
- **Reserve Books:** Notify users when reserved books are available and manage queues.

### 12. Late Fee Calculation
- **Calculate Late Fees:** Based on overdue days, apply to user accounts, and allow fee payment.



## Contact

Questions or feedback? Reach out to Ozlem at ozlamhasanova@gmail.com

---
