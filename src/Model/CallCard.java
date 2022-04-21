package Model;

import java.sql.Date;

public class CallCard {
    private int id;
    private String studentUsername;
    private String librarianUsername;
    private Date startDate;
    private Date endDate;
    private float deposit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public String getLibrarianUsername() {
        return librarianUsername;
    }

    public void setLibrarianUsername(String librarianUsername) {
        this.librarianUsername = librarianUsername;
    }

    public CallCard(int id, String studentUsername, String librarianUsername, Date startDate, Date endDate, float deposit) {
        this.id = id;
        this.studentUsername = studentUsername;
        this.librarianUsername = librarianUsername;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
    }

    public CallCard(String studentUsername, String librarianUsername, Date startDate, Date endDate, float deposit) {
        this.studentUsername = studentUsername;
        this.librarianUsername = librarianUsername;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
    }
}
