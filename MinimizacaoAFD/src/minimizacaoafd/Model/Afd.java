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

    public Afd(String estados, String alfabetoDeEntrada, String transicoes, String estadoInicial, String estadosFinais) {
        ajustaEstados(estados, estadoInicial, estadosFinais);
        ajustaAlfabetoDeEntrada(alfabetoDeEntrada);
        ajustaTransicoes(transicoes);
    }
    
    public Afd(List<Estado> estados, Estado estadoInicial, List<String> alfabetoDeEntrada, List<Transicao> transicoes){
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.alfabetoDeEntrada = alfabetoDeEntrada;
        this.transicoes = transicoes;
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
            for (String fin: estadosFinaisAjustados) {
                //verifico se o nome do estado é igual ao nome de um dos nomes de estado final
                if (x.equals(fin)) {
                    this.estados.add(new Estado(x,true));
                    flag = false;
                    break;
                }
            }
            if(flag){
                this.estados.add(new Estado(x,false));
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
        
        for(int i = 0; i < transicoesAjustadas.length - 1; i++){
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
        
        /*
        //retiro as chaves e o primeiro \n
        s = s.substring(2, s.length() - 2);

        //divido as partes, ou seja, cada movimento em si
        String transicoesAjustadas[] = s.split("\n");

        //divido o vetor denovo para poder saber oque esta antes e oque esta depois da "->"
        //desta forma cada linha da matriz representa um movimento
        //e a coluna 0 possui a parte 1 e a coluna 1 possui a parte 2
        String transicoesMatriz[][] = new String[transicoesAjustadas.length][2];
        int contLinha = 0;
        for (String x : transicoesAjustadas) {
            transicoesMatriz[contLinha] = x.split("->");
            contLinha++;
        }

        //Crio a lista
        this.transicoes = new ArrayList<Transicao>();
        //crio os vetores auxiliares para colocar os fragmentos significativos de cada string da parte 1 e 2
        String vetorAuxDaParte1[] = new String[2];//contera o estado de origem e o que deve ler para se mover
        String estadoAposTransicao;//contera para que estado ir
        //percorro cada linha da matriz extraindo as informacoes necessarias
        for (int i = 0; i < contLinha; i++) {
            //String contendo a parte um da String
            String estado_simbolo = transicoesMatriz[i][0];

            //retiro os parenteses da primeira parte
            estado_simbolo = estado_simbolo.substring(1, estado_simbolo.length());

            //divido a informacao da primeira parte em duas, na posicao 0 tem o estado e na posicao 1 tem oque deve ler para mover
            vetorAuxDaParte1 = estado_simbolo.split(",");
            String estadoAntesTransicao = vetorAuxDaParte1[0];
            String simboloAserLido = vetorAuxDaParte1[1];
            //String contendo a parte dois da movimentacao
            estadoAposTransicao = transicoesMatriz[i][1];

            //retiro os parenteses da segunda parte
            estadoAposTransicao = estadoAposTransicao.substring(0, estadoAposTransicao.length() - 1);
            //------ TALVEZ NÃO É NECESSÁRIO --------
            //neste ponto verifico se tem algum parenteses sobrando e retiro ele
            if (estadoAposTransicao.length() > 1) {
                estadoAposTransicao = estadoAposTransicao.substring(0, 1);
            }
            //crio e adiciono a transicao na lista de transições
            this.transicoes.add(new Transicao(getEstado(estadoAntesTransicao), getEstado(estadoAposTransicao), simboloAserLido));
        }
        */
    }

    
    /**
     * Adiciona o estado e ao afd.
     * @param e 
     */
    public void addEstado(Estado e){
        this.estados.add(e);
    }
    
    /**
     * Adisiona a transicao t a lista de transicoes do afd.
     * @param t 
     */
    public void addTransicao(Transicao t){
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
     * Retorna todas as transicoes do estado e
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
     * Método que retorna um objeto que reprenta a transicao ao se ler um
     * simbolo, estando no Estado e.
     * 
     * @param e estado inicial.
     * @param simboloLido string lida para a trasicao ocorrer
     * @return retorna um objeto Transicao caso exista e null caso contrário
     */
    public Transicao getTransicao(Estado e, String simboloLido) {
        for (Transicao transicao : this.transicoes) {
            if ((e.equals(transicao.getEstadoAtual())) && simboloLido.equals(transicao.getSimboloLido())) {//confirmo se oque leu para mover é igual
                return transicao;
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
        String afd = "(\n" + TAB + "{";
        for (Estado estado : estados) {
            afd += estado.getNome() + ",";
        }
        afd += "},\n";
        //gera o conjunto com o alfabeto de entrada
        afd += TAB + "{";
        for (String simbolo : alfabetoDeEntrada) {
            afd += simbolo + ",";
        }
        afd += "},\n";
        //gera o conjunto com todas as transições
        afd += TAB + "{\n";
        for (Transicao transicao : transicoes) {
            afd += TAB + TAB + "(" + transicao.getEstadoAtual().getNome() + "," + transicao.getSimboloLido()
                    + "->" + transicao.getEstadoAposTransicao().getNome() + "),\n";
        }
        afd += TAB + "},";
        //gera o estado inicial
        afd += TAB + estadoInicial.getNome() + ",\n";
        //gera o conjunto de estados finais
        afd += TAB + "{";
        for (Estado estado : estados) {
            if (estado.getEhFinal()) {
                afd += estado.getNome() + ",";
            }
        }
        //retirar ultima vírgula
        afd = afd.substring(0, afd.length() - 1);
        afd += "}\n)";
        return afd;
    }
}
