import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VirtualKeyboardApp extends JFrame implements ActionListener {
    private JTextArea textArea;
    private List<String> pangramList;
    private String currentPangram;
    private int correctKeyPresses;
    private int incorrectKeyPresses;
    private JLabel pangramLabel;
    private JLabel statsLabel;
    private char[] difficultKeys;

    public VirtualKeyboardApp() {
        setTitle("Virtual Keyboard App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel keyboardPanel = createKeyboardPanel();
        textArea = new JTextArea(10, 40);
        pangramLabel = new JLabel();
        statsLabel = new JLabel();
        difficultKeys = new char[26];

        loadPangramsFromFile(); // Load pangrams from a file into pangramList
        updatePangramLabel();

        add(keyboardPanel, BorderLayout.CENTER);
        add(textArea, BorderLayout.SOUTH);
        add(pangramLabel, BorderLayout.NORTH);
        add(statsLabel, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private JPanel createKeyboardPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 10));

        String[] keys = {
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L",
            "Z", "X", "C", "V", "B", "N", "M"
        };

        for (String key : keys) {
            JButton button = new JButton(key);
            button.addActionListener(this);
            panel.add(button);
        }

        return panel;
    }

    private void loadPangramsFromFile() {
        pangramList = new ArrayList<>();
        // Add pangrams from a file to pangramList
        pangramList.add("El veloz murciélago hindú comía feliz cardillo y kiwi.");
        pangramList.add("La cigüeña tocaba el saxofón detrás del palenque de paja.");
        // Add more pangrams...
    }

    private void updatePangramLabel() {
        if (!pangramList.isEmpty()) {
            int randomIndex = new Random().nextInt(pangramList.size());
            currentPangram = pangramList.get(randomIndex);
            pangramLabel.setText("Pangram: " + currentPangram);
        } else {
            pangramLabel.setText("No more pangrams available.");
        }
    }

    private void updateStatsLabel() {
        StringBuilder stats = new StringBuilder();
        stats.append("Correct Key Presses: ").append(correctKeyPresses);
        stats.append(" | Incorrect Key Presses: ").append(incorrectKeyPresses);
        stats.append(" | Difficult Keys: ").append(difficultKeysToString());
        statsLabel.setText(stats.toString());
    }

    private String difficultKeysToString() {
        StringBuilder result = new StringBuilder();
        for (char key : difficultKeys) {
            if (key != '\0') {
                result.append(key).append(" ");
            }
        }
        return result.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String key = button.getText();
        textArea.append(key);

        if (currentPangram != null && !currentPangram.isEmpty()) {
            if (currentPangram.startsWith(textArea.getText())) {
                correctKeyPresses++;
            } else {
                incorrectKeyPresses++;
                updateDifficultKeys(key.charAt(0));
            }
        }

        updateStatsLabel();
    }

    private void updateDifficultKeys(char key) {
        key = Character.toUpperCase(key);
        if (key >= 'A' && key <= 'Z') {
            if (difficultKeys[key - 'A'] == '\0') {
                difficultKeys[key - 'A'] = key;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VirtualKeyboardApp());
    }
}
