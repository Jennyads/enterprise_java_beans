/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jeniffer.gameranking.jsf;


import com.jeniffer.gameranking.ejb.JogoSomaBean;
import com.jeniffer.gameranking.model.Jogador;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jenny
 */
@Named
@SessionScoped
public class JogoController implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int resposta;
    private String mensagem;

    @EJB
    private JogoSomaBean jogoBean;

    private boolean iniciado = false;

    public void iniciar() {
        jogoBean.iniciarJogo(nome);
        iniciado = true;
        mensagem = null;
    }

    public void verificar() {
        mensagem = jogoBean.verificarResposta(resposta);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

    public String getMensagem() {
        return mensagem;
    }

    public boolean isIniciado() {
        return iniciado;
    }

    public int getNumero1() {
        return jogoBean.getNumero1();
    }

    public int getNumero2() {
        return jogoBean.getNumero2();
    }

    public String getRanking() {
        return jogoBean.getRankingFormatado();
    }
    
    public void reiniciar() {
    nome = "";
    resposta = 0;
    mensagem = "";
    iniciado = false;
    }
    
    public List<Jogador> getRankingLista() {
    return jogoBean.getRankingLista();
    }


}
