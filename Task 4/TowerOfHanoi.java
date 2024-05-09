
package towerofhanoi;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TowerOfHanoi{
    static int moves = 0;
    static int[] optimalK=null;
    public static void main(String[] args) {
        // TODO code application logic here
        int n = 8; // Number of disks
        Disk[] disks =new Disk[n];
        for(int i=0;i<n;i++){
            disks[i]= new Disk(i+1);
        }

        hanoi4(disks,'A','B','C','D');
        System.out.println("no of moves = " + moves);
    }
    
    /*
    * Description : Function to print the number of moves required to solve the puzzle
    * Input : number of disks, intial peg, last peg
    * Output : none
    */
    public static void printMove(int disk_num ,char from ,char to){
        System.out.println("Move Disk "+ disk_num+" from peg "+from+" to peg "+to);
        moves++;
    }
    
    /*
    * Description : Function to save the number of moves to solve the puzzle in an array
    * Input : number of disks
    * Output : none
    */
    public static void getOptimalDiskNum(int n) {

        int[] dp = new int[n + 1];
        optimalK = new int[n + 1];

        // Base cases for dp array
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 3;

        // Filling the dp and optimalK arrays
        for (int i = 3; i <= n; i++) {
            int minMoves = Integer.MAX_VALUE;
            for (int k = 1; k < i; k++) {
                int moves = 2 * dp[k] + ((int)Math.pow(2,i-k)) - 1;  // 2^(i-k) - 1, using bit shift for power calculation
                if (moves < minMoves) {
                    minMoves = moves;
                    optimalK[i] = i-k; // Update the optimal k for this i
                }
            }
            dp[i] = minMoves;
        }
    }

    /*
     * Description : Function to solve the tower of hanoi using only three pegs
     * Input : disks - disks array
     * 	 	   start - starting rod
     * 	 	   aux_peg - the auxiliary rod
     * 	 	   end - the target rod
     *
     * Output : none
     */

   public static void hanoi3(Disk [] disks ,char start , char aux_peg, char end){
        /*if disks = 0 return*/
        if(0 == disks.length)
        {
            return;
        }
        /*if only 1 disk move the disk to the target rod */
        if(1 == disks.length)
        {
            printMove(disks[0].disk_num,start,end);
            return;
        }

       int n =disks.length;
        Disk[] rem_disks=Arrays.copyOfRange(disks,0,n-1); //create an array for the top n-1 disks
        /*To move more than 2 disks we first move all n-1 disks on top to the aux rod
         * then move the last disk to the target rod
         * then move the n-1 disks to the target rod*/
        hanoi3(rem_disks,start,end,aux_peg);
        printMove(disks[n-1].disk_num,start,end);
        hanoi3(rem_disks,aux_peg,start,end);
    }
    /*
     * Description : Function to solve the tower of hanoi using four pegs
     * Input : n - number of disks
     * 	 	   start - starting rod
     * 	 	   aux_peg_1 - the first auxiliary rod
     * 	 	   aux_peg_2 - the second auxiliary rod
     * 	 	   end - the target rod
     * Output : none
     */

    public static void hanoi4(Disk[] disks , char start , char aux_peg_1 , char aux_peg_2 ,char end){
        int n = disks.length;
        /*for 0,1,2 disks use same approach as 3 rods*/
        if(0 == n)
        {
            return;
        }
        if(1 == n)
        {
            printMove(disks[0].disk_num,start,end);
            return;
        }
        if(2 == n)
        {
            printMove(disks[0].disk_num,start,aux_peg_2);
            printMove(disks[1].disk_num,start,end);
            printMove(disks[0].disk_num,aux_peg_2,end);
            return;
        }
        
    /* for n>2 */
    // one of the two following values of k is used according to the chosen running algorithm that the two cases are separate
        
        // the value of k in case of divide and conquer algorithm is that the number of disks is divided into two sub-problems
        int k=n/2;
        
        // the value of k in case of dynamic programming
//        if(optimalK==null)
//         getOptimalDiskNum(n); /*calculate the number of k disks to be fixed at first */
//        int k=optimalK[n];
        Disk[] rem_disks =Arrays.copyOfRange(disks,0,n-k);
        Disk[] fixed_disks = Arrays.copyOfRange(disks,n-k,n);
        hanoi4(rem_disks,start,aux_peg_2,end,aux_peg_1); /* move the first n-k disk from the start rod to the first aux rod using all 4 rods*/
        hanoi3(fixed_disks,start,aux_peg_2,end);/*then move the remaining k rods to the end rod using three rod approach without using the first aux rod*/
        hanoi4(rem_disks,aux_peg_1,start,aux_peg_2,end);/*then move the n-k rods from the first aux rod to the target rod using the 4 rods approach*/
    }


}
class Disk{
    int disk_num;

    public Disk(int disk_num) {
        this.disk_num = disk_num;
    }

}