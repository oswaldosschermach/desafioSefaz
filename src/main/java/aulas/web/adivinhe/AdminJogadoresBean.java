package aulas.web.adivinhe;

import aulas.web.adivinhe.jpa.controller.JogadorController;
import aulas.web.adivinhe.jpa.entity.Jogador;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    public void exluir(Integer codJogador) {
        try {
            Jogador jogador = jogadorController.remove(codJogador);
            if (jogador == null) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Jogador não localizado", null);
                FacesContext.getCurrentInstance().addMessage(null, fm);
            } else {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    String.format("Jogador %s excluído", jogador.getApelido()), null);
                FacesContext.getCurrentInstance().addMessage(null, fm);
                jogadores= null; // força a recarga dos jogadores
            }
        } catch (Throwable t) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao excluir jogador", t.getLocalizedMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            FacesContext.getCurrentInstance().getExternalContext()
                    .log("Erro ao exluir jogador código " + codJogador, t);
        }
    }
}
