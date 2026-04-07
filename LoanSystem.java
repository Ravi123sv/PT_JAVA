import java.io.*;
import java.util.*;

class Loan implements Serializable {
    int id;
    String name;
    double amount;
    double rate;
    int time;
    boolean approved;
    double paid;

    Loan(int id, String name, double amount, double rate, int time) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.rate = rate;
        this.time = time;
        this.approved = false;
        this.paid = 0;
    }

    double emi() {
        double r = rate / 12 / 100;
        return (amount * r) / (1 - Math.pow(1 + r, -time));
    }

    void pay(double amt) {
        paid += amt;
    }

    void show() {
        System.out.println(id + " " + name + " " + amount + " " + (approved ? "APPROVED" : "PENDING"));
    }

    void details() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Amount: " + amount);
        System.out.println("Rate: " + rate);
        System.out.println("Months: " + time);
        System.out.println("EMI: " + emi());
        System.out.println("Paid: " + paid);
        System.out.println("Status: " + (approved ? "APPROVED" : "PENDING"));
    }
}

public class LoanSystem {

    static ArrayList<Loan> list = new ArrayList<>();
    static final String FILE = "loan.dat";

    public static void main(String[] args) {
        load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1 Apply 2 Approve 3 View All 4 Details 5 Pay EMI 6 Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                int id = sc.nextInt();
                String name = sc.next();
                double amt = sc.nextDouble();
                double rate = sc.nextDouble();
                int time = sc.nextInt();

                list.add(new Loan(id, name, amt, rate, time));
            }
            else if (ch == 2) {
                int id = sc.nextInt();
                for (Loan l : list)
                    if (l.id == id) l.approved = true;
            }
            else if (ch == 3) {
                for (Loan l : list) l.show();
            }
            else if (ch == 4) {
                int id = sc.nextInt();
                for (Loan l : list)
                    if (l.id == id) l.details();
            }
            else if (ch == 5) {
                int id = sc.nextInt();
                double amt = sc.nextDouble();
                for (Loan l : list)
                    if (l.id == id && l.approved) l.pay(amt);
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
        } catch (Exception e) {
            System.out.println("save fail");
        }
    }

    static void load() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(FILE));
            list = (ArrayList<Loan>) o.readObject();
            o.close();
        } catch (Exception e) {
            list = new ArrayList<>();
        }
    }
}