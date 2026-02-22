/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jeniffer.gameranking.ejb;


import com.jeniffer.gameranking.model.Jogador;
import jakarta.ejb.Singleton;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import java.util.*;

/**
 *
 * @author jenny
 */
@Singleton
public class RankingBean {

    private final List<Jogador> ranking = new ArrayList<>();

    @Lock(LockType.WRITE)
    public void atualizarRanking(String nome) {
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
    }

    @Lock(LockType.READ)
    public List<Jogador> getRanking() {
        return new ArrayList<>(ranking);
    }
}