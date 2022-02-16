package aulas.web.adivinhe;

import aulas.web.adivinhe.jpa.controller.JogadorController;
import aulas.web.adivinhe.jpa.entity.Jogador;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ViewScoped
public class CadastroBean implements Serializable {

    private Jogador jogador = new Jogador();

    @Inject
    private JogadorController jogadorController;

    public CadastroBean() {
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public String cadastrar() {
        String action = null;
        try {
            jogadorController.persist(jogador);
            action = "/admin/admin_jogadores?faces-redirect=true";
        } catch (Throwable t) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao gravar registro", t.getLocalizedMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            FacesContext.getCurrentInstance().getExternalContext()
                    .log("Erro ao cadastrar " + jogador, t);
        }
        return action;
    }
}
