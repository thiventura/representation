package util;

/**
 * Node
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class No {
    private No esquerda;
    private No direita;
    private No pai;
    private int chave;
    private int balanceamento;

    public No(int k) {
            setEsquerda(setDireita(setPai(null)));
            setBalanceamento(0);
            setChave(k);
    }

    public No() { }

    @Override
    public String toString() {
            return Integer.toString(getChave());
    }

    public int getChave() {
            return chave;
    }

    public void setChave(int chave) {
            this.chave = chave;
    }

    public int getBalanceamento() {
            return balanceamento;
    }

    public void setBalanceamento(int balanceamento) {
            this.balanceamento = balanceamento;
    }

    public No getPai() {
            return pai;
    }

    public No setPai(No pai) {
            this.pai = pai;
            return pai;
    }

    public No getDireita() {
            return direita;
    }

    public No setDireita(No direita) {
            this.direita = direita;
            return direita;
    }

    public No getEsquerda() {
            return esquerda;
    }

    public void setEsquerda(No esquerda) {
            this.esquerda = esquerda;
    } 
}
