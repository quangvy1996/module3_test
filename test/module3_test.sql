create database library;
use library;
CREATE TABLE Books (
    book_id VARCHAR(50) PRIMARY KEY,
    book_name VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INT NOT NULL
);


CREATE TABLE Students (
    student_id VARCHAR(50) PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    class VARCHAR(50) NOT NULL
);


CREATE TABLE BorrowCard (
    borrow_id VARCHAR(50) PRIMARY KEY,
    book_id VARCHAR(50),
    student_id VARCHAR(50),
    status BOOLEAN NOT NULL,
    borrow_date DATE NOT NULL,
    return_date DATE,
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES Books(book_id),
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES Students(student_id)
);

