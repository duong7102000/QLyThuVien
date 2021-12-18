package Model;

import java.sql.Date;

public class CallCard {
    private int id;
    private int studentId;
    private int librarianId;
    private Date startDate;
    private Date endDate;
    private float deposit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public CallCard(int id, int studentId, int librarianId, Date startDate, Date endDate, float deposit) {
        this.id = id;
        this.studentId = studentId;
        this.librarianId = librarianId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
    }
}
