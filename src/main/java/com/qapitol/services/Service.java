package com.qapitol.services;

import com.qapitol.model.Book;
import com.qapitol.model.Student;

import java.io.IOException;

public interface Service {
    void addStudent() throws IOException;
    void getAllStudents() throws IOException;
    Student getStudentById() throws IOException;
    void updateStudentDetail() throws IOException;
    void removeStudent() throws IOException;
    void borrowBook() throws IOException;
    void addBook() throws IOException;
    void getAllBooks() throws IOException;
    Book getBookById() throws IOException;
    void updateBookDetail() throws IOException;
    void removeBook() throws IOException;

}
