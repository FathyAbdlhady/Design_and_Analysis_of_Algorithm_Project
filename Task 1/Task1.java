import java.util.Scanner;

public class Task1 
{
    static int k;
    static int quarter[] = {1,-1,-1,-1,-1};

    static void notEqualInc(int x,int y,int result[],int arr[][])
    {
      if( (arr[x][y] != 0) && (arr[x][y] != -1))
      {
        result[arr[x][y]-1] += 1;
      }
    }

    static void placetrom2Trom(int x1, int y1,
                      int x2, int y2,   
                      int x3, int y3, 
                      int n,int quart,int arr[][])
    {
      if(quarter[quart] == -1)
      {
        int result[] = {0,0,0};
        int xVals[] = {x1,x2,x3};
        int yVals[] = {y1,y2,y3};
        for(int i = 0; i < 3; i++)
        {
          if(xVals[i] == 0)
          {
            notEqualInc(xVals[i]+1,yVals[i],result,arr);
          }
          else if(xVals[i] == (k-1))
          {
            notEqualInc(xVals[i]-1,yVals[i],result,arr);
          }
          else
          {
            notEqualInc(xVals[i]+1,yVals[i],result,arr);
            notEqualInc(xVals[i]-1,yVals[i],result,arr);
          }

          if(yVals[i] == 0)
          {
            notEqualInc(xVals[i],yVals[i]+1,result,arr);
          }
          else if(yVals[i] == (k-1))
          {
            notEqualInc(xVals[i],yVals[i]-1,result,arr);
          }
          else
          {
            notEqualInc(xVals[i],yVals[i]+1,result,arr);
            notEqualInc(xVals[i],yVals[i]-1,result,arr);
          }
        }

        for(int i = 0; i < 3; i++)
        {
          if(result[i] == 0)
          {
            quarter[quart] = i+1;
            break;
          }
        }
      }

      arr[x1][y1] = quarter[quart];
      arr[x2][y2] = quarter[quart];
      arr[x3][y3] = quarter[quart];
    }

    // dynamic algorithm
    static int tromino(int n, int x, int y,int arr[][])
    {
      int r = 0, c = 0;
      if (n == 1) 
      {
        return 0;
      }
   
      // finding hole location
      for (int i = x; i < x + n; i++)
      {
        for (int j = y; j < y + n; j++) 
        {
          if (arr[i][j] != 0)
          {
            r = i;
            c = j;
          }
   
        }
      }

      if(n == 2)
      {
        // If missing tromino2 is 1st quadrant
        if (r < x + n / 2 && c < y + n / 2)
        placetrom2Trom(x + n / 2, y + (n / 2) - 1, 
                x + n / 2, y + n / 2, 
                x + n / 2 - 1, y + n / 2,
                n,1,arr);
    
        // If missing tromino2 is in 3rd quadrant
        else if (r >= x + n / 2 && c < y + n / 2)
        placetrom2Trom(x + (n / 2) - 1, y + (n / 2), 
                x + (n / 2), y + n / 2,  
                x + (n / 2) - 1, y + (n / 2) - 1,
                n,2,arr);
    
        // If missing tromino2 is in 2nd quadrant
        else if (r < x + n / 2 && c >= y + n / 2)
        placetrom2Trom(x + n / 2, y + (n / 2) - 1, 
                x + n / 2, y + n / 2, 
                x + n / 2 - 1, y + n / 2 - 1,
                n,3,arr);
    
        // If missing tromino2 is in 4th quadrant
        else if (r >= x + n / 2 && c >= y + n / 2)
        placetrom2Trom(x + (n / 2) - 1, y + (n / 2), 
                x + (n / 2), y + (n / 2) - 1, 
                x + (n / 2) - 1, y + (n / 2) - 1,
                n,4,arr);  
      }
      else
      {
        // If center missing tromino2 is 1st quadrant
        if (r < x + n / 2 && c < y + n / 2)
        placetrom2Trom(x + n / 2, y + (n / 2) - 1, 
                x + n / 2, y + n / 2, 
                x + n / 2 - 1, y + n / 2,
                n,0,arr);
    
        // If center missing tromino2 is in 3rd quadrant
        else if (r >= x + n / 2 && c < y + n / 2)
        placetrom2Trom(x + (n / 2) - 1, y + (n / 2), 
                x + (n / 2), y + n / 2,  
                x + (n / 2) - 1, y + (n / 2) - 1,
                n,0,arr);
    
        // If center missing tromino2 is in 2nd quadrant
        else if (r < x + n / 2 && c >= y + n / 2)
        placetrom2Trom(x + n / 2, y + (n / 2) - 1, 
                x + n / 2, y + n / 2, 
                x + n / 2 - 1, y + n / 2 - 1,
                n,0,arr);
    
        // If center missing tromino2 is in 4th quadrant
        else if (r >= x + n / 2 && c >= y + n / 2)
        placetrom2Trom(x + (n / 2) - 1, y + (n / 2), 
                x + (n / 2), y + (n / 2) - 1, 
                x + (n / 2) - 1, y + (n / 2) - 1,
                n,0,arr);  
      }
   
      
   
      // dividing it again in 4 quadrants
      tromino(n / 2, x, y + n / 2,arr);
      tromino(n / 2, x, y,arr);
      tromino(n / 2, x + n / 2, y,arr);
      tromino(n / 2, x + n / 2, y + n / 2,arr);  
      return 0;
    }

