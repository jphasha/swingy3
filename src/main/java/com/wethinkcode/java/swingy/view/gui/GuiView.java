package com.wethinkcode.java.swingy.view.gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import com.wethinkcode.java.swingy.model.enemies.Enemy;
import com.wethinkcode.java.swingy.model.heroes.Hero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

public class GuiView extends JFrame {

    private static final long serialVersionUID = -5088966276634378713L;

    public Container gameWindow;
    public JPanel controlsPanel;
    public JPanel directionsPanel;
    public JPanel mapPanel;
    public JPanel statsPanel;
    public JButton northButton;
    public JButton eastButton;
    public JButton southButton;
    public JButton westButton;
    public JButton newGameButton;
    public JButton cliButton;
    public JButton[][] mapCells;
    public JTextArea statsField;

    public GuiView() {
        super("Swingy Game");
    }

    public void createGameWindow() {
        setSize(1200, 650);
        getRootPane().setBorder(BorderFactory.createMatteBorder(20, 4, 4, 4, Color.BLUE));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameWindow = getContentPane();
        gameWindow.setBackground(Color.CYAN);
    }

    public void toggleGuiView(String view) {
        if (view.equalsIgnoreCase("On")) {
            setVisible(true);
        } else if (view.equalsIgnoreCase("Off")) {
            setVisible(false);
        }
    }
    public void createPanels() {
        controlsPanel = new JPanel();
        directionsPanel = new JPanel();
        mapPanel = new JPanel();
        statsPanel = new JPanel();

        controlsPanel.setBackground(Color.GREEN);
        controlsPanel.setBorder(new LineBorder(Color.BLACK, 3));

        directionsPanel.setLayout(new GridLayout(4, 1));
        directionsPanel.setBackground(Color.GREEN);
        directionsPanel.setBorder(new LineBorder(Color.BLACK, 3));

        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));
        statsPanel.setBackground(Color.CYAN);

        mapPanel.setBackground(Color.CYAN);
    }

    public void createButtons() {
        northButton = new JButton("North");
        eastButton = new JButton("East");
        southButton = new JButton("South");
        westButton = new JButton("West");
        newGameButton = new JButton("New Game");
        cliButton = new JButton("Console");
    }

    public void populatePanels() {
        statsPanel.add(statsField);

        directionsPanel.add(northButton);
        directionsPanel.add(eastButton);
        directionsPanel.add(southButton);
        directionsPanel.add(westButton);

        controlsPanel.add(newGameButton);
        controlsPanel.add(cliButton);

        gameWindow.add(controlsPanel, BorderLayout.SOUTH);
        gameWindow.add(directionsPanel, BorderLayout.EAST);
        gameWindow.add(statsPanel, BorderLayout.WEST);
        gameWindow.add(mapPanel);
    }

    public void defineStatsPanel() {
        statsField = new JTextArea();
        statsField.setEditable(false);
    }

    public void guiDrawMap(int mapSize, Hero _hero, Enemy _enemy) {
        mapCells = new JButton[mapSize][mapSize];
        mapPanel.setLayout(new GridLayout(mapSize, mapSize));
        for (int col = 0; col < mapSize; col++) {
            for (int row = 0; row < mapSize; row++) {
                mapCells[col][row] = new JButton();
                mapCells[col][row].setBackground(Color.DARK_GRAY);
                if (col == _hero.getYCoord() && row == _hero.getXCoord()) {
                    mapCells[col][row].setIcon(_hero.getImageIcon());
                }
                if (col == _enemy.getYCoord() && row == _enemy.getXCoord()) {
                    mapCells[col][row].setIcon(_enemy.getImage());
                }
                mapPanel.add(mapCells[col][row]);
            }
        }
    }

    public void populateHeroStats(Hero _hero) {
        statsField.append("HERO STATS:\n\n");
        statsField.append(_hero.heroStats());
    }
}
