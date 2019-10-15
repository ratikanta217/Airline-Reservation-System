import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Dimension;

class Form_demo{
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

public class Form extends JFrame implements ActionListener, ItemListener{
	private JLabel title,name, label_passenger_count, classes,preferences;
	private JTextField passenger_name, tf_passenger_count;
	private JButton btn_find, btn_back;
	private Choice select_preference,select_classes;

	Form() {
		getContentPane().setLayout(null);
		setTitle("Registration Form");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(500,400);
		
		//adding features: Rinku
		setResizable(false);
		setLocationRelativeTo(null);

		//contentPane = new JPanel();
		
		title = new JLabel("REGISTRATION   FORM ");
		title.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Border border_title1 = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.RED, Color.PINK);
		//Border border_title2 = BorderFactory.createCompoundBorder(border_title1, border_title1);
		title.setBorder(border_title1);
		title.setPreferredSize(new Dimension(500, 400));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setBackground(Color.WHITE);
    	title.setOpaque(true);

		name = new JLabel("Name:- ");
		name.setFont(new Font("Arial", Font.PLAIN, 12));

		//adding passenger count fields
		label_passenger_count = new JLabel("<html>Passenger Count:- </html>");
		//label_passenger_count = new JLabel("Passenger Count:- ");
		label_passenger_count.setFont(new Font("Arial", Font.PLAIN, 12));

		classes = new JLabel("Class:-");
		classes.setFont(new Font("Arial", Font.PLAIN, 12));

		preferences = new JLabel("Preferences:-");
		preferences.setFont(new Font("Arial", Font.PLAIN, 12));

		passenger_name = new JTextField();
		tf_passenger_count = new JTextField();

		btn_find = new JButton("FIND");
		btn_find.setFont(new Font("Arial", Font.BOLD, 12));

		btn_back = new JButton("BACK");
		btn_find.setFont(new Font("Arial", Font.BOLD, 12));
		
		select_classes = new Choice();
		select_classes.add("Not Selected");
		select_classes.add("Economy");
		select_classes.add("Business");
		
		//added feature: Rinku
		select_classes.addItemListener(this);

		select_preference = new Choice();	
		select_preference.setEnabled(false);

		//labels
		title.setBounds(-1, 40, 500, 30);
		name.setBounds(130,100,75,30);
		//name.setOpaque(true);
		name.setForeground(Color.WHITE);
		label_passenger_count.setBounds(130,150,100,30);
		//label_passenger_count.setOpaque(true);
		label_passenger_count.setForeground(Color.WHITE);
		classes.setBounds(130,200,75,30);
		//classes.setOpaque(true);
		classes.setForeground(Color.WHITE);
		preferences.setBounds(130,250,75,30);
		//preferences.setOpaque(true);
		preferences.setForeground(Color.WHITE);


		//text_fields
		passenger_name.setBounds(215, 100, 150, 30);
		tf_passenger_count.setBounds(215, 150, 150, 30);
		select_classes.setBounds(215, 200, 150, 30);
		select_preference.setBounds(215,250,150,30);
		
		btn_find.setBounds(250, 300, 110, 30);
		btn_find.addActionListener(this);
		btn_back.setBounds(130, 300, 110, 30);
		btn_back.addActionListener(this);


		getContentPane().add(title);
		getContentPane().add(name);
		getContentPane().add(label_passenger_count);
		getContentPane().add(classes);
		getContentPane().add(preferences);
		getContentPane().add(passenger_name);
		getContentPane().add(tf_passenger_count);
		getContentPane().add(select_classes);
		getContentPane().add(select_preference);
		getContentPane().add(btn_find);
		getContentPane().add(btn_back);
		getContentPane().setBackground(Color.BLACK);
		getRootPane().setDefaultButton(btn_find);
	}

	public void itemStateChanged(ItemEvent e){
		String selected_class = select_classes.getSelectedItem();

		//adding options to select_preferences according to selected class

		select_preference.removeAll();

		if(selected_class.equals(select_classes.getItem(1))){
			select_preference.add("Window");
			select_preference.add("Center");
		}
		else if(selected_class.equals(select_classes.getItem(2))){
			select_preference.add("Window");
			select_preference.add("Aisle");
		}
		select_preference.setEnabled(true);

	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == btn_find) {
			int passenger_count = Integer.parseInt(tf_passenger_count.getText());
			String selected_class = select_classes.getItem(select_classes.getSelectedIndex());
			String selected_preference = select_preference.getItem(select_preference.getSelectedIndex());
			Seating find_seats = new Seating(selected_class, selected_preference, passenger_count);
			int result = Seating.find_seats();
			System.out.println("\nResult from the seat search : "+result);
			switch(result){
				case 0: ImageIcon icon = new ImageIcon("C:\\Users\\monan\\Documents\\Semester 5\\JAVA\\OEP\\GUI\\jbuttonIcon.png");
						JOptionPane.showMessageDialog(null, "Seats have been Booked!");
				break;

				case 1: JOptionPane.showMessageDialog(null, "Seats cannot be booked!", "Sorry!",JOptionPane.ERROR_MESSAGE);
				break;

				default: JOptionPane.showMessageDialog(null, "Server failure\nTry again later.", "Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			this.dispose();
			menu frame_menu = new menu();
			frame_menu.setVisible(true);
		}
	  }
	  

}