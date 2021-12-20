package Model;

public class Book {
    private int id;
    private String name;
    private String artist;
    private String content;
    private String major;
    private int total;
    private int remain;

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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public Book(int id, String name, String artist, String content, String major, int total, int remain) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.content = content;
        this.major = major;
        this.total = total;
        this.remain = remain;
    }

    public Book(String name, String artist, String content, String major, int total, int remain) {
        this.name = name;
        this.artist = artist;
        this.content = content;
        this.major = major;
        this.total = total;
        this.remain = remain;
    }
}
