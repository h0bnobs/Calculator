import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textfield; //holds all the numbers that the user types in as well as the result.
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Arial", Font.BOLD, 28);
    double num1 = 0;
    double num2 = 0;
    double result = 0;
    char operator;

    Calculator() {
        frame = new JFrame("Calculator");//create a new JFrame called frame with the title 'calculator'
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set the close operation to the red 'x'
        frame.setSize(420, 550);//set the size and width
        frame.setLayout(null);

        textfield = new JTextField(); //creates a new text field, which allows a user to enter a single line text and edit it. This is the bar at the top
        textfield.setBounds(50, 25, 300, 50); //set the bounds of the new text field.
        textfield.setFont(myFont);
        textfield.setEditable(false);

        //create all the buttons related to functions
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clear");
        negButton = new JButton("(-)");

        //add those buttons to the array of JButtons.
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        //for loop that adds and action listener, sets the font, and sets the focus of each function button.
        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        //creates new JButton for the number buttons, gives them an action listener, sets the font and sets the focus of each one.
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        //set the bounds separately because they won't be on the JPanel that has the grid layout
        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 105, 50);

        //creates new JPanel that has the grid of buttons
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10)); //number of rows, columns, then the gap between the buttons.
        //panel.setBackground(Color.GRAY);

        //add the function buttons and the number buttons to the panel. (first row of calculator):
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        //second row of calculator:
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        //third row:
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        //fourth row:
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        //adds the del, clear and negative buttons
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(negButton);

        //adds the panel and text field that were created.
        frame.add(panel);
        frame.add(textfield);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //adds functionality to the buttons. checks to see if user clicks on one of the numberButtons.
        for (int i = 0; i < 10; i++) {
            //if the user clicks on a button that is the same os one of the numberButtons
            if (e.getSource() == numberButtons[i]) {
                //updates the text field.
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }

        //if the user clicks on the decimal point button, show that in the text field.
        if (e.getSource() == decButton) {
            textfield.setText(textfield.getText().concat("."));
        }

        //if the user clicks on the add button, num1 is retrieved and assigned to the number in the text field. Assigns the operator to the correct char. Then clears the text field.
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        }

        //when equals is pressed num2 is assigned to the second number in the text field.
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textfield.getText());

            //switch statement that depends on what operator the user chooses, determines what mathematical operation is performed.
            switch (operator) {
                //if the user wants to add, subtract, multiply, or divide the numbers, result is assigned to the result of the two numbers.
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> result = num1 / num2;
            }

            //after the switch update the text field to the result.
            textfield.setText(String.valueOf(result));
            //assign num1 to the result in case the user wants to re-use the same number.
            num1 = result;
        }

        //clears the text field.
        if (e.getSource() == clrButton) {
            textfield.setText("");
        }

        //when delete button is pressed, the text in the text field is stored in a string, and then the text field is emptied.
        //set the text in the text field to nothing at each char of the string.
        if (e.getSource() == delButton) {
            String s = textfield.getText();
            textfield.setText("");
            for (int i = 0; i < s.length() - 1; i++) {
                textfield.setText(textfield.getText() + s.charAt(i));
            }
        }

        //if the negative button is pressed, it takes whatever value is in the text field and makes it negative.
        if (e.getSource() == negButton) {
            //takes whatever value is in the text field and assign it to temp.
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1;
            textfield.setText(String.valueOf(temp));
        }
    }
}

//            this was the FIRST switch statement I made but intellij improved it. (I love intellij)
//            switch (operator) {
//                //first case, if the user wants to add the numbers.
//                case '+':
//                    result = num1 + num2;
//                    break;
//                case '-':
//                    result = num1 - num2;
//                    break;
//                case '*':
//                    result = num1 * num2;
//                    break;
//                case '/':
//                    result = num1 / num2;
//                    break;
//            }