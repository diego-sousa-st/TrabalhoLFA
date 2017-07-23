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
            Minimizacao minimizador = new Minimizacao(args[0],args[1],args[2]);
            minimizador.executar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
