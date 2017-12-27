import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Account extends JFrame {

	private JPanel contentPane;
	private JTextField uname;
	private JTextField nuname;
	private String username;
	private String upswrd;
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	private JPasswordField pswd;
	private JPasswordField npswd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					Account frame = new Account();
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
	
	void get_value(){
		String qr = "SELECT * FROM `log_in`"; 
	       try {
	    	st = cn.createStatement(); 
			rs = st.executeQuery(qr);	
			rs.next();
			if(rs.getString(1)!=null){
				username = rs.getString(1);
				upswrd = rs.getString(2);
			}
		} 
	       catch (SQLException ex) {
			JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
		}
	}
	/**
	 * Create the frame.
	 */
	public Account() {
		connect();
		get_value();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditAccount = new JLabel("Edit Account");
		lblEditAccount.setForeground(Color.BLUE);
		lblEditAccount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblEditAccount.setBounds(117, 11, 139, 39);
		contentPane.add(lblEditAccount);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUserName.setBounds(39, 61, 97, 23);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(39, 85, 97, 23);
		contentPane.add(lblPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewPassword.setBounds(39, 148, 97, 23);
		contentPane.add(lblNewPassword);
		
		JLabel lblNewUserName = new JLabel("New User Name");
		lblNewUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewUserName.setBounds(39, 119, 97, 23);
		contentPane.add(lblNewUserName);
		
		uname = new JTextField();
		uname.setEditable(false);
		uname.setColumns(10);
		uname.setBackground(Color.WHITE);
		uname.setBounds(145, 61, 137, 24);
		uname.setText(username);
		contentPane.add(uname);
		
		nuname = new JTextField();
		nuname.setColumns(10);
		nuname.setBackground(Color.WHITE);
		nuname.setBounds(145, 121, 137, 24);
		contentPane.add(nuname);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pswd.getText().equals(upswrd)){
				String qr = "UPDATE `log_in` SET `user` = '"+nuname.getText()+"', `pswd` = '"+npswd.getText()+"' WHERE `log_in`.`pswd` = '"+upswrd+"';"; 
			       try {
			    	st = cn.createStatement(); 
					st.execute(qr);	
					JOptionPane.showMessageDialog(contentPane, "Account Updated Successfully");
				} 
			       catch (SQLException ex) {
					JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
				}
			}
				else
					JOptionPane.showMessageDialog(contentPane, "Wrong Old Password");
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(175, 195, 107, 37);
		contentPane.add(btnUpdate);
		
		pswd = new JPasswordField();
		pswd.setEchoChar('*');
		pswd.setBounds(146, 87, 137, 24);
		contentPane.add(pswd);
		
		npswd = new JPasswordField();
		npswd.setEchoChar('*');
		npswd.setBounds(145, 150, 137, 24);
		contentPane.add(npswd);
		
		JButton button = new JButton("BACK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Mainmenu frame = new Mainmenu();
				frame.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(292, 195, 89, 37);
		contentPane.add(button);
	}
}
