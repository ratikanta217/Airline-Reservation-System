import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Color;

import static javax.swing.BoxLayout.Y_AXIS;


public class Seating extends JFrame{

    private static String selected_class, selected_preference;
    private static int passenger_count;
    private JPanel panel_seats1, panel_seats2, panel_seats3, panel_seats4, panel_seats5, parent_panel1, parent_panel2;
    private JLabel lbl_seats;
    public static JButton economy_class[][] = new JButton[15][6], business_class[][] = new JButton[9][4];

    Seating(String Class, String preference, int num_of_passenger){
        selected_class = Class;
        selected_preference = preference;
        passenger_count = num_of_passenger;
    }

    Seating(){
        setTitle("Seating");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        panel_seats1 = new JPanel();
        panel_seats1.setLayout(new GridLayout(15,6));
        panel_seats2 = new JPanel();
        panel_seats2.setBounds(100, 100, 300, 200);

        char ch = 65;
        for(int i=0; i<15; ++i){
            for(int j=0; j<6; ++j){
                if (j!= 0 && j%2 == 0){
                    JLabel lbl_space = new JLabel();
                    lbl_space.setPreferredSize(new Dimension(50, 50));
                    panel_seats1.add(lbl_space);
                }
                economy_class[i][j] = new JButton(new String(ch+String.valueOf(j+1)));
                panel_seats1.add(economy_class[i][j]);
            }
            ch++;
        }
        panel_seats2.add(panel_seats1);
        JScrollPane sp1 = new JScrollPane(panel_seats2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel_seats3 = new JPanel();
        panel_seats3.setLayout(new GridLayout(9,4));
        panel_seats4 = new JPanel();
        panel_seats4.setBounds(100, 100, 300, 200);

        ch = 65;
        for(int i=0; i<9; ++i){
            for(int j=0; j<4; ++j){
                if (j!= 0 && j%2 == 0){
                    JLabel lbl_space = new JLabel();
                    lbl_space.setPreferredSize(new Dimension(50, 50));
                    panel_seats3.add(lbl_space);
                }
                business_class[i][j] = new JButton(new String(ch+String.valueOf(j+1)));
                panel_seats3.add(business_class[i][j]);
            }
            ch++;
        }
        panel_seats4.add(panel_seats3);
        JScrollPane sp2 = new JScrollPane(panel_seats4, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel_seats5 = new JPanel();
        BoxLayout layout = new BoxLayout(panel_seats5, BoxLayout.Y_AXIS);
        panel_seats5.setLayout(layout);
        panel_seats5.add(sp1);
        panel_seats5.add(sp2);

        getContentPane().add(panel_seats5);
        getContentPane().setBackground(Color.GRAY);
        setDefaultCloseOperation(HIDE_ON_CLOSE);


    }

    static int find_seats(){
        int ch = -1;

        if(selected_class.equals("Economy")){

            Economy e1 = new Economy();
            
            if(selected_preference.equals("Center")){
                ch = e1.find_center(passenger_count);
            }
            else if(selected_preference.equals("Window")){
                ch = e1.find_window(passenger_count);
            }

            //e1.display();
        }

        else if(selected_class.equals("Business")){
            Business b1 = new Business();
            if(selected_preference.equals("Window")){
                ch = b1.find_window(passenger_count);
            }
            else if(selected_preference.equals("Aisle")){
                ch = b1.find_aisle(passenger_count);
            }

            //b1.display();
        }
        else 
            ch = -1;

        return ch;
    }

    static void show_seating(){
        System.out.print("\n------------------------------");
        System.out.print("\n\t  *Economy Class*  ");
        System.out.print("\n------------------------------\n");
        Economy.display();
        System.out.print("\n------------------------------");
        System.out.print("\n\t  *Business Class*  ");
        System.out.print("\n------------------------------\n");
        Business.display();

    }

}

class Business{

    static int[][] seats = new int[9][4];

    int find_window(int passenger_count){
        for(int row=0; row<9; ++row){
            for(int j=0; j <=3; j=j+3){
                if(seats[row][j] == 0){
                    seats[row][j] = 1;
                    Seating.business_class[row][j].setBackground(Color.RED);
                    Seating.business_class[row][j].setContentAreaFilled(false);
                    Seating.business_class[row][j].setOpaque(true);
                    passenger_count--;
                    if (passenger_count == 0)
                        break;
                }
            }
            if(passenger_count == 0){
               break;
            }
        }

        if(passenger_count != 0){
            return 1;
        }
        else return 0;
    }

    int find_aisle(int passenger_count){
    
        for(int row=0; row<9; ++row){
            for(int j=1; j<=2 ; ++j){
                if(seats[row][j] == 0){
                    seats[row][j] = 1;
                    Seating.business_class[row][j].setBackground(Color.RED);
                    Seating.business_class[row][j].setContentAreaFilled(false);
                    Seating.business_class[row][j].setOpaque(true);
                    passenger_count--;
                    if (passenger_count == 0)
                        break;
                }
            }
            if(passenger_count == 0){
                break;
            }
        }

        if(passenger_count != 0){
            return 1;
        }
        else return 0;
    }
    
    static void display(){
        for(int i=0; i<9; ++i){
            for(int j=0; j<4;++j){
                System.out.print("  "+seats[i][j]+"  ");
                if(j==1) System.out.print("          ");
            }
            System.out.println();
        }
    }

}

class Economy{

    static int [][]seats = new int[15][6];

    int find_center(int passenger_count){
        for(int row=0; row<15; ++row){
            for(int j=2; j<=3; ++j){
                if(seats[row][j] == 0){
                    seats[row][j] = 1;
                    Seating.economy_class[row][j].setBackground(Color.RED);
                    Seating.economy_class[row][j].setContentAreaFilled(false);
                    Seating.economy_class[row][j].setOpaque(true);
                    passenger_count--;
                    if (passenger_count == 0)
                        break;
                }
            }
            if(passenger_count == 0)
                break;
        }

        if(passenger_count != 0){
            return 1;
        }
        else return 0;
    }

    int find_window(int passenger_count){
    
        for(int row=0; row<15; ++row){
            for(int j=0; j<6; ++j){
                if(j == 2 || j == 3)
                    continue;
                if(seats[row][j] == 0){
                    seats[row][j] = 1;
                    Seating.economy_class[row][j].setBackground(Color.RED);
                    Seating.economy_class[row][j].setContentAreaFilled(false);
                    Seating.economy_class[row][j].setOpaque(true);
                    passenger_count--;
                    if (passenger_count == 0)
                        break;
                }
            }
            if(passenger_count == 0)
                break;
        }

        if(passenger_count != 0){
            return 1;
        }
        else return 0;
    }
    static void display(){
      for(int i=0; i<15; ++i){
            for(int j=0; j<6;++j){
                System.out.print(" "+seats[i][j]+" ");
                if((j+1)%2==0) System.out.print("     ");
            }
            System.out.println();
        }
    }

}

class Test{
    public static void main(String[] args){

    }
}