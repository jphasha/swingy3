package com.wethinkcode.java.swingy.model.heroes.asgard;

import javax.validation.constraints.NotNull;

import com.wethinkcode.java.swingy.model.heroes.Hero;

public class Aesir extends Hero {
    public Aesir(@NotNull(message = "A heroName can not be a null value") String heroName) {
        super(heroName, "Aesir");
        setHeroHitPoints(110);
    }
}
