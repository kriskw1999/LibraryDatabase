package sample.book;

/**
 * Builder book class that allow a build pattern
 */
public class BookBuilder {
    private int id = 0;
    private String name = "NA";
    private String author = "NA";
    private String category = "NA";
    private String description = "NA";

    public BookBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public BookBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public BookBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Book build(){
        return new Book(id,name,author,category,description);
    }
}
