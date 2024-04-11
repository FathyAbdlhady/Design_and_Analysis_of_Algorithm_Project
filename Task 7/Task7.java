import java.util.Random;
import java.util.Scanner;

public class Task7 {

    // Dynamic algorithm
    public static int hittingAMovingTarget(int n, int currentSpot) {
        // Base case: Only one spot left, target is guaranteed to be there
        if (n == 1) {
            return 0; // No shot needed
        }

        // Initialize DP table for minimum shots needed from each spot
        int[] dp = new int[n + 1];

        // Improved initialization (consider target movement and edge cases)
        for (int i = 1; i <= n; i++) {
            if (i == 1 || i == n) {
                // Edge cases (starting at first or last spot) - target might be on the other end in one shot
                dp[i] = 1;
            } else {
                dp[i] = n; // Initialize with worst-case scenario (n shots) for other spots
            }
        }

        // Fill the DP table (iterative approach)
        for (int i = n - 1; i >= 1; i--) {
            // Minimum shots needed from current spot (consider target movement)
            for (int j = i - 1; j <= i + 1 && j >= 1 && j <= n; j++) {
                // Skip checking the current spot itself
                if (j == i) {
                    continue;
                }

                // Minimum shots needed from adjacent spot (considering target movement)
                int nextShot = dp[j];

                // One shot needed from this spot + minimum shots from adjacent spot
                int adjustedShot = 1 + nextShot;

                // Update dp[i] if reaching the target from adjacent spot takes fewer shots
                dp[i] = Math.min(dp[i], adjustedShot);
            }
        }

        // Return the minimum number of shots needed from the current spot
        return dp[currentSpot];
    }


    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of hiding spots: ");
        int n = input.nextInt();
        System.out.print("Enter current Hiding Spot: ");
        int currentHidingSpot = input.nextInt();
        input.close();

        /*hittingAMovingTarget(n,currentHidingSpot);*/

        
        int minShots = hittingAMovingTarget(n,currentHidingSpot);
        System.out.println("Maximum shots needed to guarantee hitting the target: " + minShots);


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
