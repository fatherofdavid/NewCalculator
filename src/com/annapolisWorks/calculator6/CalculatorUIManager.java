package com.annapolisWorks.calculator6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CalculatorUIManager extends JDialog {
    private JTextArea inputTextField;
    private JPanel mainPanel;
    private JLabel outputLabel;

    private JButton m1Button;
    private JButton m2Button;
    private JButton m3Button;
    private JButton m4Button;
    private JButton m5Button;
    private JButton m6Button;
    private JButton m7Button;
    private JButton m8Button;
    private JButton m9Button;
    private JButton m0Button;

    private JButton mButtonAdd;
    private JButton mButtonSubtract;
    private JButton mButtonMultiply;
    private JButton mButtonDivide;
    private JButton mButtonExponent;
    private JButton mButtonLParen;
    private JButton mButtonRParen;

    private JButton mButtonBack;
    private JButton mButtonSolve;

    public CalculatorUIManager() {

        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        m1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                on1();
            }
        });
        m2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                on2();
            }
        });
        m3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                on3();
            }
        });
        m4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                on4();
            }
        });
        m5Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                on5();
            }
        });
        m6Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                on6();
            }
        });
        m7Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                on7();
            }
        });
        m8Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                on8();
            }
        });
        m9Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                on9();
            }
        });
        m0Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                on0();
            }
        });
        mButtonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });
        mButtonSubtract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSubtract();
            }
        });
        mButtonMultiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onMultiply();
            }
        });
        mButtonDivide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onDivide();
            }
        });
        mButtonExponent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExponent();
            }
        });
        mButtonLParen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onLParen();
            }
        });
        mButtonRParen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onRParen();
            }
        });
        mButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBack();
            }
        });
        mButtonSolve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSolve();
            }
        });
    }

    private void on1() {
        inputTextField.append("1");
    }
    private void on2() {
        inputTextField.append("2");
    }
    private void on3() {
        inputTextField.append("3");
    }
    private void on4() {
        inputTextField.append("4");
    }
    private void on5() {
        inputTextField.append("5");
    }
    private void on6() {
        inputTextField.append("6");
    }
    private void on7() {
        inputTextField.append("7");
    }
    private void on8() {
        inputTextField.append("8");
    }
    private void on9() {
        inputTextField.append("9");
    }
    private void on0() {
        inputTextField.append("0");
    }

    private void onAdd() {
        inputTextField.append("+");
    }
    private void onSubtract() {
        inputTextField.append("-");
    }
    private void onMultiply() {
        inputTextField.append("*");
    }
    private void onDivide() {
        inputTextField.append("/");
    }
    private void onExponent() {
        inputTextField.append("^");
    }
    private void onLParen() {
        inputTextField.append("(");
    }
    private void onRParen() {
        inputTextField.append(")");
    }
    private void onBack() {
        String str = inputTextField.getText();
        if(str.length() > 0) str = str.substring(0, str.length() - 1);
        inputTextField.setText(str);
    }
    private void onSolve() {
        Calculator6Main.run(this);
    }

    String askUser(){
        return inputTextField.getText();
    }

    void publishToUser(boolean isError, String str){
        if(!isError){
            outputLabel.setText(inputTextField.getText() + " = " + str);
            inputTextField.setText("");
        }
        else{
            outputLabel.setText(str);
        }
    }

}

