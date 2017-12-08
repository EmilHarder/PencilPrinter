package pencilprinter;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author For eksempel Johnny
 */
public class ReadingImagesMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ReadImage asd = new ReadImage();
        JFileChooser fc = new JFileChooser();
        
        fc.setMultiSelectionEnabled(false);
        fc.showOpenDialog(fc);
        
        File inputfile = fc.getSelectedFile();
        String path = inputfile.getAbsolutePath().toLowerCase();
        String filetype = path.substring(path.lastIndexOf(".")+1);
        
        if ("svg".equals(filetype))
        {
            String opp = asd.svg2png(path);
            asd.readBmpImage(opp);
        }else{
            asd.readBmpImage(path);
        }
    }
    
}