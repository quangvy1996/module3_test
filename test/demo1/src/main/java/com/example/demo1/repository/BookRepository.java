package com.example.demo1.repository;

import com.example.demo1.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookRepository {
    private final BaseRepository baseRepository = new BaseRepository();
    private static final String GET_BOOKS_SQL = "SELECT book_id, book_name, author, quantity, description FROM Books";
    private static final String BORROWED_BOOK ="INSERT INTO BorrowCard (borrow_id, book_id, student_id, status, borrow_date, return_date) " +
            "VALUES (?, ?, ?, 1, ?, ?)";
    ;
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();

        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOKS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String bookId = resultSet.getString("book_id");
                String bookName = resultSet.getString("book_name");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                int quantity = resultSet.getInt("quantity");

                Book book = new Book(bookId, bookName, author, description, quantity);
                bookList.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookList;
    }
    public boolean borrowBook(String borrowId, String bookId, String studentId, String borrowDate, String returnDate) {

        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BORROWED_BOOK)) {

            preparedStatement.setString(1, borrowId);  // Mã mượn sách
            preparedStatement.setString(2, bookId);    // Mã sách
            preparedStatement.setString(3, studentId); // Mã học sinh
            preparedStatement.setString(4, borrowDate); // Ngày mượn
            preparedStatement.setString(5, returnDate); // Ngày trả

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
