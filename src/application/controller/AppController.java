package application.controller;

/**
 *	This library is imported to utilize Regular Expressions in input checking.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import application.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

/**
 * This class facilitates the input from the user, the buttons, the algorithm to for evaluating the input, and the output.
 * This class acts as the brain of the application.
 * @author Nichol John F. Famadico
 * 
 */
public class AppController {
	
	/**
	 * This attribute acts as the controller's pointer to the main instance of the application.
	 */
	public Main main;
	
	/**
	 * An attribute to store the 4 bases needed for the drop down menu.
	 */
	private String[] bases = {"Binary", "Decimal", "Hexadecimal", "Octal"};
	
	/**
	 * This variable receives the first input.
	 */
	@FXML
	private TextField input1;
	
	/**
	 * This variable receives the second input.
	 */
	@FXML
	private TextField input2;
	
	/**
	 * This variable identifies the base of the first input as selected in the FXML GUI.
	 */
	@FXML
	private TilePane base1;
	
	/**
	 * This variable identifies the base of the second input as selected in the FXML GUI.
	 */
	@FXML
	private TilePane base2;
	
	/**
	 * This variable is utilized by the FXML GUI to show the sum in binary format.
	 */
	@FXML
	private Label sumBinaryLabel;
	
	/**
	 * This variable is utilized by the FXML GUI to show the product in binary format.
	 */
	@FXML
	private Label prodBinaryLabel;
	
	/**
	 * This variable is utilized by the FXML GUI to show the sum in decimal format.
	 */
	@FXML
	private Label sumDecimalLabel;
	
	/**
	 * This variable is utilized by the FXML GUI to show the product in decimal format.
	 */
	@FXML
	private Label prodDecimalLabel;
	
	/**
	 * This variable is utilized by the FXML GUI to show the sum in hexadecimal format.
	 */
	@FXML
	private Label sumHexadecimalLabel;
	
	/**
	 * This variable is utilized by the FXML GUI to show the product in hexadecimal format.
	 */
	@FXML 
	private Label prodHexadecimalLabel;
	
	/**
	 * This variable is utilized by the FXML GUI to show the sum in octal format.
	 */
	@FXML
	private Label sumOctalLabel;
	
	/**
	 * This variable is utilized by the FXML to show product in octal format.
	 */
	@FXML
	private Label prodOctalLabel;
	
	/**
	 * This variable is utilized by the FXML to alert errors in input.
	 */
	@FXML
	private Label errorLabel;
	
	/**
	 * Stores values in the GUI's drop down menu.
	 */
	private ComboBox<String> forbase1;
	/**
	 * Stores values in the GUI's drop down menu.
	 */
	private ComboBox<String> forbase2;
	
	/**
	 * Stores the first input as String.
	 */
	private String firstInput;
	/**
	 * Stores the second input as String
	 */
	private String secondInput;
	
	
	/**
	 * Stores what base the first input is.
	 */
	private String firstBase;
	/**
	 * Stores what base the second input is.
	 */
	private String secondBase;
	
	/**
	 * Stores the first input as decimal.
	 */
	private int decimal1;
	/**
	 * Stores the second input as decimal.
	 */
	private int decimal2;
	
	/**
	 * Stores the sum in binary Format.
	 */
	private String sumBinary;
	/**
	 * Stores the sum in decimal format.
	 */
	private String sumDecimal;
	/**
	 * Stores the sum in hexadecimal format.
	 */
	private String sumHexadecimal;
	/**
	 * Stores the sum in octal format.
	 */
	private String sumOctal;
	/**
	 * Stores the product in binary format.
	 */
	private String prodBinary;
	/**
	 * Stores the product in decimal format.
	 */
	private String prodDecimal;
	/**
	 * Stores the product in hexadecimal format.
	 */
	private String prodHexadecimal;
	/**
	 * Stores the product in octal format.
	 */
	private String prodOctal;
	
	/**
	 * This method is called by the Main class to integrate its instance with the controller.
	 * Furthermore, it also initializes the corresponding fields with their initial values.
	 * @param main Passes the reference to the instance of the Main class.
	 */
	public void setMain(Main main) {
		this.main = main;
		forbase1 = new ComboBox<String>(FXCollections.observableArrayList(bases));
		forbase2 = new ComboBox<String>(FXCollections.observableArrayList(bases));
		
		base1.getChildren().add(forbase1);
		base2.getChildren().add(forbase2);
		
		input1.setText("0");
		input2.setText("0");
		
		forbase1.setValue("Binary");
		forbase2.setValue("Binary");
		
		errorLabel.setText("");
		evaluate();
	}
	
