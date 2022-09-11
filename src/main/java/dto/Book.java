package dto;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    private String author;

    private boolean isIssued;

    private String genre;

    private Long copiesOfBook;

    public Book(Long id, String title, String author, boolean isIssued, String genre, Long copiesOfBook) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = isIssued;
        this.genre = genre;
        this.copiesOfBook = copiesOfBook;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public Long getCopiesOfBook() {
        return copiesOfBook;
    }

    public void setCopiesOfBook(Long copiesOfBook) {
        this.copiesOfBook = copiesOfBook;
    }

    public void setId(Long id) {
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

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isIssued=" + isIssued +
                ", genre='" + genre + '\'' +
                ", copiesOfBook=" + copiesOfBook +
                '}';
    }
}


