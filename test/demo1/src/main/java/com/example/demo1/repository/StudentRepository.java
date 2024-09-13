package com.example.demo1.repository;

import com.example.demo1.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final BaseRepository baseRepository = new BaseRepository();

    // Phương thức lấy tất cả học sinh
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Students";

        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String fullName = resultSet.getString("full_name");
                String studentClass = resultSet.getString("class");

                // Tạo đối tượng Student
                Student student = new Student(studentId, fullName, studentClass);
                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}