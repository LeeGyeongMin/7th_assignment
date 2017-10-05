import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class MyCalc extends JFrame implements ActionListener {
	JPanel myButton, myResult;
	JLabel myL1, myL2;
	JButton[] jbutton = null;
	String res = "";
	String res1 = "";
	String res2 = "";
	String number[] = { " ", " ", " " };
	String[] myStr = { "←", "CE", "C", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "0", "00", ".",
			"=" };

	public MyCalc() {
		super("My Calculator");
		myResult = new JPanel(new GridLayout(2, 1));
		myResult.setBackground(Color.WHITE);
		myL1 = new JLabel("", JLabel.RIGHT);
		myL2 = new JLabel("0", JLabel.RIGHT);

		myResult.add(myL1);
		myResult.add(myL2);

		myButton = new JPanel(new GridLayout(5, 4, 2, 2));
		myButton.setBackground(Color.WHITE);
		jbutton = new JButton[myStr.length];

		for (int i = 0; i < myStr.length; i++) {
			jbutton[i] = new JButton(myStr[i]);
			myButton.add(jbutton[i]);
			jbutton[i].addActionListener(this);
		}

		getContentPane().add("North", myResult);
		getContentPane().add("Center", myButton);
		setSize(300, 400);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String textButton = e.getActionCommand();

		if (textButton.equals("1") || textButton.equals("2") || textButton.equals("3") || textButton.equals("4")
				|| textButton.equals("5") || textButton.equals("6") || textButton.equals("7") || textButton.equals("8")
				|| textButton.equals("9")) {
			if (res2.equals("0")) {
				res2 = "";
			}
			res2 += textButton;
			myL2.setText(res2);
		} 
		else if (textButton.equals("←")) {
			int length = myL2.getText().length();
			if (length == 1) {
				res2 = "";
				myL2.setText("0");
			} 
			else {
				if (!"".equals(res2)) {
					res2 = res2.substring(0, length - 1);
					myL2.setText(res2);
				}
			}
		} 
		else if (textButton.equals("CE")) {
			res2 = "";
			res = "";
			myL2.setText("0");
		} 
		else if (textButton.equals("C")) {
			res = "";
			res1 = "";
			res2 = "";
			myL1.setText("");
			myL2.setText("0");
			number[0] = " ";
			number[1] = " ";
			number[2] = " ";
		} 
		else if (textButton.equals("0") || textButton.equals("00")) {
			if (!("".equals(res2))) {
				if (!"0".equals(res2)) {
					res2 += textButton;
					myL2.setText(res2);
				}
			} 
			else {
				res2 = "0";
			}
		}
		else if (textButton.equals(".")) {
			if ("".equals(res2)) {
				res2 = "0" + textButton;
			} 
			else {
				if (res2.indexOf(".") == -1) {
					res2 += textButton;
				}
			}
			myL2.setText(res2);
		} 
		else if (textButton.equals("=")) {
			if (!"".equals(res2)) {
				number[2] = res2;
			}
			if ("".equals(res1)) {
				if (!number[1].equals(" ")) {
					res = Calculate();
					if ("0으로 나눌 수 없습니다.".equals(res)) {
						res = "";
						res1 = "";
						res2 = "";
						number[0] = " ";
						number[1] = " ";
						number[2] = " ";
					} 
					else {
						number[0] = res;
						myL2.setText(res);
					}
				}
				myL1.setText(res1);
			} 
			else {
				if (" ".equals(number[2])) {
					number[2] = res2;
				}
				res = number[0];

				if ("0으로 나눌 수 없습니다.".equals(res)) {
					myL2.setText(res);
					res = "";
					res1 = "";
					res2 = "";
					number[0] = " ";
					number[1] = " ";
					number[2] = " ";
				} 
				else {
					res = Calculate();

					if (!"0으로 나눌 수 없습니다.".equals(res)) {
						res1 = "";
						number[0] = res;
						myL1.setText(res1);
						res2 = "";
					} 
					else {
						myL2.setText("0으로 나눌 수 없습니다.");
						myL1.setText("");
						res = "";
						res1 = "";
						res2 = "";
						number[0] = " ";
						number[1] = " ";
						number[2] = " ";
					}
					System.out.println("?");
				}
			}
		} 
		else if (textButton.equals("/") || textButton.equals("*") || textButton.equals("-") || textButton.equals("+")) {
			if ("".equals(res1)) {
				if ("".equals(res2)) {
					if ("".equals(res)) {
						res1 = "0" + textButton;
						number[0] = "0";
					} 
					else {
						res1 = res + textButton;
					}
				} 
				else {	
					if (!"".equals(res)) {
						res1 = res + textButton;
					}
					res1 = res2 + textButton;
					number[0] = res2;
				}
				number[1] = textButton;
			} 
			else {
				if ("".equals(res2)) {
					res1 = res1.substring(0, res1.length() - 1) + textButton;
					number[1] = textButton;
				} 
				else {
					res1 += res2 + textButton;
					number[2] = res2;
					res = Calculate();
					number[1] = textButton;
					myL2.setText(res);
					number[0] = res;
				}
			}
			if (number[1].equals("/") && (number[2].equals(" 0") || number[2].equals("0"))) {

			} 
			else {
				res2 = "";
				myL1.setText(res1);
			}

		}

	}

	public String Calculate() {
		String num = "";
		String Num1 = "";
		String Num2 = "";

		if (number[0].indexOf(".") != -1) {
			int index1 = number[0].indexOf(".");

			Num1 = number[0].substring(index1, number[0].length());

			if (Num1.replaceAll("0", "").equals(".")) {
				number[0] = number[0].substring(0, index1);
			}
		}

		if (number[2].indexOf(".") != -1) {
			int index2 = number[2].indexOf(".");

			Num2 = number[2].substring(index2, number[2].length());

			if (Num2.replaceAll("0", "").equals(".")) {
				number[2] = number[2].substring(0, index2);
			}
		}
		if (number[1].equals("*")) {
			if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
				num = (Double.parseDouble(number[0]) * Double.parseDouble(number[2])) + "";
			} 
			else {
				if ("".equals(number[2])) {
					num = res2;
				} 
				else {
					if (!"".equals(res)) {
						number[0] = res;
					}
					num = (Long.parseLong(number[0]) * Long.parseLong(number[2])) + "";
					res = num;
					number[0] = res;
				}
			}
		} 
		else if (number[1].equals("+")) {
			if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
				num = (Double.parseDouble(number[0]) + Double.parseDouble(number[2])) + "";} 
			else {
				if ("".equals(number[2])) {
					num = res2;} else {
					if (!"".equals(res)) {
						number[0] = res;
					}
					num = (Long.parseLong(number[0]) + Long.parseLong(number[2])) + "";
					res = num;
					number[0] = res;}
				}
			} 
		else if (number[1].equals("-")) {
			if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
				num = (Double.parseDouble(number[0]) - Double.parseDouble(number[2])) + "";
			} 
			else {
				if ("".equals(number[2])) {
					num = res2;
				} 
				else {
					if (!"".equals(res)) {
						number[0] = res;
					}
					num = (Long.parseLong(number[0]) - Long.parseLong(number[2])) + "";
					res = num;
					number[0] = res;
				}
			}
		} 
		else if (number[1].equals("/")) {
			number[2].trim();
			if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
				num = (Double.parseDouble(number[0]) / Double.parseDouble(number[2])) + "";
			} 
			else {
				if (number[2].equals("0")) {
					num = "0으로 나눌 수 없습니다.";
					res2 = "";
				} 
				else {
					if ("".equals(number[2])) {
						num = res2;
					} 
					else {
						if (!"".equals(res)) {
							number[0] = res;
						}
						num = (Double.parseDouble(number[0]) / Double.parseDouble(number[2])) + "";

						if (num.indexOf(".") != -1) {
							int index3 = num.indexOf(".");
							String testNum3 = num.substring(index3, num.length());
							if (testNum3.replaceAll("0", "").equals(".")) {
								num = num.substring(0, index3);
							}
						}
						res = num;
						number[0] = res;
					}
				}
			}
		}
		return num;

	}

	public static void main(String[] args) {
		new MyCalc();
	}
}
