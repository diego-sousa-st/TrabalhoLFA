package minimizacaoafd.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um automato finito deterministico.
 *
 * @author Diego, Nechelley e Maurício
 */
public class Afd {

    //contem o conjunto de estados do AFD
    private List<Estado> estados;
    //contem o estado inicial, ou seja, onde o AFD começa
    private Estado estadoInicial;
    //contem os simbolos do alfabeto do AFD
    private List<String> alfabetoDeEntrada;
    //contem o conjunto de transições possiveis no AFD
    private List<Transicao> transicoes;

    public Afd(List<Estado> estados, Estado estadoInicial, List<String> alfabetoDeEntrada, List<Transicao> transicoes) {
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.alfabetoDeEntrada = alfabetoDeEntrada;
        this.transicoes = transicoes;
    }

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
        estadosFinais = estadosFinais.substring(1, estadosFinais.length() - 1);

        //divido as partes
        String estadosAjustados[] = todosOsEstados.split(",");
        String estadosFinaisAjustados[] = estadosFinais.split(",");

        //crio os estados e insiro eles na lista de estados
        this.estados = new ArrayList<Estado>();
        for (String x : estadosAjustados) { // para cada nome de estado.
            Boolean flag = true;
            for (String fin : estadosFinaisAjustados) {
                //verifico se o nome do estado é igual ao nome de um dos nomes de estado final
                if (x.equals(fin)) {
                    this.estados.add(new Estado(x, true));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                this.estados.add(new Estado(x, false));
            }
        }

        //ajustando estadoInicial
        this.estadoInicial = getEstado(estadoInicial);
    }

    /**
     * Ajusta o alfabeto de entrada no construtor
     *
     * @param s String com o alfabeto de entrada
     */
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
     * Ajusta as transições no construtor
     *
     * @param s String da tabela das transições
     */
    private void ajustaTransicoes(String s) {
        //retiro as chaves e o primeiro \n
        s = s.substring(2, s.length() - 2);

        //divido as partes, ou seja, cada movimento em si
        String transicoesAjustadas[] = s.split("\n");

        //Crio a lista
        this.transicoes = new ArrayList<Transicao>();

        for (int i = 0; i < transicoesAjustadas.length - 1; i++) {
            String aux = transicoesAjustadas[i];
            //(qx,w->qy),
            aux = aux.substring(1, aux.length() - 2);

            //qx,w->qy
            String pelaVirgula[] = aux.split(",");

            //[qx] [w->qy]
            String pelaSeta[] = pelaVirgula[1].split("->");

            //[qx] [ [w] [qy] ]
            this.transicoes.add(new Transicao(getEstado(pelaVirgula[0]), getEstado(pelaSeta[1]), pelaSeta[0]));
        }

        //ultima iteracao
        String aux = transicoesAjustadas[transicoesAjustadas.length - 1];
        //(qx,w->qy),
        aux = aux.substring(1, aux.length() - 1);

        //qx,w->qy
        String pelaVirgula[] = aux.split(",");

        //[qx] [w->qy]
        String pelaSeta[] = pelaVirgula[1].split("->");

        //[qx] [ [w] [qy] ]
        this.transicoes.add(new Transicao(getEstado(pelaVirgula[0]), getEstado(pelaSeta[1]), pelaSeta[0]));
    }

    /**
     * Adiciona o estado e ao afd.
     *
     * @param e
     */
    public void addEstado(Estado e) {
        this.estados.add(e);
    }

    /**
     * Adisiona a transicao t a lista de transicoes do afd.
     *
     * @param t
     */
    public void addTransicao(Transicao t) {
        this.transicoes.add(t);
    }

    /**
     * Método que retorna o conjunto de estados do AFD
     *
     * @return
     */
    public List<Estado> getEstados() {
        return estados;
    }

    /**
     * Método que retorna o estado inicial do AFD
     *
     * @return
     */
    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    /**
     * Método que retorna o alfabeto de entrada do AFD
     *
     * @return
     */
    public List<String> getAlfabetoDeEntrada() {
        return alfabetoDeEntrada;
    }

    /**
     * Método que retorna o conjunto de transições do AFD
     *
     * @return
     */
    public List<Transicao> getTransicoes() {
        return transicoes;
    }

    /**
     * Retorna todas as transicoes partindo do estado e, lendo qualquer simbolo,
     * indo para qualquer lugar.
     *
     * @param e estado para se buscar as transicoes
     * @return retorna um objeto Transicao caso exista e null caso contrário
     */
    public List<Transicao> getTransicoes(Estado e) {
        List<Transicao> resposta = new ArrayList<Transicao>();
        for (Transicao t : this.transicoes) {
            if ((e.equals(t.getEstadoAtual()))) {
                resposta.add(t);
            }
        }
        return resposta;
    }

