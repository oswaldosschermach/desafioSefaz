package aulas.web.adivinhe.jpa.controller;

import aulas.web.adivinhe.jpa.entity.Jogador;
import aulas.web.adivinhe.jpa.entity.Jogo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Implementa operações JPA sobre os jogos.
 * @author Wilson Horstmeyer Bogado
 */
public class JogoController implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public JogoController() {
    }

    /**
     * Retorna todos todos os jogos de um jogador, dado o código do jogador.
     * @param codigo O código do jogador
     * @return A lista de jogos do jogador
     */
    public List<Jogo> findByJogador(Integer codigo) {
        TypedQuery<Jogo> q = em.createQuery("select j from Jogo j where j.jogoPK.codJogador = :codigo", Jogo.class);
        q.setParameter("codigo", codigo);
        return q.getResultList();
    }

    public List<Jogo> findByJogador(Jogador j) {
        return findByJogador(j.getCodigo());
    }
}
