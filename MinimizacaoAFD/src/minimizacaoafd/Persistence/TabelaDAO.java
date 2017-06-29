/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimizacaoafd.Persistence;

import java.io.FileWriter;
import java.io.IOException;
import minimizacaoafd.Model.Tabela;

/**
 * Classe de Acesso a Dados para a classe Tabela. Esta classe é responsável por
 * salvar os dados da tabela do algoritmo de minimização em arquivo texto.
 *
 * @author Diego
 */
public class TabelaDAO {

    /**
     * Método que salva em arquivo a tabela resultante do algoritmo de
     * minimização
     *
     * @param tabelaMinimizacao Objeto tabela resultante do algoritmo de
     * minimização
     * @param nomeArqTabela Nome do arquivo em que a tabela será salva
     * @throws IOException Se der algum erro ao escrever no arquivo será retornado
     * uma excessão com a mensagem de erro
     */
    public void saveTabelaMinimizacao(Tabela tabelaMinimizacao, String nomeArqTabela) throws IOException {
        try {
            FileWriter arquivoSaida = new FileWriter(nomeArqTabela);
            arquivoSaida.write(tabelaMinimizacao.toString());
            arquivoSaida.close();

        } catch (Exception e) {
            throw new IOException("Erro ao escrever no arquivo de tabela!");
        }
    }
}
