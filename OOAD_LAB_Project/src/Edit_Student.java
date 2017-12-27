import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Edit_Student extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtcon;
	private JTextField txtdob;
	private JRadioButton rdno;
	private JRadioButton rdyes;
	private Connection cn;
	private Statement st;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Vector vct = null;
					Edit_Student frame = new Edit_Student(vct);
					frame.setVisible(true);
	}
	
	void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOAD_Project","root","");
		}catch(ClassNotFoundException ex){
			
		} 
		
		catch (SQLException e) {
			JOptionPane.showMessageDialog(contentPane, "Unable to connect database.\nDetails :"+e);
		} 
	}

	/**
	 * Create the frame.
	 */
	public Edit_Student(Vector vct) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditStudent = new JLabel("Edit Student");
		lblEditStudent.setForeground(Color.BLUE);
		lblEditStudent.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblEditStudent.setBounds(10, 11, 187, 39);
		contentPane.add(lblEditStudent);
		
		JLabel label = new JLabel("Student ID");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 81, 91, 23);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Student Name");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(10, 117, 91, 23);
		contentPane.add(label_1);
		
		JLabel label_3 = new JLabel("Contact");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(10, 151, 91, 23);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Date of Birth");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(10, 179, 91, 23);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Payment");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_5.setBounds(10, 212, 91, 23);
		contentPane.add(label_5);
		
		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setColumns(10);
		txtid.setBounds(158, 81, 130, 26);
		contentPane.add(txtid);
		txtid.setText(vct.elementAt(0).toString());
		
		txtname = new JTextField();
		txtname.setText("");
		txtname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtname.setColumns(10);
		txtname.setBounds(159, 116, 221, 26);
		contentPane.add(txtname);
		txtname.setText(vct.elementAt(1).toString());
		
		txtcon = new JTextField();
		txtcon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtcon.setColumns(10);
		txtcon.setBounds(158, 150, 222, 26);
		contentPane.add(txtcon);
		txtcon.setText(vct.elementAt(3).toString());
		
		txtdob = new JTextField();
		txtdob.setColumns(10);
		txtdob.setBounds(158, 178, 107, 26);
		contentPane.add(txtdob);
		txtdob.setText(vct.elementAt(2).toString());
		
		JLabel label_6 = new JLabel("dd/mm/yyyy");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setBounds(276, 179, 104, 23);
		contentPane.add(label_6);
		
		rdyes = new JRadioButton("Yes");
		rdyes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdno.setSelected(false);
			}
		});
		rdyes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdyes.setBounds(158, 210, 61, 26);
		contentPane.add(rdyes);
		if(vct.elementAt(5).toString().equals("YES")){
			rdyes.setSelected(true);
		}
		
		rdno = new JRadioButton("No");
		rdno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdyes.setSelected(false);
			}
		});
		rdno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdno.setBounds(221, 212, 61, 23);
		contentPane.add(rdno);
		if(vct.elementAt(5).toString().equals("NO")){
			rdno.setSelected(true);
		}
		
		JButton button = new JButton("BACK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Student_Payments frame = new Student_Payments();
				frame.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(291, 256, 89, 37);
		contentPane.add(button);
		
		JButton button_1 = new JButton("ADD");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qr = ""; 
				//UPDATE `student` SET `s_name` = 'fdg', `s_contact` = '347985', `s_dob` = '1234', `s_pay` = 'NO' WHERE `student`.`s_id` = 8;
				if(rdyes.isSelected())
					qr = "UPDATE `student` SET `s_name` = '"+txtname.getText()+"', `s_contact` = '"+txtcon.getText()+"', `s_dob` = '"+txtdob.getText()+"',`s_pay` = 'YES' WHERE `student`.`s_id` = " + Integer.parseInt(txtid.getText());
				else if(rdno.isSelected())
					qr = "UPDATE `student` SET `s_name` = '"+txtname.getText()+"', `s_contact` = '"+txtcon.getText()+"', `s_dob` = '"+txtdob.getText()+"', `s_pay` = 'NO' WHERE `student`.`s_id` = " + Integer.parseInt(txtid.getText());
				    try {
				    	st = cn.createStatement(); 
						st.execute(qr);	
						JOptionPane.showMessageDialog(contentPane, "Student Successfully Edited");
						}
					catch (SQLException ex) {
						JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
					}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_1.setBounds(158, 256, 89, 37);
		contentPane.add(button_1);
		
		connect();
	}
}
