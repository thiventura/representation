package Representation;

/**
 * Coloque aqui a descrição da classe
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public interface Method {
    
    /** Data loading method for performing of processing 
    *
    *@param data It is an array of double values
    */
    public void load(Double data[][]);
    
    /** Method that processes the data loaded
     * 
     */
    public void process();
    
    /** Method to get the processing result
     *
     * @return Is returned the data after processing
     */
    public Double[][] getResult();
}
