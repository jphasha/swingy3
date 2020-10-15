package com.wethinkcode.java.swingy.model.heroes.westeros;

import javax.validation.constraints.NotNull;

import com.wethinkcode.java.swingy.model.heroes.Hero;

public class Westerosi extends Hero {
    public Westerosi(@NotNull(message = "A heroName can not be a null value") String heroName) {
        super(heroName, "Westerosi");
        setHeroAttack(17);
    }
}
