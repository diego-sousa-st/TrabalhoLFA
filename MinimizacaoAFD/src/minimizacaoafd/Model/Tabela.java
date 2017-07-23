package minimizacaoafd.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a tabela sobre a qual o algoritmo de minimizacao ocorrera.
 * 
 * @author Diego, Nechelley e Maurício
 */
public class Tabela {
    
    private List<Linha> linhas;
    
    public Tabela(){
        linhas = new ArrayList<Linha>();
    }
    
    /**
     * Método que insere uma nova linha na tabela do algoritmo de minimização
     * @param linha 
     */
    public void addLinha(Linha linha){
        linhas.add(linha);
    }
    
    
    /**
     * Retorna todas as linhas da tabela.
     * @return lista com as linhas da tabela.
     */
    public List<Linha> getLinhas(){
        return linhas;
    }
    
    /**
     * Retorna a linha cujo pares forem iguais ao passados nos parametros,
     * a ordem dos estados e irrelevante.
     * 
     * @param a um dos estados do par.
     * @param b o outro estado do par.
     * @return Linha procurada.
     */
    public Linha getLinha(Estado a, Estado b){
        for(Linha l: linhas){
            Estado la = l.getPar1();
            Estado lb = l.getPar2();
            if((la.equals(a) && lb.equals(b)) || (la.equals(b) && lb.equals(a))){
                return l;
            }
        }
        return null;
    }
    
    /**
     * Método que calcula a maior qtd de dependentes para formatar a tabela
     * @return 
     */
    private int getNumMaxDependentes(){
        int maxDependentes = 0;
        for(Linha linha : linhas){
            int qtdDependentes = linha.getDependentes().size();
            if(qtdDependentes > maxDependentes){
                maxDependentes = qtdDependentes;
            }
        }
        return maxDependentes;
    }
    
    /**
     * Método que retorna uma string com os tabs necessarios para formatar a 
     * tabela direito
     * @return 
     */
    private String getTabs(int qtd){
        String tab = "";
        for (int i = 0; i < qtd; i++) {
            tab += "        ";
        }
        return tab;
    }
    
    /**
     * Método que monta o cabecalho da tabela
     * @param maxDependentes
     * @param TAB
     * @return 
     */
    private String cabecalhoTabela(int maxDependentes,String TAB){
        String cabecalho = TAB;
        String tabsNecessarios = getTabs(Math.floorDiv(maxDependentes,2)+1);
        cabecalho += "INDICE" + TAB + "D[i,j]" + TAB + tabsNecessarios + "S[i,j]" + tabsNecessarios + TAB + "MOTIVO" + "\n";
        return cabecalho;
    }
    
    /**
     * Método que retorna a string "1" se os estados não puderem juntar e "0" caso contrário
     * @param podeJuntar
     * @return 
     */
    private String stringPodeJuntar(boolean podeJuntar){
        if(podeJuntar)
            return "   0    ";
        return "   1    ";
    }        
    
    /**
     * Método que retorna os dependentes de uma linha no formato String
     * @param linha
     * @return 
     */
    private String getDependentes(Linha linha){
        List<Linha> linhasDependentes = linha.getDependentes();
        String dependentes = "";
        for(Linha linhaDependente : linhasDependentes){
            dependentes += "[" + linhaDependente.getPar1().getNome() + "," + linhaDependente.getPar2().getNome() + "]" + ",";
        }
        //retiro a ultima virgula
        if(!dependentes.equals("")){
            dependentes = dependentes.substring(0, dependentes.length()-1);
        }            
        return dependentes;
    }
    
    /**
     * Método que retorna uma linha da tabela no formato String que será salva no arquivo
     * @param linha
     * @param maxDependentes
     * @param TAB
     * @return 
     */
    private String linhaToString(Linha linha,int maxDependentes,String TAB){
        int qtdDependentesLinha = linha.getDependentes().size();
        String tabsNecessarios = getTabs(Math.floorDiv(maxDependentes-qtdDependentesLinha,2)+1);
        String linhaTabela = TAB;
        linhaTabela += "[" + linha.getPar1().getNome() + ","+ linha.getPar2().getNome() +"]";
        linhaTabela += TAB + stringPodeJuntar(linha.getPodeJuntar());        
        linhaTabela += TAB + tabsNecessarios + "{" + getDependentes(linha) + "}" + tabsNecessarios;
        linhaTabela += TAB + linha.getMotivo() + "\n";
        return linhaTabela;
    }
    
    /**
     * Método toString sobreescrito para que a tabela seja salva em arquivo. O formato
     * dessa string é o mesmo mostrado na descrição do trabalho de LFA.
     * @return 
     */
    @Override
    public String toString(){
        int maxDependentes = getNumMaxDependentes();
        String tabela = "";
        String TAB = "        ";
        tabela += cabecalhoTabela(maxDependentes,TAB);
        for(Linha linha : linhas){
            tabela += linhaToString(linha,maxDependentes,TAB);
        }
        return tabela;
    }    
}
