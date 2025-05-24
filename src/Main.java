import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;

public class Main extends Canvas {
    public static void main(String[] args) {
        Color menuBoxColor = new Color(222, 148, 0);
        Color canvasBoxColor = new Color(138, 130, 114);
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 4);

        JFrame jfrCore = new JFrame("SpriteSheetTester");
        setJFrameAttributes(jfrCore);

        //Menu panel
        JPanel menuBox = new JPanel();
        menuBox.setBackground(menuBoxColor);
        menuBox.setBorder(border);
        menuBox.setLayout(new BoxLayout(menuBox, BoxLayout.Y_AXIS));

        //Path input
        JPanel pathInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton browseButton = new JButton("Browse");
        pathInputPanel.add(browseButton);
        JTextField pathTextField = new JTextField("Enter the path to your spritesheet here", 46);
        pathInputPanel.add(pathTextField);
        pathInputPanel.setBackground(menuBoxColor);

        //Set dimension and speed variables
        JPanel heightPanel = new JPanel(new GridLayout(0, 1));
        JLabel heightLabel = new JLabel("Sprite height");
        JTextField heightTextField = new JTextField("16", 10);
        heightPanel.add(heightLabel);
        heightPanel.add(heightTextField);

        JPanel widthPanel = new JPanel(new GridLayout(0, 1));
        JLabel widthLabel = new JLabel("Sprite width");
        JTextField widthTextField = new JTextField("16",10);
        widthPanel.add(widthLabel);
        widthPanel.add(widthTextField);

        JPanel rowsPanel = new JPanel(new GridLayout(0, 1));
        JLabel rowsLabel = new JLabel("Sheet rows");
        JTextField rowsTextField = new JTextField("10",10);
        rowsPanel.add(rowsLabel);
        rowsPanel.add(rowsTextField);

        JPanel columnsPanel = new JPanel(new GridLayout(0, 1));
        JLabel columnsLabel = new JLabel("Sheet columns");
        JTextField columnsTextField = new JTextField("1",10);
        columnsPanel.add(columnsLabel);
        columnsPanel.add(columnsTextField);

        JPanel fpsPanel = new JPanel(new GridLayout(0, 1));
        JLabel fpsLabel = new JLabel("Frames per second");
        JTextField fpsTextField = new JTextField("60",10);
        fpsPanel.add(fpsLabel);
        fpsPanel.add(fpsTextField);

        JPanel setVarsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setVarsPanel.add(heightPanel);
        setVarsPanel.add(widthPanel);
        setVarsPanel.add(rowsPanel);
        setVarsPanel.add(columnsPanel);
        setVarsPanel.add(fpsPanel);
        setVarsPanel.setBackground(menuBoxColor);

        //Run panel
        JPanel runPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton runButton = new JButton("Run");
        runButton.setPreferredSize(new Dimension(100, 30));
        runPanel.add(runButton);
        runPanel.setBackground(menuBoxColor);

        menuBox.add(pathInputPanel);
        menuBox.add(setVarsPanel);
        menuBox.add(runPanel);

        //Add action listener to open file chooser dialog
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(jfrCore);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    pathTextField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        //Canvas panel
        JPanel canvasBox = new JPanel();
        canvasBox.setBackground(canvasBoxColor);
        canvasBox.setBorder(border);

        Canvas canvas = new Canvas();
        canvas.setSize(300, 300);
        canvas.setBackground(Color.WHITE);
        canvasBox.add(canvas, BorderLayout.CENTER);

        //Add panels with BorderLayout constraints
        jfrCore.add(menuBox, BorderLayout.NORTH);
        jfrCore.add(canvasBox, BorderLayout.CENTER);

        jfrCore.pack();
        jfrCore.setVisible(true);
    }

    private static void setJFrameAttributes(JFrame frameMain) {
        frameMain.setLocation(200, 50);
        frameMain.setResizable(false);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setLayout(new BorderLayout());
    }
}