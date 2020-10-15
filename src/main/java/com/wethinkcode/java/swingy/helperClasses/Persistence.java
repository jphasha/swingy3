package com.wethinkcode.java.swingy.helperClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.wethinkcode.java.swingy.model.enemies.Enemy;
import com.wethinkcode.java.swingy.model.enemies.persisted.PersistedEnemy;
import com.wethinkcode.java.swingy.model.heroes.Hero;
import com.wethinkcode.java.swingy.model.heroes.persisted.PersistedHero;

public class Persistence {

    public HashMap<String, String> collectHeroData(Hero _hero) {
        HashMap<String, String> heroData = new HashMap<>();
        heroData.put("heroName", _hero.getHeroName());
        heroData.put("heroType", _hero.getHeroType());
        heroData.put("heroLevel", _hero.getHeroLevel().toString());
        heroData.put("heroHitPoints", _hero.getHeroHitPoints().toString());
        heroData.put("heroAttack", _hero.getHeroAttack().toString());
        heroData.put("heroDefense", _hero.getHeroDefense().toString());
        heroData.put("heroXP", _hero.getHeroXP().toString());
        heroData.put("heroXCoord", _hero.getXCoord().toString());
        heroData.put("heroYCoord", _hero.getYCoord().toString());
        heroData.put("heroImageIcon", _hero.getImageIcon().toString());
        return heroData;
    }

    public HashMap<String, String> collectEnemyData(Enemy _enemy) {
        HashMap<String, String> enemyData = new HashMap<>();
        enemyData.put("enemyName", _enemy.getName());
        enemyData.put("enemyType", _enemy.getEnemyType());
        enemyData.put("enemyLevel", _enemy.getLevel().toString());
        enemyData.put("enemyHitPoints", _enemy.getHitPoints().toString());
        enemyData.put("enemyAttack", _enemy.getAttack().toString());
        enemyData.put("enemyDefense", _enemy.getDefense().toString());
        enemyData.put("enemyXCoord", _enemy.getXCoord().toString());
        enemyData.put("enemyYCoord", _enemy.getYCoord().toString());
        enemyData.put("enemyImageIcon", _enemy.getImage().toString());
        return enemyData;
    }

    public void persistModel(HashMap<String, String> modeletails, String model) throws CustomException {
        try {
            BufferedWriter persi = new BufferedWriter(new FileWriter("persistence/" + model + "PersistenceFile.txt", true));
            persi.append(modeletails.toString() + "\n");
            persi.close();
        } catch (IOException e) {
            throw new CustomException(model + " persistence error", "could not persist " + model);
        }
    }

    public void createPersistenceFile(String model) throws CustomException {
        File persistanceDir = new File("persistence");
        File persistenceFile = new File("persistence/" + model + "PersistenceFile.txt");
        if (!persistenceFile.exists()) {
            if (!persistanceDir.exists())
                persistanceDir.mkdir();
            try {
                persistenceFile.createNewFile();
            } catch (IOException e) {
                throw new CustomException("Error", "There was a problem when trying to create a persistance file");
            }
        }
    }

    public void removePersistenceFile(String model) {
        File persistanceDir = new File("persistence");
        File persistenceFile = new File("persistence/" + model + "PersistenceFile.txt");
        if (persistanceDir.exists()) {
            if (persistenceFile.exists()) {
                persistenceFile.delete();
            }
        }
    }

    public Hero buildPersistedHero() throws CustomException {
        Hero _hero = null;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(new File("persistence/heroPersistenceFile.txt")));
            String readLine = null;
            String lastHeroData = null;
            String heroDetails[] = null;
            HashMap<String, String> heroBuildData = new HashMap<>();
            try {
                while ((readLine = fileReader.readLine()) != null) {
                    lastHeroData = readLine;
                }
                String[] herobits = lastHeroData.replace("{", "").replace("}", "").split(", ");
                for (String string : herobits) {
                    heroDetails = string.split("=");
                    heroBuildData.put(heroDetails[0], heroDetails[1]);
                }
                _hero = new PersistedHero(heroBuildData);
            } catch (IOException e) {
                throw new CustomException("Could not read a file", "make sure persistence file exists and it is readable");
            }
        } catch (FileNotFoundException e) {
            throw new CustomException("Cannot build hero from persistence File", "Could not find the persistence file");
        }
        return _hero;
    }

    public Enemy buildPersistedEnemy() throws CustomException {
        Enemy _enemy = null;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(new File("persistence/enemyPersistenceFile.txt")));
            String readLine = null;
            String lastEnemyData = null;
            String enemyDetails[] = null;
            HashMap<String, String> enemyBuildData = new HashMap<>();
            try {
                while ((readLine = fileReader.readLine()) != null) {
                    lastEnemyData = readLine;
                }
                String[] enemybits = lastEnemyData.replace("{", "").replace("}", "").split(", ");
                for (String string : enemybits) {
                    enemyDetails = string.split("=");
                    enemyBuildData.put(enemyDetails[0], enemyDetails[1]);
                }
                _enemy = new PersistedEnemy(enemyBuildData);
            } catch (IOException e) {
                throw new CustomException("Could not read a file", "make sure persistence file exists and it is readable");
            }
        } catch (FileNotFoundException e) {
            throw new CustomException("Cannot build enemy from persistence File", "Could not find the persistence file");
        }
        return _enemy;
    }
}
