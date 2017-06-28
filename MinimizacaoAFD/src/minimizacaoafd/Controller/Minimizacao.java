/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimizacaoafd.Controller;

import minimizacaoafd.Model.Afd;
import minimizacaoafd.Model.Tabela;
import minimizacaoafd.Persistence.AfdDAO;
import minimizacaoafd.Persistence.TabelaDAO;

/**
 *
 * @author Diego
 */
public class Minimizacao {
    private Afd afd;
    private Tabela tabelaMinimizacao;
    
    private final String nomeArqDescAfd;
    private final String nomeArqTabela;
    private final String nomeArqAfdMin;
    
    public Minimizacao(String nomeArqDescAfd, String nomeArqTabela, String nomeArqAfdMin){        
        this.nomeArqDescAfd = nomeArqDescAfd;
        this.nomeArqTabela = nomeArqTabela;
        this.nomeArqAfdMin = nomeArqAfdMin;
        //TODO inicializar afd e inicializar tabela
    }
    
    public void executar(){
        //TODO minimizar e salvar a tabela e o afd minimizado utilizando o AfdDAO e a TabelaDAO
    }
}
