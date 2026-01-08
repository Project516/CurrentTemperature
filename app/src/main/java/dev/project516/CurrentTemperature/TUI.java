package dev.project516.CurrentTemperature;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class TUI {

    public TUI() throws IOException {

        Location location = new Location();
        Temperature temp = new Temperature(location.getLat(), location.getLon());

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

        BasicWindow window = new BasicWindow();
        window.setTitle("Current Temperature");

        Panel contentPanel = new Panel();
        contentPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        contentPanel.addComponent(
                new Label(
                        "It is currently " + temp.getTemp() + "°F in " + location.getCity() + "."));

        Button exitBtn =
                new Button(
                        "Click to Exit",
                        new Runnable() {
                            @Override
                            public void run() {
                                window.close();
                            }
                        });

        contentPanel.addComponent(exitBtn);

        window.setComponent(contentPanel);

        gui.addWindowAndWait(window);

        screen.stopScreen();
    }
}
