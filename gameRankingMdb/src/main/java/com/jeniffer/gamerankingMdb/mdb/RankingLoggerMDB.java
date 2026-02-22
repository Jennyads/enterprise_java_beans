/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jeniffer.gamerankingMdb.mdb;

/**
 *
 * @author jenny
 */


import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java/Fila"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class RankingLoggerMDB implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(RankingLoggerMDB.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            String ranking = message.getBody(String.class);

            LOGGER.info(() -> "\n==============================\n"
                    + "Ranking Atualizado com Novo Líder\n"
                    + "Consumer MDB: " + this.getClass().getSimpleName() + "\n"
                    + "==============================\n"
                    + ranking);

        } catch (JMSException e) {
            LOGGER.log(Level.SEVERE, "Erro ao processar mensagem de ranking no MDB", e);
        }
    }

}