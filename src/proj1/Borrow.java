package proj1;

public class Borrow {
    private int borrowId;
    private int membId;
    private int bookId;
    private String status;

    public Borrow(int borrowId, int membId, int bookId, String status) {
        this.borrowId = borrowId;
        this.membId = membId;
        this.bookId = bookId;
        this.status = status;
    }

    public int getBorrowId() {
        return this.borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getMembId() {
        return this.membId;
    }

    public void setMembId(int membId) {
        this.membId = membId;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
