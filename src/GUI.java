import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

class GUI extends JFrame {

    private DrawingPanel drawingPanel;

    GUI() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Paint");

        Container content = getContentPane();
        drawingPanel = new DrawingPanel(this);

        JScrollPane pane = new JScrollPane(drawingPanel);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.setPreferredSize(new Dimension(500, 500));
        content.add(pane, BorderLayout.CENTER);

        JPanel colorPanel = new JPanel();
        content.add(colorPanel, BorderLayout.NORTH);
        JButton buttonRed = new JButton("Red");
        colorPanel.add(buttonRed);
        buttonRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                drawingPanel.setColor(Color.RED);
            }
        });
        JButton buttonGreen = new JButton("Green");
        colorPanel.add(buttonGreen);
        buttonGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                drawingPanel.setColor(Color.GREEN);
            }
        });
        JButton buttonBlue = new JButton("Blue");
        colorPanel.add(buttonBlue);
        buttonBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                drawingPanel.setColor(Color.BLUE);
            }
        });

        JMenuItem menuExit = new JMenuItem("Exit", KeyEvent.VK_E);
        JMenu menuOptions = new JMenu("Options");
        menuOptions.setMnemonic(KeyEvent.VK_O);//Alt+O
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuOptions);
//        menuOptions.addSeparator();
        menuOptions.add(menuExit);
        setJMenuBar(menuBar);

        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        setVisible(true);
        pack();
    }
}