package window;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

    private DrawingPanel drawingPanel;

    private static boolean isNumeric(String str) {
        return str.matches("(\\d+)");
    }

    public GUI() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Paint");

        Container content = getContentPane();

        //------------------figures list---------------------------

        DrawingPanel.Figures[] figureTypes = DrawingPanel.Figures.values();

        DefaultListModel<String> figuresModel = new DefaultListModel<>();
        figuresModel.addElement(" Segment");   //0
        figuresModel.addElement(" Ray");       //1
        figuresModel.addElement(" Line");      //2
        figuresModel.addElement(" Polyline");  //3
        figuresModel.addElement(" Circle");    //4
        figuresModel.addElement(" Ellipse");   //5
        figuresModel.addElement(" Rectangle"); //6
        figuresModel.addElement(" Triangle");  //7
        figuresModel.addElement(" Polygon");   //8
        figuresModel.addElement(" Regular Polygon"); //9
        figuresModel.addElement(" Rhomb");     //10
        figuresModel.addElement(" Square");    //11

        JList listOfFigures = new JList(figuresModel);
        listOfFigures.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listOfFigures.setLayoutOrientation(JList.VERTICAL);
        listOfFigures.setFixedCellHeight(40);
        listOfFigures.setSelectedIndex(0);

        listOfFigures.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int index = listOfFigures.getSelectedIndex();

                drawingPanel.setCurrentFigureType(figureTypes[index]);

                DrawingPanel.Figures currentType = figureTypes[index];
                if (currentType.equals(DrawingPanel.Figures.POLYLINE) ||
                        currentType.equals(DrawingPanel.Figures.POLYGON) ||
                        currentType.equals(DrawingPanel.Figures.REGULARPOLYGON)) {
                    String str = JOptionPane.showInputDialog("Vertex count: ");
                    int n;

                    if (str == null || !isNumeric(str))
                        n = currentType.equals(DrawingPanel.Figures.POLYLINE) ? 2 : 3;
                    else {
                        n = Integer.parseInt(str);
                        if (currentType.equals(DrawingPanel.Figures.POLYLINE))
                            n = n < 2 ? 2 : n;
                        else
                            n = n < 3 ? 3 : n;
                    }

                    drawingPanel.setCurrentVertexCount(n);
                }
            }
        });

        JScrollPane figuresScrollPane = new JScrollPane(listOfFigures);
        figuresScrollPane.setPreferredSize(new Dimension(140, this.getHeight()));
        content.add(figuresScrollPane, BorderLayout.WEST);

        //-----------------drawing panel---------------------------

        drawingPanel = new DrawingPanel(this);

        JScrollPane drawingScrollPane = new JScrollPane(drawingPanel);
        drawingScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        drawingScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        drawingScrollPane.setPreferredSize(new Dimension(700, 500));
        content.add(drawingScrollPane, BorderLayout.CENTER);

        //--------------colors--------------------------------------

        JPanel colorPanel = new JPanel();

        JButton buttonColor = new JButton("Color");
        JButton buttonBorderColor = new JButton("BorderColor");

        colorPanel.add(buttonColor);
        colorPanel.add(buttonBorderColor);

        buttonColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Color c = JColorChooser.showDialog(content, "Choose color", Color.CYAN);
                drawingPanel.setCurrentColor(c);
            }
        });

        buttonBorderColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Color c = JColorChooser.showDialog(content, "Choose color", Color.CYAN);
                drawingPanel.setCurrentBorderColor(c);
            }
        });

        content.add(colorPanel, BorderLayout.BEFORE_FIRST_LINE);

        //-----------------menu------------------------------------------

        JMenuBar menuBar = new JMenuBar();
        JMenu menuOptions = new JMenu("Options");
        menuOptions.setMnemonic(KeyEvent.VK_O);//Alt+O
        JMenuItem menuExit = new JMenuItem("Exit", KeyEvent.VK_E);
        menuBar.add(menuOptions);
        menuOptions.add(menuExit);
        setJMenuBar(menuBar);

        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        //---------------------------------------------------------------

        setVisible(true);
        pack();
    }
}