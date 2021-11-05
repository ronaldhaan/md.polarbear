package md.polarbeargame.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import md.json.components.Button;
import md.json.components.TextField;
import md.polarbeargame.exceptions.ExceptionCodes;
// import md.polarbeargame.interfaces.RepaintInitiator;
import md.polarbeargame.models.Dice;
import md.polarbeargame.models.DiceCollection;
import md.polarbeargame.models.Data;
import md.shared.Utility;

public class ThrowDiceListener implements ActionListener 
{
    private DiceCollection dices;
    private Data data;

    public ThrowDiceListener() {
        data = Data.getInstance();
        getCollection();
    }

    private void getCollection() {        
        dices = data.getDiceCollection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        if (data == null) {
            data = Data.getInstance();
        }
        
        if(data != null) {
            try {
                if(dices == null) {
                    getCollection();
                }

                String strDices = ((TextField)data.get("tfdDices")).getText();
                data.resetComponents();
                dices.clear();
                ((TextField)data.get("tfdDices")).setText(strDices);

                if (!Utility.isNumeric(strDices)) {
                    data.addTip(0);
                } else {
                    int totalDices = Integer.parseInt(strDices);

                    if (totalDices < 3 || totalDices > 12) {
                        data.addTip(1);                    
                    } else {
                        data.addTimeThrowed();
                        ((Button)data.get("btnShowAnswer")).setEnabled(true);
                        ((Button)data.get("btnCheckAnswer")).setEnabled(true);
                        ((Button)data.get("btnRestart")).setEnabled(true);
                        ((Button)e.getSource()).setEnabled(false);
                    }

                    setPos(totalDices);

                    data.repaint();
                }
            } catch (Exception ex) {
                Utility.handleUnexpectedException(ExceptionCodes.TDR1, ex, true);
            }
        }
    }

    /**
     * Deze methode voegt het aantal opgegeven dobbelstenen aan deArrayList
     * 
     * @since 07-06-2017
     */
    private void setPos(int totalDices) {
        try {            
            data.setTotalDices(totalDices);

            int x = 20;
            int y = 20;
            int size = 45;
            int margin = size/2;

            if (totalDices < 13 && totalDices > 2) {
                int i = 0;
                while(i < totalDices) {                    
                    if (x > 200) {
                        y += size + margin;
                        x = 20;
                    }

                    dices.add(new Dice(x, y, size));
                    x += size + margin;
                    i++;
                }
            }
        } catch (Exception ex) {
            Utility.handleUnexpectedException(ExceptionCodes.TDR2, ex, true);
        }
    }
    
}
