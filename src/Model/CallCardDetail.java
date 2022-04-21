package Model;

import java.sql.Date;

public class CallCardDetail {
    private int callCardId;
    private int bookId;
    private Date borrowDate;
    private Date returnDate;
    private float forfeit;

    public int getCallCardId() {
        return callCardId;
    }

    public void setCallCardId(int callCardId) {
        this.callCardId = callCardId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public float getForfeit() {
        return forfeit;
    }

    public void setForfeit(float forfeit) {
        this.forfeit = forfeit;
    }

    public CallCardDetail(int callCardId, int bookId, Date borrowDate, Date returnDate, float forfeit) {
        this.callCardId = callCardId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.forfeit = forfeit;
    }
}
