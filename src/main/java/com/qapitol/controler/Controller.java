package com.qapitol.controler;

import com.qapitol.model.Student;
import com.qapitol.services.Service;
import com.qapitol.services.ServiceImplemntation;

import java.io.IOException;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Service services = new ServiceImplemntation();
        System.out.println("Enter choice perform Book or student Management");
        int mainChoice;
        do {
            System.out.println("1: Book actions");
            System.out.println("2: Student actions");
            System.out.println("3: Exit");
            mainChoice = scanner.nextInt();
            switch (mainChoice) {
                case 1:
                    Controller.bookManagement(services,scanner);
                    break;
                case 2:
                    Controller.studentManagement(services,scanner);
                    break;
                case 3:
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Invalid choice, please enter a number between 1 to 3");
                    break;
            }
        } while (mainChoice != 3);
    }

        public static void bookManagement(Service services,Scanner scanner) throws IOException {
            System.out.println("Enter a choice to perform Book Management");
            int bchoice=0;
            do {
                System.out.println("1. Add Book");
                System.out.println("2: Get all book Details");
                System.out.println("3: Get book details by using Id");
                System.out.println("4: Update Book");
                System.out.println("5: Remove Book");
                System.out.println("6: Exit");

                bchoice = scanner.nextInt();
                scanner.nextLine();
                switch (bchoice) {
                    case 1:
                        services.addBook();
                        break;
                    case 2:
                        services.getAllBooks();
                        break;
                    case 3:
                        services.getBookById();
                        break;
                    case 4:
                        services.updateBookDetail();
                        break;
                    case 5:
                        services.removeBook();
                        break;
                    case 6:
                        System.out.println("Exiting from Book actions");
                        return;
                    default:
                        System.out.println("Invalid choice, please enter a number between 1 and 6.");
                        break;
                }
            }while(bchoice!=6);
        }

        public static void studentManagement(Service services, Scanner scanner) throws IOException {
            System.out.println("Enter choice to perform Student management");
            int schoice = 0;
            do {

                System.out.println("1. Add Student");
                System.out.println("2: List All students");
                System.out.println("3: get Student By Id");
                System.out.println("4. Update Student Details");
                System.out.println("5. Remove Student");
                System.out.println("6. Barrow Books");
                System.out.println("7: Exit");
                schoice = scanner.nextInt();
                scanner.nextLine();
                switch (schoice) {
                    case 1:
                        services.addStudent();
                        break;
                    case 2:
                        services.getAllStudents();
                        break;
                    case 3:
                        services.getStudentById();
                        break;
                    case 4:
                        services.updateStudentDetail();
                        break;
                    case 5:
                        services.removeStudent();
                        break;
                    case 6:
                        services.borrowBook();
                        break;
                    case 7:
                        System.out.println("Exiting from student action");
                        return;
                    default:
                        System.out.println("Invalid choice, please enter a number between 1 to 7");
                        break;
                }
            } while (schoice != 7);
        }
}
