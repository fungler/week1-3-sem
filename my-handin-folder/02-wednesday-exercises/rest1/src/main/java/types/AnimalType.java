/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

/**
 *
 * @author Martin
 */
public enum AnimalType {
    DOG {
        @Override
        public String toString(){
            return "Dog";
        }
    },
    FISH {
        @Override
        public String toString(){
            return "Fish";
        }
    },
    CAT {
        @Override
        public String toString(){
            return "Cat";
        }
    },
    COW {
        @Override
        public String toString(){
            return "Cow";
        }
    },
    SNAKE {
        @Override
        public String toString(){
            return "Snake";
        }
    }
}

