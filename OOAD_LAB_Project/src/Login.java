import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField passwordField;
	private JButton btnExit;
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	private String username;
	private String pswrd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					Login frame = new Login();
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
				pswrd = rs.getString(2);
			}
		} 
	       catch (SQLException ex) {
			JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
		}
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		connect();
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUserName.setBounds(55, 68, 82, 26);
		contentPane.add(lblUserName);
		
		txtusername = new JTextField();
		txtusername.setBackground(Color.WHITE);
		txtusername.setBounds(152, 68, 137, 24);
		contentPane.add(txtusername);
		txtusername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(55, 107, 82, 26);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(152, 107, 137, 24);
		passwordField.setEchoChar('*');
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_value();
				if(passwordField.getText().equals(pswrd) && txtusername.getText().equals(username)){
					//Successful login
					setVisible(false);
					Mainmenu frame = new Mainmenu();
					frame.setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(contentPane, "Wrong Password OR Username");
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogin.setBounds(200, 142, 89, 34);
		contentPane.add(btnLogin);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnExit.setBounds(200, 187, 89, 26);
		contentPane.add(btnExit);

	}
}
