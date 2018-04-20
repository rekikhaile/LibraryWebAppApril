package com.rekik.librarywebappapril;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Scanner;

@Service
public class LibraryService {
    Scanner reader = new Scanner(System.in);
    ArrayList<Book> bookList = new ArrayList<>();
    ArrayList<Book> availableBookList = new ArrayList<>();
    ArrayList<Book> borrowedBookList = new ArrayList<>();
    Book book;
    boolean bookDone = true;
    String bookAnswer = "";

    public void showMenu() {
        System.out.println("1 : List books\n" +
                "\n" +
                "2 : Add a book\n" +
                "\n" +
                "3 : Borrow a book\n" +
                "\n" +
                "4 : Return a book");
        System.out.println();
    }

    public void listBooks() {
        if (!bookList.isEmpty()) {
            for (Book eachBook : bookList
                    ) {
                System.out.println(eachBook.getTitle());
                System.out.println(eachBook.getAuthor());
                System.out.println(eachBook.getIsbn());
                System.out.println(eachBook.getYearPub());
                //System.out.println(eachBook.isBorrowed());
                String status = eachBook.isAvailable() == true ? "Is Available" : "Is Borrowed";
                System.out.println("Available or borrowed? Status: " + status);
            }
        } else System.out.println("There are no books available");
    }


    public void AddBook() {

        do {
            book = new Book();
            System.out.println("Enter title of the book");
            book.setTitle(reader.nextLine());

            System.out.println("Enter author of the book");
            book.setAuthor(reader.nextLine());

            System.out.println("Enter year  of the publication");
            book.setYearPub(reader.nextLong());
            reader.nextLine();

            System.out.println("Enter ISBN number");
            book.setIsbn(reader.nextLine());

            System.out.println("Is the book available?(answer available/yes or borrowed/no)");
            String borrowed = reader.nextLine();

            if(borrowed.equalsIgnoreCase("available") || borrowed.equalsIgnoreCase("yes")){
                book.setAvailable(true);
            }else book.setAvailable(false);

            bookList.add(book);

            if(book.isAvailable()==true) {
                availableBookList.add(book);
            }

            System.out.println("Do you want to add another book");
            bookAnswer = reader.nextLine();

            if (bookAnswer.equalsIgnoreCase("no") || bookAnswer.equalsIgnoreCase("n"))
                bookDone = false;
            else bookDone = true;

        } while (bookDone);
    }

    public void borrowBook() {

        if(!availableBookList.isEmpty()) {
            for (Book eachBook : availableBookList
                    ) {

                System.out.println(eachBook.getTitle());
                System.out.println(eachBook.getAuthor());
                System.out.println(eachBook.getIsbn());
                System.out.println(eachBook.getYearPub());
                //System.out.println(eachBook.isBorrowed());
                String status = eachBook.isAvailable() == true ? "Is Available" : "Is Borrowed";
                System.out.println("Available or borrowed? Status: " + status);


            }
        } else System.out.println("There are no books available to borrow");



        if(!availableBookList.isEmpty()) {
            System.out.println("Enter the title of the book you want to borrow?");
            String borrowname = reader.nextLine();
            for (Book eachBook : bookList
                    ) {

                if (borrowname.equalsIgnoreCase(eachBook.getTitle())) {
                    eachBook.setAvailable(false);
                    availableBookList.remove(eachBook);
                    borrowedBookList.add(eachBook);
                    System.out.println("You have borrowed " + eachBook.getTitle() + " by " + eachBook.getAuthor());
                }
            }
        }
    }

    public void returnBook() {

        if(!borrowedBookList.isEmpty()) {
            for (Book eachBook : borrowedBookList
                    ) {

                System.out.println(eachBook.getTitle());
                System.out.println(eachBook.getAuthor());
                System.out.println(eachBook.getIsbn());
                System.out.println(eachBook.getYearPub());
                //System.out.println(eachBook.isBorrowed());
                String status = eachBook.isAvailable() == true ? "Is Available" : "Is Borrowed";
                System.out.println("Available or borrowed? Status: " + status);


            }
        } else System.out.println("You have not borrowed any book");



        if(!borrowedBookList.isEmpty()) {
            System.out.println("Enter the title of the book you want to return?");
            String returnname = reader.nextLine();
            for (Book eachBook : bookList
                    ) {

                if (returnname.equalsIgnoreCase(eachBook.getTitle())) {
                    eachBook.setAvailable(true);
                    borrowedBookList.remove(eachBook);
                    availableBookList.add(eachBook);
                    System.out.println("You have returned " + eachBook.getTitle() + " by " + eachBook.getAuthor());
                }
            }
        }
    }

}
