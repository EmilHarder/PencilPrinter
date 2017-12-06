/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bytetest;

/**
 *
 * @author emil
 */
public class ByteTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int arrayLenght = 256;
        boolean[] array = new boolean[arrayLenght];
        String strToPrint = null;

        for (int j = 0; j < arrayLenght; j++) {
        
        array[j] = (boolean) (j % 3 != 0);
        
        }
        
        /*array[0] = true;
        array[1] = true;
        array[2] = false;
        array[3] = true;
        array[4] = false;
        array[5] = false;
        array[6] = true;
        array[7] = true;*/

        //Printer af boolArray
        for (boolean b : array) {
            int tmpInt = (b) ? 1 : 0;
            if (strToPrint == null) {
                strToPrint = String.valueOf(tmpInt);
            } else {
                strToPrint = strToPrint + tmpInt;
            }

        }
        System.out.println("boolArray som string: " + strToPrint);
        //Printer aff boolArray slut

        byte tmpByte = 0;
        int hasValue = 0;
        int forInt = 0;
        int bitIn = 0;
        int finInt = 0;

        
        
        for (int i = 0; i < 8; i++) {
            hasValue = (array[i]) ? 1 : 0;
            bitIn = 8 - i;
            forInt = (hasValue * ((int) Math.pow(2, bitIn - 1)));
            finInt += forInt;
            //System.out.println(bitIn+" "+hasValue+" "+forInt);
        }

        tmpByte = (byte) finInt;

        byte b1 = tmpByte;
        String s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
        System.out.println("byte in bits: " + s1);

        System.out.println("Signed byte: " + (tmpByte));
        System.out.println("Unsigned byte: " + (tmpByte & 0xFF));

    }

}
