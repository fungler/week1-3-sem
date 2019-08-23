/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import types.AnimalType;

/**
 *
 * @author Martin
 */
public class Animal {
    private AnimalType type;
    private Date birthYear;
    private String sound;

    public Animal() {
    }

    public Animal(AnimalType type, Date birthYear, String sound) {
        this.type = type;
        this.birthYear = birthYear;
        this.sound = sound;
    }
    
    
}