    /**
     * Método que retorna o estadoAposTransicao com base no estadoAtual passado
     * e, e no simbolo lido.
     *
     * @param e estado inicial.
     * @param simboloLido string lida para a trasicao ocorrer
     * @return retorna o Estado caso exista e null caso contrário
     */
    public Estado getTransicao(Estado e, String simboloLido) {
        for (Transicao transicao : this.transicoes) {
            if ((e.equals(transicao.getEstadoAtual())) && simboloLido.equals(transicao.getSimboloLido())) {//confirmo se oque leu para mover é igual
                return transicao.getEstadoAposTransicao();
            }
        }
        return null;
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
     * Método que retorna uma representação do AFD em formato de String. O
     * formato segue o padrão apresentado no enunciado do trabalho de LFA
     *
     * @return String que representa o AFD
     */
    @Override
    public String toString() {
        //incializa a string e gera o conjunto de estados
        String TAB = "\t";
        String afd = "(\n" + TAB;
        //adiciona o conjunto de estados
        afd += stringConjuntoDeEstados();
        afd += ",\n";
        //adiciona o conjunto com o alfabeto de entrada
        afd += TAB + stringAlfabetoDeEntrada();
        afd += ",\n";
        //adiciona o conjunto com todas as transições
        afd += TAB + stringTransicoes(TAB);
        afd += ",\n";
        //adiciona o estado inicial
        afd += TAB + estadoInicial.getNome() + ",\n";
        //adiciona o conjunto de estados finais
        afd += TAB + stringEstadosFinais();
        afd += "\n)";
        return afd;
    }

    /**
     * Método que retorna uma string represenando o conjunto de estados. Tem a
     * forma {q1,q2,...,qn}
     *
     * @return Uma String representando o conjunto de estados
     */
    private String stringConjuntoDeEstados() {
        String conjuntoEstados = "{";
        for (Estado estado : estados) {
            conjuntoEstados += estado.getNome() + ",";
        }
        //retiro a ultima virgula
        conjuntoEstados = conjuntoEstados.substring(0, conjuntoEstados.length() - 1);
        conjuntoEstados += "}";
        return conjuntoEstados;
    }

    /**
     * Método que retorna uma string represenando o alfabeto de entrada. Tem a
     * forma {a,b,...,z}
     *
     * @return
     */
    private String stringAlfabetoDeEntrada() {
        String conjuntoAlfabetoEntrada = "{";
        for (String simbolo : alfabetoDeEntrada) {
            conjuntoAlfabetoEntrada += simbolo + ",";
        }
        //retiro a ultima virgula
        conjuntoAlfabetoEntrada = conjuntoAlfabetoEntrada.substring(0, conjuntoAlfabetoEntrada.length() - 1);
        conjuntoAlfabetoEntrada += "}";
        return conjuntoAlfabetoEntrada;
    }

    /**
     * Método que retorna uma string represenando o conjunto de transições. Tem
     * a forma
     *
     * {
     * (q1,a->q2), (q1,b->q3), ... (qn,a->qk) }
     *
     * @param TAB String representado o tab , podendo ser "\t" ou " ". "\t" é
     * mais aconselhável.
     * @return string representando o conjunto de transições.
     */
    private String stringTransicoes(String TAB) {
        String stringTransicoes = "{\n";
        for (Transicao transicao : transicoes) {
            stringTransicoes += TAB + TAB + "(" + transicao.getEstadoAtual().getNome() + "," + transicao.getSimboloLido()
                    + "->" + transicao.getEstadoAposTransicao().getNome() + "),\n";
        }
        //retiro a ultima virgula
        stringTransicoes = stringTransicoes.substring(0, stringTransicoes.length() - 2);
        stringTransicoes += "\n" + TAB + "}";
        return stringTransicoes;
    }

    /**
     * Método que retorna uma string representado os estados finais. Tem a forma
     * {q1,q2,qk}, onde cada estado é final
     *
     * @return Uma string representado os estados finais.
     */
    private String stringEstadosFinais() {
        String stringEstadosFinais = "{";
        for (Estado estado : estados) {
            if (estado.getEhFinal()) {
                stringEstadosFinais += estado.getNome() + ",";
            }
        }
        //retiro ultima vírgula
        stringEstadosFinais = stringEstadosFinais.substring(0, stringEstadosFinais.length() - 1);
        stringEstadosFinais += "}";
        return stringEstadosFinais;
    }
}
