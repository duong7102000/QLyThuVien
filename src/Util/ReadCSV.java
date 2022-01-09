package Util;

import Model.Book;
import Model.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
    private static final String COMMA_DELIMITER = ","; // Split by comma

    public static List<Book> csvListBook(String csvPath) {
        List<Book> listBook = new ArrayList<>();
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(csvPath));
            while ((line = br.readLine()) != null) {
                try{
                    String name = parseCsvLine(line).get(0);
                    String artist = parseCsvLine(line).get(1);
                    String content = parseCsvLine(line).get(2);
                    String major = parseCsvLine(line).get(3);
                    int total = Integer.parseInt(parseCsvLine(line).get(4));
                    int remain = Integer.parseInt(parseCsvLine(line).get(5));
                    Book book = new Book(name, artist, content, major, total, remain);
                    listBook.add(book);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException Exception) {
                Exception.printStackTrace();
            }
        }
        return listBook;
    }

    public static List<Student> csvListStudent(String csvPath) {
        List<Student> listStudent = new ArrayList<>();
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(csvPath));
            while ((line = br.readLine()) != null) {
                try{
                    String name = parseCsvLine(line).get(0);
                    Date birth = Date.valueOf(parseCsvLine(line).get(1));
                    String address = parseCsvLine(line).get(2);
                    String major = parseCsvLine(line).get(3);
                    String class_ = parseCsvLine(line).get(4);
                    String username = parseCsvLine(line).get(5);
                    Student std = new Student(username, name, birth, address, major, class_);
                    listStudent.add(std);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException Exception) {
                Exception.printStackTrace();
            }
        }
        return listStudent;
    }

    public static List<String> parseCsvLine(String csvLine) {
        List<String> result = new ArrayList<String>();
        if (csvLine != null) {
            String[] splitData = csvLine.split(COMMA_DELIMITER);
            for (int i = 0; i < splitData.length; i++) {
                result.add(splitData[i]);
            }
        }
        return result;
    }
}
