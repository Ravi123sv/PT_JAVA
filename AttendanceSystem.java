import java.io.*;
import java.util.*;

public class AttendanceSystem {
    static HashMap<String, Integer> map = new HashMap<>();
    static int totalDays = 0;

    public static void main(String[] args) throws Exception {
        load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 Mark 2 Report 3 Percentage 0 Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                totalDays++;
                String name = sc.next();
                map.put(name, map.getOrDefault(name, 0) + 1);
            }
            else if (ch == 2) {
                for (String k : map.keySet())
                    System.out.println(k + " " + map.get(k));
            }
            else if (ch == 3) {
                String n = sc.next();
                int p = map.getOrDefault(n, 0);
                System.out.println((p * 100) / totalDays);
            }
            else break;
        }
        save();
    }

    static void save() throws Exception {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("att.dat"));
        o.writeObject(map);
        o.writeInt(totalDays);
        o.close();
    }
//https://github.com/Ravi123sv-----
    static void load() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("att.dat"));
            map = (HashMap<String, Integer>) o.readObject();
            totalDays = o.readInt();
            o.close();
        } catch (Exception e) {}
    }
}
