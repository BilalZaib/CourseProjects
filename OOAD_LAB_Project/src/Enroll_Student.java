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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class Enroll_Student extends JFrame {

	private JPanel contentPane;
	private JTextField txtrstatus;
	private JTextField txtsid;
	private JTextField txtsname;
	private JTextField txtsaddress;
	private JTextField txtscontact;
	private JTextField txtdob;
	private JComboBox cmbsel_hostel;
	private JComboBox cmbroom_no;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnYes;
	public Connection cn;
	private Statement st;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					Enroll_Student frame = new Enroll_Student();
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
		String qr = "SELECT MAX(`s_id`) FROM `student` ";
		int	max_ID=0; 
		       try {
		    	st = cn.createStatement(); 
				rs = st.executeQuery(qr);
				rs.next();
				if(rs.getString(1)!=null){
				max_ID=Integer.parseInt(rs.getString(1));			
				}	
				txtsid.setText(Integer.toString(max_ID+1));
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
			}
	}
	
	void setcom(){
		getmax();
		
		 String qr = "SELECT MAX(`h_id`) FROM `hostel` ";
		 int  max_ID=0; 
	       try {
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
	       
	       qr = "SELECT MAX(`h_id`) FROM `hostel` ";
			max_ID=0; 
		       try {
		    	st = cn.createStatement(); 
				rs = st.executeQuery(qr);
				rs.next();
				if(rs.getString(1)!=null){
				max_ID=Integer.parseInt(rs.getString(1));			
				}
				String[] str = new String[10];
				
				qr = "SELECT rooms.r_id FROM hostel JOIN rooms WHERE hostel.h_id = rooms.h_id and hostel.h_name = '"+cmbsel_hostel.getSelectedItem().toString()+"'";
				try {
			    	  st = cn.createStatement(); 
					rs = st.executeQuery(qr);
					for(int i =0; rs.next(); i++){
						str[i] = rs.getString(1);
					}
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
				}

				cmbroom_no.setModel(new DefaultComboBoxModel(str));
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
			}
		       txtrstatus.setText("Unfill");
		       setstatus();
	}
	
	void setstatus(){
		//SELECT COUNT(*) from student where student.r_id = 5
		 String qr = "SELECT COUNT(*) from student where student.r_id ="+Integer.parseInt(cmbroom_no.getSelectedItem().toString());
			int max_ID=0; 
			       try {
			    	st = cn.createStatement(); 
					rs = st.executeQuery(qr);
					rs.next();
					if(rs.getString(1)!=null){
					max_ID=Integer.parseInt(rs.getString(1));			
					}
			       }
					catch (SQLException ex) {
					JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
					}
			       qr = "SELECT r_capacity FROM `rooms` WHERE rooms.r_id="+Integer.parseInt(cmbroom_no.getSelectedItem().toString());
			       int smax_ID=0; 
					       try {
					    	st = cn.createStatement(); 
							rs = st.executeQuery(qr);
							rs.next();
							if(rs.getString(1)!=null){
							smax_ID=Integer.parseInt(rs.getString(1));			
							}
							if(max_ID<smax_ID){
								txtrstatus.setText("UnFill");
							}
							else
								txtrstatus.setText("Filled");
					       }
							catch (SQLException ex) {
							JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
							}
	}
	
	void updatetables(){
		int t = cmbsel_hostel.getSelectedIndex()+1;
		String qr = "SELECT T_student from hostel where hostel.h_id ="+t;
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
		qr = "UPDATE `hostel` SET `T_student` = '"+max_ID+"' WHERE `hostel`.`h_id` = "+t;
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
	 * SELECT rooms.r_id FROM hostel JOIN rooms WHERE hostel.h_id = rooms.h_id and hostel.h_name = "Zia Hostel"
	 * INSERT INTO `student` (`s_id`, `s_name`, `s_address`, `s_contact`, `s_dob`, `s_pay`, `h_id`, `r_id`) VALUES ('1', 'Bilal Zaib', 'Peshawar', '03022144497', '2016-12-05', 'YES', '1', '1');
	 */
	public Enroll_Student() {
		setTitle("Enroll Student");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbsel_hostel = new JComboBox();
		cmbsel_hostel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			    
			    String qr = "SELECT MAX(`h_id`) FROM `hostel` ";
				int max_ID=0; 
				       try {
				    	st = cn.createStatement(); 
						rs = st.executeQuery(qr);
						rs.next();
						if(rs.getString(1)!=null){
						max_ID=Integer.parseInt(rs.getString(1));			
						}
						String[] str = new String[10];
						
						qr = "SELECT rooms.r_id FROM hostel JOIN rooms WHERE hostel.h_id = rooms.h_id and hostel.h_name = '"+cmbsel_hostel.getSelectedItem().toString()+"'";
						try {
					    	  st = cn.createStatement(); 
							rs = st.executeQuery(qr);
							for(int i =0; rs.next(); i++){
								str[i] = rs.getString(1);
							}
							
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
						}

						cmbroom_no.setModel(new DefaultComboBoxModel(str));
						setstatus();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
					}
			}
		});
		cmbsel_hostel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbsel_hostel.setBounds(111, 61, 130, 26);
		contentPane.add(cmbsel_hostel);
		
		JLabel lblSelectHostel = new JLabel("Select Hostel");
		lblSelectHostel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSelectHostel.setBounds(10, 63, 91, 23);
		contentPane.add(lblSelectHostel);
		
		JLabel lblRoomNo = new JLabel("Room No");
		lblRoomNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRoomNo.setBounds(264, 61, 68, 26);
		contentPane.add(lblRoomNo);
		
		cmbroom_no = new JComboBox();
		cmbroom_no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setstatus();			
			}
		});
		cmbroom_no.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbroom_no.setBounds(369, 61, 86, 26);
		contentPane.add(cmbroom_no);
		
		JLabel lblRoomStatus = new JLabel("Room Status");
		lblRoomStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRoomStatus.setBounds(268, 97, 91, 26);
		contentPane.add(lblRoomStatus);
		
		txtrstatus = new JTextField();
		txtrstatus.setEditable(false);
		txtrstatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtrstatus.setBounds(369, 98, 86, 26);
		contentPane.add(txtrstatus);
		txtrstatus.setColumns(10);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStudentId.setBounds(10, 128, 91, 23);
		contentPane.add(lblStudentId);
		
		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStudentName.setBounds(10, 162, 91, 23);
		contentPane.add(lblStudentName);
		
		txtsid = new JTextField();
		txtsid.setEditable(false);
		txtsid.setBounds(111, 127, 130, 26);
		contentPane.add(txtsid);
		txtsid.setColumns(10);
		
		txtsname = new JTextField();
		txtsname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtsname.setText("");
		txtsname.setBounds(111, 161, 221, 26);
		contentPane.add(txtsname);
		txtsname.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(10, 196, 91, 23);
		contentPane.add(lblAddress);
		
		txtsaddress = new JTextField();
		txtsaddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtsaddress.setBounds(111, 195, 360, 26);
		contentPane.add(txtsaddress);
		txtsaddress.setColumns(10);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContact.setBounds(10, 230, 91, 23);
		contentPane.add(lblContact);
		
		txtscontact = new JTextField();
		txtscontact.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtscontact.setBounds(111, 229, 222, 26);
		contentPane.add(txtscontact);
		txtscontact.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDateOfBirth.setBounds(10, 264, 91, 23);
		contentPane.add(lblDateOfBirth);
		
		txtdob = new JTextField();
		txtdob.setBounds(111, 263, 107, 26);
		contentPane.add(txtdob);
		txtdob.setColumns(10);
		
		JLabel lblDdmmyyyy = new JLabel("dd/mm/yyyy");
		lblDdmmyyyy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDdmmyyyy.setBounds(228, 264, 104, 23);
		contentPane.add(lblDdmmyyyy);
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPayment.setBounds(10, 298, 91, 23);
		contentPane.add(lblPayment);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNo.setSelected(false);
			}
		});
		rdbtnYes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnYes.setBounds(111, 296, 61, 26);
		contentPane.add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnYes.setSelected(false);
			}
		});
		rdbtnNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNo.setBounds(180, 298, 61, 23);
		contentPane.add(rdbtnNo);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtrstatus.getText().equals("Filled")){
					JOptionPane.showMessageDialog(contentPane, "Select Unfill Room");
				}
				else{
				String qr;
				String s = "NO";
				int t = cmbsel_hostel.getSelectedIndex()+1;
				if(rdbtnYes.isSelected())
					s = "YES";
				else if(rdbtnNo.isSelected())
					s="NO";
				qr = " INSERT INTO `student` (`s_id`, `s_name`, `s_address`, `s_contact`, `s_dob`, `s_pay`, `h_id`, `r_id`) VALUES ('"+Integer.parseInt(txtsid.getText())+"', '"+txtsname.getText()+"', '"+txtsaddress.getText()+"', '"+txtscontact.getText()+"', '"+txtdob.getText()+"', '"+s+"', '"+t+"', '"+Integer.parseInt(cmbroom_no.getSelectedItem().toString())+"');";

				int	max_ID=0; 
				    try {
				    	st = cn.createStatement(); 
						st.execute(qr);	
						JOptionPane.showMessageDialog(contentPane, "Student Successfully Added");
						updatetables();
						}
					catch (SQLException ex) {
						JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
					}
				    getmax();
				    setstatus();
				    txtdob.setText("");
				    txtsaddress.setText("");
				    txtscontact.setText("");
				    txtsname.setText("");
				}
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
		
		JLabel lblEnrollStudent = new JLabel("Enroll Student");
		lblEnrollStudent.setForeground(Color.BLUE);
		lblEnrollStudent.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblEnrollStudent.setBounds(10, 11, 221, 39);
		contentPane.add(lblEnrollStudent);
		
		connect();
		setcom();
	}
}
