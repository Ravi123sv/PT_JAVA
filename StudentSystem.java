import java.io.*;
import java.util.*;

class Student implements Serializable {
    int id;
    String name;

    Student(int i, String n) {
        id = i;
        name = n;
    }

    void show() {
        System.out.println(id + " " + name);
    }
}

public class StudentSystem {
    static ArrayList<Student> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 Add 2 Update 3 Search 4 Delete 5 All 0 Exit");
            int ch = sc.nextInt();

            if (ch == 1) list.add(new Student(sc.nextInt(), sc.next()));
            else if (ch == 2) {
                int id = sc.nextInt();
                String n = sc.next();
                for (Student s : list) if (s.id == id) s.name = n;
            }
            else if (ch == 3) {
                int id = sc.nextInt();
                for (Student s : list) if (s.id == id) s.show();
            }
            else if (ch == 4) {
                int id = sc.nextInt();
                list.removeIf(s -> s.id == id);
            }
            else if (ch == 5) {
                for (Student s : list) s.show();
            }
            else break;
        }
        save();
    }

    static void save() throws Exception {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("s.dat"));
        o.writeObject(list);
        o.close();
    }

    static void load() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("s.dat"));
            list = (ArrayList<Student>) o.readObject();
            o.close();
        } catch (Exception e) {}
    }
}