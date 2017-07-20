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
        String TAB = "\t";
        int maiorNumeroDependentes = 0;
        for(Linha linha : linhas){
            //ver qual linha qual linha tem o maior numero de dependentes
            if (linha.getDependentes().size() > maiorNumeroDependentes) {
                maiorNumeroDependentes = linha.getDependentes().size();
            }
            //Acrescentar a linha em forma de string na stringona
        }
       
        String linhaTabela = TAB + " INDICE" + TAB + TAB;
        linhaTabela += TAB + "D[i,j] = " + TAB + TAB;
        //cada caso de numero de dependentes:
        //se o maior numero de dependentes for menor que 3, ele formata 
        //toda a coluna S[i,j] para comportar até 2 dependentes
        if (maiorNumeroDependentes < 3) {
            linhaTabela += TAB + "   S[i,j] = " + TAB + TAB;
            linhaTabela += TAB + "  MOTIVO\n";
            
            for(Linha linha : linhas){
                linhaTabela += TAB + "[" + linha.getPar1().getNome() + "," + linha.getPar2().getNome() + "]";
                linhaTabela += TAB + TAB;
                //Verificando se pode juntar
                linhaTabela += TAB + TAB + (linha.getPodeJuntar() ? "   0" : "   1") + TAB + TAB;
                //colocando dependentes de acordo com o tanto que tem
                int qtdDependentesAux = linha.getDependentes().size();
                if (qtdDependentesAux == 0)
                    linhaTabela += TAB + TAB + "  { }" + TAB + TAB;
                
                if (qtdDependentesAux == 1) {
                    List<Linha> dependentesDaLinha;
                    dependentesDaLinha = linha.getDependentes();
                    linhaTabela += TAB + "  { ";
                    for (Linha dependente : dependentesDaLinha){
                        linhaTabela += "[" + dependente.getPar1().getNome() + ",";
                        linhaTabela += linhaTabela += dependente.getPar2().getNome() + "]";
                    }
                    linhaTabela += " }" + TAB + TAB;
                }
                if (qtdDependentesAux == 2) {
                    List<Linha> dependentesDaLinha = linha.getDependentes();
                    linhaTabela +=  "  { ";
                    for (Linha dependente : dependentesDaLinha){
                        linhaTabela += "[" + dependente.getPar1().getNome() + ",";
                        linhaTabela += linhaTabela += dependente.getPar2().getNome() + "], ";
                    }
                    linhaTabela = linhaTabela.substring(0, linhaTabela.length() - 1);
                    linhaTabela += "}" + TAB;
                }
                if (linha.getMotivo().indexOf("f") == 0) {
                    linhaTabela += TAB + linha.getMotivo() + '\n';
                }
                else if (linha.getMotivo().indexOf("p") == 0) {
                    linhaTabela += TAB + "   " + linha.getMotivo() + "\n";
                }
                else {
                    linhaTabela += TAB + TAB + linha.getMotivo() + "\n";
                }
            }
        }
      
        //se o maior numero de dependentes for 3, ele formata 
        //toda a coluna S[i,j] para comportar até 3 dependentes
        if (maiorNumeroDependentes == 3) {
            linhaTabela += TAB + " S[i,j] = " + TAB + TAB;
            linhaTabela += TAB + "  MOTIVO\n";
            
            for(Linha linha : linhas){
                linhaTabela += TAB + "[" + linha.getPar1().getNome() + "," + linha.getPar2().getNome() + "]";
                linhaTabela += TAB + TAB;
                //Verificando se pode juntar
                linhaTabela += TAB + TAB + (linha.getPodeJuntar() ? "   0" : "   1") + TAB + TAB;
                //colocando dependentes de acordo com o tanto que tem
                int qtdDependentesAux = linha.getDependentes().size();
                if (qtdDependentesAux == 0)
                    linhaTabela += TAB + TAB + TAB + "   { }" + TAB + TAB + TAB;
                
                if (qtdDependentesAux == 1) {
                    List<Linha> dependentesDaLinha = linha.getDependentes();
                    linhaTabela += TAB + TAB + "  { ";
                    for (Linha dependente : dependentesDaLinha){
                        linhaTabela += "[" + dependente.getPar1().getNome() + ",";
                        linhaTabela += linhaTabela += dependente.getPar2().getNome() + "]";
                    }
                    linhaTabela += " }" + TAB + TAB;
                }
                if (qtdDependentesAux == 2) {
                    List<Linha> dependentesDaLinha = linha.getDependentes();
                    linhaTabela += TAB + "   { ";
                    for (Linha dependente : dependentesDaLinha){
                        linhaTabela += "[" + dependente.getPar1().getNome() + ",";
                        linhaTabela += linhaTabela += dependente.getPar2().getNome() + "], ";
                    }
                    linhaTabela = linhaTabela.substring(0, linhaTabela.length() - 2);
                    linhaTabela += " }" + TAB + TAB;
                }
                if (qtdDependentesAux == 3) {
                    List<Linha> dependentesDaLinha = linha.getDependentes();
                    linhaTabela += "   { ";
                    for (Linha dependente : dependentesDaLinha){
                        linhaTabela += "[" + dependente.getPar1().getNome() + ",";
                        linhaTabela += linhaTabela += dependente.getPar2().getNome() + "], ";
                    }
                    linhaTabela = linhaTabela.substring(0, linhaTabela.length() - 2);
                    linhaTabela += " }" + TAB;                    
                }
                if (linha.getMotivo().indexOf("f") == 0) {
                    linhaTabela += TAB + linha.getMotivo() + '\n';
                }
                else if (linha.getMotivo().indexOf("p") == 0) {
                    linhaTabela += TAB + "   " + linha.getMotivo() + "\n";
                }
                else {
                    linhaTabela += TAB + TAB + linha.getMotivo() + "\n";
                }
            }
        }
        
        //se o maior numero de dependentes for 4, ele formata 
        //toda a coluna S[i,j] para comportar até 4 dependentes
        if (maiorNumeroDependentes == 4) {
            linhaTabela += TAB + TAB + TAB + "   S[i,j] = " + TAB + TAB + TAB + TAB;
            linhaTabela += TAB + "  MOTIVO\n";
            for(Linha linha : linhas){
                linhaTabela += TAB + "[" + linha.getPar1().getNome() + "," + linha.getPar2().getNome() + "]";
                linhaTabela += TAB + TAB;
                //Verificando se pode juntar
                linhaTabela += TAB + TAB + (linha.getPodeJuntar() ? "   0" : "   1") + TAB + TAB;
                //colocando dependentes de acordo com o tanto que tem
                int qtdDependentesAux = linha.getDependentes().size();
                if (qtdDependentesAux == 0)
                    linhaTabela += TAB + TAB + TAB + TAB +"  { }" + TAB + TAB + TAB + TAB;
                
                if (qtdDependentesAux == 1) {
                    List<Linha> dependentesDaLinha = linha.getDependentes();
                    linhaTabela += TAB + TAB + TAB + "  { ";
                    for (Linha dependente : dependentesDaLinha){
                        linhaTabela += "[" + dependente.getPar1().getNome() + ",";
                        linhaTabela += linhaTabela += dependente.getPar2().getNome() + "]";
                    }
                    linhaTabela += " }" + TAB + TAB + TAB + TAB;
                }
                if (qtdDependentesAux == 2) {
                    List<Linha> dependentesDaLinha = linha.getDependentes();
                    linhaTabela += TAB + TAB + "  { ";
                    for (Linha dependente : dependentesDaLinha){
                        linhaTabela += "[" + dependente.getPar1().getNome() + ",";
                        linhaTabela += linhaTabela += dependente.getPar2().getNome() + "], ";
                    }
                    linhaTabela = linhaTabela.substring(0, linhaTabela.length() - 2);
                    linhaTabela += " }" + TAB + TAB + TAB;
                }
                if (qtdDependentesAux == 3) {
                    List<Linha> dependentesDaLinha = linha.getDependentes();
                    linhaTabela += TAB + "   { ";
                    for (Linha dependente : dependentesDaLinha){
                        linhaTabela += "[" + dependente.getPar1().getNome() + ",";
                        linhaTabela += linhaTabela += dependente.getPar2().getNome() + "], ";
                    }
                    linhaTabela = linhaTabela.substring(0, linhaTabela.length() - 2);
                    linhaTabela += " }" + TAB + TAB;                    
                }
                if (qtdDependentesAux == 4) {
                    List<Linha> dependentesDaLinha = linha.getDependentes();
                    linhaTabela += "   { ";
                    for (Linha dependente : dependentesDaLinha){
                        linhaTabela += "[" + dependente.getPar1().getNome() + ",";
                        linhaTabela += linhaTabela += dependente.getPar2().getNome() + "], ";
                    }
                    linhaTabela = linhaTabela.substring(0, linhaTabela.length() - 2);
                    linhaTabela += " }" + TAB;
                }
                if (linha.getMotivo().indexOf("f") == 0) {
                    linhaTabela += TAB + linha.getMotivo() + '\n';
                }
                else if (linha.getMotivo().indexOf("p") == 0) {
                    linhaTabela += TAB + "   " + linha.getMotivo() + "\n";
                }
                else {
                    linhaTabela += TAB + TAB + linha.getMotivo() + "\n";
                }
            }
        }
        return linhaTabela;
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
