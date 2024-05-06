package com.example.library_management_system;

public class Book {
    // Private member variables for book properties
    private String bookId;
    private String bookTitle;
    private String authorName;
    private String publisherName;
    private String branchId;

    // Getter methods for the book properties
    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getBranchId() {
        return branchId;
    }

    // Setter methods for the book properties
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
