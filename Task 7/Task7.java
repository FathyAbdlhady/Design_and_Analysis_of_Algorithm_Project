import java.util.Random;
import java.util.Scanner;

public class Task7 {

    public static int getspot(int i,int j,int n){
        if( i < 1 )
        {
            return j;
        }
        else if(j > n)
        {
            return i;
        }
        else
        {
            Random rand = new Random();
            int randomInt = (rand.nextInt(1000)) >= 499 ? i:j;
            return randomInt;
        }
    }
    // Dynamic algorithm
    public static int hittingAMovingTarget(int n, int currentHidingSpot) {
        
        int[] dp = new int[n + 1];
        
        
        for (int i = 1; i <= n; i++) {
            dp[i] = 0;
        }

        int k = 1;
        int count = n-1;
        int shotspot = 2;
        for (int i = 0; i < n+2; i++)
        {
            
            if(count == 1)
            {
                dp[shotspot] += 1;
                k = -1;
                count = n-1;
            }
            else
            {
                dp[shotspot] = Math.max(dp[shotspot-1]+1, dp[shotspot+1]+1);
            }
            System.out.println("shot = " + shotspot + ", hiding spot = " + currentHidingSpot);
            if(shotspot == currentHidingSpot)
            {
                break;
            }
            count--; 
            currentHidingSpot =  getspot(currentHidingSpot-1, currentHidingSpot+1,n);
            if(( (shotspot != 2) && (shotspot != n-1) ) || (count != 1))
            {
                shotspot += k;
            }    
        }
       
        return dp[currentHidingSpot];
    }

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of hiding spots: ");
        int n = input.nextInt();
        System.out.print("Enter current Hiding Spot: ");
        int currentHidingSpot = input.nextInt();
        input.close();

        System.out.println("Maximum shots needed to guarantee hitting the target: " + hittingAMovingTarget(n,currentHidingSpot));


    }
}


class GreedySolution {

    public static int getspot(int i,int j,int n){
        if( i < 1 )
        {
            return j;
        }
        else if(j > n)
        {
            return i;
        }
        else
        {
            Random rand = new Random();
            int randomInt = (rand.nextInt(1000)) >= 499 ? i:j;
            return randomInt;
        }
    }

    // greedy algorithm
    public static void hittingAMovingTarget2(int n,int hidingSpot)
    {
        int count = n-2;
        int k = 1;
        int shot = 2;
        while( shot != hidingSpot)
        {
            System.out.println("shot = " + shot + ", hiding spot = " + hidingSpot);
            hidingSpot =  getspot(hidingSpot-1, hidingSpot+1,n);
            --count;
            if(count == 0)
            {
                k = -1;
                continue;
            }
            else
            {
                shot += k;
            }   
        }
        System.out.println("shot = " + shot + ", hiding spot = " + hidingSpot);
    }
}
