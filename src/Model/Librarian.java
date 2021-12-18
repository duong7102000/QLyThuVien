package Model;

import java.sql.Date;

public class Librarian {
    private int id;
    private String name;
    private Date birth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Librarian(int id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }
}
