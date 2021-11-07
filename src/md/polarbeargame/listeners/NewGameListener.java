package md.polarbeargame.listeners;

import java.awt.event.ActionListener;

import md.polarbeargame.models.Data;

import java.awt.event.ActionEvent;

/**
 * de ActionListener @NewGameListener wordt gebruikt om het hele spel te
 * resetten zodat de gebruiker opnieuw kan spelen en niet het spel opnieuw hoeft
 * op te starten.
 * 
 * @author R Haan
 * @version 1.0
 * @since 9-3-2017
 */
public class NewGameListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Data data = Data.getInstance();
		data.clear();
		data.resetComponents();
		data.repaint();
	}

}
