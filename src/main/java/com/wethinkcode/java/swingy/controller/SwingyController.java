package com.wethinkcode.java.swingy.controller;

import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.Validation;
import javax.validation.ValidationException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.wethinkcode.java.swingy.helperClasses.CustomException;
import com.wethinkcode.java.swingy.helperClasses.Persistence;
import com.wethinkcode.java.swingy.model.enemies.Enemy;
import com.wethinkcode.java.swingy.model.enemies.beyondTheWall.defaultModel.IceSpider;
import com.wethinkcode.java.swingy.model.enemies.beyondTheWall.defaultModel.TheOther;
import com.wethinkcode.java.swingy.model.enemies.beyondTheWall.defaultModel.WhiteWalker;
import com.wethinkcode.java.swingy.model.enemies.jortunheim.defaultModel.Jormungandr;
import com.wethinkcode.java.swingy.model.enemies.jortunheim.defaultModel.Loki;
import com.wethinkcode.java.swingy.model.enemies.jortunheim.defaultModel.Surtur;
import com.wethinkcode.java.swingy.model.enemies.tartarus.defaultModel.Atlas;
import com.wethinkcode.java.swingy.model.enemies.tartarus.defaultModel.Kronos;
import com.wethinkcode.java.swingy.model.enemies.tartarus.defaultModel.Oranos;
import com.wethinkcode.java.swingy.model.heroes.Hero;
import com.wethinkcode.java.swingy.model.heroes.asgard.Aesir;
import com.wethinkcode.java.swingy.model.heroes.asgard.defaultModel.Baldur;
import com.wethinkcode.java.swingy.model.heroes.asgard.defaultModel.Odin;
import com.wethinkcode.java.swingy.model.heroes.asgard.defaultModel.Thor;
import com.wethinkcode.java.swingy.model.heroes.mountOlympus.Olympian;
import com.wethinkcode.java.swingy.model.heroes.mountOlympus.defaultModel.Ares;
import com.wethinkcode.java.swingy.model.heroes.mountOlympus.defaultModel.Athena;
import com.wethinkcode.java.swingy.model.heroes.mountOlympus.defaultModel.Zeus;
import com.wethinkcode.java.swingy.model.heroes.westeros.Westerosi;
import com.wethinkcode.java.swingy.model.heroes.westeros.defaultModel.Balerion;
import com.wethinkcode.java.swingy.model.heroes.westeros.defaultModel.SerDuncan;
import com.wethinkcode.java.swingy.model.heroes.westeros.defaultModel.WunWun;
import com.wethinkcode.java.swingy.view.cli.CliView;
import com.wethinkcode.java.swingy.view.gui.GuiView;

public class SwingyController {
    GuiView guiView = new GuiView();
    CliView cliView = new CliView();
    Hero _hero;
    Enemy _enemy;
    int mapSize;
    File heroPersistenceFile = new File("persistence/heroPersistenceFile.txt");
    File enemyPersistenceFile = new File("persistence/enemyPersistenceFile.txt");
    Persistence pers = new Persistence();
    Random rand = new Random();
    ButtonControl buttonAction = new ButtonControl();
    static Validator validator;

    // console
    public void cliGamePlay() {
        cliStart();
        cliDispMap();
        cliMoveHero();
    }

