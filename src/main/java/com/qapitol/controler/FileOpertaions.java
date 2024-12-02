package com.qapitol.controler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qapitol.model.Book;
import com.qapitol.model.Student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileOpertaions {
    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
    }

    public static List<Student> readStudentFile(String file) throws IOException {
        File file1=new File(file);
        if (!file1.exists() || file1.length() == 0) {
            return new ArrayList<Student>(); // Return an empty list if the file doesn't exist or is empty
        }
        List<Student> listOfStudent =	mapper.readValue(file1,new TypeReference<List<Student>>(){});
        return listOfStudent;
    }
    public static List<Book> readBookFile(String file) throws IOException {
        File file1=new File(file);
        if (!file1.exists() || file1.length() == 0) {
            return new ArrayList<Book>(); // Return an empty list if the file doesn't exist or is empty
        }
        List<Book> listOfBooks= mapper.readValue(file1, new TypeReference<List<Book>>() {});
        return listOfBooks;
    }
    public static void writeBookFile(String file,List<Book> books) throws IOException {
        File file1=new File(file);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file1,books);
    }
    public static void writeStudentFile(String file,List<Student> students) throws IOException {
        File file1=new File(file);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file1,students);
    }

}
