package hemi.xmu.learn.smartAlgorithm.antSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *主程序 调用ACO求解问题
 * @author FashionXu
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ACO aco = new ACO();
        try {
            aco.init("C:\\Users\\Vanguard\\Desktop\\aco\\aco\\data.txt", 50);
            aco.run(2000);
            aco.ReportResult();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}