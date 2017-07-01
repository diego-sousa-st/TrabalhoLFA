/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimizacaoafd.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class Tabela {
    
    private List<Linha> linhas;
    
    public Tabela(){
        linhas = new ArrayList<Linha>();
    }
    
    /**
     * Método que insere uma nova linha na tabela do algoritmo de minimização
     * @param linha 
     */
    public void addLinha(Linha linha){
        linhas.add(linha);
    }
    
    @Override
    public String toString(){
       //TODO Maurício formatar a tabela toda bonita independente de como ficar
       return "";
    }
    
    /**
     * Retorna a linha na posição pos da tabela.
     * @param pos posição que se deseja procurar.
     * @return Linha procurada.
     */
    public Linha getLinha(int pos){
        return linhas.get(pos);
    }
    
    /**
     * Retorna todas as linhas da tabela.
     * @return lista com as linhas da tabela.
     */
    public List<Linha> getLinhas(){
        return linhas;
    }
    
    /**
     * Retorna a linha cujo pares forem iguais ao passados nos parametros.
     * 
     * @param a um dos estados do par.
     * @param b o outro estado do par.
     * @return Linha procurada.
     */
    public Linha getLinha(Estado a, Estado b){
        Linha resposta = null;
        for(Linha l: linhas){
            Estado la = l.getPar1();
            Estado lb = l.getPar2();
            if((la.equals(a) && lb.equals(b)) || (la.equals(b) && lb.equals(a))){
                resposta = l;
                break;
            }
        }
        return resposta;
    }
    
    public void exibirTabelaImprovisada(){
        for(Linha l: linhas){
            System.out.println("(" + l.getPar1() + "," + l.getPar2() + ") " + l.getPodeJuntar() + " - " + l.getMotivo());
        }
        
    }
}
