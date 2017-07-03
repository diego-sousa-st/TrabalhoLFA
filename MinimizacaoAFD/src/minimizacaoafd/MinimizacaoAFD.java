package minimizacaoafd;

import minimizacaoafd.Controller.Minimizacao;

/**
 * Classe principal que apenas invoca o metódo para executar a minimização
 *
 * @author Diego, Nechelley e Maurício
 */
public class MinimizacaoAFD {

    /**
     * 1º parametro - Nome do arquivo descrição do AFD 2º parametro - Nome do
     * arquivo que será salvo a tabela de minimização 3º parametro - Nome do
     * arquivo que será salvo a AFD minimizado
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Minimizacao minimizador = new Minimizacao("C:\\Users\\Diego\\Documents\\NetBeansProjects\\TrabalhoLFA\\MinimizacaoAFD\\desc1.txt", "" , "");
            Minimizacao minimizador = new Minimizacao("C:\\Users\\neche\\Documents\\GitHub\\TrabalhoLFA\\MinimizacaoAFD\\desc1.txt", "" , "");
            minimizador.executar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
