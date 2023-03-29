import javax.swing.*;
import java.lang.Exception;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;

public class Participants extends JFrame {
	// Introduce our UI elements
	private JPanel main;
	private JTextField nameTextField;
	private JSpinner mSpinner;
	private JSpinner hSpinner;
	private JButton addParticipantButton;
	private JTextArea logPane;
	private JTextArea participantsPane;

	// Configure both arrays
	ArrayList<String> logList = new ArrayList<>();
	ArrayList<String> participantsList = new ArrayList<>();

	// Code for interaction
	public Participants() {
		nameTextField.requestFocus(); // Set focus to input on launch

		// Limit the spinners
		SpinnerModel hourModel = new SpinnerNumberModel(0, 0, 23, 1);
		hSpinner.setModel(hourModel);
		SpinnerModel minuteModel = new SpinnerNumberModel(0, 0, 59, 1);
		mSpinner.setModel(minuteModel);

		// Check and add content when button gets pressed
		addParticipantButton.addActionListener(actionEvent -> {
			if (nameTextField.getText().isEmpty()) { // If no name has been given
				logList.add("No name given");
				update(logPane, logList);
				nameTextField.requestFocus();
			} else if (nameTextField.getText().contains(" ")) { // If the name contains spaces
				logList.add("The given name is not valid");
				update(logPane, logList);
				nameTextField.requestFocus();
			} else { // Input accepted!
				String participantString = String.format("%s on time %02d:%02d", nameTextField.getText(), hSpinner.getValue(), mSpinner.getValue());
				participantsList.add(participantString);
				update(participantsPane, participantsList);
				logList.add("Participant was added");
				update(logPane, logList);
				reset();
				nameTextField.requestFocus();
			}
		});
	}

	private static void update(JTextArea area, ArrayList<String> list) {
		area.setText(""); // Clear the text pane before updating it
		for (String line : list) {
			area.append(line + "\n");
		}
	}

	private void reset() {
		nameTextField.setText("");
		for (JSpinner jSpinner : Arrays.asList(hSpinner, mSpinner)) {
			jSpinner.setValue(0);
		}
	}

	public static void main(String[] args) {
		// Try to enforce the Windows UI style
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception ignored){}
		// Setup our UI
		Participants h = new Participants();                // Call a new instance of the window
		h.setContentPane(h.main);                           // Display the main window of the Participants class
		h.setTitle("TimeSlots");                            // Give the window a name
		h.setSize(750, 750);                  // Default width and height
		h.setLocationRelativeTo(null);                      // In the middle of the screen
		h.setVisible(true);                                 // Make it show up
		h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Close the program when the user closes the window
	}
}