    public void cliMoveHero() {
        try {
            cliView.moveHero();
            Scanner input = new Scanner(System.in);
            int choice = Integer.parseInt(input.nextLine());
            int heroDirs[] = { _hero.getYCoord() - 1, _hero.getXCoord() + 1, _hero.getYCoord() + 1,
                    _hero.getXCoord() - 1 };
            String NSEW[] = { "NORTH", "EAST", "SOUTH", "WEST" };
            if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
                cliProcessDirectionsInput(heroDirs, NSEW, choice);
            } else if (choice == 5) {
                pers.removePersistenceFile("enemy");
                try {
                    pers.createPersistenceFile("hero");
                    pers.createPersistenceFile("enemy");
                    pers.persistModel(pers.collectHeroData(_hero), "hero");
                    pers.persistModel(pers.collectEnemyData(_enemy), "enemy");
                } catch (CustomException e1) {
                    System.out.println("persistance Issues");
                }
                guiGamePlay();
            } else if (choice == 6) {
                cliView.exitGame();
                cliExitGame();
            } else {
                cliView.invalidOption();
                cliDispMap();
            }
        } catch (NumberFormatException e) {
            cliView.invalidOption();
            cliDispMap();
        }
        cliMoveHero();
    }

    public void cliProcessDirectionsInput(int[] coordChanges, String[] NSEW, int choice) {
        System.out.println(NSEW[choice - 1]);
        int coordChange = coordChanges[choice - 1];
        if (coordChange >= 0 && coordChange < mapSize) {
            setCoords(coordChange, choice);
            cliDispMap();
        } else {
            _hero.setXCoord(-1);
            _hero.setYCoord(-1);
            cliView.missionComplete();
            cliView.exitGame();
            cliExitGame();
        }
    }

    public void cliEnemyEncounter() {
        if ((_hero.getXCoord() == _enemy.getXCoord()) && (_hero.getYCoord() == _enemy.getYCoord())) {
            cliView.enemyEncounter();
            try {
                Scanner option = new Scanner(System.in);
                int action = Integer.parseInt(option.nextLine());
                if (action == 1) {
                    cliFightSimulation();
                } else if (action == 2) {
                    cliRunSimulation();
                } else if (action == 3) {
                    cliExitGame();
                } else {
                    cliView.invalidOption();
                    cliEnemyEncounter();
                }
            } catch (NumberFormatException e) {
                cliView.invalidOption();
                cliEnemyEncounter();
            }
            pers.removePersistenceFile("enemy");
        }
    }

    public void cliRunSimulation() {
        int success = rand.nextInt(10);

        if (success > 5) {
            System.out.println("COWARD!!");
            _hero.restorePrePos();
        } else {
            System.out.println("SORRY, YOU HAVE TO FIGHT!!!");
            cliFightSimulation();
        }
    }

    public void cliFightSimulation() {
        int heroHP = _hero.getHeroHitPoints();
        int enemyHP = _enemy.getHitPoints();
        int heroAttack = _hero.getHeroAttack();
        int enemyAttack = _enemy.getAttack();
        int heroDefence = _hero.getHeroDefense();
        int enemyDefence = _enemy.getDefense();
        int fightTurn = rand.nextInt(1);
        while (heroHP > 0 && enemyHP > 0) {
            if (heroAttack > enemyDefence && fightTurn == 0) {
                enemyHP -= heroAttack - enemyDefence;
            }
            if (enemyHP <= 0) {
                System.out.println("Nothing is as beatiful as a dead enemy");
                _enemy.setXCoord(-1);
                _enemy.setYCoord(-1);
                pers.removePersistenceFile("enemy");
            } else {
                fightTurn = 1;
            }
            if (enemyAttack > heroDefence && fightTurn == 1) {
                heroHP -= enemyAttack - heroDefence;
            }
            if (heroHP <= 0) {
                System.out.println("The enemy killed you. Mission failed");
                _hero.setXCoord(-1);
                _hero.setYCoord(-1);
                try {
                    pers.createPersistenceFile("hero");
                    pers.persistModel(pers.collectHeroData(_hero), "hero");
                    pers.removePersistenceFile("enemy");
                } catch (CustomException e) {
                    System.out.println("Could Not Persist Your Hero");
                }
                cliView.exitGame();
                System.exit(0);
            } else {
                fightTurn = 0;
            }
        }
    }

    public void setCoords(int coordChange, int choice) {
        if (choice == 1 || choice == 3) {
            _hero.setYCoord(coordChange);
        } else if (choice == 2 || choice == 4) {
            _hero.setXCoord(coordChange);
        }
    }

    public void cliExitGame() {
        try {
            pers.createPersistenceFile("hero");
            pers.persistModel(pers.collectHeroData(_hero), "hero");
            if (_enemy.getXCoord() >= 0 || _enemy.getYCoord() >= 0) {
                pers.createPersistenceFile("enemy");
                pers.persistModel(pers.collectEnemyData(_enemy), "enemy");
            }
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }

    public void cliStart() {
        try {
            _hero = pers.buildPersistedHero();
            if (_hero.getXCoord() < 0 || _hero.getYCoord() < 0) {
                cliCreateHero();
                putModelsOnMap();
            } else {
                try {
                    _enemy = pers.buildPersistedEnemy();
                } catch (CustomException e) {
                    cliCreateHero();
                    putModelsOnMap();
                }
            }
        } catch (CustomException e) {
            cliCreateHero();
            putModelsOnMap();
        }
    }

    public void cliCreateHero() {
        cliView.createHero();
        try {
            Scanner input = new Scanner(System.in);
            int option = Integer.parseInt(input.nextLine());
            if (option == 1) {
                cliCreateCustomHero();
            } else if (option == 2) {
                cliCreateDefaultHero();
            } else if (option == 3) {
                cliView.exitGame();
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            cliView.invalidOption();
            cliCreateHero();
        }
    }

    public void cliCreateDefaultHero() {
        try {
            cliView.selectHeroClass();
            Scanner input = new Scanner(System.in);
            int option = Integer.parseInt(input.nextLine());
            if (option == 1) {
                cliView.aesirOptions();
                Hero[] aesirInstances = { new Baldur(), new Thor(), new Odin() };
                cliInstantiateDefaultHero(aesirInstances);
            } else if (option == 2) {
                cliView.olympianOptions();
                Hero[] olympianInstances = { new Ares(), new Athena(), new Zeus() };
                cliInstantiateDefaultHero(olympianInstances);
            } else if (option == 3) {
                cliView.westerosOptions();
                Hero[] westerosiInstances = { new SerDuncan(), new WunWun(), new Balerion() };
                cliInstantiateDefaultHero(westerosiInstances);
            } else if (option == 4) {
                cliView.exitGame();
                System.exit(0);
            } else {
                cliView.invalidOption();
                cliCreateDefaultHero();
            }
        } catch (NumberFormatException e) {
            cliView.invalidOption();
            cliCreateDefaultHero();
        }
    }

    public void cliInstantiateDefaultHero(Hero[] heroInstances) {
        try {
            int heroOption;
            Scanner option = new Scanner(System.in);
            heroOption = Integer.parseInt(option.nextLine());
            if (heroOption >= 1 && heroOption <= 3) {
                pickDefaultHeroInstance(heroInstances, heroOption);
            } else if (heroOption == 4) {
                cliView.exitGame();
                System.exit(0);
            } else {
                cliView.invalidOption();
                cliInstantiateDefaultHero(heroInstances);
            }
        } catch (NumberFormatException e) {
            cliView.invalidOption();
            cliInstantiateDefaultHero(heroInstances);
        }
    }

    public void pickDefaultHeroInstance(Hero[] heroInstances, int heroOption) {
        _hero = heroInstances[heroOption - 1];
    }

    public void cliCreateCustomHero() {
        try {
            cliView.selectHeroClass();
            Scanner input = new Scanner(System.in);
            int option = Integer.parseInt(input.nextLine());
            if (option >= 1 && option <= 3) {
                String heroName = cliEnterHeroName();
                cliInstantiateCustomHero(heroName, option);
            } else if (option == 4) {
                cliView.exitGame();
                System.exit(0);
            } else {
                cliView.invalidOption();
                cliCreateCustomHero();
            }
        } catch (NumberFormatException e) {
            cliView.invalidOption();
            cliCreateCustomHero();
        }
    }

    String cliEnterHeroName() {
        cliView.enterHeroName();
        Scanner input = new Scanner(System.in);
        String heroName = input.nextLine();
        if (heroName.trim().isEmpty()) {
            cliView.invalidOption();
            cliEnterHeroName();
        }
        if (heroName.equals(null) || heroName.equals("null")) {
            cliView.invalidOption();
            cliEnterHeroName();
        }
        return heroName;
    }

    public void cliInstantiateCustomHero(String heroName, int option) {
        Hero[] heroTypeInstances = { new Aesir(heroName), new Olympian(heroName), new Westerosi(heroName) };
        _hero = heroTypeInstances[option - 1];
    }

    public void cliDispMap() {
        mapSize = getMapSize(_hero.getHeroLevel());
        cliEnemyEncounter();
        System.out.println("\n===========================================================\n");
        System.out.println(_hero.heroStats());
        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                if (x == _hero.getXCoord() && y == _hero.getYCoord()) {
                    System.out.print(" H ");
                } else if (x == _enemy.getXCoord() && y == _enemy.getYCoord()) {
                    System.out.print(" E ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
        System.out.println("\n===========================================================\n");
    }

    // gui
    public void guiGamePlay() {
        guiStart();
        mapSize = getMapSize(_hero.getHeroLevel());
        guiDispMap();
        while (true) {}
    }

    public void guiStart() {
        System.out.println("GUI");
        try {
            _hero = pers.buildPersistedHero();
            if (_hero.getXCoord() < 0 || _hero.getYCoord() < 0) {
                guiCreateHero();
                putModelsOnMap();
            } else {
                try {
                    _enemy = pers.buildPersistedEnemy();
                } catch (CustomException e) {
                    guiCreateHero();
                    putModelsOnMap();
                }
            }
        } catch (CustomException e) {
            guiCreateHero();
            putModelsOnMap();
        }
    }

    public void guiCreateHero() {
        String[] modes = { "Custom", "Default" };
        int mode = JOptionPane.showOptionDialog(null, "Create a Custom Hero or Choose from the Default Heroes",
                "Hero Mode", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, modes, modes[0]);
        if (mode == 0) {
            guiCreateCustomHero();
        } else if (mode == 1) {
            guiCreateDefaultHero();
        } else {
            guiCreateHero();
        }
    }

    public void guiCreateCustomHero() {
        try {
            setUpValidator();
            String heroName = JOptionPane.showInputDialog("Enter Hero Name");
            if (heroName.trim().isEmpty() || heroName.equals(null)) {
                System.out.println("Hero name cannot be an empty value");
                guiCreateCustomHero();
            }
            Hero[] heroInstances = { new Aesir(heroName), new Olympian(heroName), new Westerosi(heroName) };
            String[] heroTypes = { "Aesir", "Olympian", "Westerosi" };
            _hero = heroInstances[JOptionPane.showOptionDialog(null, "Select Hero Type", "Hero Type",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, heroTypes, heroTypes[0])];
            Set<ConstraintViolation<Hero>> heroErrors = validateHeroCredentials(_hero);
            if (heroErrors.size() > 0) {
                StringBuilder errors = new StringBuilder();
                for (ConstraintViolation<Hero> constraintViolation : heroErrors) {
                    errors.append(constraintViolation + "\n");
                }
                JOptionPane.showMessageDialog(null, errors, "ERROR!!", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (ValidationException e) {
            // System.out.println(e.)
            System.out.println(e.getMessage());
        }
    }

    public void guiCreateDefaultHero() {
        String[] heroTypes = { "Aesir", "Olympian", "Westerosi" };
        String[] aesir = { "Baldur", "Thor", "Odin" };
        Aesir[] aesirInstances = { new Baldur(), new Thor(), new Odin() };
        String[] olympians = { "Ares", "Athena", "Zeus" };
        Olympian[] olympianInstances = { new Ares(), new Athena(), new Zeus() };
        String[] westerosi = { "SerDuncan", "WunWun", "Balerion" };
        Westerosi[] westerosiInstances = { new SerDuncan(), new WunWun(), new Balerion() };
        Hero[][] heroTypeInstances = { aesirInstances, olympianInstances, westerosiInstances };
        String[][] heroTypeOptions = { aesir, olympians, westerosi };

        int type = JOptionPane.showOptionDialog(null, "Select Hero Type", "Hero Type", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, heroTypes, heroTypes[0]);
        String[] heroTypeSelection = heroTypeOptions[type];
        Hero[] heroType = heroTypeInstances[type];
        _hero = heroType[JOptionPane.showOptionDialog(null, "Select Default Hero", "Default Hero",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, heroTypeSelection,
                heroTypeSelection[0])];
    }

    public void guiDispMap() {
        guiView.createGameWindow();
        guiView.createPanels();
        guiView.createButtons();
        guiView.defineStatsPanel();
        guiView.populateHeroStats(_hero);
        guiView.guiDrawMap(mapSize, _hero, _enemy);
        guiView.populatePanels();
        activateButtons();
        guiView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    pers.createPersistenceFile("hero");
                    pers.createPersistenceFile("enemy");
                    pers.persistModel(pers.collectHeroData(_hero), "hero");
                    if (_enemy.getXCoord() >= 0) {
                        pers.persistModel(pers.collectEnemyData(_enemy), "enemy");
                    }
                } catch (CustomException e1) {
                    System.out.println("Persistance gone wrong");
                }
                System.exit(0);
            }
        });
        guiView.toggleGuiView("On");
        guiEnemyEncounter();
    }

    public Enemy generateEnemy() {
        Enemy[] enemies = { new IceSpider(), new TheOther(), new WhiteWalker(), new Jormungandr(), new Loki(),
                new Surtur(), new Atlas(), new Kronos(), new Oranos() };
        Enemy enemy = enemies[rand.nextInt(8)];
        return enemy;
    }

    public void guiMoveNorth() {
        if (_hero.getYCoord() > 0) {
            guiView.mapCells[_hero.getYCoord()][_hero.getXCoord()].setIcon(null);
            guiView.mapCells[_hero.getYCoord() - 1][_hero.getXCoord()].setIcon(_hero.getImageIcon());
            _hero.setYCoord(_hero.getYCoord() - 1);
            guiEnemyEncounter();
        } else {
            guiMissionComplete();
        }
    }

    public void guiMoveSouth() {
        if (_hero.getYCoord() < mapSize - 1) {
            guiView.mapCells[_hero.getYCoord()][_hero.getXCoord()].setIcon(null);
            guiView.mapCells[_hero.getYCoord() + 1][_hero.getXCoord()].setIcon(_hero.getImageIcon());
            _hero.setYCoord(_hero.getYCoord() + 1);
            guiEnemyEncounter();
        } else {
            guiMissionComplete();
        }
    }

    public void guiMoveWest() {
        if (_hero.getXCoord() > 0) {
            guiView.mapCells[_hero.getYCoord()][_hero.getXCoord()].setIcon(null);
            guiView.mapCells[_hero.getYCoord()][_hero.getXCoord() - 1].setIcon(_hero.getImageIcon());
            _hero.setXCoord(_hero.getXCoord() - 1);
            guiEnemyEncounter();
        } else {
            guiMissionComplete();
        }
    }

    public void guiMoveEast() {
        if (_hero.getXCoord() < mapSize - 1) {
            guiView.mapCells[_hero.getYCoord()][_hero.getXCoord()].setIcon(null);
            guiView.mapCells[_hero.getYCoord()][_hero.getXCoord() + 1].setIcon(_hero.getImageIcon());
            _hero.setXCoord(_hero.getXCoord() + 1);
            guiEnemyEncounter();
        } else {
            guiMissionComplete();
        }
    }

    public void guiMissionComplete() {
        guiView.mapCells[_hero.getYCoord()][_hero.getXCoord()].setIcon(null);
        _hero.setXCoord(-1);
        _hero.setYCoord(-1);
        try {
            pers.createPersistenceFile("hero");
            pers.persistModel(pers.collectHeroData(_hero), "hero");
            pers.removePersistenceFile("enemy");
        } catch (CustomException e) {
            System.out.println("Hero not persisited");
        }
        JOptionPane.showMessageDialog(null, "CONGRADULATIONS!!!, YOU HAVE SUCCESSFULLY COMPLETED THE MISSION.", "MISSION COMPLETE!!", JOptionPane.PLAIN_MESSAGE);
        guiView.toggleGuiView("Off");
        System.exit(0);
    }

    public void activateButtons() {
        guiView.northButton.addActionListener(buttonAction);
        guiView.southButton.addActionListener(buttonAction);
        guiView.eastButton.addActionListener(buttonAction);
        guiView.westButton.addActionListener(buttonAction);
        guiView.cliButton.addActionListener(buttonAction);
        // guiView.newGameButton.addActionListener(buttonAction);
    }

    public void guiEnemyEncounter() {
        String[] choices = { "Fight", "Run" };
        int choice;
        if (_hero.getXCoord() == _enemy.getXCoord() && _hero.getYCoord() == _enemy.getYCoord()) {
            choice = JOptionPane.showInternalOptionDialog(null, "You have encountered an enemy, what will you do?",
            "Enemy encounter", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
            choices, choices[1]);
            if (choice == 0) {
                guiFight();
            } else if (choice == 1) {
                guiRunAway();
            } else {
                System.out.println("You have to make a choice");
                guiEnemyEncounter();
            }
        }
    }

    public void guiFight() {
        int heroHP = _hero.getHeroHitPoints();
        int enemyHP = _enemy.getHitPoints();
        int heroAttack = _hero.getHeroAttack();
        int enemyAttack = _enemy.getAttack();
        int heroDefence = _hero.getHeroDefense();
        int enemyDefence = _enemy.getDefense();
        int fightTurn = 0;
        while (heroHP > 0 && enemyHP > 0) {
            if (heroAttack > enemyDefence && fightTurn == 0) {
                enemyHP -= heroAttack - enemyDefence;
            } if (enemyHP <= 0) {
                System.out.println("Nothing is as beatiful as a dead enemy");
                _enemy.setXCoord(-1);
                _enemy.setYCoord(-1);
                pers.removePersistenceFile("enemy");
                JOptionPane.showMessageDialog(null, "The enemy is Dead, Proceed with the Game", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
                guiView.mapCells[_hero.getYCoord()][_hero.getXCoord()].setIcon(_hero.getImageIcon());
            } else {
                fightTurn = 1;
            }
            if (enemyAttack > heroDefence && fightTurn == 1) {
                heroHP -= enemyAttack - heroDefence;
            } if (heroHP <= 0) {
                System.out.println("The enemy killed you. Mission failed");
                _hero.setXCoord(-1);
                _hero.setYCoord(-1);
                pers.removePersistenceFile("enemy");
                guiView.mapCells[_enemy.getYCoord()][_enemy.getXCoord()].setIcon(_enemy.getImage());
                JOptionPane.showMessageDialog(null, "The enemy has killed you, Mission Failed!!", "FAILURE", JOptionPane.PLAIN_MESSAGE);
                guiView.toggleGuiView("Off");
                System.exit(0);
            } else {
                fightTurn = 0;
            }
        }
        pers.removePersistenceFile("enemy");
    }

    public void guiRunAway() {
        int success = rand.nextInt(10);
        if (success > 5) {
            _hero.restorePrePos();
            guiView.mapCells[_hero.getYCoord()][_hero.getXCoord()].setIcon(_hero.getImageIcon());
            guiView.mapCells[_enemy.getYCoord()][_enemy.getXCoord()].setIcon(_enemy.getImage());
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, You have to Fight!!", "CANNOT RUN AWAY", JOptionPane.PLAIN_MESSAGE);
            guiFight();
        }
    }

    // general
    public void putModelsOnMap() {
        mapSize = getMapSize(_hero.getHeroLevel());
        _hero.setXCoord(mapSize / 2);
        _hero.setYCoord(mapSize / 2);
        _enemy = generateEnemy();
        _enemy.setXCoord(rand.nextInt(mapSize));
        _enemy.setYCoord(rand.nextInt(mapSize));
    }

    public int getMapSize(int level) {
        return (level - 1) * 5 + 10 - (level % 2);
    }

    public void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public Set<ConstraintViolation<Hero>> validateHeroCredentials(Hero _hero) {
        Set<ConstraintViolation<Hero>> errors = validator.validate(_hero);
        
        return errors;
    }

    public class ButtonControl implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "North") {
                guiMoveNorth();
            } else if (e.getActionCommand() == "South") {
                guiMoveSouth();
            } else if (e.getActionCommand() == "East") {
                guiMoveEast();
            } else if (e.getActionCommand() == "West") {
                guiMoveWest();
            } else if (e.getActionCommand() == "Console") {
                guiView.toggleGuiView("Off");
                try {
                    pers.removePersistenceFile("enemy");
                    pers.createPersistenceFile("hero");
                    pers.createPersistenceFile("enemy");
                    pers.persistModel(pers.collectHeroData(_hero), "hero");
                    pers.persistModel(pers.collectEnemyData(_enemy), "enemy");
                } catch (CustomException e1) {
                    System.out.println("Could not persist model");
                }
                cliGamePlay();
            } else if (e.getActionCommand() == "New Game") {
                pers.removePersistenceFile("enemy");
                guiView.toggleGuiView("Off");
                guiGamePlay();
            }
        }
    }
}
