package md.polarbeargame.listeners;

import java.awt.event.ActionListener;

import md.polarbeargame.exceptions.ExceptionCodes;
import md.json.components.Button;
import md.polarbeargame.models.DiceCollection;
import md.polarbeargame.models.Data;
import md.shared.Utility;

import java.awt.event.ActionEvent;

/**
	 * de ActionListener @AnswerHandler wordt gebruikt dat als je op de knop klikt dat het spel dan
	 * de antwoorden weergeeft, maar daarna kun je het niet invullen doordat de 
	 * knop die het controleerd uigezet (disabled) is
	 * @author R Haan
	 * @version 1.0
	 * @since 9-3-2017
	 */
	public class ShowAnswerListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e)
		{
			try
			{
				Data data = Data.getInstance();
                DiceCollection col = data.getDiceCollection();
				data.addChance();
				
				data.getTextComponent("jtaTips").setText("");
				data.getTextComponent("tfdWakesAnswer").setText(String.valueOf(col.getTotalWakes()));
				data.getTextComponent("tfdPolarAnswer").setText(String.valueOf(col.getTotalPolarbears()));
				data.getTextComponent("tfdPinguinsAnswer").setText(String.valueOf(col.getTotalPinguins()));
				((Button)data.get("btnCheckAnswer")).setEnabled( false );
				((Button)data.get("btnShowAnswer")).setEnabled( false );
				((Button)data.get("btnwerpDices")).setEnabled( true );
				
				data.repaint();
			}
			catch(Exception ex)
			{
				Utility.handleUnexpectedException(ExceptionCodes.SAR1, ex, true);
			}
		}
	}
