package Representation;

/**
 * Segmented Sum of Variation (SSV) is a method that divides the original data series 
 * into N segments, these segments are connected, i.e., the end of a segment is 
 * the beginning of the next segment. This is necessary because the method calculates the 
 * variation of each segment, if the segments are disconnected variation would be lost.
 * In this method there is a parameter K which defines what is the segment size.
 *  
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class SSV implements Method {
    private Double[][] result = null;
    private Double[][] data = null;
    private int K = 8;
    private int W = 1;
    
    /** 
     * Defines what is the segment size, by default K is 8
     * @param K An integer value
     */
    
    public void setK(int K){
        if(K!=0) this.K = K;
    }
    /**
     * 
     * @param W 
     */
    public void setW(int W){
        if(W!=0) this.W = W;
    }
    
    /**  Method for the realization of the sum of variation of segment
     * 
     * @param tab Data series 
     * @param column column to realization of sum
     * @param begin starting line for the realization of the sum
     * @param end finish line for the realization of the sum
     * @return An value Double with the sum of segment
     */
    private Double sumVariation(Double[][] tab,int column, int begin, int end){
        Double r = 0.0;
        while(begin!=end){
           r = r+ Math.abs(tab[begin+1][column]- tab[begin][column]);
           begin++;
        }
        return r;
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
            int interval = K-W;
            int lines = (data.length-W)/interval;
            int columns = data[0].length;
            
            result = new Double[lines][columns];
            
            for(int i=0; i<lines; i++){
                for(int j=0; j<columns; j++){
                    if(W==1){
                        result[i][j] = sumVariation(data,j,i*interval,(i+1)*interval);
                    }else{
                        result[i][j] = sumVariation(data,j,i*interval,(i*interval+W));
                    }
                }
            }
        }catch (NullPointerException | ArithmeticException e){
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
