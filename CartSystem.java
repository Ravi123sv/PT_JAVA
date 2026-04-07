import java.io.*;
import java.util.*;

class Product implements Serializable {
    String name;
    int price, qty;

    Product(String n, int p, int q) {
        name = n;
        price = p;
        qty = q;
    }
}

public class CartSystem {
    static ArrayList<Product> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 Add 2 Remove 3 Update 4 Total 5 Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                list.add(new Product(sc.next(), sc.nextInt(), sc.nextInt()));
            }
            else if (ch == 2) {
                String n = sc.next();
                list.removeIf(p -> p.name.equals(n));
            }
            else if (ch == 3) {
                String n = sc.next();
                int q = sc.nextInt();
                for (Product p : list)
                    if (p.name.equals(n)) p.qty = q;
            }
            else if (ch == 4) {
                int t = 0;
                for (Product p : list) t += p.price * p.qty;
                System.out.println(t);
            }
            else break;
        }
        save();
    }
//https://github.com/Ravi123sv-----
    static void save() throws Exception {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("cart.dat"));
        o.writeObject(list);
        o.close();
    }

    static void load() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("cart.dat"));
            list = (ArrayList<Product>) o.readObject();
            o.close();
        } catch (Exception e) {}
    }
}
