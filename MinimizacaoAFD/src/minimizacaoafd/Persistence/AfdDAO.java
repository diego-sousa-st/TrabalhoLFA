/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimizacaoafd.Persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import minimizacaoafd.Model.Afd;

/**
 *
 * @author Diego
 */
public class AfdDAO {

    private static final int TAB = 4;
    private static final int QUINTUPLA = 5;

    /**
     * Método que coloca cada dado importante no arquivo em um vetor com os
     * dados que o AFD precisa para ser inicializado. Por exemplo, dados[0]
     * refere ao conjunto de estados que encontra-se na primeira tupla e assim
     * por diante.
     *
     * @param nomeArqAfd Nome do arquivo que será lido os dados para a geração
     * do AFD
     * @return retorna um vetor de Strings representando a tupla do AFD
     * @throws IOException Se der algum erro ao ler do arquivo será retornado uma
     * excessão com a mensagem de erro
     */
    private String[] colocarConteudoArquivoNasStrings(String nomeArqAfd) throws IOException {
        try {
            FileReader arquivoEntrada = new FileReader(nomeArqAfd);
            BufferedReader arq = new BufferedReader(arquivoEntrada);
            String linha = arq.readLine();//le a primeira linha(inútil)
            String dados[] = new String[QUINTUPLA];
            String estados = arq.readLine();
            dados[0] = estados.substring(TAB, estados.length() - 1);
            String alfabetoDeEntrada = arq.readLine();
            dados[1] = alfabetoDeEntrada.substring(TAB, alfabetoDeEntrada.length() - 1);
            String transicoesAux = arq.readLine();
            transicoesAux = transicoesAux.substring(TAB);
            String transicoes = transicoesAux;
            boolean naoAchou = true;
            while (naoAchou) {
                transicoesAux = arq.readLine();
                if (transicoesAux.equals("    },")) {
                    transicoesAux = transicoesAux.substring(TAB);
                    naoAchou = false;
                } else {
                    transicoesAux = transicoesAux.substring(2 * TAB);
                }
                transicoes += "\n" + transicoesAux;
            }
            dados[2] = transicoes.substring(0, transicoes.length() - 1);
            String estadoInicial = arq.readLine();
            dados[3] = estadoInicial.substring(TAB, estadoInicial.length() - 1);
            String estadosFinais = arq.readLine();
            dados[4] = estadosFinais.substring(TAB);
            arq.close();
            return dados;
        } catch (Exception e) {
            throw new IOException("Erro ao ler do arquivo que contém a descricao do AFD!");
        }
    }

    /**
     * Método que abri um arquivo texto e retorna um Objeto AFD
     *
     * @param nomeArqAfd Nome do arquivo que encontra-se a descrição do AFD
     * @return Novo Objeto AFD
     * @throws IOException Se der algum erro ao ler do arquivo será retornado uma
     * excessão com a mensagem de erro
     */
    public Afd openAfd(String nomeArqAfd) throws IOException {
        String dadosBrutos[] = colocarConteudoArquivoNasStrings(nomeArqAfd);
        String estados = dadosBrutos[0];
        String alfabetoDeEntrada = dadosBrutos[1];
        String transicoes = dadosBrutos[2];
        String estadoInicial = dadosBrutos[3];
        String estadosFinais = dadosBrutos[4];
        return new Afd(estados, alfabetoDeEntrada, transicoes, estadoInicial, estadosFinais);
    }

    /**
     * Método que salve o afdMinimizado em um arquivo texto. Este método usa o
     * método toString para escrever no arquivo, então é necessário que o AFD
     * sobrescreva o método toString de forma a retornar uma Stringona que será
     * escrita no arquivo
     *
     * @param afd AFD Minimizado que será escrito no arquivo
     * @param nomeArqAfdMin Nome do arquivo em que será salvo o AFD minimizado
     * @throws IOException Se der algum erro ao escrever no arquivo será retornado
     * uma excessão com a mensagem de erro
     */
    public void saveAfd(Afd afd, String nomeArqAfdMin) throws IOException {
        try {
            FileWriter arquivoSaida = new FileWriter(nomeArqAfdMin);
            arquivoSaida.write(afd.toString());
            arquivoSaida.close();

        } catch (Exception e) {
            throw new IOException("Erro ao escrever no arquivo de AFD!");
        }
    }
}
