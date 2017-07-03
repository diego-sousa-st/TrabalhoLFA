package minimizacaoafd.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import minimizacaoafd.Model.Afd;
import minimizacaoafd.Model.Estado;
import minimizacaoafd.Model.Linha;
import minimizacaoafd.Model.Tabela;
import minimizacaoafd.Model.Transicao;
import minimizacaoafd.Persistence.AfdDAO;
import minimizacaoafd.Persistence.TabelaDAO;

/**
 *
 * @author Diego, Nechelley e Maurício
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
    
    /**
     * Minimiza o afd, gera a tabela, salva o afd minimizado em um arquivo, salva a tabela gerada em um arquivo.
     */
    public void executar(){
        // gerar tabela inicial que ja contem a questao dos estados finais com nao finais.
        geraTabela();
        
        //processo de minimizacao em cima da tabela.
        minimizandoTabela();
        
        // atualizando o afd.
        atualizandoAfd();
        System.out.println(afd.toString());
    }
    
    /**
     * Gera a tabela inicial do algoritmo e também ja diz quais indices sao estados finais com nao finais.
     */
    private void geraTabela(){
        List<Estado> estados = afd.getEstados();
        int qntEstados = estados.size();
        for(int i = 0; i < qntEstados - 1; i++){
            for(int j = i+1; j < qntEstados; j++){
                Estado a = estados.get(i);
                Estado b = estados.get(j);
                
                if(a.getEhFinal() ^ b.getEhFinal()){ // estado final com nao final.
                    tabelaMinimizacao.addLinha(new Linha(a,b,false,"final/nao-final"));
                }else{
                    tabelaMinimizacao.addLinha(new Linha(a,b));
                }
            }
        }
    }
    
    /**
     * Aplica o algoritmo de minimizacao em cima da tabela.
     */
    private void minimizandoTabela(){
        for(Linha l: tabelaMinimizacao.getLinhas()){ // cada linha da tabela.
            if(!l.getMotivo().equals("")){
                continue;
            }
            Estado a = l.getPar1();
            Estado b = l.getPar2();
            
            for(String simb: afd.getAlfabetoDeEntrada()){ // verifico para cada simbolo aonde os estados vao.
                Estado ondeAVai = afd.getTransicao(a, simb).getEstadoAposTransicao();
                Estado ondeBVai = afd.getTransicao(b, simb).getEstadoAposTransicao();
                
                // achar linha.
                Linha paraOndeVai = tabelaMinimizacao.getLinha(ondeAVai, ondeBVai);
                if(paraOndeVai == null || paraOndeVai == l){
                    continue;
                }
                if(paraOndeVai.getPodeJuntar()){
                    // siginifica que os dois estados podem teoricamente se juntar, porem deve-se colocar eles na lista de dependencias desta linha.
                    paraOndeVai.inserirDependente(l); // dizer que a linha l depende de paraOndeVai.
                }else{
                    // caso contrario, eles não vao para o mesmo lugar pelo motivo simb, e entao sua dependencias devem ser tratadas.
                    resolverDependencias(l, simb + "[" + paraOndeVai.getPar1().getNome() + "," + paraOndeVai.getPar2().getNome() + "]");
                    break;
                }
            }
        }
    }
    
    /**
     * Resolve as dependencias da linha l .
     * 
     * @param l linha que nao pode se juntar e suas dependencias devem ser resolvidas.
     * @param motivo motivo pelo qual a linha l nao pode se juntar.
     */
    private void resolverDependencias(Linha l, String motivo){
        if(l.getPodeJuntar()){
            // resolve a linha atual.
            l.setMotivo(motivo);
            l.setPodeJuntar(false);
            // resolve as linhas que dependiam desta.
            for(Linha dependencia: l.getDependentes()){
                resolverDependencias(dependencia, "prop" + "[" + l.getPar1().getNome() + "," + l.getPar2().getNome() + "]");
            }
        }
    }
    
    /**
     * Atualiza o afd com base na tabela de minimizacao.
     */
    private void atualizandoAfd(){
        List<HashSet> conjuntosDeEstadosJuntaveis = new ArrayList<HashSet>(); // lista de conjuntos
        for(Linha l: tabelaMinimizacao.getLinhas()){
            if(l.getPodeJuntar()){
                Boolean flag = true;
                Estado a = l.getPar1();
                Estado b = l.getPar2();
                // verifico se um dos estados ja esta no conjunto.
                for(HashSet conjunto: conjuntosDeEstadosJuntaveis){
                    if(conjunto.contains(a) || conjunto.contains(b)){
                        conjunto.add(a);
                        conjunto.add(b);
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    HashSet conj = new HashSet();
                    conj.add(a);
                    conj.add(b);
                    conjuntosDeEstadosJuntaveis.add(conj);
                }
            }
        }
        
        // neste ponto todos os estados juntaveis estao em um conjunto.
        
        // agora crio uma lista com os novos estados e digo de que estados sao feitos
        List<Estado> novosEstados = new ArrayList<Estado>();
        List<ArrayList> doQueSaoFormadosOsNovosEstados = new ArrayList<ArrayList>();
        for(HashSet conjunto: conjuntosDeEstadosJuntaveis){
            // criar o novo estado.
            Estado novoEstado = new Estado(gerarNovoNome(conjunto),gerarNovoEhFinal(conjunto));
            novosEstados.add(novoEstado);
            
            // dizer doq e feito o novo estado
            ArrayList<Estado> x = new ArrayList<Estado>();
            for(Object e: conjunto){
                x.add((Estado) e);
            }
            doQueSaoFormadosOsNovosEstados.add(x);
        }
        
        //agora tenho todos os novos estados e do que sao feitos
        
        //ARRUMANDO LISTA DE ESTADOS
        List<Estado> estados = new ArrayList<Estado>();
        for(Estado e: afd.getEstados()){ // todos os estados do afd original
            Estado x = tornarEmNovoEstado(e,doQueSaoFormadosOsNovosEstados,novosEstados);
            
            // verifico se x ja nao esta na lista de estados do novo afd
            Boolean flag = true;
            for(Estado y: estados){
                if(x.equals(y)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                estados.add(x);
            }
        }
        
        //ARRUMANDO ALFABETO DE ENTRADA
        //permanece o mesmo
        
        //ARRUMANDO TRANSICOES
        List<Transicao> transicoes = new ArrayList<Transicao>();
        for(Transicao t: afd.getTransicoes()){
            //atual = ou continua o mesmo estado ou vira um novo estado formado
            Estado atual = tornarEmNovoEstado(t.getEstadoAtual(),doQueSaoFormadosOsNovosEstados,novosEstados);
            //simb
            String simb = t.getSimboloLido();
            //alvo
            Estado alvo = tornarEmNovoEstado(t.getEstadoAposTransicao(),doQueSaoFormadosOsNovosEstados,novosEstados);
            //LEMBRAR DE NESTE PONTO VERIFICAR SE EXISTE UMA TRANSICAO IGUAL A ATUAL
            Transicao transicaoAtual = new Transicao(atual,alvo,simb);
            Boolean flag = true;
            for(Transicao ts: transicoes){
                if(transicaoAtual.equals(ts)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                transicoes.add(transicaoAtual);
            }
        }
        
        //ARRUMANDO ESTADO INICIAL
        Estado inicial = tornarEmNovoEstado(afd.getEstadoInicial(),doQueSaoFormadosOsNovosEstados,novosEstados);
        
        //ARRUMANDO LISTAESTADOS FINAIS
        //nao precisa arruma
        
        afd = new Afd(estados, inicial, afd.getAlfabetoDeEntrada(), transicoes);
    }
    
    /**
     * Dado o estado ele verifica se ele e formador de um novo estado, caso seja e retornado o estado que ele se torna,
     * senao e retornado ele mesmo.
     * 
     * @param e
     * @param doQueSaoFormadosOsNovosEstados
     * @param novosEstados
     * @return 
     */
    private Estado tornarEmNovoEstado(Estado e, List<ArrayList> doQueSaoFormadosOsNovosEstados, List<Estado> novosEstados){
        //descobrir se o estado atual e um formador
        for(int i = 0; i < doQueSaoFormadosOsNovosEstados.size(); i++){
            for(Object x: doQueSaoFormadosOsNovosEstados.get(i)){
                if(e.equals(x)){// o estado e um formador
                    return novosEstados.get(i);
                }
            }
        }
        return e;
    }
    
    private String gerarNovoNome(HashSet conjunto){
        // precisa garantir que o numero do nome esteja ordenado.
        String s = "q";
        int v[] = new int[conjunto.size()];
        int cont = 0;
        for(Object e : conjunto){ // cada estado do conjunto
            v[cont] = Integer.parseInt(((Estado)e).getNome().substring(1));
            cont += 1;
        }
        Arrays.sort(v);
        
        for(int i: v){
            s += String.valueOf(i);
        }
        return s;
    }
    
    private Boolean gerarNovoEhFinal(HashSet conjunto){
        for(Object e : conjunto){ // cada estado do conjunto
            return ((Estado)e).getEhFinal();
        }
        return null;
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
