import java.io.*;
import java.util.*;

class Appointment implements Serializable {
    String time;
    String name;

    Appointment(String t, String n) {
        time = t;
        name = n;
    }
}

public class AppointmentSystem {
    static ArrayList<Appointment> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 Book 2 Cancel 3 View 0 Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                String t = sc.next();
                boolean ok = true;

                for (Appointment a : list)
                    if (a.time.equals(t)) ok = false;

                if (ok) list.add(new Appointment(t, sc.next()));
                else System.out.println("Busy");
            }
            else if (ch == 2) {
                String t = sc.next();
                list.removeIf(a -> a.time.equals(t));
            }
            else if (ch == 3) {
                for (Appointment a : list)
                    System.out.println(a.time + " " + a.name);
            }
            else break;
        }
        save();
    }
//https://github.com/Ravi123sv-----
    static void save() throws Exception {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("a.dat"));
        o.writeObject(list);
        o.close();
    }

    static void load() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("a.dat"));
            list = (ArrayList<Appointment>) o.readObject();
            o.close();
        } catch (Exception e) {}
    }
}
