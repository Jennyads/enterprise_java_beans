/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jeniffer.gameranking.ejb;

import com.jeniffer.gameranking.model.Jogador;
import jakarta.ejb.Stateful;
import jakarta.ejb.EJB;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jenny
 */
@Stateful
public class JogoSomaBean {

    private String nomeJogador;
    private int numero1;
    private int numero2;

    @EJB
    private RankingBean rankingBean;

    private final Random random = new Random();

    public void iniciarJogo(String nome) {
        this.nomeJogador = nome;
        gerarNumeros();
    }

    public void gerarNumeros() {
        numero1 = random.nextInt(50);
        numero2 = random.nextInt(50);
    }

    public int getNumero1() {
        return numero1;
    }

    public int getNumero2() {
        return numero2;
    }

    public String verificarResposta(int resposta) {
        int soma = numero1 + numero2;
        if (resposta == soma) {
            rankingBean.atualizarRanking(nomeJogador);
            gerarNumeros();
            return "✅ Acertou!";
        } else {
            gerarNumeros();
            return "❌ Errou!";
        }
    }

    public String getRankingFormatado() {
        StringBuilder sb = new StringBuilder("===== Ranking Atual =====\n");
        int pos = 1;
        for (var j : rankingBean.getRanking()) {
            sb.append(pos++).append(". ")
              .append(j.getNome())
              .append(" - ").append(j.getPontos()).append(" pontos\n");
        }
        return sb.toString();
    }
    
    public List<Jogador> getRankingLista() {
    return rankingBean.getRanking();
}

}