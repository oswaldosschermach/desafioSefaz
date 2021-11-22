package aulas.web.adivinhe.jpa.controller;

import aulas.web.adivinhe.jpa.entity.Jogador;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Implemente operações JPA sobre os jogadores.
 * @author Wilson Horstmeyer Bogado
 */
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
     * Retorna todos os jogadores cadastrados.
     * Usa uma consulta JPQL.
     * @return A lista de jogadores
     */
    public List<Jogador> findAll() {
        TypedQuery<Jogador> q = em.createQuery("select j from Jogador j", Jogador.class);
        return q.getResultList();
    }

    public boolean isObterJogos() {
        return obterJogos;
    }

    public void setObterJogos(boolean obterJogos) {
        this.obterJogos = obterJogos;
    }
}
