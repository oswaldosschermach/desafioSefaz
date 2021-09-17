package aulas.web.adivinhe;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Representa a sessão do jogador.
 * @author Wilson Horstmeyer Bogado
 */
@Named
@SessionScoped
public class JogadorBean implements Serializable {
    private int numeroAtual;
    private int minimo = 1;
    private int maximo = 9;
    private int numTentativas = 0;

    public JogadorBean() {
        novoNumero();
    }

    public int getNumeroAtual() {
        return numeroAtual;
    }

    public void setNumeroAtual(int numeroAtual) {
        this.numeroAtual = numeroAtual;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public int getNumTentativas() {
        return numTentativas;
    }

    public void setNumTentativas(int numTentativas) {
        this.numTentativas = numTentativas;
    }

    public void incrementarTentativas() {
        this.numTentativas++;
    }

    public void jogarNovamente() {
        numTentativas = 0;
        novoNumero();
    }

    private void novoNumero() {
        this.numeroAtual = (int) (Math.random() * (maximo - minimo) + minimo + 1);
    }
}
