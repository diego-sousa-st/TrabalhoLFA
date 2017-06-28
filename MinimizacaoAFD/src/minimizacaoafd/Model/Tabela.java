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
    
    public void addLinha(Linha linha){
        linhas.add(linha);
    }
}
