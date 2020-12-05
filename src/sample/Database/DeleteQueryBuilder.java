package sample.Database;

/**
 * <p>This class's aim is to build custom  delete query
 * using the Builder pattern to allow more flexible queries.</p>
 */
public class DeleteQueryBuilder{
    private String deleteStatement = "DELETE FROM library WHERE ";
    private boolean isEmpty = true;

    public DeleteQueryBuilder addNameQuery(String s){
        if (!isEmpty)
            deleteStatement += "AND ";
        deleteStatement +=  "name = '" + s + "' ";
        isEmpty = false;
        return this;
    }

    public DeleteQueryBuilder addAuthorQuery(String s){
        if (!isEmpty)
            deleteStatement += "AND ";
        deleteStatement += "author = '" + s + "' ";
        isEmpty = false;
        return this;
    }

    public DeleteQueryBuilder addCategory(String s){
        if (!isEmpty)
            deleteStatement += "AND ";
        deleteStatement += "category = '" + s + "' ";
        isEmpty = false;
        return this;
    }

    public boolean isEmpty(){
        return isEmpty;
    }

    public String build(){
        return deleteStatement;
    }
}
