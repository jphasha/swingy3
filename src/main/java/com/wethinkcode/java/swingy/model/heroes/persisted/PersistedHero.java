package com.wethinkcode.java.swingy.model.heroes.persisted;

import java.util.HashMap;

import javax.validation.constraints.NotNull;

import com.wethinkcode.java.swingy.model.heroes.Hero;

public class PersistedHero extends Hero {
    public PersistedHero(@NotNull(message = "Hero data cannot be empty") HashMap<String, String> heroData) {
        super(heroData.get("heroName"), heroData.get("heroType"));
        setHeroHitPoints(Integer.parseInt(heroData.get("heroHitPoints")));
        setHeroAttack(Integer.parseInt(heroData.get("heroAttack")));
        setHeroDefence(Integer.parseInt(heroData.get("heroDefense")));
        setHeroLevel(Integer.parseInt(heroData.get("heroLevel")));
        setHeroXP(Integer.parseInt(heroData.get("heroXP")));
        setXCoord(Integer.parseInt(heroData.get("heroXCoord")));
        setYCoord(Integer.parseInt(heroData.get("heroYCoord")));
        setImageIcon(heroData.get("heroImageIcon"));
    }
}
