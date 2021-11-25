package application.controller;

import application.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

public class AppController {
	public Main main;
	
	private String[] bases = {"Binary", "Decimal", "Hexadecimal", "Octal"};
	
	
	@FXML
	private TextField input1;
	
	@FXML
	private TextField input2;
	
	@FXML
	private TilePane base1;
	
	@FXML
	private TilePane base2;
	
	@FXML
	private Label sumBinaryLabel;
	
	@FXML
	private Label prodBinaryLabel;
	
	@FXML
	private Label sumDecimalLabel;
	
	@FXML
	private Label prodDecimalLabel;
	
	@FXML
	private Label sumHexadecimalLabel;
	
	@FXML 
	private Label prodHexadecimalLabel;
	
	@FXML
	private Label sumOctalLabel;
	
	@FXML
	private Label prodOctalLabel;
	
	@FXML
	private Label errorLabel;
	
	// Stores the values for the drop down menu: Binary, Decimal, Hexadecimal, Octal 
	private ComboBox<String> forbase1;
	private ComboBox<String> forbase2;
	
	// Stores the users input
	private String firstInput;
	private String secondInput;
	
	
	// Stores what bases each inputs are
	private String firstBase;
	private String secondBase;
	
	// Stores the converted inputs
	private int decimal1;
	private int decimal2;
	
	// Stores the output
	private String sumBinary;
	private String sumDecimal;
	private String sumHexadecimal;
	private String sumOctal;
	private String prodBinary;
	private String prodDecimal;
	private String prodHexadecimal;
	private String prodOctal;
	
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
		
		evaluate();
	}
	@FXML //Function called by the button, Evaluate.
	private void evaluate() {
		firstInput = input1.getText();
		secondInput = input2.getText();
		
		firstBase = forbase1.getValue().toString();
		secondBase = forbase2.getValue().toString();
		
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
	
	//Decimal to Binary
	private String convertToBinary(int x) {
		String binary = "";
		
		do{
			if(x == 0) {
				binary = Integer.toString(0) + binary;
				break;
			}
			binary = Integer.toString(x%2) + binary;
			x = x/2;
		} while (x > 0);
		
		return binary;
	}
	
	// Decimal to Decimal
	private String convertToDecimal(int x) {
		return Integer.toString(x);
	}
	
	// Decimal to Hexadecimal
	private String convertToHexadecimal(int x) {
		String hex = "";
		
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
			
		} while (x > 0);
		
		return hex;
	}
	
	// Decimal to Octal
	private String convertToOctal(int x) {
		String octal= "";
		
		do{
			if(x == 0) {
				octal = Integer.toString(0) + octal;
				break;
			}
			octal = Integer.toString(x%8) + octal;
			x = x/8;
		} while (x > 0);
		
		return octal;
	}
	
	
	// Binary to Decimal
	private int convertFromBinary(String x) {
		int decimal = 0;
		int n = x.length();
		
		
		for(int i = n - 1; i >= 0; i--) {
			decimal += Integer.parseInt(Character.toString(x.charAt(i))) * Math.pow(2, n - 1 - i);
		}
		
		return decimal;
	}
	
	// Decimal to Decimal
	private int convertFromDecimal(String x) {
		return Integer.parseInt(x);
	}
	
	// Hexadecimal to Decimal
	private int convertFromHexadecimal(String x) {
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
	
	// Octal to Decimal
	private int convertFromOctal(String x) {
		int decimal = 0;
		int n = x.length();
		
		
		for(int i = n - 1; i >= 0; i--) {
			decimal += Integer.parseInt(Character.toString(x.charAt(i))) * Math.pow(8, n - 1 - i);
		}
		
		return decimal;
	}
	
}
