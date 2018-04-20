package com.rekik.librarywebappapril;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Book {

   // @NotEmpty
    //@Size(max = 2)
    private String title;

    private String author;

    @NotNull
    @Range(min = 1500,max = 2018)
    private long yearPub;

    private String isbn;

    private boolean available;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    public Book() {
        available = true;
        yearPub = 2018;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getYearPub() {
        return yearPub;
    }

    public void setYearPub(long yearPub) {
        this.yearPub = yearPub;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}