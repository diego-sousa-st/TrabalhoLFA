/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimizacaoafd.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa cada linha da tabela do algoritmo de minimização.
 * Contém os índice [i,j], D[i,j], S[i,j] e o Motivo porque i e j não podem
 * juntar
 *
 * @author Diego
 */
public class Linha {

    //índice [par1,par2]
    private Estado par1;
    private Estado par2;
    //D[par1,par2]
    private boolean podeJuntar;
    //S[par1,par2]
    private List<Linha> dependentes;
    //Motivo
    private String motivo;

    /**
     * Construtor que inicializa os pares e define o par como apto a juntar-se
     *
     * @param par1 estado i
     * @param par2 estado j
     */
    public Linha(Estado par1, Estado par2) {
        this(par1, par2, true, "");
    }

    /**
     * Construtor que inicializa os pares e define se o par pode juntar-se ou
     * não
     *
     * @param par1 estado i
     * @param par2 estado j
     * @param podeJuntar Representa se o estado i e j podem(true) se juntar ou
     * não(false)
     * @param motivo Motivo pelo qual nao pode juntar um estado com outro
     */
    public Linha(Estado par1, Estado par2, boolean podeJuntar, String motivo) {
        this.par1 = par1;
        this.par2 = par2;
        this.podeJuntar = podeJuntar;
        dependentes = new ArrayList<Linha>();
        this.motivo = motivo;
    }

    /**
     * Método que insere uma linha como dependente deste objeto. Se este objeto
     * não puder se juntar nenhum de seus dependentes poderá
     *
     * @param linha linha que depende desta linha(este objeto) da tabela
     */
    public void inserirDependente(Linha linha) {
        dependentes.add(linha);
    }

    /**
     * Altera o flag se pode juntar ou nao
     *
     * @param podeJuntar
     */
    public void setPodeJuntar(boolean podeJuntar) {
        this.podeJuntar = podeJuntar;
    }

    /**
     * Seta o motivo de não poder juntar o estado i e j
     *
     * @param motivo
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Retorna o estado i
     *
     * @return
     */
    public Estado getPar1() {
        return par1;
    }

    /**
     * Retorna o estado j
     *
     * @return
     */
    public Estado getPar2() {
        return par2;
    }

    /**
     * Retorna se pode juntar ou nao
     *
     * @return
     */
    public boolean getPodeJuntar() {
        return podeJuntar;
    }

    /**
     * retorna uma referencia à lista com os dependentes
     *
     * @return
     */
    public List<Linha> getDependentes() {
        return dependentes;
    }

    /**
     * Retorna o motivo
     *
     * @return
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Método que verifica se uma linha é igual a outra.
     *
     * @param linha linha a ser comparada com a linha atual
     * @return true caso a linha seja igual e false caso contrario
     */
    public boolean equals(Linha linha) {
        if (linha == this) {
            return true;
        }
        return false;
    }
}
