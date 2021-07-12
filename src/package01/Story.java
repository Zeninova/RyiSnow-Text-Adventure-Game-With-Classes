package package01;

import package02.*;

public class Story {

    Game game;
    UI ui;
    VisibilityManager vm;
    Player player = new Player();
    SuperMonster monster;

    int silverRing;

    public Story(Game g, UI userInterface, VisibilityManager vManager) {
        game = g;
        ui = userInterface;
        vm = vManager;
    }

    public void defaultSetup() {

        player.hp = 10;
        ui.hpNumberLabel.setText("" + player.hp);

        player.currentWeapon = new Weapon_Knife();
        ui.weaponNameLabel.setText(player.currentWeapon.name);

        silverRing = 0;
    }

    public void selectPosition(String nextPosition) {
        switch(nextPosition) {
            case "townGate": townGate(); break;
            case "talkGuard": talkGuard(); break;
            case "attackGuard": attackGuard(); break;
            case "crossRoad": crossRoad(); break;
            case "north": north(); break;
            case "east": east(); break;
            case "west": west(); break;
            case "fight": fight(); break;
            case "playerAttack": playerAttack(); break;
            case "monsterAttack": monsterAttack(); break;
            case "win": win(); break;
            case "lose": lose(); break;
            case "ending": ending(); break;
            case "toTitle": toTitle(); break;
        }
    }

    public void townGate() {
        ui.mainTextArea.setText("You are at the gate of the town. \nA guard is standing in front of you. \n\nWhat do you do?");
        ui.choice1.setText("Talk to the guard");
        ui.choice2.setText("Attack the guard");
        ui.choice3.setText("Leave");
        ui.choice4.setText("");

        game.nextPosition1 = "talkGuard";
        game.nextPosition2 =  "attackGuard";
        game.nextPosition3 = "crossRoad";
        game.nextPosition4 = "";
    }

    public void talkGuard() {

        if(silverRing == 0) {
            ui.mainTextArea.setText("Guard: Hello Stranger. I have never seen your face. \nI'm sorry, but we cannot let a stranger enter our town.");
            ui.choice1.setText(">");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "townGate";
            game.nextPosition2 =  "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
        else if(silverRing == 1) {
            ending();
        }
    }

    public void attackGuard() {
        ui.mainTextArea.setText("Guard: Hey! Don't be stupid! \n\nThe guard attacked you. \n(You receive 3 damange)");
        player.hp -= 3;
        ui.hpNumberLabel.setText("" + player.hp);
        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "townGate";
        game.nextPosition2 =  "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void crossRoad() {
        ui.mainTextArea.setText("You are at a crossroad.\nIf you go south, you will go back to the town.");
        ui.choice1.setText("Go north");
        ui.choice2.setText("Go east");
        ui.choice3.setText("Go south");
        ui.choice4.setText("Go west");

        game.nextPosition1 = "north";
        game.nextPosition2 =  "east";
        game.nextPosition3 = "townGate";
        game.nextPosition4 = "west";
    }

    public void north() {
        ui.mainTextArea.setText("There is a river.\nYou drink the water and rest at the riverside.\n\n(Your HP is recovered by 2)");
        player.hp += 2;
        ui.hpNumberLabel.setText("" + player.hp);

        ui.choice1.setText("Go south");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 =  "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void east() {
        ui.mainTextArea.setText("You walked into a forest and found a Long Sword!\n\n(You obtained a Long Sword)");
        player.currentWeapon = new Weapon_LongSword();
        ui.weaponNameLabel.setText(player.currentWeapon.name);

        ui.choice1.setText("Go west");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 =  "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void west() {

        int i = new java.util.Random().nextInt(100)+1;

        if(i < 90) {
            monster = new Monster_Goblin();
        }
        else {
            monster = new Monster_Gandalf();
        }

        ui.mainTextArea.setText("You encounter a " + monster.name + "!");
        ui.choice1.setText("Fight");
        ui.choice2.setText("Run");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "fight";
        game.nextPosition2 =  "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void fight() {

        ui.mainTextArea.setText(monster.name + ": " + monster.hp + "\n\nWhat do you do?");
        ui.choice1.setText("Attack");
        ui.choice2.setText("Run");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "playerAttack";
        game.nextPosition2 =  "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void playerAttack() {

        int playerDamage = new java.util.Random().nextInt(player.currentWeapon.damage);

        ui.mainTextArea.setText("You attacked the " + monster.name + " and gave " + playerDamage + " damage!");

        monster.hp -= playerDamage;

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if(monster.hp > 0) {
            game.nextPosition1 = "monsterAttack";
            game.nextPosition2 =  "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
        else if(monster.hp < 1) {
            game.nextPosition1 = "win";
            game.nextPosition2 =  "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void monsterAttack() {

        int monsterDamage = new java.util.Random().nextInt(monster.attack);

        player.hp -= monsterDamage;
        ui.hpNumberLabel.setText("" + player.hp);

        ui.mainTextArea.setText(monster.attackMessage + "\nYou received " + monsterDamage + " damage!");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if(player.hp > 0) {
            game.nextPosition1 = "fight";
            game.nextPosition2 =  "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
        else if(player.hp < 1) {
            game.nextPosition1 = "lose";
            game.nextPosition2 =  "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void win() {

        ui.mainTextArea.setText("You've defeated the " + monster.name + "!\nThe monster dropped a ring!\n\n(You obtained a Silver Ring!)");

        silverRing = 1;

        ui.choice1.setText("Go east");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 =  "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void lose() {

        ui.mainTextArea.setText("You are dead!\n\n<GAME OVER>");

        ui.choice1.setText("To the title screen");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "toTitle";
        game.nextPosition2 =  "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void ending() {

        ui.mainTextArea.setText("Guard: Oh you killed that goblin!?\nThank you so much. You are a true hero!\nWelcome to our town!\n\n<THE END>");

        ui.choice1.setVisible(false);
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);
    }

    public void toTitle() {

        defaultSetup();
        vm.showTitleScreen();
    }
}
