package com.wethinkcode.java.swingy.controller;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

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
    static Hero _hero;
    static Enemy _enemy;
    int mapSize;
    File heroPersistenceFile = new File("persistence/heroPersistenceFile.txt");
    File enemyPersistenceFile = new File("persistence/enemyPersistenceFile.txt");
    Persistence pers = new Persistence();
    Random rand = new Random();

    // console
    public void cliGamePlay() {
        cliStart();
        cliDispMap();
    }

    public void cliStart() {
        System.out.println("CLI");
        try {
            _hero = pers.buildPersistedHero();
            if (_hero.getXCoord() < 0 || _hero.getYCoord() < 0) {
                cliCreateHero();
                putModelsOnMap();
            } else {
                try {
                    _enemy = pers.buildPersistedEnemy();
                } catch (CustomException e) {
                    System.out.println("Empty Map");
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
                Hero[] aesirInstances = {new Baldur(), new Thor(), new Odin()};
                instantiateDefaultHero(aesirInstances);
            } else if (option == 2) {
                cliView.olympianOptions();
                Hero[] olympianInstances = {new Ares(), new Athena(), new Zeus()};
                instantiateDefaultHero(olympianInstances);
            } else if (option == 3) {
                cliView.westerosOptions();
                Hero[] westerosiInstances = {new SerDuncan(), new WunWun(), new Balerion()};
                instantiateDefaultHero(westerosiInstances);
            } else if (option == 4) {
                cliView.exitGame();
            } else {
                cliView.invalidOption();
                cliCreateDefaultHero();
            }
        } catch (NumberFormatException e) {
            cliView.invalidOption();
            cliCreateDefaultHero();
        }
    }

    public void instantiateDefaultHero(Hero[] heroInstances) {
        try {
            int heroOption;
            Scanner option = new Scanner(System.in);
            heroOption = Integer.parseInt(option.nextLine());
            if (heroOption >= 1 && heroOption <= 3) {
                pickDefaultHeroInstance(heroInstances, heroOption);
            } else if (heroOption == 4) {
                cliView.exitGame();
            } else {
                cliView.invalidOption();
                instantiateDefaultHero(heroInstances);
            }
        } catch (NumberFormatException e) {
            cliView.invalidOption();
            instantiateDefaultHero(heroInstances);
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
                String heroName = enterHeroName();
                instantiateCustomHero(heroName, option);
            } else if (option == 4) {
                cliView.exitGame();
             } else {
                cliView.invalidOption();
                cliCreateCustomHero();
            }
        } catch (NumberFormatException e) {
            cliView.invalidOption();
            cliCreateCustomHero();
        }
    }

    String enterHeroName() {
        cliView.enterHeroName();
        Scanner input = new Scanner(System.in);
        String heroName = input.nextLine();
        if (heroName.trim().isEmpty()) {
            cliView.invalidOption();
            enterHeroName();
        }
        return heroName;
    }

    public void instantiateCustomHero(String heroName, int option) {
        Hero[] heroTypeInstances = {new Aesir(heroName), new Olympian(heroName), new Westerosi(heroName)};
        _hero = heroTypeInstances[option - 1];
    }

    public void cliDispMap() {
        mapSize = getMapSize(_hero.getHeroLevel());
        System.out.println("\n===========================================================\n");
        System.out.println(_hero.heroStats());
        for(int y = 0; y < mapSize; y++) {
            for(int x = 0; x < mapSize; x++) {
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
        guiDispMap();
        guiView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("WindowClosingDemo.windowClosing");
                System.exit(0);
            }
        });
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
                    System.out.println("Empty Map");
                }
            }
        } catch (CustomException e) {
            guiCreateHero();
            putModelsOnMap();
        }
    }

    public void guiCreateHero() {
        String[] modes = {"Custom", "Default"};
        int mode = JOptionPane.showOptionDialog(null, "Create a Custom Hero or Choose from the Default Heroes", "Hero Mode", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, modes, modes[0]);
        if (mode == 0) {
            guiCreateCustomHero();
        } else if (mode == 1) {
            guiCreateDefaultHero();
        } else {
            guiCreateHero();
        }
    }

    public void guiCreateCustomHero() {
        String heroName = JOptionPane.showInputDialog("Enter Hero Name");
        if (heroName.trim().isEmpty() || heroName.equals(null)) {
            guiCreateCustomHero();
        }
        Hero[] heroInstances = {new Aesir(heroName), new Olympian(heroName), new Westerosi(heroName)};
        String[] heroTypes = {"Aesir", "Olympian", "Westerosi"};
        _hero = heroInstances[JOptionPane.showOptionDialog(null, "Select Hero Type", "Hero Type", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, heroTypes, heroTypes[0])];
    }

    public void guiCreateDefaultHero() {
        String[] heroTypes = {"Aesir", "Olympian", "Westerosi"};
        String[] aesir = {"Baldur", "Thor", "Odin"};
        Aesir[] aesirInstances = {new Baldur(), new Thor(), new Odin()};
        String[] olympians = {"Ares", "Athena", "Zeus"};
        Olympian[] olympianInstances = {new Ares(), new Athena(), new Zeus()};
        String[] westerosi = {"SerDuncan", "WunWun", "Balerion"};
        Westerosi[] westerosiInstances = {new SerDuncan(), new WunWun(), new Balerion()};
        Hero[][] heroTypeInstances = {aesirInstances, olympianInstances, westerosiInstances};
        String[][] heroTypeOptions = {aesir, olympians, westerosi};

        int type = JOptionPane.showOptionDialog(null, "Select Hero Type", "Hero Type", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, heroTypes, heroTypes[0]);
        String[] heroTypeSelection = heroTypeOptions[type];
        Hero[] heroType = heroTypeInstances[type];
        _hero = heroType[JOptionPane.showOptionDialog(null, "Select Default Hero", "Default Hero", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, heroTypeSelection, heroTypeSelection[0])];
    }

    public void guiDispMap() {
        guiView.createGameWindow();
        guiView.createPanels();
        guiView.createButtons();
        guiView.defineStatsPanel();
        guiView.populateHeroStats(_hero);
        guiView.guiDrawMap(mapSize, _hero, _enemy);
        guiView.populatePanels();
        guiView.toggleGuiView("On");
    }

    // general
    public void putModelsOnMap() {
        mapSize = getMapSize(_hero.getHeroLevel());
        _hero.setXCoord(mapSize/2);
        _hero.setYCoord(mapSize/2);
        _enemy = generateEnemy();
        _enemy.setXCoord(rand.nextInt(mapSize));
        _enemy.setYCoord(rand.nextInt(mapSize));
    }

    public int getMapSize(int level) {
        return (level - 1) * 5 + 10 - (level % 2);
    }

    public Enemy generateEnemy() {
        Enemy[] enemies = {new IceSpider(), new TheOther(), new WhiteWalker(),
                            new Jormungandr(), new Loki(), new Surtur(),
                            new Atlas(), new Kronos(), new Oranos()};
        Enemy enemy = enemies[rand.nextInt(8)];
        return enemy;
    }
}
