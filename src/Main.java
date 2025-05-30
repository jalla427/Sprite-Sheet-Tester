import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;

public class Main extends Canvas {
    public static spriteRun activeSprite;
    static Canvas canvas;
    Graphics2D g;
    static int canvasSize = 300;

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
        JTextField pathTextField = new JTextField("Enter the path to your spritesheet here", 57);
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

        JPanel targetRowPanel = new JPanel(new GridLayout(0, 1));
        JLabel targetRowLabel = new JLabel("Target row");
        JTextField targetRowTextField = new JTextField("60",10);
        targetRowPanel.add(targetRowLabel);
        targetRowPanel.add(targetRowTextField);

        JPanel setVarsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setVarsPanel.add(heightPanel);
        setVarsPanel.add(widthPanel);
        setVarsPanel.add(rowsPanel);
        setVarsPanel.add(columnsPanel);
        setVarsPanel.add(fpsPanel);
        setVarsPanel.add(targetRowPanel);
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

        //Canvas panel
        JPanel canvasBox = new JPanel();
        canvasBox.setBackground(canvasBoxColor);
        canvasBox.setBorder(border);

        canvas = new Canvas();
        canvas.setSize(canvasSize, canvasSize);
        canvas.setBackground(Color.WHITE);
        canvasBox.add(canvas, BorderLayout.CENTER);

        //Add panels with BorderLayout constraints
        jfrCore.add(menuBox, BorderLayout.NORTH);
        jfrCore.add(canvasBox, BorderLayout.CENTER);

        jfrCore.pack();
        jfrCore.setVisible(true);

        //Add action listener to browse button
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

        //Add action listener to run button
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeSprite = new spriteRun(Integer.parseInt(heightTextField.getText()), Integer.parseInt(widthTextField.getText()),
                                            Integer.parseInt(rowsTextField.getText()), Integer.parseInt(columnsTextField.getText()),
                                            Integer.parseInt(fpsTextField.getText()), Integer.parseInt(targetRowTextField.getText()),
                                            pathTextField.getText());
                activeSprite.loadImage();

                int rowCounter = 1;
                while(0 == 0) {
                    while(rowCounter < activeSprite.getRows()) {
                        for(int i = 1; i <= activeSprite.getColumns(); i++) {
                            //Define canvas graphics
                            BufferStrategy bs = canvas.getBufferStrategy();
                            if(bs == null) {
                                canvas.createBufferStrategy(3);
                                return;
                            }
                            Graphics g = bs.getDrawGraphics();

                            g.drawImage(activeSprite.grabImage(rowCounter, i, activeSprite.getWidth(), activeSprite.getHeight()),
                                    (canvasSize + activeSprite.getHeight())/2, (canvasSize + activeSprite.getWidth())/2, null);
                            g.dispose();
                            bs.show();
                        }
                        rowCounter++;
                    }
                }
            }
        });
    }

    private static void setJFrameAttributes(JFrame frameMain) {
        frameMain.setLocation(200, 50);
        frameMain.setResizable(false);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setLayout(new BorderLayout());
    }
}