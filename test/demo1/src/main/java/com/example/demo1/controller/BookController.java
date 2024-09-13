package com.example.demo1.controller;

import com.example.demo1.model.Book;
import com.example.demo1.model.BorrowCard;
import com.example.demo1.model.Student;
import com.example.demo1.repository.BookRepository;
import com.example.demo1.repository.BorrowCardRepository;
import com.example.demo1.repository.StudentRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BookController", value = "/books")
public class BookController extends HttpServlet {
    private final BookRepository bookRepository = new BookRepository();
    private final StudentRepository studentRepository = new StudentRepository();
    private final BorrowCardRepository borrowCardRepository = new BorrowCardRepository();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {

                default:
                    getAllBooks(request, response);
                    break;
            }
        } catch (ServletException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "borrowForm":
                showBorrowedBook(request, response);
                break;
            case "borrowBook":
                borrowBook(request, response);
                break;
            case "borrowList":
                BookList(request, response);
                break;

        }
    }

    private void getAllBooks(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Book> bookList = bookRepository.getAllBooks();

        request.setAttribute("bookList", bookList);

        request.getRequestDispatcher("view.jsp").forward(request, response);

    }

    private void showBorrowedBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookRepository.getAllBooks();
        List<Student> students = studentRepository.getAllStudents();

        request.setAttribute("books", books);
        request.setAttribute("students", students);

        request.getRequestDispatcher("borrowForm.jsp").forward(request, response);
    }

    private void borrowBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String borrowId = request.getParameter("borrowId");
        String bookId = request.getParameter("bookId");
        String studentId = request.getParameter("studentId");
        String borrowDate = request.getParameter("borrowDate");
        String returnDate = request.getParameter("returnDate");

        if (!borrowId.matches("MS-\\d{4}")) {
            request.setAttribute("error", "Invalid Borrow ID format. It should be MS-XXXX.");
            request.getRequestDispatcher("borrowForm.jsp").forward(request, response);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date bDate = sdf.parse(borrowDate);
            Date rDate = sdf.parse(returnDate);

            if (!rDate.after(bDate)) {
                request.setAttribute("error", "Return date cannot be before the borrow date.");
                request.getRequestDispatcher("borrowForm.jsp").forward(request, response);
                return;
            }

            boolean success = bookRepository.borrowBook(borrowId, bookId, studentId, borrowDate, returnDate);
            if (success) {
                response.sendRedirect("success.jsp");
            } else {
                request.setAttribute("error", "Failed to borrow the book.");
                request.getRequestDispatcher("borrowForm.jsp").forward(request, response);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid date format.");
            request.getRequestDispatcher("borrowForm.jsp").forward(request, response);
        }
    }

    private void BookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        String studentName = request.getParameter("studentName");

        List<BorrowCard> borrowBookStats = borrowCardRepository.getBorrowCard(bookName, studentName);

        request.setAttribute("borrowBookStats", borrowBookStats);

        request.getRequestDispatcher("borrowBookList.jsp").forward(request, response);
    }
}


