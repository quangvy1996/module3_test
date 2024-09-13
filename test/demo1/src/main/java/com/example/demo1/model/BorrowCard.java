package com.example.demo1.model;

import java.time.LocalDate;

public class BorrowCard {
    private String borrowId;
    private String bookName;
    private String author;
    private String studentFullName;
    private String studentClass;
    private String borrowDate;
    private String returnDate;

    public BorrowCard(String borrowId, String bookName, String author, String studentFullName, String studentClass, String borrowDate, String returnDate) {
        this.borrowId = borrowId;
        this.bookName = bookName;
        this.author = author;
        this.studentFullName = studentFullName;
        this.studentClass = studentClass;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
