package minimizacaoafd.Model;

/**
 * Classe que representa um estado do AFD, contendo informações como o seu nome
 * e se é ou não um estado final
 *
 * @author Diego, Nechelley e Maurício
 */
public class Estado {

    //atributos com os nomes dos estados e se é final ou não
    private String nome;
    private boolean ehFinal;

    //construtor de um estado que recebe o nome do estado e se ele é final ou não
    public Estado(String nome, boolean ehFinal) {
        this.nome = nome;
        this.ehFinal = ehFinal;
    }

    /**
     * Método que retorna o nome do estado
     *
     * @return Retorna o nome do estado
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que retorna se o estado é final ou não
     *
     * @return Retorna true caso e estado seja final e false caso contrário
     */
    public boolean getEhFinal() {
        return ehFinal;
    }

    /**
     * Método que verifica se um estado é igual ao outro
     *
     * @param e estado a ser comparado com o estado atual
     * @return True caso sejam iguais e false caso contrário
     */    
    public boolean equals(Estado e) {
        if (this == e || (nome.equals(e.getNome()) && (ehFinal == e.getEhFinal()))) {
            return true;
        }
        return false;
    }
    
    /**
     * Método hashCode Sobresrito que é usado pelo hashset
     * @return 
     */
    @Override
    public int hashCode(){
       String numEstado = nome;
       /* retiro o "q" do nome do estado (este método somente funcionará se o 
       *  estado tiver somente uma letra inicial no nome, e depois o resto tem 
       *  que ser numero
       */
       numEstado = numEstado.substring(1);
       int hash = Integer.parseInt(numEstado);              
       return hash*hash;
    }    
}
