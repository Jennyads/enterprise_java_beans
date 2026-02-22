/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jeniffer.calcweb.ejb;

/**
 *
 * @author jenny
 */
import com.jeniffer.calcinterface.CalcInterface;
import jakarta.ejb.Stateless;

@Stateless
public class EjbCalculadora implements CalcInterface {
    @Override public double somar(double a, double b) { return a + b; }
    @Override public double subtrair(double a, double b) { return a - b; }
    @Override public double multiplicar(double a, double b) { return a * b; }
    @Override public double dividir(double a, double b) {
        if (b == 0) throw new ArithmeticException("Divisão por zero");
        return a / b;
    }
}