package com.wethinkcode.java.swingy.view.cli;

public class CliView {
    
    public void exitGame() {
        System.out.println("\n==============================================\n");
        System.out.println("Thank you for playing. Good Bye");
        System.out.println("\n==============================================\n");
    }

    public void invalidOption() {
        System.out.println("\n==============================================\n");
        System.out.println("Invalid option");
        System.out.println("\n==============================================\n");
    }

    public void enterHeroName() {
        System.out.println("\n==============================================\n");
        System.out.println("Please Enter Hero Name");
        System.out.println("\n==============================================\n");
    }

    public void selectHeroClass() {
        System.out.println("\n==============================================\n");
        System.out.println("PLEASE SELECT A HERO TYPE:");
        System.out.println("1.AESIR\n2.OLYMPIAN\n3.WESTEROSI\n4.EXIT");
        System.out.println("\n==============================================\n");
    }

    public void createHero() {  
        System.out.println("\n==============================================\n");
        System.out.println("1.CREATE NEW HERO\n2.SELECT DEFAULT HERO\n3.EXIT");
        System.out.println("\n==============================================\n");
    }

    public void aesirOptions() {
        System.out.println("ASGARDIAN");
        System.out.println("\n==============================================\n");
        System.out.println("PLEASE SELECT A DEFAULT CHARACTER:");
        System.out.println("1.BALDUR\n2.THOR\n3.ODIN\n4.EXIT");
        System.out.println("\n==============================================\n");
    }

    public void olympianOptions() {
        System.out.println("OLYMPUS");
        System.out.println("\n==============================================\n");
        System.out.println("PLEASE SELECT A DEFAULT CHARACTER:");
        System.out.println("1.ARES\n2.ATHENA\n3.ZEUS\n4.EXIT");
        System.out.println("\n==============================================\n");
    }

    public void westerosOptions() {
        System.out.println("WESTEROSI");
        System.out.println("\n==============================================\n");
        System.out.println("PLEASE SELECT A DEFAULT CHARACTER:");
        System.out.println("1.SER DUNCAN\n2.WUN WUN\n3.BALERION\n4.EXIT");
        System.out.println("\n==============================================\n");
    }

    public void moveHero() {
        System.out.println("\n==============================================\n");
        System.out.println("MOVE HERO:");
        System.out.println("1.NORTH\n2.EAST\n3.SOUTH\n4.WEST\n5.GUI\n6.EXIT");
        System.out.println("\n==============================================\n");
    }

    public void missionComplete() {
        System.out.println("\n==============================================\n");
        System.out.println("MISSION COMPLETE");
        System.out.println("Congradulations!! You have successfully completed the mission!");
        System.out.println("\n==============================================\n");
    }

    public void enemyEncounter() {
        System.out.println("ENEMY ENCOUNTER");
        System.out.println("\n==============================================\n");
        System.out.println("YOU HAVE ENCOUNTERED AN ENEMY");
        System.out.println("1.FIGHT\n2.RUN\n3.EXIT");
        System.out.println("\n==============================================\n");
    }
}
