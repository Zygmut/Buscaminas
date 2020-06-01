/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and locked the template in the editor.
 */
package Buscaminas;

/**
 *
 * @author Zygmut
 */
public class cell {

    public enum cont {
        zero, one, two, three, four, five, six, seven, eight, bomb, mark
    }

    public String prisoner;
    public boolean locked;
    public boolean marked;

    public cell() {
        this.prisoner = cont.zero.toString();
        this.locked = true;
        this.marked = false;
    }

    public cell(String num) {
        this.prisoner = num;
        this.locked = true;
        this.marked = false;
    }
}
