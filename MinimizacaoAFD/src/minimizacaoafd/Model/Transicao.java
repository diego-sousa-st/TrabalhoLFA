package minimizacaoafd.Model;

/**
 *
 * @author sDiego, Nechelley e Maurício
 */
public class Transicao {
    //estado que iniciou a transição
    private final Estado estadoAtual;
    //estado o qual se chegou após a transição
    private final Estado estadoAposTransicao;    
    //símbolo lido para que esta transiçao seja realizada
    private final String simboloLido; 
    
    /**
     * Método que cria e inicializa um objeto Transicao que representa um possível movimento na 
     * AFD
     * @param estadoAtual parametro que representa o estado que inicia a transição
     * @param estadoAposTransicao parametro que representa o estado após a transição     
     * @param simboloLido parametro que representa o que foi lido para iniciar a transição     
     */       
    public Transicao(Estado estadoAtual, Estado estadoAposTransicao, String simboloLido){
        this.estadoAtual = estadoAtual;
        this.estadoAposTransicao = estadoAposTransicao;        
        this.simboloLido = simboloLido;        
    }

    /**Método que retorna o estadoAtual, o qual se inicia a transição
     * @return Retorna o estado atual
     */
    public Estado getEstadoAtual() {
        return estadoAtual;
    }

    /**Método que retorna o estado em que o AFD se encontra após a realização da transição
     * @return Retorna o estado após a transição
     */
    public Estado getEstadoAposTransicao() {
        return estadoAposTransicao;
    }    

    /**Retorna o simbolo lido para que a transição do estadoAtual->estadoAposTransicao seja feita
     * @return Retorna o simbolo lido
     */
    public String getSimboloLido() {
        return simboloLido;
    }
    
    public Boolean equals(Transicao t){
        if (this == t || (t.estadoAtual.equals(estadoAtual) && t.simboloLido.equals(simboloLido) && t.estadoAposTransicao.equals(estadoAposTransicao))) {
            return true;
        }
        return false;
    }
}
