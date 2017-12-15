package pencilprinter;


public class ByteConvert {
    
    //Used to store value of bool in array as integer, used for convertion to
    //byte
    int hasValue = 0;
    //Used to store the current value of the byte, while traversing through the
    //8 bits, used for the byte
    int forInt = 0;
    //Used to count which bit in the current byte
    int bitInByteNr = 0;
    //The final value of the byte, used to store in the array of bytes to send
    int finInt = 0;
    //Counter used to jump 8 steps up, for the 8 bits in byte
    int xStep = 0;

    //The array to send. 32 bytes for 32*8=256
    byte[] arrayToSend = new byte[32];
    
    /**
     *
     * @param boolArray the bool array given from imageHandler
     * @return Returns the byte array, thats going to be send by TcpClient
     */
    public byte[] boolToByte(boolean[] boolArray) {
        
        //For loop for the 32 bytes in arrayToSend
        for (int i = 0; i < 32; i++) {

            //System.out.println("Byte nr.: "+i);
            
            //Resetting the counter for bitInByte
            bitInByteNr = 0; //Should be 8
            //Resetting the final int
            finInt = 0;

            //The for loop that takes the next 8 bits in the given bool array
            for (int j = xStep; j < xStep + 8; j++) {
                
                //Determine if the bit is true or false
                hasValue = (boolArray[j]) ? 1 : 0;
                //System.out.println("Index in boolArray "+j+" is: "+hasValue);
                
                //Calculating the byte value, based on wich bits is true or false
                forInt = (hasValue * ((int) Math.pow(2, bitInByteNr))); //LSB is sat wrong!
                
                //Adding the value for the current places bit
                finInt += forInt;
                
                //System.out.println("BitInByte: "+bitInByteNr+" has value: "+forInt);
                
                //Increasing the counter, ready for next bit
                bitInByteNr ++; //Should be --
                
            }
            
            //System.out.println("Final byte value: "+finInt);

            //Type cast the final value of the byte from int, and store it in
            //arrayToSend
            arrayToSend[i] = (byte)  finInt;
            //Jumps up to the next 8 bits
            xStep += 8;

        }
            //Return the finished byte array
            return arrayToSend;

        }

    }
