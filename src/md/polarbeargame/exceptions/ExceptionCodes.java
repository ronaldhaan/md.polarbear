package md.polarbeargame.exceptions;

import java.lang.Thread.State;

import ec.shared.Utility;
import ec.shared.exceptions.IExceptionCodes;

public enum ExceptionCodes implements IExceptionCodes{
    
    //BaseComponent.java
    BCT1,
    BCT2,
    BCT3,
    BCT4,

    //AnswerCheckListener.java
    ACR1 ,

    //NumericKeyListener.java
    NKR1,

    //ShowAnswerListener.java
    SAR1,

    //AnswerCheckListener.java
    TDR1,
    TDR2,

    //GamePanel.java
    GPL1,
    DATA,
    EVENT1,
    EVENT2,
    FRAME1,
    DICE1;

    @Override
    public String[] getCodes() {
        return Utility.getCodes(State.values());
    }
}
