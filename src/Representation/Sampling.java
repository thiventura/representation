package Representation;

/**
 * The sampling method reduces the size data series n by a factor L 
 * determines that which will be the values contained in the reduced 
 * data series {0, L, ..., kL}.
 * In this method there is a parameter L which is the jump factor.
 * 
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class Sampling implements Method {
    private Double[][] result = null;
    private Double[][] data = null;
    private int L = 3;
    
    /** 
     * Defines what is the segment size, by default L is 3
     * @param L An integer value
     */
    public void setL(int L){
        if(L!=0) this.L = L;
    }
    
    /** Data loading method for performing of processing 
    *
    *@param data It is an array of double values
    */
    @Override
    public void load(Double[][] data) {
        this.data = data;
    }
    
    
    /** Method that processes the data loaded
     * 
     */
    @Override
    public void process() {
        try{
            int lines;
            if(data.length%L!=0) lines = data.length/L+1;
            else lines = data.length/L;
            int columns = data[0].length;
            
            result = new Double[lines][columns];
            
            for(int i=0; i<data.length; i = i+L){
                for(int j=0; j<columns; j++){
                    result[i/L][j] = data[i][j];
                }
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
    
    /** Method to get the processing result
     *
     * @return Is returned the data after processing
     */
    @Override
    public Double[][] getResult() {
        return result;
    }
    
}
