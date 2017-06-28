/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimizacaoafd;

import minimizacaoafd.Controller.Minimizacao;

/**
 * Classe principal que apenas invoca o metódo para executar a minimização
 * @author Diego, Nechelley e Maurício
 */
public class MinimizacaoAFD {

    /**1º parametro - Nome do arquivo descrição do AFD
     * 2º parametro - Nome do arquivo que será salvo a tabela de minimização
     * 3º parametro - Nome do arquivo que será salvo a AFD minimizado
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Minimizacao minimizador = new Minimizacao(args[0], args[1], args[2]);
        minimizador.executar();
    }
    
}