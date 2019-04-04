
package util;

/**
 * Point
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class Ponto {
    private int x=0;
    private Double y=0.0;
    
    public Ponto() { }
    public Ponto(int x, Double y){
        this.x=x;
        this.y = y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public Double getY(){
        return this.y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(Double y){
        this.y = y;
    }
}
