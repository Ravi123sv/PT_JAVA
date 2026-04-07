import java.io.*;
import java.util.*;

class Account implements Serializable {
    int accNo;
    String name;
    double balance;
    ArrayList<String> history = new ArrayList<>();

    Account(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
        history.add("Created with " + balance);
    }

    void deposit(double amt) {
        balance += amt;
        history.add("Deposit " + amt);
    }

    void withdraw(double amt) {
        if (amt > balance) {
            history.add("Failed Withdraw " + amt);
            System.out.println("Insufficient balance");
            return;
        }
        balance -= amt;
        history.add("Withdraw " + amt);
    }

    void showBalance() {
        System.out.println("Balance: " + balance);
    }

    void showHistory() {
        for (String s : history) System.out.println(s);
    }
}

public class BankSystem {

    static HashMap<Integer, Account> map = new HashMap<>();
    static final String FILE = "bank.dat";

    public static void main(String[] args) {
        load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1 Create 2 Deposit 3 Withdraw 4 Balance 5 History 6 Exit");
            int ch = sc.nextInt();

            try {
                if (ch == 1) {
                    int id = sc.nextInt();
                    String name = sc.next();
                    double bal = sc.nextDouble();

                    if (map.containsKey(id)) {
                        System.out.println("Account exists");
                        continue;
                    }

                    map.put(id, new Account(id, name, bal));
                }
                else if (ch == 2) {
                    int id = sc.nextInt();
                    double amt = sc.nextDouble();

                    Account a = map.get(id);
                    if (a != null) a.deposit(amt);
                    else System.out.println("Not found");
                }
                else if (ch == 3) {
                    int id = sc.nextInt();
                    double amt = sc.nextDouble();

                    Account a = map.get(id);
                    if (a != null) a.withdraw(amt);
                    else System.out.println("Not found");
                }
                else if (ch == 4) {
                    int id = sc.nextInt();
                    Account a = map.get(id);
                    if (a != null) a.showBalance();
                    else System.out.println("Not found");
                }
                else if (ch == 5) {
                    int id = sc.nextInt();
                    Account a = map.get(id);
                    if (a != null) a.showHistory();
                    else System.out.println("Not found");
                }
                else break;

            } catch (Exception e) {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }

        save();
    }

    static void save() {
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(FILE));
            o.writeObject(map);
            o.close();
        } catch (Exception e) {
            System.out.println("Save error");
        }
    }

    static void load() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(FILE));
            map = (HashMap<Integer, Account>) o.readObject();
            o.close();
        } catch (Exception e) {
            map = new HashMap<>();
        }
    }
}