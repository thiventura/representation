package Representation;

/**
 * Piecewise Aggregate Approximation (PAA). The method divides the data set n
 * of N sized segments and computes the average of each segment to represent
 * the corresponding set of data points.
 *  
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class PAA implements Method {
    
    private Double[][] result = null;
    private Double[][] data = null;
    private int N = 5;
    
    /** Method for setting the segment size
     *  By default the segment size is 5
     * 
     * @param N An integer value greater than 0
     */
    public void setN(int N){
        if(N!=0) this.N = N;
    }
    
    /**  Method for the realization of the sum of a segment, the concept used in the PAA method
     * 
     * @param tab Data serie 
     * @param column column to realization of sum
     * @param begin starting line for the realization of the sum
     * @param end finish line for the realization of the sum
     * @return An value Double with the sum of segment
     */
    private Double sumSegment(Double tab[][],int column, int begin, int end) {
        Double r = 0.0;
        while(begin<=end){
           r = r+ tab[begin][column];
           begin++;
        }
        return r;
    }
    
    /** Data loading method for performing of processing 
    *
    *@param data It is an array of double values
    **/
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
            int lines = data.length;
            int columns = data[0].length;

            result = new Double[lines/N][columns];

            for(int i=0; (lines-i)>=N; i=i+N){
                for(int j=0; j<columns; j++){
                    result[i/N][j] = sumSegment(data,j,i,i+N-1)/N;
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
