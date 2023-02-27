package proj1;

public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String gener;
    private int quantity;
    private String imageBook;

    public Book() {
    }

    public Book(int bookId,String bookName, String author, String gener, int quantity,String imageBook) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.gener = gener;
        this.quantity = quantity;
        this.imageBook = imageBook;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGener() {
        return this.gener;
    }

    public void setGener(String gener) {
        this.gener = gener;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageBook() {
        return this.imageBook;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }

    @Override
    public String toString() {
        return "{" +
            " bookName='" + getBookName() + "'" +
            ", author='" + getAuthor() + "'" +
            ", gener='" + getGener() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }
}
