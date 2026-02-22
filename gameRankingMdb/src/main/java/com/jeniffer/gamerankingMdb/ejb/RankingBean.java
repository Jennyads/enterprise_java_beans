/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jeniffer.gamerankingMdb.ejb;


import com.jeniffer.gamerankingMdb.model.Jogador;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.jms.Queue;        
import jakarta.jms.JMSContext;   
import jakarta.jms.ConnectionFactory; 
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jenny
 */
@Singleton
public class RankingBean {

    private static final Logger LOGGER = Logger.getLogger(RankingBean.class.getName());

    private final List<Jogador> ranking = new ArrayList<>();

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java/Fila")
    private Queue fila;

    @Lock(LockType.WRITE)
    public void atualizarRanking(String nome) {

        String antigoLider = ranking.isEmpty() ? null : ranking.get(0).getNome();

        Optional<Jogador> jogadorOpt = ranking.stream()
                .filter(j -> j.getNome().equalsIgnoreCase(nome))
                .findFirst();

        if (jogadorOpt.isPresent()) {
            jogadorOpt.get().adicionarPonto();
        } else {
            Jogador novo = new Jogador(nome);
            novo.adicionarPonto();
            ranking.add(novo);
        }

        ranking.sort(Comparator.comparingInt(Jogador::getPontos).reversed());


        String novoLider = ranking.isEmpty() ? null : ranking.get(0).getNome();


        if (novoLider != null && !novoLider.equalsIgnoreCase(antigoLider)) {
            enviarRankingParaFila();
        }
    }

    @Lock(LockType.READ)
    public List<Jogador> getRanking() {
        return new ArrayList<>(ranking);
    }

    @Lock(LockType.READ)
    public String getRankingFormatado() {
        StringBuilder sb = new StringBuilder("===== Ranking Atual =====\n");
        int pos = 1;
        for (Jogador j : ranking) {
            sb.append(pos++)
              .append(". ")
              .append(j.getNome())
              .append(" - ")
              .append(j.getPontos())
              .append(" pontos\n");
        }
        return sb.toString();
    }


    private void enviarRankingParaFila() {
        StringBuilder sb = new StringBuilder("===== Ranking Atual =====\n");
        int pos = 1;
        for (Jogador j : ranking) {
            sb.append(pos++)
              .append(". ")
              .append(j.getNome())
              .append(" - ")
              .append(j.getPontos())
              .append(" pontos\n");
        }
        String rankingStr = "[Producer: " + this.getClass().getSimpleName() + "]\n" + getRankingFormatado();


        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(fila, rankingStr);
            LOGGER.info("Mensagem de ranking enviada para a fila java/Fila.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao enviar ranking para a fila JMS", e);
        }
    }
}