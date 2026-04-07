import java.io.*;
import java.util.*;

public class SearchEngine {

    static ArrayList<String> data = new ArrayList<>();
    static HashMap<String, Integer> freq = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1 Load File 2 Search 3 Frequency 4 Top Words 5 Exit");
            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                String file = sc.nextLine();
                load(file);
            }
            else if (ch == 2) {
                String key = sc.nextLine();
                search(key);
            }
            else if (ch == 3) {
                String word = sc.nextLine().toLowerCase();
                System.out.println(freq.getOrDefault(word, 0));
            }
            else if (ch == 4) {
                topWords();
            }
            else break;
        }
    }

    static void load(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                data.add(line);

                String[] words = line.toLowerCase().split("\\W+");
                for (String w : words) {
                    if (w.length() == 0) continue;
                    freq.put(w, freq.getOrDefault(w, 0) + 1);
                }
            }

            br.close();
            System.out.println("loaded");
        } catch (Exception e) {
            System.out.println("file error");
        }
    }

    static void search(String key) {
        key = key.toLowerCase();
        ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> rank = new HashMap<>();

        for (String line : data) {
            String lower = line.toLowerCase();

            if (lower.contains(key)) {
                res.add(line);

                int count = countMatch(lower, key);
                rank.put(line, count);
            }
        }

        res.sort((a, b) -> rank.get(b) - rank.get(a));

        for (String s : res) {
            System.out.println(highlight(s, key));
        }
    }

    static int countMatch(String text, String key) {
        int count = 0, i = 0;

        while ((i = text.indexOf(key, i)) != -1) {
            count++;
            i += key.length();
        }
        return count;
    }

    static String highlight(String line, String key) {
        String lower = line.toLowerCase();
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < line.length()) {
            if (i <= line.length() - key.length() &&
                    lower.substring(i, i + key.length()).equals(key)) {

                sb.append("[").append(line.substring(i, i + key.length())).append("]");
                i += key.length();
            } else {
                sb.append(line.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    static void topWords() {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        for (int i = 0; i < Math.min(5, list.size()); i++) {
            System.out.println(list.get(i).getKey() + " " + list.get(i).getValue());
        }
    }
}