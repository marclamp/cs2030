import java.util.Scanner;

class SampleProg{
    public static void main(String[] args){
        System.out.println("Key in an integer and a double:");

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        double b = sc.nextDouble();

        if (a < 0 || b < 0){
            return;
        }
        
        double c = Math.pow(a,b);
        int d = gcdBF(a, b);

        int[] e = fib(a, (int) b);

        System.out.format("a: %d%nb: %f%na^b: %f%ngcd = %d%n", a, b, c, d); 
        
        String fibs = "[";

        for (int i = 0; i < e.length; ++i){
           if(i != 0){
               fibs += ", ";
           }
           fibs += e[i];
        }
        fibs += "]";
        
        System.out.println("Fibs = " + fibs);
    
    }
    
   static int gcdBF(int n1, double n2){
	    int gcd = 1;
        n2 = Math.floor(n2);
    	for (int i = 1; i <= n1 && i <= n2; i++) {
        	if (n1 % i == 0 && n2 % i == 0) {
            	gcd = i;
	    	}
        }
   return gcd;
   }

   static int[] fib(int n1, int n2){
       int[] result = new int[n2];
       int i = 0;
       while(i < n2){
           if(isFib(n1)){
               result[i++] = n1;
           }
           n1++;
       }
       return result;
   }

    static boolean isFib(int n){
        return isPerfectSquare(5 * n * n + 4) || isPerfectSquare(5 * n * n - 4);
    }

    static boolean isPerfectSquare(int n){
        int s = (int) Math.sqrt(n);
        return s*s == n;
    }
}
