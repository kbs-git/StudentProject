package com.qapitol.services;

import com.qapitol.controler.FileOpertaions;
import com.qapitol.model.Address;
import com.qapitol.model.Book;
import com.qapitol.model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ServiceImplemntation implements Service{
    Scanner scanner;
    Student student;
    Book book;
    List<Student> students=new ArrayList<Student>();
    List<Book> books=new ArrayList<Book>();
    String studfentFilePath="C:\\Users\\Qapitol QA\\IdeaProjects\\StudentLibrary\\src\\main\\resources\\student.json";
    String bookFilePath="C:\\Users\\Qapitol QA\\IdeaProjects\\StudentLibrary\\src\\main\\resources\\book.json";

    @Override
    public void addStudent() throws IOException {
        scanner=new Scanner(System.in);
        student=new Student();
        System.out.println("Enter the Student Id");
        int sid = scanner.nextInt();
        List<Student> studentList=FileOpertaions.readStudentFile(studfentFilePath);
        boolean idExists = false;
        for (Student student1 : studentList) {
            if (student1.getId() == sid) {
                idExists = true;
                break;
            }
        }
        if (idExists) {
            System.out.println("The Id is already allocated to another student");
        } else {
            student.setId(sid);
            System.out.println("Enter a Student Name");
            student.setName(scanner.next());
            System.out.println("Enter a student age");
            student.setAge(scanner.nextInt());
            System.out.println("Enter a mobile number");
            student.setMbNo(scanner.nextInt());
            System.out.println("Enter an address");
            List<Address> addresses = new ArrayList<>();
            System.out.println("Enter a street name");
            scanner.nextLine(); // to consume any leftover newline character
            String street = scanner.nextLine();
            System.out.println("Enter a complete address");
            String caddress = scanner.nextLine();
            System.out.println("Enter a pincode");
            String pincode = scanner.nextLine();
            Address address = new Address(street, caddress, pincode);
            addresses.add(address);
            student.setAddresses(addresses);
            List<Student> studentList1=FileOpertaions.readStudentFile(studfentFilePath);
            studentList1.add(student);
            FileOpertaions.writeStudentFile(studfentFilePath,studentList1);

            System.out.println("Student information has been added successfully!");
        }
    }

    @Override
    public void getAllStudents() throws IOException {
        System.out.println("The list of students are");
        if(!FileOpertaions.readStudentFile(studfentFilePath).isEmpty()){
            List<Student> readStudenList=FileOpertaions.readStudentFile(studfentFilePath);
            for (Student student1:readStudenList){
                System.out.println(student1.toString());
            }
        }else{
            System.out.println("No students are available");
        }
    }

    @Override
    public Student getStudentById() throws IOException {
        scanner = new Scanner(System.in);
        List<Student> studentList = FileOpertaions.readStudentFile(studfentFilePath);

        if (studentList.isEmpty()) {
            System.out.println("No students are available.");
            return  null;
        }

        System.out.println("Enter the student ID to retrieve details:");
        int studentId = scanner.nextInt();
        for (Student student : studentList) {
            if (student.getId() == studentId) {
                System.out.println("Student details: " + student.toString());
                return  student;
            }
        }

        System.out.println("Student with ID " + studentId + " not found.");
        return null;
    }


    @Override
    public void updateStudentDetail() throws IOException {
        scanner=new Scanner(System.in);
        System.out.println("Enter the student id to update the details");
        int studentId=scanner.nextInt();
        if (!FileOpertaions.readStudentFile(studfentFilePath).isEmpty()) {
            List<Student> studentList=FileOpertaions.readStudentFile(studfentFilePath);
            for (Student student1:studentList) {
                if (student1.getId() == studentId) {
                    System.out.println("Which field do you want to update?");
                    System.out.println("1: Name");
                    System.out.println("2: Age");
                    System.out.println("3: Address");
                    int editChoice = scanner.nextInt();
                    switch (editChoice) {
                        case 1:
                            System.out.println("Enter a new name");
                            student1.setName(scanner.next());
                            System.out.println("Name is updated successfully");
                            break;

                        case 2:
                            System.out.println("Enter a new age");
                            student1.setAge(scanner.nextInt());
                            System.out.println("Age is updated successfully");
                            break;
                        case 3:
                            List<Address> addresses = new ArrayList<>();
                            System.out.println("Street: ");
                            scanner.nextLine();
                            String street = scanner.nextLine();
                            System.out.println("Complete address: ");
                            String caddress = scanner.nextLine();
                            System.out.println("Pincode: ");
                            String pincode = scanner.nextLine();
                            Address address = new Address(street, caddress, pincode);
                            addresses.add(address);
                            student1.setAddresses(addresses);
                            System.out.println("Address is updated successfully");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }

                }
                FileOpertaions.writeStudentFile(studfentFilePath,studentList);
            }
        }else {
            System.out.println("The student list is already empty.");
        }
    }

    @Override
    public void removeStudent() throws IOException {
        scanner=new Scanner(System.in);
        System.out.println("Enter the student id to remove details");
        int studentId = scanner.nextInt();
        List<Student> studentList=FileOpertaions.readStudentFile(studfentFilePath);
        if (!studentList.isEmpty()) {
            boolean removed = studentList.removeIf(student -> student.getId() == studentId);
            if (removed) {
                FileOpertaions.writeStudentFile(studfentFilePath, studentList);
                System.out.println("Student with ID " + studentId + " "+student.getName()+ " has been removed.");
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
            }
        } else {
            System.out.println("The student list is already empty.");
        }
    }

    @Override
    public void borrowBook() throws IOException {
        scanner = new Scanner(System.in);

        List<Book> bookList = FileOpertaions.readBookFile(bookFilePath);
        List<Student> studentList = FileOpertaions.readStudentFile(studfentFilePath);

        Student borrowingStudent=getStudentById();
       /* if(borrowingStudent==null){
            System.out.println("Studenty not found");
        }*/

        System.out.println("Available Books:");
        getAllBooks();

        Book bookToBorrow=getBookById();
        /*if ( bookToBorrow== null) {
            System.out.println("Book not found");
            return;
        }*/
        if (bookToBorrow.getBookCount() <= 0) {
            System.out.println("The book is out of stock and cannot be borrowed.");
            return;
        }
        if (bookToBorrow.getStudents() == null) {
            bookToBorrow.setStudents(new ArrayList<>());
        }
        bookToBorrow.getStudents().add(borrowingStudent.getName());

        if (borrowingStudent.getBooks() == null) {
            borrowingStudent.setBooks(new ArrayList<>());
        }
        borrowingStudent.getBooks().add(bookToBorrow);

        bookToBorrow.setBookCount(bookToBorrow.getBookCount() - 1);

        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookId() == bookToBorrow.getBookId()) {
                bookList.set(i, bookToBorrow);
                break;
            }
        }
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == borrowingStudent.getId()) {
                studentList.set(i, borrowingStudent);
                break;
            }
        }

        FileOpertaions.writeBookFile(bookFilePath, bookList);
        FileOpertaions.writeStudentFile(studfentFilePath, studentList);

        System.out.println("Book borrowed successfully!");
    }

    @Override
    public void addBook() throws IOException {
        scanner=new Scanner(System.in);
        book=new Book();
        System.out.println("Enter Book Details");
        System.out.println("Enter BookId");
        int bid=scanner.nextInt();
        List<Book> bookList=FileOpertaions.readBookFile(bookFilePath);
        boolean idExists = false;
        for (Book book1 : bookList) {
            if (book1.getBookId()==(bid)) {
                idExists = true;
                break;
            }
        }
        if (idExists) {
            System.out.println("The Id is already allocated to another Book");
        } else {
            book.setBookId(bid);
            System.out.println("Enter Book Subject/name");
            book.setSubject(scanner.next());
            System.out.println("Enter the book count");
            book.setBookCount(scanner.nextInt());
            bookList.add(book);
            FileOpertaions.writeBookFile(bookFilePath,bookList);
            System.out.println("Book information has been added successfully!");
        }
    }

    @Override
    public void getAllBooks() throws IOException {
        System.out.println("The list of Books are");

        if(!FileOpertaions.readBookFile(bookFilePath).isEmpty()){
            List<Book> bookList=FileOpertaions.readBookFile(bookFilePath);
            for (Book book1:bookList){
                System.out.println(book1.toString());
            }
        }else{
            System.out.println("No books are available");
        }
    }

    @Override
    public Book getBookById() throws IOException {
        scanner=new Scanner(System.in);
        List<Book> bookList = FileOpertaions.readBookFile(bookFilePath);
        if (bookList.isEmpty()) {
            System.out.println("No books are available.");
            return null;
        }
        System.out.println("Enter the Book ID to retrieve details:");
        int bid = scanner.nextInt();
        for (Book book1 : bookList) {
            if (book1.getBookId() == bid) {
                System.out.println("Book details: " + book1.toString());
                return book1;
            }
        }
        System.out.println("Book with ID " + bid + " not found.");
        return null;

    }

    @Override
    public void updateBookDetail() throws IOException {
        scanner=new Scanner(System.in);
        System.out.println("Enter the book id to update the details");
        int bId=scanner.nextInt();
        if (!FileOpertaions.readBookFile(bookFilePath).isEmpty()) {
            List<Book> bookList=FileOpertaions.readBookFile(bookFilePath);
            for (Book book1:bookList) {
                if (book1.getBookId()==bId) {
                    System.out.println("Enter a new Book name/subject");
                    book1.setSubject(scanner.next());
                }
                FileOpertaions.writeBookFile(bookFilePath,bookList);
            }
        }else {
            System.out.println("The book list is already empty.");
        }
    }

    @Override
    public void removeBook() throws IOException {
        scanner=new Scanner(System.in);
        System.out.println("Enter the Book id to remove details");
        int bId=scanner.nextInt();
        if (!FileOpertaions.readBookFile(bookFilePath).isEmpty()) {
            List<Book> bookList=FileOpertaions.readBookFile(bookFilePath);
            List<Student> studentList=FileOpertaions.readStudentFile(studfentFilePath);
            boolean removed = bookList.removeIf(book -> book.getBookId()==bId);
            if (removed) {
                for(Student student : studentList){
                    student.setBooks(student.getBooks().stream()
                            .filter(book -> book.getBookId()!=bId) // Filter out the book to be removed
                            .collect(Collectors.toList()));
                }
                FileOpertaions.writeBookFile(bookFilePath, bookList);
                FileOpertaions.writeStudentFile(studfentFilePath, studentList);
                System.out.println("Book with ID " + bId + " has been removed, and it has been removed from all students' borrowed lists.");
            } else {
                System.out.println("Book with ID " + bId + " not found.");
            }
        } else {
            System.out.println("The Book list is already empty.");
        }
    }
}
