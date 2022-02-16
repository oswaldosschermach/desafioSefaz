package aulas.web.adivinhe.jpa.controller;

import aulas.web.adivinhe.jpa.entity.Jogador;
import aulas.web.adivinhe.jpa.entity.Jogo;
import aulas.web.adivinhe.jpa.entity.JogoPK_;
import aulas.web.adivinhe.jpa.entity.Jogo_;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Jogo> cq = cb.createQuery(Jogo.class);
        Root rt = cq.from(Jogo.class);
        cq.where(cb.equal(rt.get(Jogo_.jogoPK).get(JogoPK_.codJogador), codigo));
        TypedQuery<Jogo> q = em.createQuery(cq);
        return q.getResultList();
    }

    public List<Jogo> findByJogador(Jogador j) {
        return findByJogador(j.getCodigo());
    }
}
