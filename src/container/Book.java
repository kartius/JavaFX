package container;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by dell on 09.03.17.
 */
public class Book {
    private SimpleStringProperty name;
    private SimpleStringProperty company;
    private SimpleStringProperty account;

    public Book() {
    }

    public Book(String s1, String s2, String s3) {
        name = new SimpleStringProperty(s1);
        company = new SimpleStringProperty(s2);
        account= new SimpleStringProperty(s3);
    }

    public String getTitle() {
        return name.get();
    }

    public void setTitle(String s) {
        name.set(s);
    }

    public String getAuthor() {
        return company.get();
    }

    public void setAuthor(String s) {
        company.set(s);
    }

    public String getAccount() {
        return account.get();
    }

    public SimpleStringProperty accountProperty() {
        return account;
    }

    public void setAccount(String account) {
        this.account.set(account);
    }

    @Override
    public String toString() {
        return (name.get() + ", by " + company.get());
    }

}
