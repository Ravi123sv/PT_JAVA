import java.io.*;
import java.util.*;

class Candidate implements Serializable {
    String name;
    int votes;

    Candidate(String n) {
        name = n;
    }
}

public class VotingSystem {
    static HashMap<String, Candidate> map = new HashMap<>();
    static HashSet<String> voted = new HashSet<>();

    public static void main(String[] args) throws Exception {
        load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 Add 2 Vote 3 Result 4 Winner 5 Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                String n = sc.next();
                map.put(n, new Candidate(n));
            }
            else if (ch == 2) {
                String voter = sc.next();
                String cand = sc.next();

                if (voted.contains(voter)) continue;

                if (map.containsKey(cand)) {
                    map.get(cand).votes++;
                    voted.add(voter);
                }
            }
            else if (ch == 3) {
                for (Candidate c : map.values())
                    System.out.println(c.name + " " + c.votes);
            }
            else if (ch == 4) {
                Candidate w = null;
                for (Candidate c : map.values())
                    if (w == null || c.votes > w.votes) w = c;
                if (w != null) System.out.println(w.name);
            }
            else break;
        }
        save();
    }
//https://github.com/Ravi123sv-----
    static void save() throws Exception {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("vote.dat"));
        o.writeObject(map);
        o.writeObject(voted);
        o.close();
    }

    static void load() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("vote.dat"));
            map = (HashMap<String, Candidate>) o.readObject();
            voted = (HashSet<String>) o.readObject();
            o.close();
        } catch (Exception e) {}
    }
}
