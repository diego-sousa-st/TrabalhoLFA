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
    
    
    @Override
    public String toString(){
       //TODO Maurício formatar a tabela toda bonita independente de como ficar
       return "";
    }
    
    //APAGAR ESTE METODO DEPOIS
    public void exibirTabelaImprovisada(){
        for(Linha l: linhas){
            String x = "";
            for(Linha dep: l.getDependentes()){
                x += "(" + dep.getPar1().getNome() + "," + dep.getPar2().getNome() + ") ";
            }
            System.out.println("(" + l.getPar1().getNome() + "," + l.getPar2().getNome() + ") " + l.getPodeJuntar() + " - " + x + " - " + l.getMotivo());
        }
        
    }
}
