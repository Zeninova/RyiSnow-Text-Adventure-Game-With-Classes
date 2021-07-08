//

package package01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    ChoiceHandler cHandler = new ChoiceHandler();
    UI ui = new UI();
    VisibilityManager vm = new VisibilityManager(ui);

    public static void main(String[] args) {

        new Game();
    }

    public Game() {
        ui.createUI(cHandler);
        vm.showTitleScreen();
    }

    public class ChoiceHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            String yourChoice = event.getActionCommand();

            switch(yourChoice) {
                case "start": vm.titleToTown(); break;
                case "c1": break;
                case "c2": break;
                case "c3": break;
                case "c4": break;
            }
        }
    }
}
