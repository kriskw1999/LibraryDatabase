package sample.book;

/**
 * book class
 * each row of the database contains dates of one book
 */
public class Book {
    private int id;
    private String name;
    private String author;
    private String category;
    private String description;

    Book(int id, String name, String author, String category, String description){
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
