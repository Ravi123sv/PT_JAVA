
import java.util.Scanner;
public class project {
    public static boolean Ravi(int[] arr, int k){
        int coras = arr[arr.length-1];
        return k == coras;
    }
    public static void display(int[] arr){
        for(int i =0;i< arr.length-1;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.print("?");
        System.out.println();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[][] arr = {
                {2,4,6,8,10},
                {1,2,4,8,16},
                {5,10,15,20,25},
                {3,6,12,24,48},
                {7,14,21,28,35},
                {22,44,44,33,66,66}
        };
        int score =0;
        int in = 0;
        while(true){
            System.out.println("\nNumber series question: ");
            display(arr[in]);
            System.out.println("Enter your answer: ");
            int k = sc.nextInt();
            if(Ravi(arr[in],k)){
                System.out.println("Correct answer ");
                score++;
            }
            else{
                System.out.println("Wrong! correct answer is "+arr[in][arr[in].length-1]);
            }
            System.out.println("Your score: "+score);
            in++;
            if(in == arr.length){
                System.out.println("Completed!! Your Score: "+score);
                break;
            }
        }
    }
}

