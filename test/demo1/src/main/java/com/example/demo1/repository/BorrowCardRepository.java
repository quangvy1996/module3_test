package com.example.demo1.repository;

import com.example.demo1.model.BorrowCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BorrowCardRepository {
    private final BaseRepository baseRepository = new BaseRepository();
private static final String GET_BORROW_CARDS_SQL = "SELECT bc.borrow_id, b.book_name, b.author, s.full_name, s.class, " +
        "bc.borrow_date, bc.return_date " +
        "FROM BorrowCard bc " +
        "JOIN Books b ON bc.book_id = b.book_id " +
        "JOIN Students s ON bc.student_id = s.student_id " +
        "WHERE (b.book_name LIKE ? OR ? IS NULL) " +
        "AND (s.full_name LIKE ? OR ? IS NULL)";
    public List<BorrowCard> getBorrowCard(String bookName, String studentName) {
        List<BorrowCard> borrowBookStatsList = new ArrayList<>();

        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BORROW_CARDS_SQL)) {

            preparedStatement.setString(1, "%" + bookName + "%");
            preparedStatement.setString(2, bookName);
            preparedStatement.setString(3, "%" + studentName + "%");
            preparedStatement.setString(4, studentName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String borrowId = resultSet.getString("borrow_id");
                String bookNameResult = resultSet.getString("book_name");
                String author = resultSet.getString("author");
                String studentFullName = resultSet.getString("full_name");
                String studentClass = resultSet.getString("class");
                String borrowDate = resultSet.getString("borrow_date");
                String returnDate = resultSet.getString("return_date");

                BorrowCard borrowCard = new BorrowCard(borrowId, bookNameResult, author,
                        studentFullName, studentClass, borrowDate, returnDate);
                borrowBookStatsList.add(borrowCard);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return borrowBookStatsList;
    }
}