	/**
	 * This method is called after clicking the "Evaluate" button in the GUI.
	 */
	@FXML 
	private void evaluate() {
		firstInput = input1.getText();
		secondInput = input2.getText();
		
		firstBase = forbase1.getValue().toString();
		secondBase = forbase2.getValue().toString();
		
		if(checkErrors()) {
			return;
		}
		
		System.out.println("First Input: " + firstInput + "\tBase: " + firstBase);
		System.out.println("Second Input: " + secondInput + "\tBase: " + secondBase);
		
		switch(firstBase) {
			case "Binary":
				decimal1 = convertFromBinary(firstInput);
				break;
			case "Decimal":
				decimal1 = convertFromDecimal(firstInput);
				break;
			case "Hexadecimal":
				decimal1 = convertFromHexadecimal(firstInput);
				break;
			case "Octal":
				decimal1 = convertFromOctal(firstInput);
				break;
			default:
				decimal1 = 0;
		}
		
		switch(secondBase) {
			case "Binary":
				decimal2 = convertFromBinary(secondInput);
				break;
			case "Decimal":
				decimal2 = convertFromDecimal(secondInput);
				break;
			case "Hexadecimal":
				decimal2 = convertFromHexadecimal(secondInput);
				break;
			case "Octal":
				decimal2 = convertFromOctal(secondInput);
				break;
			default:
				decimal2 = 0;
		}
		
		sumDecimal = convertToDecimal(decimal1 + decimal2);
		prodDecimal = convertToDecimal(decimal1 * decimal2);
		
		System.out.println("Sum in Decimal: " + sumDecimal);
		System.out.println("Product in Decimal: " + prodDecimal);
		System.out.println();
		
		sumBinary = convertToBinary(decimal1 + decimal2);
		prodBinary = convertToBinary(decimal1 * decimal2);
		System.out.println("Sum in Binary: " + sumBinary);
		System.out.println("Product in Binary: " + prodBinary);
		System.out.println();
		
		sumHexadecimal = convertToHexadecimal(decimal1 + decimal2);
		prodHexadecimal = convertToHexadecimal(decimal1 * decimal2);
		System.out.println("Sum in Hexadecimal: " + sumHexadecimal);
		System.out.println("Product in Hexadecimal: " + prodHexadecimal);
		System.out.println();
		
		sumOctal = convertToOctal(decimal1 + decimal2);
		prodOctal = convertToOctal(decimal1 * decimal2);
		System.out.println("Sum in Octal: " + sumOctal);
		System.out.println("Product in Octal: " + prodOctal);
		
		sumBinaryLabel.setText(sumBinary);
		prodBinaryLabel.setText(prodBinary);
		
		sumDecimalLabel.setText(sumDecimal);
		prodDecimalLabel.setText(prodDecimal);
		
		sumHexadecimalLabel.setText(sumHexadecimal);
		prodHexadecimalLabel.setText(prodHexadecimal);
		
		sumOctalLabel.setText(sumOctal);
		prodOctalLabel.setText(prodOctal);
		
		
	}
	
