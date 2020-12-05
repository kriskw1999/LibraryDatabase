package sample.Database;

import sample.book.Book;
import sample.book.BookBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * database singleton class
 * SQLite local database
 */
public class Database {

    private static Database db;
    private static final String location = "jdbc:sqlite:/home/kris/Download/SQLiteJavaProject/lib.db";
    private static final String SQL_INSERT_TABLE = "CREATE TABLE IF NOT EXISTS library(" +
                                                    "id integer PRIMARY KEY," +
                                                    "name text NOT NULL," +
                                                    "author text NOT NULL," +
                                                    "category text NOT NULL," +
                                                    "description text NOT NULL" +
                                                    ");";
    private static final String SQL_GET_ALL_BOOKS = "SELECT * FROM library";
    private static final String SQL_INSERT_BOOK = "INSERT INTO library VALUES(?,?,?,?,?)";

    Database(){
        try (Connection conn = DriverManager.getConnection(location);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate(SQL_INSERT_TABLE);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * query in the database for all of the books if no books are present it returns null
     * @return a list of books contained in the database
     * @throws SQLException
     */
    public List<Book> getBooks() {
        try (Connection conn = DriverManager.getConnection(location);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_GET_ALL_BOOKS)) {
            List<Book> bookList = new ArrayList<>();
            while (rs.next()) {
                Book book = new BookBuilder().setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setAuthor(rs.getString("author"))
                        .setCategory(rs.getString("category"))
                        .setDescription(rs.getString("description"))
                        .build();
                bookList.add(book);
            }
            return bookList;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;        }
    }

    /**
     * insert new book into the database
     * @param book book to insert into the database
     * @return 0 if the element wasn't inserted another value else
     */
    public boolean insertBook(Book book){
        int inserted = 0;
        try(Connection conn = DriverManager.getConnection(location);
            PreparedStatement stmt = conn.prepareStatement(SQL_INSERT_BOOK)){
            stmt.setInt(1 ,book.getId());
            stmt.setString(2, book.getName());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4,book.getCategory());
            stmt.setString(5,book.getDescription());
            inserted = stmt.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return inserted!=0;
    }

    /**
     * query for some correspond results and delete them
     * @param book the book's parts to query and delete
     * @return how many values were deleted
     */
    public int deleteBook(Book book){
        int deleted = 0;
        try(Connection conn = DriverManager.getConnection(location);
            Statement stmt = conn.createStatement()){

            DeleteQueryBuilder dqb = new DeleteQueryBuilder();
            if (!book.getName().equals("NA"))
                dqb = dqb.addNameQuery(book.getName());

            if (!book.getAuthor().equals("NA"))
                dqb = dqb.addAuthorQuery(book.getAuthor());

            if (!book.getCategory().equals("NA"))
                dqb = dqb.addCategory(book.getCategory());

            System.out.println(dqb.build());

            if (!dqb.isEmpty())
                deleted = stmt.executeUpdate(dqb.build());
            else
                return 0;

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return deleted;
    }

    /**
     * require database
     * @return the singleton database instance
     */
    public static Database getDb(){
        if (db == null)
            db = new Database();
        return db;
    }
}