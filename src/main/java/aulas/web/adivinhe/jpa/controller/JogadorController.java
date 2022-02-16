package aulas.web.adivinhe.jpa.controller;

import aulas.web.adivinhe.jpa.entity.Jogador;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

public class JogadorController implements Serializable {

    @PersistenceContext
    private EntityManager em;

    private boolean obterJogos = false;

    public JogadorController() {
    }

    /**
     * Retorna um jogador, dado o seu código (chave primária).
     * @param codigo O código do jogador
     * @return O jogador
     */
    public Jogador findByCodigo(Integer codigo) {
        Jogador j = em.find(Jogador.class, codigo);
        if (j != null && obterJogos) {
            // força a carga dos jogos do jogador
            j.getJogos().get(0);
        }
        return j;
    }
    /**
     * Retorna um jogador, dado o seu apelido.
     * @param apelido O apelido do jogador
     * @return O jogador
     */
    public Jogador findByApelido(String apelido) {
        Jogador jogador;
        String query = "select j from Jogador j where apelido = :apelido";
        try {
            TypedQuery<Jogador> q = em.createQuery(query, Jogador.class)
                    .setParameter("apelido", apelido);
            jogador = q.getSingleResult();
        } catch (NoResultException nre) {
            jogador = null;
        }
        return jogador;
    }

    /**
     * Retorna todos os jogadores cadastrados.
     * Usa a API de critérios.
     * @return A lista de jogadores
     */
    public List<Jogador> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Jogador> cq = cb.createQuery(Jogador.class);
        cq.from(Jogador.class);
        TypedQuery<Jogador> q = em.createQuery(cq);
        return q.getResultList();
    }

    public boolean isObterJogos() {
        return obterJogos;
    }

    public void setObterJogos(boolean obterJogos) {
        this.obterJogos = obterJogos;
    }

    @Transactional
    public void persist(Jogador jogador) {
        em.persist(jogador);
    }

    @Transactional
    public Jogador merge(Jogador jogador) {
        return em.merge(jogador);
    }

    @Transactional
    public Jogador remove(Integer codJogador) {
        Jogador j = findByCodigo(codJogador);
        if (j != null)
            em.remove(j);
        return j;
    }

    @Transactional
    public Jogador remove(Jogador jogador) {
        Jogador j = merge(jogador);
        em.remove(j);
        return j;
    }
}