	/**
	 * This method checks if there are errors in the user input.
	 * @return True if there are errors detected, False if otherwise.
	 */
	private boolean checkErrors() {
		boolean hasErrors = false;
		Pattern pattern;
		Matcher matcher;
		boolean matchFound;
		
		
		if(firstBase.equals("Binary")){
			pattern = Pattern.compile("^[01 ]+$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(firstInput);
			matchFound = matcher.find();
			
			if(!matchFound) {
				hasErrors = true;
				errorLabel.setText("Error: Base 2 Invalid Input!");
				input1.setStyle("-fx-background-color: red");
				return hasErrors;
			}
		} 
		if (firstBase.equals("Decimal")){
			pattern = Pattern.compile("^[0-9 ]+$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(firstInput);
			matchFound = matcher.find();
			
			if(!matchFound) {
				hasErrors = true;
				errorLabel.setText("Error: Base 10 Invalid Input!");
				input1.setStyle("-fx-background-color: red");
				return hasErrors;
			}
		}
		if(firstBase.equals("Hexadecimal")) {
			pattern = Pattern.compile("^[A-Fa-f0-9 ]+$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(firstInput);
			matchFound = matcher.find();
			
			if(!matchFound) {
				hasErrors = true;
				errorLabel.setText("Error: Base 16 Invalid Input!");
				input1.setStyle("-fx-background-color: red");
				return hasErrors;
			}
		}
		if(firstBase.equals("Octal")) {
			pattern = Pattern.compile("^[0-7 ]+$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(firstInput);
			matchFound = matcher.find();
			
			if(!matchFound) {
				hasErrors = true;
				errorLabel.setText("Error: Base 8 Invalid Input!");
				input1.setStyle("-fx-background-color: red");
				return hasErrors;
			}
		}
		
		System.out.println("No Errors");
		hasErrors = false;
		errorLabel.setText("");
		input1.setStyle("-fx-background-color: white");
		
		
		if(secondBase.equals("Binary")){
			pattern = Pattern.compile("^[01 ]+$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(secondInput);
			matchFound = matcher.find();
			
			if(!matchFound) {
				hasErrors = true;
				errorLabel.setText("Error: Base 2 Invalid Input!");
				input2.setStyle("-fx-background-color: red");
				return hasErrors;
			}
		} 
		if (secondBase.equals("Decimal")){
			pattern = Pattern.compile("^[0-9 ]+$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(secondInput);
			matchFound = matcher.find();
			
			if(!matchFound) {
				hasErrors = true;
				errorLabel.setText("Error: Base 10 Invalid Input!");
				input2.setStyle("-fx-background-color: red");
				return hasErrors;
			}
		}
		if(secondBase.equals("Hexadecimal")) {
			pattern = Pattern.compile("^[A-Fa-f0-9 ]+$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(secondInput);
			matchFound = matcher.find();
			
			if(!matchFound) {
				hasErrors = true;
				errorLabel.setText("Error: Base 16 Invalid Input!");
				input2.setStyle("-fx-background-color: red");
				return hasErrors;
			}
		}
		if(secondBase.equals("Octal")) {
			pattern = Pattern.compile("^[0-7 ]+$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(secondInput);
			matchFound = matcher.find();
			
			if(!matchFound) {
				hasErrors = true;
				errorLabel.setText("Error: Base 8 Invalid Input!");
				input2.setStyle("-fx-background-color: red");
				return hasErrors;
			}
		}
		
		System.out.println("No Errors");
		hasErrors = false;
		errorLabel.setText("");
		input2.setStyle("-fx-background-color: white");
		
		return hasErrors;
	}
	
	/**
	 * Converts a decimal to binary.
	 * @param x A decimal integer.
	 * @return The binary format in String.
	 */
	private String convertToBinary(int x) {
		String binary = "";
		int multiple = 1;
		do{
			if(x == 0) {
				binary = Integer.toString(0) + binary;
				break;
			}
			binary = Integer.toString(x%2) + binary;
			x = x/2;
			if (multiple % 8 == 0) {
				binary = " " + binary;
			}
			multiple++;
		} while (x > 0);
		
		String temp = binary;
		
		temp = temp.replaceAll("\\s", "");
		
		int leading = 8 - (temp.length() % 8);
		String zeroes = "";
		
		if ((temp.length() % 8) != 0) {
			for (int i = 0; i < leading; i++) {
				zeroes += "0";
			}
			
		}
		
		binary = "" + zeroes + binary;
		
		return binary;
	}
	
	/**
	 * Converts a decimal to decimal.
	 * @param x A decimal integer.
	 * @return The decimal format in String.
	 */
	private String convertToDecimal(int x) {
		return Integer.toString(x);
	}
	
	/**
	 * Converts a decimal to hexadecimal.
	 * @param x A decimal integer.
	 * @return The hexadecimal format in String.
	 */
	private String convertToHexadecimal(int x) {
		String hex = "";
		int multiple = 1;
		do {
			if(x == 0) {
				hex = Integer.toString(0) + hex;
				break;
			}
			
			int r = x % 16;
			
			switch(r) {
				case 10:
					hex = "A" + hex;
					break;
				case 11:
					hex = "B" + hex;
					break;
				case 12:
					hex = "C" + hex;
					break;
				case 13:
					hex = "D" + hex;
					break;
				case 14:
					hex = "E" + hex;
					break;
				case 15:
					hex = "F" + hex;
					break;
				default:
					hex = Integer.toString(r) + hex;
			}
			x = x / 16;
			if (multiple % 4 == 0) {
				hex = " " + hex;
			}
			multiple++;
		} while (x > 0);
		
		String temp = hex;
		
		temp = temp.replaceAll("\\s", "");
		
		int leading = 4 - (temp.length() % 4);
		String zeroes = "";
		
		if ((temp.length() % 4) != 0) {
			for (int i = 0; i < leading; i++) {
				zeroes += "0";
			}
			
		}
		
		hex = "" + zeroes + hex;
		
		return hex;
	}
	
	/**
	 * Converts a decimal to octal.
	 * @param x A decimal integer.
	 * @return The octal format in String.
	 */
	private String convertToOctal(int x) {
		String octal= "";
		int multiple = 1;
		do{
			if(x == 0) {
				octal = Integer.toString(0) + octal;
				break;
			}
			octal = Integer.toString(x%8) + octal;
			x = x/8;
			
			if (multiple % 3 == 0) {
				octal = " " + octal;
			}
			multiple++;
			
		} while (x > 0);
		
		String temp = octal;
		
		temp = temp.replaceAll("\\s", "");
		
		int leading = 3 - (temp.length() % 3);
		String zeroes = "";
		
		if ((temp.length() % 3) != 0) {
			for (int i = 0; i < leading; i++) {
				zeroes += "0";
			}
			
		}
		
		octal = "" + zeroes + octal;
		
		return octal;
	}
	
	
	/**
	 * Converts a binary to decimal.
	 * @param x A binary String.
	 * @return A decimal format as Integer.
	 */
	private int convertFromBinary(String x) {
		x = x.replace(" ", "");
		int decimal = 0;
		int n = x.length();
		
		for(int i = n - 1; i >= 0; i--) {
			decimal += Integer.parseInt(Character.toString(x.charAt(i))) * Math.pow(2, n - 1 - i);
		}
		return decimal;
	}
	
	/**
	 * Converts a decimal to decimal.
	 * @param x	A decimal String.
	 * @return A decimal format as Integer.
	 */
	private int convertFromDecimal(String x) {
		x = x.replace(" ", "");
		return Integer.parseInt(x);
	}
	
	/**
	 * Converts a hexadecimal to decimal.
	 * @param x A hexadecimal String.
	 * @return A decimal format as Integer.
	 */
	private int convertFromHexadecimal(String x) {
		x = x.replace(" ", "");
		int decimal = 0;
		int n = x.length();
		
		
		for(int i = n - 1; i >= 0; i--) {
			char y = x.charAt(i);
			
			switch (y) {
			case 'A':
			case 'a':
				decimal += 10 * Math.pow(16,  n - 1 - i);
				break;
			case 'B':
			case 'b':
				decimal += 11 * Math.pow(16,  n - 1 - i);
				break;
			case 'C':
			case 'c':
				decimal += 12 * Math.pow(16,  n - 1 - i);
				break;
			case 'D':
			case 'd':
				decimal += 13 * Math.pow(16,  n - 1 - i);
				break;
			case 'E':
			case 'e':
				decimal += 14 * Math.pow(16,  n - 1 - i);
				break;
			case 'F':
			case 'f':
				decimal += 15 * Math.pow(16,  n - 1 - i);
				break;
			default:
				decimal += Integer.parseInt(Character.toString(x.charAt(i))) * Math.pow(16, n - 1 - i);
			}
		}
		
		return decimal;
	}
	
	/**
	 * Converts an octal to decimal.
	 * @param x An octal String.
	 * @return A decimal format as Integer.
	 */
	private int convertFromOctal(String x) {
		x = x.replace(" ", "");
		int decimal = 0;
		int n = x.length();
		
		
		for(int i = n - 1; i >= 0; i--) {
			decimal += Integer.parseInt(Character.toString(x.charAt(i))) * Math.pow(8, n - 1 - i);
		}
		
		return decimal;
	}
	
}
