package md.polarbeargame.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import md.json.components.TextField;
import md.polarbeargame.exceptions.ExceptionCodes;
import md.polarbeargame.models.Data;

import md.shared.Utility;

/**
 * de ActionListener gaat kijken of de invoer van de gebruiker klopt of niet.
 * als het niet klopt, worden de JTextFields waar een fout antwoord is gegeven
 * rood gekleurd. als een antwoord klopt, worden de JTextFields waar een goed
 * antwoord is gegeven groen gekleurd.
 * 
 * @author R Haan
 * @version 1.0
 * @since 9-3-2017
 */
public class AnswerCheckListener implements ActionListener {
    private Data data;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (data == null) {
            data = Data.getInstance();
        }

        try {
            String sInputW = ((TextField) data.get("tfdWakes")).getText();
            String sInputI = ((TextField) data.get("tfdPolarBears")).getText();
            String sInputP = ((TextField) data.get("tfdPinguins")).getText();

            if (!Utility.isNumeric(sInputW) || !Utility.isNumeric(sInputI) || !Utility.isNumeric(sInputP)) {
                data.addTip(0);
            }

            int inputI = Integer.parseInt(sInputI);
            int inputW = Integer.parseInt(sInputW);
            int inputP = Integer.parseInt(sInputP);

            if (validate(inputW, inputI, inputP)) {
                data.addScore();
            } else {
                data.addChance();
            }

        } catch (Exception ex) {
            Utility.handleUnexpectedException(ExceptionCodes.ACR1, ex, true);
        }
    }

    private boolean validate(int inputW, int inputI, int inputP) {
        ((TextField) data.get("tfdPinguins")).setBackground(Color.RED);
        ((TextField) data.get("tfdWakes")).setBackground(Color.RED);
        ((TextField) data.get("tfdPolarBears")).setBackground(Color.RED);

        int rightW = data.getDiceCollection().getTotalWakes();
        int rightI = data.getDiceCollection().getTotalPolarbears();
        int rightP = data.getDiceCollection().getTotalPinguins();

        boolean answer = true;

        if (inputW == rightW) {
            ((TextField) data.get("tfdWakes")).setBackground(Color.GREEN);
        } else {
            answer = false;
        }

        if (inputI == rightI) {
            ((TextField) data.get("tfdPolarBears")).setBackground(Color.GREEN);
        } else {
            answer = false;
        }

        if (inputP == rightP) {
            ((TextField) data.get("tfdPinguins")).setBackground(Color.GREEN);
        } else {
            answer = false;
        }

        return answer;
    }

}
