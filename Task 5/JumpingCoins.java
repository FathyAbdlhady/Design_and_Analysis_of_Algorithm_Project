// Task 5
public class JumpingCoins {
    
    
    ////////////////////////////////////////////////////////////////////////////
    // number of coins
    // n = 2^(i+3)-4  where i is non-negative integer [0,...]
    // i.e. (i=0, n=4), (i=1, n=12), (i=2, n=28), ... 
    int n = 28;

    // there's a row of coins
    // 0 in a column -> empty column
    // 1 in a column -> one coin
    // 2 in a column -> pair of coins
    int[] rowOfCoins = new int[n];
    
    // number of moves that are going to be done to form the pairs
    int numberOfMoves = 0;
    
    // number of coins that a coin will jump over in a certain move
    // it should be equal to the number of the move being made
    private int jumpedOverCoins;
    ////////////////////////////////////////////////////////////////////////////
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Constructor
    public JumpingCoins(){
    
        // each column in the row has just one coin at first
        for (int i = 0; i < n; i++)
            rowOfCoins[i]=1;
    
    }
    ////////////////////////////////////////////////////////////////////////////
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Main
    public static void main(String [] args){
        
        JumpingCoins jc1 = new JumpingCoins();
        jc1.printRowOfCoins();
        jc1.algorithmUsingGreedy();
        System.out.println("\nUsing Greedy Technique:\n");
        jc1.printRowOfCoins();
        
        System.out.println("\n-----------------------------------------\n");
        
        JumpingCoins jc2 = new JumpingCoins();
        jc2.printRowOfCoins();
        jc2.algorithmUsingBruteForce(0, 1, "right");
        System.out.println("\nUsing Brute Force Technique:\n");
        jc2.printRowOfCoins();

    }
    ////////////////////////////////////////////////////////////////////////////
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Solution using greedy technique
    public void algorithmUsingGreedy(){      
        
        for(int i = 0; i < n-1; i++){
           
            // skip any coin pairs (2) or empty spaces (0)
            // skip until you find the nearest coin (greedy step)
            if(rowOfCoins[i] != 1)
                continue;
            
            // the current move number
            numberOfMoves++;

            jumpedOverCoins = 0;

            // making a coin move to form a pair of coins
            for (int j = i+1; j<n; j++){
                
                // move the coin to the column if the condition is satisfied
                if(jumpedOverCoins == numberOfMoves){
                    rowOfCoins[i] = 0;
                    rowOfCoins[j] += 1;
                    break;
                }
                
                // or jump over the column and count the jumped over coins
                jumpedOverCoins += rowOfCoins[j];
               
            }
            
        }
    
    }
    ////////////////////////////////////////////////////////////////////////////
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Solution using brute force technique
    public boolean algorithmUsingBruteForce(int position, int currentMoveNumber, String direction){      
        
        if ((position < 0) || (position > n)){
            return false;
        }
        
        numberOfMoves = currentMoveNumber;
        jumpedOverCoins = 0;
        boolean doneFlag = false;
        
        // check in the right direction the possibility to form a pair
        if (direction.equals("right")){
        
            for(int i = position+1; i < n; i++){
            
                // move the coin to the column if the condition is satisfied
                // or break if exceeded
                if(jumpedOverCoins >= numberOfMoves){
                    if (jumpedOverCoins == numberOfMoves){
                        rowOfCoins[position] = 0;
                        rowOfCoins[i] += 1;
                        currentMoveNumber++;
                        doneFlag = true;
                    }
                    break;
                }
                
                // or jump over the column and count the jumped over coins
                else if(jumpedOverCoins < numberOfMoves) 
                    jumpedOverCoins += rowOfCoins[i];
            
            }
        
        }
        // check in the left direction the possibility to form a pair
        else if (direction.equals("left")){
        
            for(int i = position-1; i >= 0; i--){
            
                // move the coin to the column if the condition is satisfied
                // or break if exceeded
                if(jumpedOverCoins >= numberOfMoves){
                    if (jumpedOverCoins == numberOfMoves){
                        rowOfCoins[position] = 0;
                        rowOfCoins[i] += 1;
                        currentMoveNumber++;
                        doneFlag = true;
                    }
                    break;
                }
                
                // or jump over the column and count the jumped over coins
                else if(jumpedOverCoins < numberOfMoves) 
                    jumpedOverCoins += rowOfCoins[i];
            
            }
        
        }
        
        // check if there're still single coins
        for(int i : rowOfCoins){
            if (i == 1){
                doneFlag = false;
                break;
            }
        }
        
        // check if all are the coins are pairs
        // if so, then we are done
        if(doneFlag == true)
            return true;

        // next single coin to be moved to form a pair
        int nextPosition = position;
        for(int i = position+1; i < n; i++){
            if (rowOfCoins[i] == 1){
                nextPosition = i;
                break;
            }
        }
        
        doneFlag = algorithmUsingBruteForce(nextPosition, currentMoveNumber, "right");
        
        if(doneFlag == true)
            return true;

        doneFlag = algorithmUsingBruteForce(nextPosition, currentMoveNumber, "left");
        
        return doneFlag;
        
    }
    ////////////////////////////////////////////////////////////////////////////
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Print row and number of moves
    public void printRowOfCoins(){
    
        for (int i = 0; i < n; i++){
            System.out.print(rowOfCoins[i]+"  ");
        }
        System.out.println("");
        System.out.println("Number of Moves = " + numberOfMoves);
    
    }
    ////////////////////////////////////////////////////////////////////////////
    
    
}
