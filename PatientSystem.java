import java.io.*;
import java.util.*;

class Patient implements Serializable {
    int id;
    String name;
    String record;
    ArrayList<String> history = new ArrayList<>();

    Patient(int id, String name, String record) {
        this.id = id;
        this.name = name;
        this.record = record;
        history.add("Created: " + record);
    }

    void update(String r) {
        record = r;
        history.add("Updated: " + r);
    }

    void show() {
        System.out.println(id + " " + name + " " + record);
    }

    void showHistory() {
        for (String h : history) System.out.println(h);
    }
}

public class PatientSystem {
    static ArrayList<Patient> list = new ArrayList<>();
    static final String FILE = "patient.dat";

    public static void main(String[] args) {
        load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 Add 2 Search 3 Update 4 Delete 5 History 0 Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                list.add(new Patient(sc.nextInt(), sc.next(), sc.next()));
            }
            else if (ch == 2) {
                int id = sc.nextInt();
                for (Patient p : list) if (p.id == id) p.show();
            }
            else if (ch == 3) {
                int id = sc.nextInt();
                String r = sc.next();
                for (Patient p : list) if (p.id == id) p.update(r);
            }
            else if (ch == 4) {
                int id = sc.nextInt();
                list.removeIf(p -> p.id == id);
            }
            else if (ch == 5) {
                int id = sc.nextInt();
                for (Patient p : list) if (p.id == id) p.showHistory();
            }
            else break;
        }
        save();
    }

    static void save() {
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(FILE));
            o.writeObject(list);
            o.close();
        } catch (Exception e) {}
    }

    static void load() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(FILE));
            list = (ArrayList<Patient>) o.readObject();
            o.close();
        } catch (Exception e) {}
    }
}