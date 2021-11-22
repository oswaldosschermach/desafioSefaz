package aulas.web.adivinhe;

import aulas.web.adivinhe.jpa.controller.JogadorController;
import aulas.web.adivinhe.jpa.entity.Jogador;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Suporte à view de administração de jogadores.
 * @author Wilson Horstmeyer Bogado
 */
@Named
@ViewScoped
public class AdminJogadoresBean implements Serializable {

    private List<Jogador> jogadores;

    @Inject
    private JogadorController jogadorController;

    public AdminJogadoresBean() {
    }

    public List<Jogador> getJogadores() {
        if (jogadores == null) {
            jogadores = jogadorController.findAll();
        }
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
