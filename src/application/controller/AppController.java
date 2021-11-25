package application.controller;

import application.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
		
		System.out.println(decimal1);
		System.out.println(decimal2);
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
