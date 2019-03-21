package me.timo.hw18;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import me.timo.hw18.user.User;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class NewForm {

	private JFrame frame;
	private JTextField nameTextField;
	private Game game;
	private JTextField countdownTextfield;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	public NewForm(Game game) {
		this.game = game;
		createForm();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void createForm() {
		int width = 500;
		int height = 300;

		Dimension dimension = new Dimension(width, height);
		frame = new JFrame("Configuration");
		frame.setPreferredSize(dimension);
		frame.setMaximumSize(dimension);
		frame.setMinimumSize(dimension);
		frame.setLocationRelativeTo(null);

		frame.setResizable(true);
		frame.setAlwaysOnTop(true);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JTabbedPane eventsPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Events", null, eventsPane, null);

		JPanel chooseTwoPanel = new JPanel();
		eventsPane.addTab("ChooseTwo", null, chooseTwoPanel, null);
		chooseTwoPanel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel drinksHeader = new JLabel("Drinks");
		drinksHeader.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chooseTwoPanel.add(drinksHeader, "2, 2, center, default");

		JCheckBox shotCheckBox = new JCheckBox("Shot (5P)");
		shotCheckBox.setSelected(true);
		chooseTwoPanel.add(shotCheckBox, "2, 4");

		JCheckBox longdrinkCheckBox = new JCheckBox("Longdrink (3P)");
		longdrinkCheckBox.setSelected(true);
		chooseTwoPanel.add(longdrinkCheckBox, "2, 6");

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		chooseTwoPanel.add(separator, "4, 3, 1, 10");

		JCheckBox gemischtCheckBox = new JCheckBox("Gemischt (7P)");
		chooseTwoPanel.add(gemischtCheckBox, "2, 8");

		JCheckBox weinCheckBox = new JCheckBox("Wein (3P)");
		chooseTwoPanel.add(weinCheckBox, "2, 10");

		JCheckBox exenCheckBox = new JCheckBox("Exen (7P)");
		exenCheckBox.setSelected(true);
		chooseTwoPanel.add(exenCheckBox, "2, 12");

		JButton buttonSave = new JButton("Save");
		buttonSave.addActionListener(new ActionListener() {

			// Save Button
			public void actionPerformed(ActionEvent e) {

				updateCheckBoxes(chooseTwoPanel);

			}
		});

		updateCheckBoxes(chooseTwoPanel);

		chooseTwoPanel.add(buttonSave, "2, 14");

		JPanel scarePanel = new JPanel();
		eventsPane.addTab("Scare", null, scarePanel, null);

		JPanel newUserPanel = new JPanel();
		tabbedPane.addTab("New User", null, newUserPanel, "Add a new user.");
		newUserPanel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel registriertLabel = new JLabel("Bereits Registriert");
		registriertLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		newUserPanel.add(registriertLabel, "8, 2, center, center");

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newUserPanel.add(nameLabel, "2, 4, center, default");

		nameTextField = new JTextField();
		newUserPanel.add(nameTextField, "4, 4, center, default");
		nameTextField.setColumns(10);

		JTextPane usersTextPane = new JTextPane();
		usersTextPane.setEditable(false);
		newUserPanel.add(usersTextPane, "8, 4, 1, 10, fill, default");

		JLabel drinksLabel = new JLabel("Trinkt Alkohol");
		drinksLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newUserPanel.add(drinksLabel, "2, 6, center, default");
		
		JLabel lblNewLabel_1 = new JLabel("Geschlecht");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newUserPanel.add(lblNewLabel_1, "2, 8, center, default");
		
		JRadioButton mRadioButton = new JRadioButton("M");
		mRadioButton.setSelected(true);
		buttonGroup_1.add(mRadioButton);
		newUserPanel.add(mRadioButton, "4, 8, center, default");
		
		JRadioButton wRadioButton = new JRadioButton("W");
		buttonGroup_1.add(wRadioButton);
		newUserPanel.add(wRadioButton, "4, 10, center, default");
		


		JCheckBox drinksCheckBox = new JCheckBox("Ja");
		newUserPanel.add(drinksCheckBox, "4, 6, center, default");

		JButton buttonAddUser = new JButton("Add user");
		buttonAddUser.addActionListener(new ActionListener() {

			// User Hinzufügen
			public void actionPerformed(ActionEvent e) {

				User user = new User(nameTextField.getText(), drinksCheckBox.isSelected(), mRadioButton.isSelected());
				game.getUserManager().getUsers().add(user);
				usersTextPane.setText(usersTextPane.getText() + user.toString() + "\n");
			}
		});

		game.getUserManager().getUsers().add(new User("Timo", true, true));
		usersTextPane.setText(usersTextPane.getText() + game.getUserManager().getUsers().get(0) + "\n");
		
		newUserPanel.add(buttonAddUser, "2, 14, center, center");

		JPanel optionsPanel = new JPanel();
		tabbedPane.addTab("Options", null, optionsPanel, "Option configuration.");
		optionsPanel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel = new JLabel("Music");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		optionsPanel.add(lblNewLabel, "2, 2, 4, 1, center, default");

		game.getOptions().setCountdownTime(1);

		JCheckBox titleMusicCheckBox = new JCheckBox("Title Screen");
		titleMusicCheckBox.addActionListener(new ActionListener() {

			// Musik

			public void actionPerformed(ActionEvent e) {
				game.getOptions().setPlayMusic(titleMusicCheckBox.isSelected());
			}
		});

		JLabel countdownHeaderLabel = new JLabel("Countdown");
		countdownHeaderLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		optionsPanel.add(countdownHeaderLabel, "10, 2, center, default");

		titleMusicCheckBox.setSelected(true);
		optionsPanel.add(titleMusicCheckBox, "2, 6");

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		optionsPanel.add(separator_2, "4, 3, 1, 8");

		JLabel countdownLabel = new JLabel("Time (min)");
		countdownLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		optionsPanel.add(countdownLabel, "8, 6, center, default");

		countdownTextfield = new JTextField();
		countdownTextfield.addActionListener(new ActionListener() {

			// Countdown zeit

			public void actionPerformed(ActionEvent e) {
				game.getCountdown().setCountdownTime(Double.parseDouble(countdownTextfield.getText()));
				game.getOptions().setCountdownTime(Double.parseDouble(countdownTextfield.getText()));
			}
		});

		optionsPanel.add(countdownTextfield, "10, 6, left, default");
		countdownTextfield.setColumns(10);

		JCheckBox countdownMusicCheckBox = new JCheckBox("Countdown");
		optionsPanel.add(countdownMusicCheckBox, "2, 8");

		JCheckBox eventsMusicCheckBox = new JCheckBox("Events");
		optionsPanel.add(eventsMusicCheckBox, "2, 10");
	}

	private void updateCheckBoxes(JPanel panel) {
		// Add chooseTwo boxes
		for (Component comp : panel.getComponents()) {

			if (comp instanceof JCheckBox) {
				JCheckBox box = (JCheckBox) comp;

				if (box.isSelected()) {
					if (!game.getOptions().getCheckBoxes().contains(box))
						game.getOptions().getCheckBoxes().add(box);

				} else {
					if (game.getOptions().getCheckBoxes().contains(box)) {
						game.getOptions().getCheckBoxes().remove(box);
					}
				}

			}

		}
	}

	public void openForm() {
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

}
