import java.io.*;
import java.util.*;

public class LogAnalyzer {

    static ArrayList<String> logs = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        loadLogs();

        while (true) {
            System.out.println("\n1 Show All 2 Count Errors 3 Filter Keyword 4 Search Date 5 Summary 0 Exit");
            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                for (String l : logs) System.out.println(l);
            }
            else if (ch == 2) {
                int err = 0, warn = 0;

                for (String l : logs) {
                    if (l.contains("ERROR")) err++;
                    if (l.contains("WARN")) warn++;
                }

                System.out.println("ERROR: " + err);
                System.out.println("WARN: " + warn);
            }
            else if (ch == 3) {
                String key = sc.nextLine();

                for (String l : logs)
                    if (l.toLowerCase().contains(key.toLowerCase()))
                        System.out.println(l);
            }
            else if (ch == 4) {
                String date = sc.nextLine();

                for (String l : logs)
                    if (l.startsWith(date))
                        System.out.println(l);
            }
            else if (ch == 5) {
                HashMap<String, Integer> map = new HashMap<>();

                for (String l : logs) {
                    String[] parts = l.split(" ");
                    if (parts.length > 2) {
                        String type = parts[2];
                        map.put(type, map.getOrDefault(type, 0) + 1);
                    }
                }

                for (String k : map.keySet())
                    System.out.println(k + " " + map.get(k));
            }
            else break;
        }
    }
//https://github.com/Ravi123sv-----
    static void loadLogs() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("log.txt"));
            String line;

            while ((line = br.readLine()) != null)
                logs.add(line);

            br.close();
        } catch (Exception e) {
            System.out.println("no file");
        }
    }
}
