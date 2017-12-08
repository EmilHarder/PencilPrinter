package pencilprinter;

/**
 *
 * @author emil
 */
public class ByteConvert {

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
                
                bitInByteNr ++;
                
                forInt = (hasValue * ((int) Math.pow(2, bitInByteNr - 1)));
                finInt += forInt;
                
                
                //System.out.println("Bit nr.: "+j+" Bit in byte nr.: "+bitInByteNr+" Bit val.: "+hasValue);
                //System.out.println("Add to byte: "+forInt+" Byte value: "+finInt);
                
                
            }
            //System.out.println("finInt i 32-loop: "+finInt);
            arrayToSend[i] = (byte)  finInt;
                xStep += 8;

        }
            return arrayToSend;

        }

    }
