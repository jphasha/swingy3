package com.wethinkcode.java.swingy.model.heroes.mountOlympus;

import javax.validation.constraints.NotNull;

import com.wethinkcode.java.swingy.model.heroes.Hero;

public class Olympian extends Hero {
    public Olympian(@NotNull(message = "A heroName can not be a null value") String heroName) {
        super(heroName, "Olympian");
        setHeroDefence(7);
    }
}
