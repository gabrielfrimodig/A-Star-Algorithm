import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private PathFinder board;
    private JSlider dimensionSlider;

    public Main(){
        createGui();
    }

    private void createGui(){
        JFrame frame = new JFrame("A* Path Finder");
        board = new PathFinder();
        JPanel bottomPane = new JPanel();

        JButton sortButton = new JButton("Start");
        JButton resetButton = new JButton("Reset");
        JButton quitButton = new JButton("Quit");

        dimensionSlider = new JSlider();

        dimensionSlider.setMinorTickSpacing(40);
        dimensionSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                board.updateBoard(dimensionSlider.getValue());
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                board.solvePath();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                board.resetBoard();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        bottomPane.add(sortButton);
        bottomPane.add(resetButton);
        bottomPane.add(dimensionSlider);
        bottomPane.add(quitButton);

        bottomPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Settings"));

        frame.setLayout(new BorderLayout());
        frame.add(board, BorderLayout.CENTER);
        frame.add(bottomPane, BorderLayout.SOUTH);
        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        // Run the GUI in the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
