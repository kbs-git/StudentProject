package com.qapitol.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int bookID;
    private int bookCount;
    private String subject;
    private List<String> student;
    public Book(int bookID, String subject, int bookCount) {
        this.bookID = bookID;
        this.subject = subject;
        this.bookCount = bookCount;
        this.student = new ArrayList<>();
    }

    public Book() {
        this.student = new ArrayList<>();
    }

    public int getBookId() {
        return bookID;
    }

    public void setBookId(int bookid) {
        this.bookID = bookid;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookcount) {
        this.bookCount = bookcount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getStudents() {
        return student;
    }

    public void setStudents(List<String> students) {
        this.student = students;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookCount=" + bookCount +
                ", subject='" + subject + '\'' +
                ", students=" + student +
                '}';
    }
}
