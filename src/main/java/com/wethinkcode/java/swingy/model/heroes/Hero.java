package com.wethinkcode.java.swingy.model.heroes;

import javax.swing.ImageIcon;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Hero {
    @Min(value = 0, message = "There is no such thing as a negative position.")
    @Max(value = 2000, message = "How many heroes are gonna make?")
    private int _heroId;

    @NotNull(message = "Hero Name can not be a null value.")
    @NotEmpty(message = "Hero Name can not be an empty value.")
    private String _name;

    @NotNull(message = "Hero type can not be a null value.")
    private String _type;

    @Min(value = 0, message = "Level Range is between 0 and inclusive 5.")
    @Max(value = 5, message = "Level Range is between 0 and inclusive 5.")
    private Integer _level;

    @Min(value = 0, message = "Experience cannot be taken away, Positive XP only.")
    @Max(value = 12200, message = "That is enough XP for a hero's lifetime.")
    private Integer _xp;

    @Min(value = 0, message = "Negative attack, really? What does he do, help the attacker?.")
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

    @Min(value = -2, message = "Unless you can justify a negative position, please just stick to positives.")
    @Max(value = 29, message = "A map can only be so big before it becomes weird")
    private Integer _preXCoord;

    @Min(value = -2, message = "Unless you can justify a negative position, please just stick to positives.")
    @Max(value = 29, message = "A map can only be so big before it becomes weird")
    private Integer _preYCoord;
    private ImageIcon _heroImage;

    protected Hero(String heroName, String heroClass) {
        setHeroName(heroName);
        setHeroType(heroClass);
        setHeroHitPoints(100);
        setHeroAttack(15);
        setHeroDefence(5);
        setHeroLevel(0);
        setHeroXP(0);
        setXCoord(-1);
        setYCoord(-1);
        setImageIcon("src/main/java/com/wethinkcode/java/swingy/model/images/custom/superman.jpg");
    }

    public void setHeroName(String heroName) {
        this._name = heroName;
    }

    public void setHeroType(String heroType) {
        this._type = heroType;
    }

    public void setHeroLevel(Integer heroLevel) {
        this._level = heroLevel;
    }

    public void setHeroXP(Integer heroExperience) {
        this._xp = heroExperience;
    }

    public void setHeroAttack(Integer heroAttack) {
        this._attack = heroAttack;
    }

    public void setHeroDefence(Integer heroDefence) {
        this._defense = heroDefence;
    }

    public void setHeroHitPoints(Integer heroHitPoints) {
        this._hitPoints = heroHitPoints;
    }

    public void setXCoord(Integer xCoord) {
        this._preXCoord = this._xCoord;
        this._preYCoord = this._yCoord;
        this._xCoord = xCoord;
    }

    public void setYCoord(Integer yCoord) {
        this._preXCoord = this._xCoord;
        this._preYCoord = this._yCoord;
        this._yCoord = yCoord;
    }

    public void setImageIcon(String imageUrl) {
        _heroImage = new ImageIcon(imageUrl);
    }

    public String getHeroName() {
        return _name;
    }

    public String getHeroType() {
        return _type;
    }

    public Integer getHeroLevel() {
        return _level;
    }

    public Integer getHeroXP() {
        return _xp;
    }

    public Integer getHeroAttack() {
        return _attack;
    }

    public Integer getHeroDefense() {
        return _defense;
    }

    public Integer getHeroHitPoints() {
        return _hitPoints;
    }

    public Integer getHeroId() {
        return _heroId;
    }

    public Integer getXCoord() {
        return _xCoord; 
    }

    public Integer getYCoord() {
        return _yCoord; 
    }

    public void restorePrePos() {
        this._xCoord = this._preXCoord;
        this._yCoord = this._preYCoord;
    }

    public ImageIcon getImageIcon() {
        return this._heroImage;
    }

    public String heroStats() {
        StringBuilder stats = new StringBuilder();
        stats.append("HeroName: " + _name + "\n");
        stats.append("HeroType: " + _type + "\n");
        stats.append("HeroLevel: " + _level + "\n");
        stats.append("HeroExperience: " + _xp + "\n");
        stats.append("HeroAttack: " + _attack + "\n");
        stats.append("HeroDefense: " + _defense + "\n");
        stats.append("HeroHitPoints: " + _hitPoints + "\n");
        return stats.toString();
    }
}
