/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Miscellanea;

/**
 *
 * @author mr.nam
 */
public enum EnumString {

    
    VIDEO("video") //
    , IMAGE("image") //
    , AUDIO("audio") //
    ;
    private String s;

    private EnumString(String s) {
        this.s = s;
    }

    public String getValue() {
        return s;
    }
}