    public static void main(String[] args) throws Exception 
    {
        System.out.print("Enter k which is 2^k: ");
        Scanner input = new Scanner(System.in);

        k = (int)Math.pow(2.00,input.nextDouble());

        int arr[][] = new int[k][k];

        System.out.print("Enter location of hole (x,y) ");
        int squareMissingY = input.nextInt();
        int squareMissingX = input.nextInt();
        
        input.close();
    
        // Here tromino2 can not be placetrom2d
        arr[squareMissingX][squareMissingY] = -1;
        tromino(k, 0, 0,arr);

        System.out.println("output: ");
        // The grid is
        for (int i = 0; i < k; i++) 
        {
          for (int j = 0; j < k; j++)
              System.out.print(arr[i][j] + " \t");
          System.out.println();;
        }
    }        

}

class DivideAndConquerSolution
{
    static int cnt = 1;
    
    static void placetrom2(int x1, int y1,
    int x2, int y2,   
    int x3, int y3, 
    int n,int val,int arr[][])
    {
    if(n >= 4)
    {
    cnt = 1;
    }
    else
    {
    cnt = val;
    }
    arr[x1][y1] = cnt;
    arr[x2][y2] = cnt;
    arr[x3][y3] = cnt;
    }


    // divide and conquer algorithm
    static int tromino2(int n, int x, int y,int arr[][])
    {
    int r = 0, c = 0;
    if (n == 1) 
    {
    return 0;
    }


    for (int i = x; i < x + n; i++)
    {
    for (int j = y; j < y + n; j++) 
    {
    if (arr[i][j] != 0)
    {
    r = i;
    c = j;
    }

    }
    }

    // If missing tromino2 is 1st quadrant
    if (r < x + n / 2 && c < y + n / 2)
    placetrom2(x + n / 2, y + (n / 2) - 1, 
    x + n / 2, y + n / 2, 
    x + n / 2 - 1, y + n / 2,
    n,2,arr);

    // If missing tromino2 is in 3rd quadrant
    else if (r >= x + n / 2 && c < y + n / 2)
    placetrom2(x + (n / 2) - 1, y + (n / 2), 
    x + (n / 2), y + n / 2,  
    x + (n / 2) - 1, y + (n / 2) - 1,
    n,3,arr);

    // If missing tromino2 is in 2nd quadrant
    else if (r < x + n / 2 && c >= y + n / 2)
    placetrom2(x + n / 2, y + (n / 2) - 1, 
    x + n / 2, y + n / 2, 
    x + n / 2 - 1, y + n / 2 - 1,
    n,3,arr);

    // If missing tromino2 is in 4th quadrant
    else if (r >= x + n / 2 && c >= y + n / 2)
    placetrom2(x + (n / 2) - 1, y + (n / 2), 
    x + (n / 2), y + (n / 2) - 1, 
    x + (n / 2) - 1, y + (n / 2) - 1,
    n,2,arr);


    tromino2(n / 2, x, y + n / 2,arr);
    tromino2(n / 2, x, y,arr);
    tromino2(n / 2, x + n / 2, y,arr);
    tromino2(n / 2, x + n / 2, y + n / 2,arr);  
    return 0;
    } 
}



