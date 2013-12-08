/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Miscellanea;

/**
 *
 * @author mr.nam
 */
public enum EnumValue {
    PACKET_PRICE_DEFAULTS(10)//
    , PACKET_QUESTION_NUMBER(20)//
    ,TIME_PER_QUESTION(10)//
    ,TUTORIAL_QUESTION_NUMBER(10)//
    , INITIAL_POINT(100);//
    
    private int value;
    private EnumValue(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
}
