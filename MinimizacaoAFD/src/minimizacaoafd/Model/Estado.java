/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimizacaoafd.Model;

/**
 * Classe que representa um estado do AFD, contendo informações como o seu nome e se é ou não um
 * estado final
 * @author Diego
 */
public class Estado {
    //atributos com os nomes dos estados e se é final ou não
    private String nome;
    private boolean ehFinal;
    //construtor de um estado que recebe o nome do estado e se ele é final ou não
    public Estado(String nome, boolean ehFinal){
        this.nome = nome;
        this.ehFinal = ehFinal;
    }
    //construtor de um estado que recebe somente o nome não coloca-o como final
    public Estado(String nome){
        this(nome,false);
    }

    /**Método que retorna o nome do estado
     * @return Retorna o nome do estado
     */
    public String getNome() {
        return nome;
    }

    /**Método que retorna se o estado é final ou não
     * @return Retorna true caso e estado seja final e false caso contrário
     */
    public boolean getEhFinal() {
        return ehFinal;
    }

    /**Método que altera se um estado é final ou não. Em tese, esse método nem poderia existir, já que
     * na descrição do AFD essa informação já é passada, mas por questões de conveniência e 
     * dinamicidade ele foi mantido.
     * @param ehFinal True caso seja final e false caso contrário
     */
    public void setEhFinal(boolean ehFinal) {
        this.ehFinal = ehFinal;
    }
}
