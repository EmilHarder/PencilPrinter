package pencilprinter;


public class ByteConvert {
    
    //COMMENTS!!!

    //Some tmp vars for storing wile traversing
    byte tmpByte = 0;
    int hasValue = 0;
    int forInt = 0;
    int bitInByteNr = 0;
    int finInt = 0;

    byte[] arrayToSend = new byte[32];

    public byte[] boolToByte(boolean[] boolArray) {

        int xStep = 0;

        for (int i = 0; i < 32; i++) {

            //System.out.println("Byte nr.: "+(i + 1));
            
            bitInByteNr = 0;
            finInt = 0;
            

            for (int j = xStep; j < xStep + 8; j++) {
                
                hasValue = (boolArray[j]) ? 1 : 0;
                //System.out.println(j+", "+hasValue);
                
                bitInByteNr ++;
                
                forInt = (hasValue * ((int) Math.pow(2, bitInByteNr - 1)));
                finInt += forInt;
                
                
            }

            arrayToSend[i] = (byte)  finInt;
                xStep += 8;

        }
            return arrayToSend;

        }

    }
