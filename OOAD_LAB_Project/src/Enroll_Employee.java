import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Enroll_Employee extends JFrame {

	private JPanel contentPane;
	private JTextField txteid;
	private JTextField txtename;
	private JTextField txtaddress;
	private JTextField txtecontact;
	private JTextField txtdob;
	private JTextField txtpay;
	private JComboBox cmbsel_hostel;
	private JComboBox comboBox;
	private Connection cn;
	private Statement st;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					Enroll_Employee frame = new Enroll_Employee();
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
	
	void getmax(){
		String qr = "SELECT MAX(`e_id`) FROM `employee` ";
		 int  max_ID=0; 
	       try {
	    	   st = cn.createStatement(); 
				rs = st.executeQuery(qr);
				rs.next();
				if(rs.getString(1)!=null){
				max_ID=Integer.parseInt(rs.getString(1));			
				}
				txteid.setText(Integer.toString(max_ID+1));
	       } catch (SQLException e) {
				JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
			}
	}
	
	void setcom(){
		 
			getmax();
			String qr = "SELECT MAX(`h_id`) FROM `hostel` ";
			int max_ID=0; 
			try{
	    	st = cn.createStatement(); 
			rs = st.executeQuery(qr);
			rs.next();
			if(rs.getString(1)!=null){
			max_ID=Integer.parseInt(rs.getString(1));			
			}
			String[] str = new String[max_ID];
			
			qr = "SELECT * FROM `hostel`";
			try {
		    	  st = cn.createStatement(); 
				rs = st.executeQuery(qr);
				for(int i =0; rs.next(); i++){
					str[i] = rs.getString(2);
				}
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
			}
			cmbsel_hostel.setModel(new DefaultComboBoxModel(str));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
		}
	}

	void updatetables(int t){
		String qr = "SELECT T_employee from hostel where hostel.h_id ="+t;
		int max_ID=0; 
		       try {
		    	st = cn.createStatement(); 
				rs = st.executeQuery(qr);
				rs.next();
				if(rs.getString(1)!=null){
				max_ID=Integer.parseInt(rs.getString(1));			
				}
				max_ID++;
		       }
				catch (SQLException ex) {
				JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
				}
		qr = "UPDATE `hostel` SET `T_employee` = '"+max_ID+"' WHERE `hostel`.`h_id` = "+t;
		try {
	    	st = cn.createStatement(); 
			 st.execute(qr);
		}
			catch (SQLException ex) {
			JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
			}
	}

	
	/**
	 * Create the frame.
	 */
	public Enroll_Employee() {
		setTitle("Enroll Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbsel_hostel = new JComboBox();
		cmbsel_hostel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbsel_hostel.setBounds(111, 58, 130, 26);
		contentPane.add(cmbsel_hostel);
		
		JLabel lblSelectHostel = new JLabel("Select Hostel");
		lblSelectHostel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSelectHostel.setBounds(10, 60, 91, 23);
		contentPane.add(lblSelectHostel);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmployeeId.setBounds(10, 128, 91, 23);
		contentPane.add(lblEmployeeId);
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmployeeName.setBounds(10, 162, 91, 23);
		contentPane.add(lblEmployeeName);
		
		txteid = new JTextField();
		txteid.setEditable(false);
		txteid.setBounds(111, 127, 130, 26);
		contentPane.add(txteid);
		txteid.setColumns(10);
		
		txtename = new JTextField();
		txtename.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtename.setText("");
		txtename.setBounds(111, 161, 221, 26);
		contentPane.add(txtename);
		txtename.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(10, 199, 91, 23);
		contentPane.add(lblAddress);
		
		txtaddress = new JTextField();
		txtaddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtaddress.setBounds(111, 198, 360, 26);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContact.setBounds(10, 233, 91, 23);
		contentPane.add(lblContact);
		
		txtecontact = new JTextField();
		txtecontact.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtecontact.setBounds(111, 230, 222, 26);
		contentPane.add(txtecontact);
		txtecontact.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDateOfBirth.setBounds(10, 267, 91, 23);
		contentPane.add(lblDateOfBirth);
		
		txtdob = new JTextField();
		txtdob.setBounds(111, 266, 107, 26);
		contentPane.add(txtdob);
		txtdob.setColumns(10);
		
		JLabel lblDdmmyyyy = new JLabel("dd/mm/yyyy");
		lblDdmmyyyy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDdmmyyyy.setBounds(228, 267, 104, 23);
		contentPane.add(lblDdmmyyyy);
		
		JLabel lblPayment = new JLabel("Employee Pay");
		lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPayment.setBounds(10, 301, 91, 23);
		contentPane.add(lblPayment);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int t = cmbsel_hostel.getSelectedIndex()+1;
				String qr = "INSERT INTO `employee` (`e_id`, `e_name`, `e_dob`, `e_contact`, `e_address`, `e_job`, `e_pay`, `h_id`) VALUES ('"+Integer.parseInt(txteid.getText())+"', '"+txtename.getText()+"', '"+txtdob.getText()+"', '"+txtecontact.getText()+"', '"+txtaddress.getText()+"', '"+comboBox.getSelectedItem().toString()+"', '"+Integer.parseInt(txtpay.getText())+"', '"+t+"'); ";
			       try {
			    	   st = cn.createStatement(); 
			    	   st.execute(qr);
						JOptionPane.showMessageDialog(contentPane, "Employee Added Successfully");
						updatetables(t);
			       } catch (SQLException ex) {
						JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
					}
			       txtaddress.setText("");
			       txtdob.setText("");
			       txtecontact.setText("");
			       txtename.setText("");
			       txtpay.setText("");
			       getmax();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdd.setBounds(111, 345, 89, 37);
		contentPane.add(btnAdd);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Mainmenu frame = new Mainmenu();
				frame.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.setBounds(385, 345, 89, 37);
		contentPane.add(btnBack);
		
		JLabel lblEmployeeJob = new JLabel("Employee Job");
		lblEmployeeJob.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmployeeJob.setBounds(10, 94, 91, 23);
		contentPane.add(lblEmployeeJob);
		
		JLabel lblEnrollEmployee = new JLabel("Enroll Employee");
		lblEnrollEmployee.setForeground(Color.BLUE);
		lblEnrollEmployee.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblEnrollEmployee.setBounds(10, 0, 221, 39);
		contentPane.add(lblEnrollEmployee);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cook", "Sweeper", "Warden", "Gaurd"}));
		comboBox.setBounds(111, 89, 130, 26);
		contentPane.add(comboBox);
		
		txtpay = new JTextField();
		txtpay.setBounds(111, 297, 107, 26);
		contentPane.add(txtpay);
		txtpay.setColumns(10);
		
		connect();
		setcom();
	}
}
