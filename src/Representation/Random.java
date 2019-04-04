package Representation;

import java.util.Arrays;

/**
 * The method random performs raffle of values random that will compose the data series reduced
 * In this method exist a concept called alignment of data, this alignment defines whether it is
 * applied to considering technical the data line according to the alignment column or the method 
 * is applied column by column it is required more processing.
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class Random implements Method{
    
    private Double[][] result = null;
    private Double[][] data = null;
    private boolean alignment;
    private int N = 2;
    private int column = 0;
    
    /**
     * Applying the method with alignment in the column defined.
     * @param columnAlignment Defines the column that will be held the alignment of data
     */
    public Random(int columnAlignment) {
        this.alignment = true;
        this.column = columnAlignment;
    }

    /**
     * Applying the method column by column of  data serie.
     */
    public Random() {
        this.alignment = false;
    }

    /** Data loading method for performing the processing
     * 
     * @param data It is an array of double values
     *
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
            int lines = data.length;
            int columns = data[0].length;
            int[] numbers;
            result = new Double[N][columns];
            
            if(alignment){
                numbers = getNumbers(column);
                for(int i=0; i<N;i++){
                    for(int j=0; j<columns; j++){
                        result[i][j] = data[numbers[i]][j];
                    }
                }
            }else{
                for(int i=0; i<columns; i++){
                    numbers = getNumbers(i);
                    for(int j=0; j<N; j++){
                        result[j][i] = data[numbers[j]][i];
                    }
                }
            }
        } catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    /** Method to get the processing result
     *
     * @return Is returned the data after processing
     */
    @Override
    public Double[][] getResult() {
        return this.result;
    }
 
    /** Method to get random numbers without repetition 
     * 
     * @return  A vector of random numbers without repetition
     */
    private int[] getNumbers(int column){
        java.util.Random ger = new java.util.Random();
        int[] numbers = new int[N];
        boolean[] raffled = new boolean[data.length]; //Default false
        for (int i = 0; i < N; i++) {
            do {
                numbers[i] = ger.nextInt(data.length);
            } while (raffled[numbers[i]]);
            raffled[numbers[i]] = true;
        }
        Arrays.sort(numbers);
        return numbers;
    }
    /**
     * Value that define the number of elements extracted of series original
     * @param N 
     */
    public void setN(int N) {
        this.N = N;
    }
    
}
