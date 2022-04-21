package Model;

import java.sql.Date;

public class Librarian {
    private String username;
    private String name;
    private Date birth;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Librarian(String username, String name, Date birth) {
        this.username = username;
        this.name = name;
        this.birth = birth;
    }
}
