package pencilprinter;

public class TmpBool {

    /**
     * @return this method return a boolean array used tmp for testing
     */
    public boolean[][] tmpBoolArray(){

        
        boolean boolArray[][];
        
        //CHANGE TO "RECIEVED" ARRAY WHEN MERGIN WITH IMAGEREAD
        //Use the toDraw var
        //START for midlertidigt array
        int arrayLenght = 256;
        boolArray = new boolean[arrayLenght][arrayLenght];
        
        for (int i = 0; i < arrayLenght; i++) {
        
        for (int j = 0; j < arrayLenght; j++) {
        
        boolArray[i][j] = (boolean) ((i % 2 == 0) ^ (j % 3 == 0));
        }
        //System.out.println(Arrays.toString(boolArray[i]));
        }
        //SLUT for midlertidigt array
        return boolArray;
    }
        
        
        
    }


