import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizContestManagement extends JFrame {
    private final Color NAVY = new Color(25, 39, 67);
    private final Color BLUE = new Color(59, 130, 246);
    private final Color BG = new Color(245, 247, 251);
    private final List<Question> questions = new ArrayList<>();

    private String studentName;
    private int currentQuestion = 0;
    private int score = 0;
    private int[] selectedAnswers;
    private JLabel progressLabel;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JPanel mainPanel;
    private JButton nextButton;

    public QuizContestManagement() {
        setTitle("Quiz Contest Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(760, 520);
        setMinimumSize(new Dimension(650, 460));
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG);
        loadQuestions();
        showWelcomeScreen();
    }

    private void loadQuestions() {
        questions.add(new Question("Which keyword is used to inherit a class in Java?",
                new String[]{"implements", "extends", "inherits", "super"}, 1));
        questions.add(new Question("Which method is the entry point of a standard Java application?",
                new String[]{"start()", "run()", "main()", "init()"}, 2));
        questions.add(new Question("Which data type stores a whole number?",
                new String[]{"int", "boolean", "char", "String"}, 0));
        questions.add(new Question("Which symbol ends most Java statements?",
                new String[]{":", ".", ";", ","}, 2));
        questions.add(new Question("Which keyword creates an object?",
                new String[]{"class", "new", "void", "this"}, 1));
        questions.add(new Question("Which collection does not allow duplicate elements?",
                new String[]{"ArrayList", "LinkedList", "HashSet", "Vector"}, 2));
        questions.add(new Question("Which block is used to handle an exception?",
                new String[]{"try-catch", "if-else", "for-loop", "switch-case"}, 0));
        questions.add(new Question("Which access modifier makes a member accessible from anywhere?",
                new String[]{"private", "protected", "default", "public"}, 3));
        questions.add(new Question("Which Swing component is used for a clickable button?",
                new String[]{"JLabel", "JButton", "JFrame", "JPanel"}, 1));
        questions.add(new Question("Which operator checks equality between primitive values?",
                new String[]{"=", "==", "!=", "&&"}, 1));
    }

    private void showWelcomeScreen() {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BG);
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 238)),
                new EmptyBorder(28, 35, 28, 35)));

        JLabel title = heading("QUIZ CONTEST", 30, NAVY);
        JLabel subtitle = heading("MANAGEMENT SYSTEM", 18, BLUE);
        JLabel description = new JLabel("<html><div style='text-align:center'>Test your Java knowledge with 10 multiple-choice questions.</div></html>");
        description.setFont(new Font("SansSerif", Font.PLAIN, 14));
        description.setForeground(new Color(90, 100, 120));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Enter your name");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(320, 40));
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(205, 213, 225)),
                new EmptyBorder(8, 10, 8, 10)));

        JButton startButton = button("Start Quiz");
        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name to start.",
                        "Name Required", JOptionPane.WARNING_MESSAGE);
                nameField.requestFocus();
                return;