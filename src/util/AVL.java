package util;

import java.util.ArrayList;

/**
 * AVL tree
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class AVL {
    protected No raiz;

    public void inserir(int k) {
            No n = new No(k);
            inserirAVL(this.raiz, n);
    }

    private void inserirAVL(No aComparar, No aInserir) {

            if (aComparar == null) {
                    this.raiz = aInserir;

            } else {

                    if (aInserir.getChave() < aComparar.getChave()) {

                            if (aComparar.getEsquerda() == null) {
                                    aComparar.setEsquerda(aInserir);
                                    aInserir.setPai(aComparar);
                                    verificarBalanceamento(aComparar);

                            } else {
                                    inserirAVL(aComparar.getEsquerda(), aInserir);
                            }

                    } else if (aInserir.getChave() > aComparar.getChave()) {

                            if (aComparar.getDireita() == null) {
                                    aComparar.setDireita(aInserir);
                                    aInserir.setPai(aComparar);
                                    verificarBalanceamento(aComparar);

                            } else {
                                    inserirAVL(aComparar.getDireita(), aInserir);
                            }

                    } else {
                            // O nó já existe
                    }
            }
    }

    public void verificarBalanceamento(No atual) {
            setBalanceamento(atual);
            int balanceamento = atual.getBalanceamento();

            if (balanceamento == -2) {

                    if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
                            atual = rotacaoDireita(atual);

                    } else {
                            atual = duplaRotacaoEsquerdaDireita(atual);
                    }

            } else if (balanceamento == 2) {

                    if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
                            atual = rotacaoEsquerda(atual);

                    } else {
                            atual = duplaRotacaoDireitaEsquerda(atual);
                    }
            }

            if (atual.getPai() != null) {
                    verificarBalanceamento(atual.getPai());
            } else {
                    this.raiz = atual;
            }
    }

    public void remover(int k) {
            removerAVL(this.raiz, k);
    }

    private void removerAVL(No atual, int k) {
            if (atual == null) {
                    return;

            } else {

                    if (atual.getChave() > k) {
                            removerAVL(atual.getEsquerda(), k);

                    } else if (atual.getChave() < k) {
                            removerAVL(atual.getDireita(), k);

                    } else if (atual.getChave() == k) {
                            removerNoEncontrado(atual);
                    }
            }
    }

    private void removerNoEncontrado(No aRemover) {
            No r;

            if (aRemover.getEsquerda() == null || aRemover.getDireita() == null) {

                    if (aRemover.getPai() == null) {
                            this.raiz = null;
                            aRemover = null;
                            return;
                    }
                    r = aRemover;

            } else {
                    r = sucessor(aRemover);
                    aRemover.setChave(r.getChave());
            }

            No p;
            if (r.getEsquerda() != null) {
                    p = r.getEsquerda();
            } else {
                    p = r.getDireita();
            }

            if (p != null) {
                    p.setPai(r.getPai());
            }

            if (r.getPai() == null) {
                    this.raiz = p;
            } else {
                    if (r == r.getPai().getEsquerda()) {
                            r.getPai().setEsquerda(p);
                    } else {
                            r.getPai().setDireita(p);
                    }
                    verificarBalanceamento(r.getPai());
            }
            r = null;
    }

    private No rotacaoEsquerda(No inicial) {

            No direita = inicial.getDireita();
            direita.setPai(inicial.getPai());

            inicial.setDireita(direita.getEsquerda());

            if (inicial.getDireita() != null) {
                    inicial.getDireita().setPai(inicial);
            }

            direita.setEsquerda(inicial);
            inicial.setPai(direita);

            if (direita.getPai() != null) {

                    if (direita.getPai().getDireita() == inicial) {
                            direita.getPai().setDireita(direita);

                    } else if (direita.getPai().getEsquerda() == inicial) {
                            direita.getPai().setEsquerda(direita);
                    }
            }

            setBalanceamento(inicial);
            setBalanceamento(direita);

            return direita;
    }

    private No rotacaoDireita(No inicial) {

            No esquerda = inicial.getEsquerda();
            esquerda.setPai(inicial.getPai());

            inicial.setEsquerda(esquerda.getDireita());

            if (inicial.getEsquerda() != null) {
                    inicial.getEsquerda().setPai(inicial);
            }

            esquerda.setDireita(inicial);
            inicial.setPai(esquerda);

            if (esquerda.getPai() != null) {

                    if (esquerda.getPai().getDireita() == inicial) {
                            esquerda.getPai().setDireita(esquerda);

                    } else if (esquerda.getPai().getEsquerda() == inicial) {
                            esquerda.getPai().setEsquerda(esquerda);
                    }
            }

            setBalanceamento(inicial);
            setBalanceamento(esquerda);

            return esquerda;
    }

    private No duplaRotacaoEsquerdaDireita(No inicial) {
            inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
            return rotacaoDireita(inicial);
    }

    private No duplaRotacaoDireitaEsquerda(No inicial) {
            inicial.setDireita(rotacaoDireita(inicial.getDireita()));
            return rotacaoEsquerda(inicial);
    }

    private No sucessor(No q) {
            if (q.getDireita() != null) {
                    No r = q.getDireita();
                    while (r.getEsquerda() != null) {
                            r = r.getEsquerda();
                    }
                    return r;
            } else {
                    No p = q.getPai();
                    while (p != null && q == p.getDireita()) {
                            q = p;
                            p = q.getPai();
                    }
                    return p;
            }
    }

    private int altura(No atual) {
            if (atual == null) {
                    return -1;
            }

            if (atual.getEsquerda() == null && atual.getDireita() == null) {
                    return 0;

            } else if (atual.getEsquerda() == null) {
                    return 1 + altura(atual.getDireita());

            } else if (atual.getDireita() == null) {
                    return 1 + altura(atual.getEsquerda());

            } else {
                    return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
            }
    }

    private void setBalanceamento(No no) {
            no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
    }

    final protected ArrayList<No> inorder() {
            ArrayList<No> ret = new ArrayList<>();
            inorder(raiz, ret);
            return ret;
    }

    final protected void inorder(No no, ArrayList<No> lista) {
            if (no == null) {
                    return;
            }
            inorder(no.getEsquerda(), lista);
            lista.add(no);
            inorder(no.getDireita(), lista);
    }
    
    public void exibe(){
        ArrayList<No> ret = inorder();
        for(int i=0; i<ret.size(); i++) System.out.println(ret.get(i));
    }
    
    public No findPIP(int valor){
        return findPIP(this.raiz, valor);
    }
    
    private No findPIP(No no, int valor){
        if(no==null){
            return null;
        }else{
            boolean temFilho = no.getDireita()!=null || no.getEsquerda()!=null;
            while(temFilho){
                
                if(valor < no.getChave()) {
                    if(no.getEsquerda()!=null) no = no.getEsquerda();
                    else temFilho = false;
                    
                }else{
                    if(no.getDireita()!=null) no = no.getDireita();
                    else temFilho = false;
                }
            }
            
            No sol = new No();
            sol.setChave(no.getChave());
            
            if(valor < no.getPai().getChave()){
                sol.setEsquerda(no);
                sol.setDireita(no.getPai());
            }else{
                sol.setEsquerda(no.getPai());
                sol.setDireita(no);
            }
            
            return sol;
        }
    }
    
    public void destruir(){
        this.raiz = null;
    }
}
