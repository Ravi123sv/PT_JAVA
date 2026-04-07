import java.io.*;
import java.util.*;

class Item implements Serializable {
    String name;
    int price, qty;

    Item(String n, int p, int q) {
        name = n;
        price = p;
        qty = q;
    }
}

public class BillingSystem {
    static ArrayList<Item> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 Add 2 Total 3 Bill 0 Exit");
            int ch = sc.nextInt();

            if (ch == 1)
                list.add(new Item(sc.next(), sc.nextInt(), sc.nextInt()));
            else if (ch == 2) {
                int t = 0;
                for (Item i : list) t += i.price * i.qty;
                System.out.println(t);
            }
            else if (ch == 3) {
                int t = 0;
                for (Item i : list) {
                    int x = i.price * i.qty;
                    t += x;
                    System.out.println(i.name + " " + x);
                }
                t = t - (t * 10 / 100);
                System.out.println("Final: " + t);
            }
            else break;
        }
        save();
    }

    static void save() throws Exception {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("bill.dat"));
        o.writeObject(list);
        o.close();
    }

    static void load() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("bill.dat"));
            list = (ArrayList<Item>) o.readObject();
            o.close();
        } catch (Exception e) {}
    }
}