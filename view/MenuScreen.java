package view;

import java.util.Random;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.MenuListener;
import model.Aim;

public class MenuScreen {
	
	private JFrame window;
	Aim aimModel;
	public static Dimension MAXSCREEN_DIMENSION;

	private JLabel backGroundColorText = new JLabel("Background Color:");
	private JLabel shapeColorText = new JLabel("Shape Color:");
	private JLabel targetShapeText = new JLabel("Target Shape:");
	private JLabel targetSizeText = new JLabel("Target Size:");
	private JLabel intervalSpeedText = new JLabel("Target Speed:");

	private JRadioButton[] backgroundColorButtons = new JRadioButton[3];
	private JRadioButton[] shapeColorButtons = new JRadioButton[3];
	private JRadioButton[] targetShapeButtons = new JRadioButton[2];
	private JRadioButton[] targetSizeButtons = new JRadioButton[3];
	private JRadioButton[] intervalSpeedButtons = new JRadioButton[3];
	private JButton startButton = new JButton("Start!");

	public MenuScreen(JFrame window) {
		this.window = window;
	}

	public void init() {
		Container cp = window.getContentPane();

		// header text for menu
		JPanel northPanel = new JPanel();
		northPanel.add(new JLabel("<html>Welcome to Noah's Aim Trainer!<br />Please select from the following options:</html>"));
		cp.add(BorderLayout.NORTH, northPanel);

		initRadioButtons();

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(5, 2));
		// panels for radio buttons
		JPanel backGroundColorButtonsPanel = new JPanel();
		addRadioButtonGroup(backGroundColorButtonsPanel, backgroundColorButtons);
		JPanel shapeColorButtonsPanel = new JPanel();
		addRadioButtonGroup(shapeColorButtonsPanel, shapeColorButtons);
		JPanel targetShapeButtonsPanel = new JPanel();
		addRadioButtonGroup(targetShapeButtonsPanel, targetShapeButtons);
		JPanel targetSizeButtonsPanel = new JPanel();
		addRadioButtonGroup(targetSizeButtonsPanel, targetSizeButtons);
		JPanel intervalSpeedButtonsPanel = new JPanel();
		addRadioButtonGroup(intervalSpeedButtonsPanel, intervalSpeedButtons);
		// adding labels and radio buttons to 5x2 centerPanel & then centerPanel to content pane
		centerPanel.add(backGroundColorText);
		centerPanel.add(backGroundColorButtonsPanel);
		centerPanel.add(shapeColorText);
		centerPanel.add(shapeColorButtonsPanel);
		centerPanel.add(targetShapeText);
		centerPanel.add(targetShapeButtonsPanel);
		centerPanel.add(targetSizeText);
		centerPanel.add(targetSizeButtonsPanel);
		centerPanel.add(intervalSpeedText);
		centerPanel.add(intervalSpeedButtonsPanel);
		cp.add(BorderLayout.CENTER, centerPanel);

		JPanel southPanel = new JPanel();
		southPanel.add(startButton);
		cp.add(BorderLayout.SOUTH, southPanel);

		aimModel = new Aim();
		aimModel.init(this);

		// action event listener
		MenuListener menuListener = new MenuListener(this);
		addRadioButtonToListener(backgroundColorButtons, menuListener);
		addRadioButtonToListener(shapeColorButtons, menuListener);
		addRadioButtonToListener(targetShapeButtons, menuListener);
		addRadioButtonToListener(targetSizeButtons, menuListener);
		addRadioButtonToListener(intervalSpeedButtons, menuListener);

		startButton.addActionListener(e -> {
			window.getContentPane().removeAll();
			var gamePanel = new GamePanel(window, aimModel);
			gamePanel.init();
			window.pack();
			window.revalidate();
		});

		/* Supposed to get max screen width/height but... doesnt... -_-
		window.pack();
		MAXSCREEN_DIMENSION = window.getSize();
		System.out.println("Width: " + MAXSCREEN_DIMENSION.getWidth() + " | Height: " + MAXSCREEN_DIMENSION.getHeight());
		*/
	}

	// initializes all the radio buttons along with their tag.
	void initRadioButtons() {
		backgroundColorButtons[0] = new JRadioButton("White");
		backgroundColorButtons[1] = new JRadioButton("Gray");
		backgroundColorButtons[2] = new JRadioButton("Black");
		initButtonGroup(new ButtonGroup(), backgroundColorButtons);

		shapeColorButtons[0] = new JRadioButton("Red");
		shapeColorButtons[1] = new JRadioButton("Green");
		shapeColorButtons[2] = new JRadioButton("Blue");
		initButtonGroup(new ButtonGroup(), shapeColorButtons);

		targetShapeButtons[0] = new JRadioButton("Square");
		targetShapeButtons[1] = new JRadioButton("Circle");
		//targetShapeButtons[2] = new JRadioButton("Triangle");
		initButtonGroupShapes(new ButtonGroup(), targetShapeButtons);

		targetSizeButtons[0] = new JRadioButton("Small");
		targetSizeButtons[1] = new JRadioButton("Medium");
		targetSizeButtons[2] = new JRadioButton("Large");
		initButtonGroup(new ButtonGroup(), targetSizeButtons);

		intervalSpeedButtons[0] = new JRadioButton("Slow");
		intervalSpeedButtons[1] = new JRadioButton("Normal");
		intervalSpeedButtons[2] = new JRadioButton("Fast");
		initButtonGroup(new ButtonGroup(), intervalSpeedButtons);
	}

	void initButtonGroup(ButtonGroup buttonGroup, JRadioButton[] buttonArray) {
		for (var button : buttonArray) {
			buttonGroup.add(button);
		}
		
		buttonArray[new Random().nextInt(3)].setSelected(true);
	}

	void initButtonGroupShapes(ButtonGroup buttonGroup, JRadioButton[] buttonArray) {
		for (var button : buttonArray) {
			buttonGroup.add(button);
		}
		
		buttonArray[new Random().nextInt(2)].setSelected(true);
	}

	// adds each button within JRadioButton array into the RadioButton panel.
	void addRadioButtonGroup(JPanel panel, JRadioButton[] buttonArray) {
		for (var button : buttonArray) {
			panel.add(button);
		}
	}

	public int getSelected(JRadioButton[] buttonArray) {
		for (int i = 0; i < 3; i++) {
			if (buttonArray[i].isSelected()) {
				return i;
			}
		}

		return -1;
	}

	void addRadioButtonToListener(JRadioButton[] buttonArray, MenuListener menuListener) {
		for (var button : buttonArray) {
			button.addActionListener(menuListener);
		}
	}

	public JRadioButton[] getBackgroundColorButtons() {
		return backgroundColorButtons;
	}

	public JRadioButton[] getShapeColorButtons() {
		return shapeColorButtons;
	}

	public JRadioButton[] getTargetShapeButtons() {
		return targetShapeButtons;
	}

	public JRadioButton[] getTargetSizeButtons() {
		return targetSizeButtons;
	}

	public JRadioButton[] getIntervalSpeedButtons() {
		return intervalSpeedButtons;
	}
	

	public Aim getAim() {
		return aimModel;
	}
}
