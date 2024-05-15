import java.util.Scanner;
import java.util.ArrayList;


public class Task8 {

    static public int findFakeBox(float boxes[], double digitalScaleReading,double realMetalPieceWeigt, double fakeMetalPieceWeigt) {
        double sum = 0;
        double arrayOfProbablities[] = new double[50];
        for (int i = 1; i < 51; i++) {
            sum = 0;
            for (int j = 0; j < 50; j++) {
                if (j + 1 == i)
                    sum += boxes[j] * fakeMetalPieceWeigt;
                else
                    sum += boxes[j] * realMetalPieceWeigt;
            }
            arrayOfProbablities[i - 1] = sum;
        }

        for (int i = 0; i < 50; i++) {
            if (digitalScaleReading == arrayOfProbablities[i])
                return i + 1;
        }
        return 0;
    }
    

    public static void main(String[] args) throws Exception {
         
        
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Real box weight: ");
        Double realBoxWeight = input.nextDouble();
        System.out.print("Enter digital Scale Reading: ");
        Double digitalScaleReading = input.nextDouble();
        input.close();


        float boxes[] = {1,2,3,4,5,6,7,8,9,10
        ,11,12,13,14,15,16,17,18,19,20
        ,21,22,23,24,25,26,27,28,29,30
        ,31,32,33,34,35,36,37,38,39,40
        ,41,42,43,44,45,46,47,48,49,50};
        

        System.out.println("fake box is box number " + findFakeBox(boxes,digitalScaleReading,(realBoxWeight/50.0),(realBoxWeight-1.0)/50.0));

    }
}



