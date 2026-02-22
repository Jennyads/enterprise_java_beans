/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jeniffer.calcweb.jsf;

/**
 *
 * @author jenny
 */


import com.jeniffer.calcinterface.CalcInterface;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("calcJSF")
@RequestScoped
public class JsfCalculadora {

    private double numeroA;
    private double numeroB;

    private double resultadoSoma;
    private double resultadoSubtracao;
    private double resultadoMultiplicacao;
    private double resultadoDivisao;

    private String erro;

    @EJB
    private CalcInterface calc; 

    public void calcular() {
        erro = null;
        try {
            resultadoSoma = calc.somar(numeroA, numeroB);
            resultadoSubtracao = calc.subtrair(numeroA, numeroB);
            resultadoMultiplicacao = calc.multiplicar(numeroA, numeroB);
            resultadoDivisao = calc.dividir(numeroA, numeroB);
        } catch (ArithmeticException e) {
            erro = e.getMessage();
            resultadoDivisao = Double.NaN;
        }
    }


    public double getNumeroA() { return numeroA; }
    public void setNumeroA(double numeroA) { this.numeroA = numeroA; }
    public double getNumeroB() { return numeroB; }
    public void setNumeroB(double numeroB) { this.numeroB = numeroB; }
    public double getResultadoSoma() { return resultadoSoma; }
    public double getResultadoSubtracao() { return resultadoSubtracao; }
    public double getResultadoMultiplicacao() { return resultadoMultiplicacao; }
    public double getResultadoDivisao() { return resultadoDivisao; }
    public String getErro() { return erro; }
}