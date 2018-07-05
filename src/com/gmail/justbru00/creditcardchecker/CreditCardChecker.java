package com.gmail.justbru00.creditcardchecker;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreditCardChecker {

	private JFrame frmCreditCardNumber;
	private JTextField textBox;
	private JButton checkButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditCardChecker window = new CreditCardChecker();
					window.frmCreditCardNumber.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreditCardChecker() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreditCardNumber = new JFrame();
		frmCreditCardNumber.setTitle("Credit Card Number Checker");
		frmCreditCardNumber.setBounds(100, 300, 700, 500);
		frmCreditCardNumber.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPanel = new JPanel();
		frmCreditCardNumber.getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		JPanel labelPanel = new JPanel();
		contentPanel.add(labelPanel);
		
		JLabel lblCreditCardNumber = new JLabel("Credit Card Number:");
		labelPanel.add(lblCreditCardNumber);
		
		JPanel textInputPanel = new JPanel();
		contentPanel.add(textInputPanel);
		
		textBox = new JTextField();
		textBox.setToolTipText("Enter a credit card number to check. ");
		textInputPanel.add(textBox);
		textBox.setColumns(25);
		
		JPanel checkButtonPanel = new JPanel();
		contentPanel.add(checkButtonPanel);
		
		checkButton = new JButton("Check");
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean valid = false;
				
				valid = checkLuhn(textBox.getText());
				
				if (valid) {
					JOptionPane.showMessageDialog(frmCreditCardNumber, "The number " + textBox.getText() + " is theoretically valid.\nIt passed the Luhn algorithm.", "Number Valid!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frmCreditCardNumber, "The number " + textBox.getText() + " is NOT valid.\nIt FAILED the Luhn algorithm.", "Number Invalid!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		checkButton.setToolTipText("Checks the number with the Luhn algorithm.");
		checkButtonPanel.add(checkButton);
	}
	
	/**
	 * Checks the provided number with the Luhn algorithm
	 * @param ccNumber
	 * @return True if the number is theoretically valid. False if the number is not valid.
	 */
	public boolean checkLuhn(String ccNumber)  {
		try {
            int sum = 0;
            boolean alternate = false;
            for (int i = ccNumber.length() - 1; i >= 0; i--) {
                    int n = Integer.parseInt(ccNumber.substring(i, i + 1));
                    if (alternate) {
                            n *= 2;
                            if (n > 9) {
                            	n = (n % 10) + 1;
                            }
                    }
                    sum += n;
                    alternate = !alternate;
            }
            return (sum % 10 == 0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmCreditCardNumber, "The text '" + textBox.getText() + "' is invalid. You can only provide numbers to check.", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
    }

}
