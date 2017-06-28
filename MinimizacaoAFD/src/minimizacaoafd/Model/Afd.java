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
public class Afd {

    //contem o conjunto de estados do AFD
    private List<Estado> estados;
    //contem o estado atual do AFD
    private Estado estadoAtual;
    //contem o estado inicial, ou seja, onde o AFD começa
    private Estado estadoInicial;
    //contem os simbolos do alfabeto do AFD
    private List<String> alfabetoDeEntrada;
    //contem o conjunto de transições possiveis no AFD
    private List<Transicao> transicoes;

    public Afd(String estados, String alfabetoDeEntrada, String transicoes, String estadoInicial, String estadosFinais) {
        ajustaEstados(estados, estadoInicial, estadosFinais);
        ajustaAlfabetoDeEntrada(alfabetoDeEntrada);
        ajustaTransicoes(transicoes);
    }

    /**
     * Ajusta os Estados no construtor
     *
     * @param todosOsEstados String com todos os estados
     * @param estadoInicial String dizendo qual o estado inicial
     * @param estadosFinais String dizendo qual ou quais sao o(os) Estados
     * finais
     */
    private void ajustaEstados(String todosOsEstados, String estadoInicial, String estadosFinais) {
        //retiro as chaves
        todosOsEstados = todosOsEstados.substring(1, todosOsEstados.length() - 1);

        //divido as partes
        String estadosAjustados[] = todosOsEstados.split(",");

        //crio os estados e insiro eles na lista de estados
        this.estados = new ArrayList<Estado>();
        for (String x : estadosAjustados) {
            this.estados.add(new Estado(x));
        }

        //ajustando estadoInicial
        this.estadoInicial = getEstado(estadoInicial);

        //ajustando estadoAtual
        this.estadoAtual = this.estadoInicial;

        //ajustando estados finais
        //retiro as chaves
        estadosFinais = estadosFinais.substring(1, estadosFinais.length() - 1);

        //divido as partes
        String estadosFinaisAjustados[] = estadosFinais.split(",");

        //percorro todos os estados
        for (Estado x : this.estados) {
            //percorro todos os nomes de estados finais
            for (int i = 0; i < estadosFinaisAjustados.length; i++) {
                //verifico se o nome do estado é igual ao nome de um dos nomes de estado final
                if (x.getNome().equals(estadosFinaisAjustados[i])) {
                    x.setEhFinal(true);
                }
            }
        }
    }
    
    /**
     * Ajusta o alfabeto de entrada no construtor
     *
     * @param s String com o alfabeto de entrada
     */
    //AJUSTAR ESTE MÉTODO PORQUE ALGUMAS COISAS MUDARAM NA DESCRIÇÃO
    private void ajustaAlfabetoDeEntrada(String s) {
        //retiro as chaves
        s = s.substring(1, s.length() - 1);

        //divido as partes
        String alfabetoDeEntradaAjustado[] = s.split(",");

        //insiro cada caracter na lista
        this.alfabetoDeEntrada = new ArrayList<String>();
        for (String x : alfabetoDeEntradaAjustado) {
            this.alfabetoDeEntrada.add(x);
        }
    }         

    /**
     * Ajusta as transições no contrutor
     *
     * @param s String da tabela das transições
     */
    private void ajustaTransicoes(String s) {
        //retiro as chaves e o primeiro \n
        s = s.substring(2, s.length() - 2);

        //divido as partes, ou seja, cada movimento em si
        String movimentosAjustado[] = s.split("\n");

        //divido o vetor denovo para poder saber oque esta antes e oque esta depois da "->"
        //desta forma cada linha da matriz representa um movimento
        //e a coluna 0 possui a parte 1 e a coluna 1 possui a parte 2
        String movimentosMatriz[][] = new String[movimentosAjustado.length][2];
        int contLinha = 0;
        for (String x : movimentosAjustado) {
            movimentosMatriz[contLinha] = x.split("->");
            contLinha++;
        }

        //Crio a lista
        this.transicoes = new ArrayList<Transicao>();
        //crio os vetores auxiliares para colocar os fragmentos significativos de cada string da parte 1 e 2
        String vetorAuxDaParte1[] = new String[2];//contera o estado de origem e o que deve ler para se mover
        String vetorAuxDaParte2[] = new String[3];//contera para que estado ir
        //percorro cada linha da matriz extraindo as informacoes necessarias
        for (int i = 0; i < contLinha; i++) {
            //String contendo a parte um da String
            String m = movimentosMatriz[i][0];

            //retiro os parenteses da primeira parte
            m = m.substring(1, m.length() - 1);

            //divido a informacao da primeira parte em duas, na posicao 0 tem o estado e na posicao 1 tem oque deve ler para mover
            vetorAuxDaParte1 = m.split(",");

            //String contendo a parte dois da movimentacao
            String m2 = movimentosMatriz[i][1];

            //retiro os parenteses da segunda parte
            m2 = m2.substring(1, m2.length() - 1);

            //divido a informacao da segunda parte em tres, na posicao 0 tem o estado para onde ir ,na posicao 1 tem oque se deve marcar 
            //e na posicao 2 tem a direcao para onde mover a cabeça de leitura
            vetorAuxDaParte2 = m2.split(",");

            //neste ponto verifico se tem algum parenteses sobrando e retiro ele
            if (vetorAuxDaParte2[2].length() > 1) {
                vetorAuxDaParte2[2] = vetorAuxDaParte2[2].substring(0, 1);
            }
            //crio e adiciono o movimento a lista de movimentos
            this.transicoes.add(new Transicao(getEstado(vetorAuxDaParte1[0]), getEstado(vetorAuxDaParte2[0]), vetorAuxDaParte1[0]));
        }
    }

    /**
     * Busca um Estado pelo nome dentro do AFD e o retorna
     *
     * @param nome String com o nome do Estado procurado
     * @return Estado buscado
     */
    public Estado getEstado(String nome) {
        for (Estado s : this.estados) {
            if (s.getNome().equals(nome)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Verifica se um simbolo s pertence ao alfabeto.
     *
     * @param s simbolo a ser verificada
     * @return boolean onde true-pertence e false-não pertence
     */
    private boolean simboloPertenceAoAlfabeto(String s) {
        for (String x : alfabetoDeEntrada) {
            if (x.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que retorna um objeto que reprenta a transicao ao se ler um
     * simbolo
     *
     * @param simboloLido string lida na fita de entrada
     * @return retorna um objeto Transicao caso exista e null caso contrário
     */
    private Transicao getTransicao(String simboloLido) {
        for (Transicao transicao : this.transicoes) {
            if (estadoAtual.equals(transicao.getEstadoAtual())) {//confirmo se é o mesmo estado
                if (simboloLido.equals(transicao.getSimboloLido())) {//confirmo se oque leu para mover é igual
                    return transicao;
                }
            }
        }
        return null;
    }

    /**
     * Método que altera o estado atual do AFD
     *
     * @param movimento objeto movimento passado como parametro para se alterar
     * o movimento
     */
    private void setEstadoAtual(Transicao movimento) {
        estadoAtual = movimento.getEstadoAposTransicao();
    }
}