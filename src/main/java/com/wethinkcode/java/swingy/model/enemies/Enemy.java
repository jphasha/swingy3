package com.wethinkcode.java.swingy.model.enemies;

import javax.swing.ImageIcon;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Enemy {
    @Min(value = 0, message = "There is no such thing as a negative position.")
    @Max(value = 2000, message = "How many enemies are gonna make?")
    private Integer _enemyId;

    @NotNull(message = "Enemy Name can not be a null value.")
    private String _name;

    @NotNull(message = "Enemy type can not be a null value.")
    private String _type;

    @Min(value = 0, message = "Level Range is between 0 and inclusive 5.")
    @Max(value = 5, message = "Level Range is between 0 and inclusive 5.")
    private Integer _level;

    @Min(value = 0, message = "Negative attack, really? What does he do, help the attacker.")
    @Max(value = 200, message = "Okay, let's play fare. Enough attack points")
    private Integer _attack;

    @Min(value = 0, message = "Negative defense, really? What does he do, hit himself?.")
    @Max(value = 200, message = "Okay, let's play fare. Enough defense points")
    private Integer _defense;

    @Min(value = 0, message = "Negative hit points, really? is he dead(er)?.")
    @Max(value = 200, message = "Okay, let's play fare. Enough hit points")
    private Integer _hitPoints;

    @Min(value = -2, message = "Unless you can justify a negative position, please just stick to positives.")
    @Max(value = 29, message = "A map can only be so big before it becomes weird")
    private Integer _xCoord;

    @Min(value = -2, message = "Unless you can justify a negative position, please just stick to positives.")
    @Max(value = 29, message = "A map can only be so big before it becomes weird")
    private Integer _yCoord;

    private ImageIcon _enemyImage;

    protected Enemy(String enemyName, String enemyType) {
        setName(enemyName);
        setEnemyType(enemyType);
        setXCoord(-1);
        setYCoord(-1);
    }

    public void setEnemyId(Integer enemyId) {
        this._enemyId = enemyId;
    }
    public void setName(String enemyName) {
        this._name = enemyName;
    }

    public void setEnemyType(String enemyType) {
        this._type = enemyType;
    }

    public void setLevel(Integer enemyLevel) {
        this._level = enemyLevel;
    }

    public void setAttack(Integer enemyAttack) {
        this._attack = enemyAttack;
    }

    public void setDefense(Integer enemyDefence) {
        this._defense = enemyDefence;
    }

    public void setHitPoints(Integer enemyHitPoints) {
        this._hitPoints = enemyHitPoints;
    }

    public void setXCoord(Integer enemyXCoord) {
        this._xCoord = enemyXCoord;
    }

    public void setYCoord(Integer enemyYCoord) {
        this._yCoord = enemyYCoord;
    }

    public void setImage(String imageURL) {
        this._enemyImage = new ImageIcon(imageURL);
    }

    public Integer getEnemyId() {
        return _enemyId;
    }

    public String getName() {
        return _name;
    }

    public String getEnemyType() {
        return _type;
    }

    public Integer getLevel() {
        return _level;
    }

    public Integer getAttack() {
        return _attack;
    }

    public Integer getDefense() {
        return _defense;
    }

    public Integer getHitPoints() {
        return _hitPoints;
    }

    public Integer getXCoord() {
        return _xCoord;
    }

    public Integer getYCoord() {
        return _yCoord;
    }

    public ImageIcon getImage() {
        return this._enemyImage;
    }

    public String enemyStats() {
        StringBuilder stats = new StringBuilder();
        stats.append("EnemyName: " + _name + "\n");
        stats.append("EnemyType: " + _type + "\n");
        stats.append("EnemyLevel: " + _level + "\n");
        stats.append("EnemyAttack: " + _attack + "\n");
        stats.append("EnemyDefense: " + _defense + "\n");
        stats.append("EnemyHitPoints: " + _hitPoints + "\n");
        return stats.toString();
    }
}
