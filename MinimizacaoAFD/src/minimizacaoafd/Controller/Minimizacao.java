/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimizacaoafd.Controller;

import java.io.IOException;
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
    
    public Minimizacao(String nomeArqDescAfd, String nomeArqTabela, String nomeArqAfdMin) throws IOException{        
        this.nomeArqDescAfd = nomeArqDescAfd;
        this.nomeArqTabela = nomeArqTabela;
        this.nomeArqAfdMin = nomeArqAfdMin;
        AfdDAO afdDao = new AfdDAO();
        afd = afdDao.openAfd(this.nomeArqDescAfd);
        tabelaMinimizacao = new Tabela();
    }
    
    public void executar(){
        //TODO minimizar e salvar a tabela e o afd minimizado utilizando o AfdDAO e a TabelaDAO
    }
    
    /**
     * Método privado que salva os resultados do algoritmo de minimização. Salva o AFD minimizado em um arquivo
     * e a tabela do algoritmo de minimização em um outro arquivo
     * @throws IOException Se der algum erro ao escrever no arquivo será retornado
     * uma excessão com a mensagem de erro
     */
    private void save() throws IOException{
        AfdDAO afdDao = new AfdDAO();
        afdDao.saveAfd(afd, nomeArqAfdMin);
        TabelaDAO tabelaDao = new TabelaDAO();
        tabelaDao.saveTabelaMinimizacao(tabelaMinimizacao, nomeArqTabela);
    }
}
