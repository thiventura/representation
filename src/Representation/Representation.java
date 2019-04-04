package Representation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main
 * 
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class Representation {

    public static int tipo = -1;
  
    public static void main(String[] args) {
        
        Double data[][] = {{5.0}, {4.0}, {5.0}, {6.0}, {8.0}, {7.0}, {7.0}, {5.0}, {4.0}, {3.0}, {4.0}, {5.0}, {7.0}};
        SSV ssv =  new SSV();
        ssv.setK(5);
        ssv.setW(1);
        ssv.load(data);
        ssv.process();
        show(ssv.getResult());
    }

}
