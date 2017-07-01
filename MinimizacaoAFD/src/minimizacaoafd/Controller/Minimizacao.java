/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimizacaoafd.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import minimizacaoafd.Model.Afd;
import minimizacaoafd.Model.Estado;
import minimizacaoafd.Model.Linha;
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
    }
    
    /**
     * Gera a tabela inicial do algoritmo e também ja diz quais indices sao estados finais com nao finais.
     */
    private void geraTabela(){
        int qntEstados = afd.getEstados().size();
        for(int i = 0; i < qntEstados - 1; i++){
            for(int j = i+1; j < qntEstados; j++){
                Estado a = afd.getEstados().get(i);
                Estado b = afd.getEstados().get(j);
                
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
            Estado a = l.getPar1();            
            Estado b = l.getPar2();
            
            for(String simb: afd.getAlfabetoDeEntrada()){ // verifico para cada simbolo aonde os estados vao.
                Estado ondeAVai = afd.getTransicao(a, simb).getEstadoAposTransicao();
                System.out.println(ondeAVai.getNome());
                Estado ondeBVai = afd.getTransicao(b, simb).getEstadoAposTransicao();
                
                // achar linha.
                Linha paraOndeVai = tabelaMinimizacao.getLinha(ondeAVai, ondeBVai);
                if(paraOndeVai == null || paraOndeVai.getPodeJuntar()){
                    // siginifica que os dois estados podem teoricamente se juntar, porem deve-se colocar eles na lista de dependencias desta linha.
                    paraOndeVai.inserirDependente(l); // dizer que a linha l depende de paraOndeVai.
                }else{
                    // caso contrario, eles não vao para o mesmo lugar pelo motivo simb, e entao sua dependencias devem ser tratadas.
                    resolverDependencias(l, simb + "[" + paraOndeVai.getPar1().getNome() + "," + paraOndeVai.getPar2().getNome() + "]");
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
        
        for(HashSet conjunto: conjuntosDeEstadosJuntaveis){
            // criar o novo estado.
            // criar todos as trasicoes para ele.PROBLEMA: SE UM ESTADO CONJUNTO VAI PARA UM ESTADO Q ATUALMENTE TB E UM ESTADO CONJUNTO, COMO O ESTADO 1 CONSEGUIRA DESCOBRIR Q POR EXEMPLO O ESTADO 4 Q ELE QER IR VIROU O ESTADO 456??
            //R: o nomeconcatena tudo, testa com subtrsgs
            // remover todas as trasicoes dos estados antigos.
            // remover os estados antigos.
        }
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
