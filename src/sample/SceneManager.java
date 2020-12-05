package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import sample.Database.Database;
import sample.book.Book;
import sample.book.BookBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * The scene manager class allow the interactivity and display of the UI
 * Creates all style and layout
 */
public class SceneManager extends Scene{
    private static SceneManager sm;
    private static final BorderPane borderPane = new BorderPane();
    private static final Font customFontLittle = new Font("arial",20);
    private static final Font customFontMedium = new Font("arial",30);

    public SceneManager() {
        super(borderPane);
        List<Book> bl = new ArrayList<>();
        setupTopBarLayout();
        loadBooks(Database.getDb().getBooks());
        editPanel();
    }

    /**
     * setup the top bar layout with buttons
     */
    private void setupTopBarLayout(){
        Button addBtn = new Button("Add");
        addBtn.setPrefSize(100,30);
        addBtn.setOnAction(e->editPanel());

        Button deleteBtn = new Button("Delete");
        deleteBtn.setPrefSize(100,30);
        deleteBtn.setOnAction(e-> removePanel());

        HBox topBarLayout = new HBox(10, addBtn, deleteBtn);
        topBarLayout.setPadding(new Insets(10,10,10,10));
        topBarLayout.setStyle("-fx-background-color: #336699");
        borderPane.setTop(topBarLayout);
    }

    /**
     * Loads and displays all books into a table at the center of the screen
     * @param bookList list to load and display into the layout
     */
    public void loadBooks(List<Book> bookList){
        Rectangle rectangle = new Rectangle(600,100);
        rectangle.setFill(Color.DARKRED);

        Label title = new Label("Library");
        title.setFont(new Font("arial", 40));
        title.setTextFill(Color.WHITE);

        StackPane stackPane = new StackPane(rectangle,title);
        stackPane.setAlignment(Pos.CENTER);

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.TOP_CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
        bookList.forEach(b->{

            Label nameLb = new Label(b.getName());
            nameLb.setFont(customFontLittle);
            nameLb.setPrefSize(140,30);

            Label authorLb = new Label(b.getAuthor());
            authorLb.setFont(customFontLittle);
            authorLb.setPrefSize(140,30);

            Label categoryLb = new Label(b.getCategory());
            categoryLb.setFont(customFontLittle);
            categoryLb.setPrefSize(140,30);

            gp.add(nameLb,1,b.getId());
            gp.add(authorLb,2,b.getId());
            gp.add(categoryLb,3,b.getId());
        });

        VBox centralLayout = new VBox(20,stackPane, gp);
        centralLayout.setPadding(new Insets(20,0,0,0));
        centralLayout.setAlignment(Pos.TOP_CENTER);

        borderPane.setCenter(centralLayout);
    }

    /**
     * creates right panel that adds to the current database and update the current center layout
     */
    public void editPanel(){
        VBox ep = new VBox(10);
        ep.setMinWidth(400);
        ep.setPadding(new Insets(20,20,10,20));
        ep.setStyle("-fx-background-color: #dfdfdf");

        // label
        Label mainLbl = new Label("ADD");
        mainLbl.setFont(customFontMedium);

        // label
        Label title = new Label("Title");
        title.setFont(customFontLittle);

        // text field
        TextField bookName = new TextField();

        // label
        Label author = new Label("Author");
        author.setFont(customFontLittle);

        // text field
        TextField bookAuthor = new TextField();

        // label
        Label category = new Label("Category");
        category.setFont(customFontLittle);

        // text field
        TextField bookCategory = new TextField();

        // button
        Button createBtn = new Button("Add");
        createBtn.setOnAction(e->{
            BookBuilder bb = new BookBuilder();
            if (!bookName.getText().isEmpty())
                bb.setName(bookName.getText());
            if (!bookAuthor.getText().isEmpty())
                bb.setAuthor(bookAuthor.getText());
            if (!bookCategory.getText().isEmpty())
                bb.setCategory(bookCategory.getText());
            System.out.println(Database.getDb().getBooks().size());
            bb.setId(Database.getDb().getBooks() == null ? 0 : Database.getDb().getBooks().size());
            Database.getDb().insertBook(bb.build());
            loadBooks(Database.getDb().getBooks());
        });
        createBtn.setPrefWidth(100);
        createBtn.setPrefHeight(40);

        ep.getChildren().addAll(mainLbl ,title, bookName, author, bookAuthor, category,bookCategory, createBtn);
        borderPane.setRight(ep);
    }

    /**
     * Removes custom parameter with custom parameters and updates the main central layout
     */
    public void removePanel(){
        VBox ep = new VBox(10);
        ep.setMinWidth(400);
        ep.setPadding(new Insets(20,20,10,20));
        ep.setStyle("-fx-background-color: #dfdfdf");

        // label
        Label mainLbl = new Label("REMOVE");
        mainLbl.setFont(customFontMedium);

        // label
        Label title = new Label("Title");
        title.setFont(customFontLittle);

        // text field
        TextField bookName = new TextField();

        // label
        Label author = new Label("Author");
        author.setFont(customFontLittle);

        // text field
        TextField bookAuthor = new TextField();

        // label
        Label category = new Label("Category");
        category.setFont(customFontLittle);

        // text field
        TextField bookCategory = new TextField();

        // button
        Button createBtn = new Button("Add");
        createBtn.setOnAction(e->{
            BookBuilder bb = new BookBuilder();
            if (!bookName.getText().isEmpty())
                bb.setName(bookName.getText());
            if (!bookAuthor.getText().isEmpty())
                bb.setAuthor(bookAuthor.getText());
            if (!bookCategory.getText().isEmpty())
                bb.setCategory(bookCategory.getText());
            Database.getDb().deleteBook(bb.build());
            loadBooks(Database.getDb().getBooks());
        });
        createBtn.setPrefWidth(100);
        createBtn.setPrefHeight(40);

        ep.getChildren().addAll(mainLbl, title, bookName, author, bookAuthor, category,bookCategory, createBtn);
        borderPane.setRight(ep);
    }

    /**
     * @return singleton sceneManager instance
     */
    public static SceneManager getSm() {
        if(sm == null)
            sm = new SceneManager();
        return sm;
    }
}
