package TextInputGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.NoSuchFileException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextInputGUI implements ActionListener {

	static private JTextField textField;
	static private JTextArea jta;
	static private JLabel mLabel;

	/*
	 * create frame, panel and components, and add components to panel, and panel to frame
	 * use GridBagLayout for layout control, build a package for 
	 */
	private void placeComponents() {
		JFrame frame = new JFrame("TextInputGUI");
		frame.setBounds(300, 100, 750, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBag = new GridBagLayout();
		JPanel panel = new JPanel(gridBag);
		frame.add(panel);
		
		jta = new JTextArea("", 5, 30);
		jta.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
		        if (jta.getText().equals("Input Text...")) {
		        	jta.setText("");
		        	jta.setForeground(Color.BLACK);
		        }
			}
			@Override
			public void focusLost(FocusEvent e) {
		        if (jta.getText().isEmpty()) {
		        	jta.setForeground(Color.GRAY);
		        	jta.setText("Input Text...");
		        }
			}
		});
		jta.setLineWrap(true);
		jta.setForeground(Color.BLACK);
		jta.setFont(new Font("MicroSoft Yahei", Font.PLAIN, 14));
		jta.setBackground(Color.WHITE);
		JScrollPane jsp = new JScrollPane(jta);
		gridBag.addLayoutComponent(jsp,
				new GBC(0, 0, 7, 3).setFill(GBC.BOTH).setIpad(20, 50).setWeight(100, 0).setInsets(0, 100, 10, 100));
		panel.add(jsp);

		
		JLabel label = new JLabel("File Path:");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("MicroSoft Yahei", Font.PLAIN, 14));
		gridBag.addLayoutComponent(label,
				new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(100, 0).setInsets(0, 100, 0, 0));
		panel.add(label);

	
		textField = new JTextField(60);
		gridBag.addLayoutComponent(textField,
				new GBC(1, 3, 4, 1).setFill(GBC.BOTH).setIpad(200, 0).setWeight(100, 0).setInsets(0, 0, 0, 0));
		panel.add(textField);

		JButton browseBtn = new JButton("Browse");
		gridBag.addLayoutComponent(browseBtn, new GBC(5, 3, 1, 1).
				setFill(GBC.BOTH).setWeight(100, 0).setInsets(0, 5, 0, 0));
		browseBtn.addActionListener(new TextInputGUI());
		panel.add(browseBtn);

		JButton saveBtn = new JButton("Save");
		saveBtn.setForeground(Color.white);
		saveBtn.setBackground(new Color(70, 60, 150));
		saveBtn.addActionListener(new TextInputGUI());
		gridBag.addLayoutComponent(saveBtn,
				new GBC(6, 3, 1, 1).setFill(GBC.BOTH).setWeight(100, 0).setInsets(0, 5, 0, 100));
		panel.add(saveBtn);

		mLabel = new JLabel();
		mLabel.setFont(new Font("MicroSoft Yahei", Font.PLAIN, 14));
		gridBag.addLayoutComponent(mLabel,
				new GBC(0, 4, 7, 1).setFill(GBC.BOTH).setIpad(0, 50).setWeight(100, 0).setInsets(0, 100, 0, 100));
		panel.add(mLabel);

		frame.setVisible(true);
	}

	/*
	 * ActionListener for BrowseButton, and choose file path for write text content
	 * ActionListener for SaveButton, and read text and write in the file in .txt type.
	 */ 
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Browse")) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.showDialog(new JLabel(), "Select");
			File file = chooser.getSelectedFile();
			textField.setText(file.getAbsolutePath().toString());
		} 
		if (e.getActionCommand().equals("Save")){
			String content = jta.getText();
			String filePath = textField.getText();
			String line = null;
			Exception exceptionMsg = null;
			try (BufferedReader in = new BufferedReader(new StringReader(content));
					BufferedWriter out = new BufferedWriter(new FileWriter(filePath))) {
				while ((line = in.readLine()) != null) {
					out.write(line);
					out.newLine();
				}
			} catch (FileNotFoundException e1) {
				System.out.println("File Path Not Found");
				exceptionMsg = e1;
			} catch (NoSuchFileException e2) {
				System.out.println("File Not Found");
				exceptionMsg = e2;
			} catch (IOException e3) {
				System.out.println("IO Issue");
				exceptionMsg = e3;
			}
			if (exceptionMsg == null) {
				mLabel.setText("File saved successfully!");
				mLabel.setForeground(Color.blue);
			} else {
				mLabel.setText("No path or file save failed");
				mLabel.setForeground(Color.red);
			}
		}
	}
	public static void main(String[] args) {
		TextInputGUI textInputGui = new TextInputGUI();
		textInputGui.placeComponents();
	}

}
