package dev.project516.TUIJavaTest;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class TUI {

    public TUI() throws IOException {

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

        BasicWindow window = new BasicWindow();
        window.setTitle("Current Temperature");

        Panel contentPanel = new Panel();
        contentPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        contentPanel.addComponent(new Label("Temperature Fetcher"));

        Button exitBtn =
                new Button(
                        "Click to Exit",
                        new Runnable() {
                            @Override
                            public void run() {
                                window.close();
                            }
                        });

        Button newWindow =
                new Button(
                        "new window",
                        new Runnable() {
                            @Override
                            public void run() {
                                BasicWindow subWindow = new BasicWindow();

                                Panel subPanel = new Panel();
                                subPanel.addComponent(new Label("New Window"));

                                subPanel.addComponent(new Button("Close", subWindow::close));
                                subWindow.setComponent(subPanel);

                                gui.addWindow(subWindow);
                            }
                        });
        contentPanel.addComponent(newWindow);
        contentPanel.addComponent(exitBtn);

        window.setComponent(contentPanel);

        gui.addWindowAndWait(window);

        screen.stopScreen();
    }
}
