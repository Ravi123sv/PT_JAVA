import java.io.*;
import java.util.*;

class Employee implements Serializable {
    int id;
    String name;
    int salary;

    Employee(int i, String n, int s) {
        id = i;
        name = n;
        salary = s;
    }

    void show() {
        System.out.println(id + " " + name + " " + salary);
    }
}

public class EmployeeSystem {
    static ArrayList<Employee> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 Add 2 Update 3 Delete 4 Search 5 All 0 Exit");
            int ch = sc.nextInt();

            if (ch == 1)
                list.add(new Employee(sc.nextInt(), sc.next(), sc.nextInt()));
            else if (ch == 2) {
                int id = sc.nextInt();
                int sal = sc.nextInt();
                for (Employee e : list) if (e.id == id) e.salary = sal;
            }
            else if (ch == 3) {
                int id = sc.nextInt();
                list.removeIf(e -> e.id == id);
            }
            else if (ch == 4) {
                int id = sc.nextInt();
                for (Employee e : list) if (e.id == id) e.show();
            }
            else if (ch == 5) {
                for (Employee e : list) e.show();
            }
            else break;
        }
        save();
    }
//https://github.com/Ravi123sv-----
    static void save() throws Exception {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("emp.dat"));
        o.writeObject(list);
        o.close();
    }

    static void load() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("emp.dat"));
            list = (ArrayList<Employee>) o.readObject();
            o.close();
        } catch (Exception e) {}
    }
}
