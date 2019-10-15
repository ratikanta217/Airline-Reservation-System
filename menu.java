import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class menu extends JFrame implements ActionListener{

    private JLabel lbl_title;
    private JButton btn_add_passengers, btn_show_seating, btn_logout;
    public static Seating frame_seats = new Seating();
    //public static Form frame_form = new Form();

    
    menu(){
        getContentPane().setLayout(null);
		setTitle("Menu");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack();
		setSize(500,400);
		setResizable(false);
		setLocationRelativeTo(null); //opens frame in center

        lbl_title = new JLabel("M E N U");
        lbl_title.setBounds(-1, 40, 500, 40);
        lbl_title.setFont(new Font("Arial", Font.BOLD, 20));
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        lbl_title.setBorder(border);
        lbl_title.setPreferredSize(new Dimension(500, 50));
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        lbl_title.setVerticalAlignment(JLabel.CENTER);
        lbl_title.setBackground(Color.WHITE);
        lbl_title.setOpaque(true);

        btn_add_passengers = new JButton("Add passengers");
        btn_add_passengers.setFont(new Font("Arial", Font.PLAIN, 16));
        btn_add_passengers.setBounds(150, 120, 200, 40);
        btn_add_passengers.addActionListener(this);

        btn_show_seating = new JButton("Show Seating");
        btn_show_seating.setFont(new Font("Arial", Font.PLAIN, 16));
        btn_show_seating.setBounds(150, 180, 200, 40);
        btn_show_seating.addActionListener(this);

        btn_logout = new JButton("Logout");
        btn_logout.setFont(new Font("Arial", Font.PLAIN, 16));
        btn_logout.setBounds(150, 240, 200, 40);
        btn_logout.addActionListener(this);

        getContentPane().add(lbl_title);
        getContentPane().add(btn_add_passengers);
        getContentPane().add(btn_show_seating);
        getContentPane().add(btn_logout);
        getContentPane().setBackground(Color.BLACK);
        
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btn_add_passengers){
            Form frame_form = new Form();
            frame_form.setVisible(true);
            this.dispose();
        }
        else if(ae.getSource() == btn_show_seating){
            System.out.print("\nSelected button : Show seating");
            Seating.show_seating();
            //frame_seats = new Seating();
            frame_seats.setVisible(true);
        }
        else if(ae.getSource() == btn_logout){
            int selected_option = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out");
            if(selected_option == 0) {
                this.dispose();
                //Login frame_login = new Login();
                loginDemo2.login_frame.setVisible(true);

                JOptionPane.showMessageDialog(null, "Logged Out Successfully!");
                //JOptionPane.showConfirmDialog(null, "Logged Out Successfully!", "Confirmation",JOptionPane.DEFAULT_OPTION);

            }
        }
        else{
            JOptionPane.showMessageDialog(this,"Error Occurred",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}

class menu_test{
    public static void main(String []args){
       EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu frame_menu = new menu();
					frame_menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        });
        
    }
}
