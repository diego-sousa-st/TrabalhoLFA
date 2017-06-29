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
}
