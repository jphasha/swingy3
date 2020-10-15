package com.wethinkcode.java.swingy.view.cli;

public class CliView {
    
    public void exitGame() {
        System.out.println("\n==============================================\n");
        System.out.println("Thank you for playing. Good Bye");
        System.out.println("\n==============================================\n");
        System.exit(0);
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
}
