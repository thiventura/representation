package Representation;

import util.AVL;
import util.No;
import util.Ponto;

/**
 * Perceptually important points(PIP). The method considers each value of the 
 * data series as a point, and its order in which they read the x axis and the 
 * value contained to y, in a Cartesian plane. The algorithm starts considering 
 * the first and last point of the data series as two PIPs, the initial PIPs. 
 * For the next PIP search is the point that has the highest vertical distance 
 * to the two segments of adjacent straight to the candidate point to PIP.
 * The method has a parameter N that defines how many points of the original 
 * series should be included in the reduced series, can be defined in which column 
 * is to be done to align.
 * In this method exist a concept called alignment of data, this alignment defines whether it is
 * applied to considering technical the data line according to the alignment column or the method 
 * is applied column by column it is required more processing.
 * 
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class PIP implements Method {
    private final Double SELECTED = -1.0;
    
    private Double[][] data = null;
    private Double[][] result = null;
    private boolean alignment;
    private int column;
    private int N = 2;
    
     /**
     * Applying the method with alignment in the column defined.
     * @param columnAlignment Defines the column that will be held the alignment of data
     */
    public PIP(int columnAlignment){
        this.alignment = true;
        this.column = columnAlignment;
    }
    
    /**
     * Applying the method column by column of  data serie.
     */
    public PIP(){
        this.alignment = false;
    }
    /** 
     * Method that defines PIPs must be extracted from the original series
     * @param N An integer value greater than 0
     */
    public void setN(int N) throws Exception{
        if(N<2) throw new Exception("Error : Invalid N!");
        else this.N = N;
    }
    
    /** Method for calculating the vertical distance between the pip on the left and right
     * 
     * @param p1 PIP left of the candidate point
     * @param p2 PIP right of the candidate point
     * @param p3 Point candidate the PIP
     * @return 
     */
    private Double distanciaVertical( Ponto p1,  Ponto p2, Ponto p3){
        Double difX = (double)(p3.getX()-p1.getX())/(double)(p2.getX()-p1.getX());
        return Math.abs((p1.getY() - (p2.getY()-p1.getY())*difX)- p3.getY());
    }
    /** Method for calculating the vertical distance between the pip on the left and right
     * 
     * @param x1 Coordinate x of pip left
     * @param y1 Coordinate y of pip left
     * @param x2 Coordinate x of pip right
     * @param y2 Coordinate y of pip right
     * @param x3 Coordinate x of point candidate
     * @param y3 Coordinate y of point candidate
     * @return An value Double 
     */  
    private Double distanciaVertical(int x1, Double y1, int x2, Double y2, int x3, Double y3){
        return Math.abs((y1+(y2-y1)*((x3-x1)/(x2-x1))) - y3);
    }
    /** Method to find the point with greater distance
     * 
     * @param dist List of Doubles with distances 
     * @return Index of point of bigger distance
     */
    private int maiorDistancia(Double dist[]){
        int maior = 0;
        for(int i=1; i<dist.length-1; i++){
            if(dist[maior] < dist[i]) maior = i;
        }
        return maior;
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
            int columns = data[0].length;
            int lines = data.length;
            Double[] dist = new Double[lines];
            Ponto[] p = new Ponto[3];
            int counter = 0;
            int maior = 0;
            int n;
            result = new Double[N][columns];
            for(int i= 0; i<p.length; i++) p[i] = new Ponto();
            
            if(alignment){
                for(int k=0; k<lines; k++) dist[k]=0.0;
                AVL pip = new AVL();
                pip.inserir(0);
                pip.inserir(lines-1);
                dist[0] = dist[lines-1] = SELECTED;
                counter = 2;

                while(counter < N){
                    for(int j=0; j<lines; j++){
                        if(dist[j]!=SELECTED) {
                            No no = pip.findPIP(j);

                            // PIP of left
                            p[0].setX(no.getEsquerda().getChave());
                            p[0].setY(data[no.getEsquerda().getChave()][column]);

                            // PIP of right
                            p[1].setX(no.getDireita().getChave());
                            p[1].setY(data[no.getDireita().getChave()][column]);

                            // Point candidate 
                            p[2].setX(j);
                            p[2].setY(data[j][column]);

                            dist[j] = distanciaVertical(p[0], p[1], p[2]);
                        }
                    }
                    maior = maiorDistancia(dist);

                    dist[maior] = SELECTED;
                    pip.inserir(maior);
                    counter++;
                }
                
                n = 0;
                int l=0;
                for(int i=0; i<N; i++){
                    while(dist[n]!=SELECTED) n++;
                    System.arraycopy(data[n], 0, result[l], 0, columns);
                    l++;
                    n++;
                }
            }else{
                
                for(int i=0; i<columns; i++){
                    for(int k=0; k<lines; k++) dist[k]=0.0;
                    AVL pip = new AVL();
                    pip.inserir(0);
                    pip.inserir(lines-1);
                    dist[0] = dist[lines-1] = SELECTED;
                    counter = 2;

                    while(counter < N){
                        for(int j=0; j<lines; j++){
                            if(dist[j]!=SELECTED) {
                                No no = pip.findPIP(j);

                                // PIP of left
                                p[0].setX(no.getEsquerda().getChave());
                                p[0].setY(data[no.getEsquerda().getChave()][i]);

                                // PIP of right
                                p[1].setX(no.getDireita().getChave());
                                p[1].setY(data[no.getDireita().getChave()][i]);

                                // Point candidate 
                                p[2].setX(j);
                                p[2].setY(data[j][i]);

                                dist[j] = distanciaVertical(p[0], p[1], p[2]);
                            }
                        }
                        maior = maiorDistancia(dist);
                        dist[maior] = SELECTED;
                        pip.inserir(maior);
                        counter++;
                    }

                    n = 0;
                    for(int k=0; k<lines; k++){
                        if(dist[k]==SELECTED){
                            result[n][i] = data[k][i];
                            n++;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Pointer "+ e.getMessage());
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
