package com.wethinkcode.java.swingy.model.enemies.persisted;

import java.util.HashMap;

import javax.validation.constraints.NotNull;

import com.wethinkcode.java.swingy.model.enemies.Enemy;

public class PersistedEnemy extends Enemy {
    public PersistedEnemy(@NotNull(message = "Enemy data cannot be empty") HashMap<String, String> enemyData) {
        super(enemyData.get("enemyName"), enemyData.get("enemyType"));
        setLevel(Integer.parseInt(enemyData.get("enemyLevel")));
        setHitPoints(Integer.parseInt(enemyData.get("enemyHitPoints")));
        setAttack(Integer.parseInt(enemyData.get("enemyAttack")));
        setDefense(Integer.parseInt(enemyData.get("enemyDefense")));
        setXCoord(Integer.parseInt(enemyData.get("enemyXCoord")));
        setYCoord(Integer.parseInt(enemyData.get("enemyYCoord")));
        setImage(enemyData.get("enemyImageIcon"));
    }
}
