import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;

public class Student_Payments extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBox ;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private DefaultTableModel dtm;
	private Connection cn;
	private Statement st;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					Student_Payments frame = new Student_Payments();
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
	
	
	void setcom(){
		 
			String qr = "SELECT MAX(`h_id`) FROM `hostel` ";
			int max_ID=0; 
			try{
	    	st = cn.createStatement(); 
			rs = st.executeQuery(qr);
			rs.next();
			if(rs.getString(1)!=null){
			max_ID=Integer.parseInt(rs.getString(1));			
			}
			String[] str = new String[max_ID+1];
			str[0] = "ALL";
			qr = "SELECT * FROM `hostel`";
			try {
		    	  st = cn.createStatement(); 
				rs = st.executeQuery(qr);
				for(int i =1; rs.next(); i++){
					str[i] = rs.getString(2);
				}
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
			}
			comboBox.setModel(new DefaultComboBoxModel(str));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Student_Payments() {
		setTitle("Student Payments");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentPayments = new JLabel("Student Payments");
		lblStudentPayments.setForeground(Color.BLUE);
		lblStudentPayments.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblStudentPayments.setBounds(10, 9, 187, 39);
		contentPane.add(lblStudentPayments);
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPayment.setBounds(225, 61, 88, 23);
		contentPane.add(lblPayment);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm.setRowCount(0);
				rdbtnNo.setSelected(false);
				Object[] row = new Object[6];
				String qr = "";
				if(comboBox.getSelectedItem().toString().equals("ALL"))
					qr = "SELECT *FROM student WHERE student.s_pay = 'Yes'";
				else{
					int t = comboBox.getSelectedIndex();
					qr = "SELECT * FROM student WHERE student.s_pay = 'Yes' and student.h_id="+t;
				}
				try{
		    	st = cn.createStatement(); 
				rs = st.executeQuery(qr);
				for(int i = 0; rs.next(); i++){
					row[0] = rs.getString(1);
					row[1] = rs.getString(2);
					row[2] = rs.getString(5);
					row[3] = rs.getString(4);
					row[4] = rs.getString(8);
					row[5] = rs.getString(6);
					dtm.addRow(row);
				}	
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
			}
		}});
		rdbtnYes.setBounds(319, 62, 56, 23);
		contentPane.add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm.setRowCount(0);
				rdbtnYes.setSelected(false);
				Object[] row = new Object[6];
				String qr = "";
				if(comboBox.getSelectedItem().toString().equals("ALL"))
					qr = "SELECT *FROM student WHERE student.s_pay = 'No'";
				else{
					int t = comboBox.getSelectedIndex();
					qr = "SELECT * FROM student WHERE student.s_pay = 'No' and student.h_id="+t ;
				}
				try{
		    	st = cn.createStatement(); 
				rs = st.executeQuery(qr);
				for(int i = 0; rs.next(); i++){
					row[0] = rs.getString(1);
					row[1] = rs.getString(2);
					row[2] = rs.getString(5);
					row[3] = rs.getString(4);
					row[4] = rs.getString(8);
					row[5] = rs.getString(6);
					dtm.addRow(row);
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
			}
			}
		});
		rdbtnNo.setBounds(377, 62, 56, 23);
		contentPane.add(rdbtnNo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 506, 206);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dtm = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Student ID", "Student Name", "Date of Birth", "Contact", "Room No", "Payment"
				}
			);
		table.setModel(dtm);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(79);
		table.getColumnModel().getColumn(3).setPreferredWidth(93);
		scrollPane.setViewportView(table);
		
		JLabel lblSelectHostel = new JLabel("Select Hostel");
		lblSelectHostel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelectHostel.setBounds(10, 59, 97, 23);
		contentPane.add(lblSelectHostel);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNo.setSelected(false);
				rdbtnYes.setSelected(false);
			}
		});
		comboBox.setBounds(104, 61, 97, 23);
		contentPane.add(comboBox);
		
		JButton btnEditStudent = new JButton("Edit Selected Student");
		btnEditStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1){
					JOptionPane.showMessageDialog(contentPane, "Select a ROW For Edit");
				}
				else{
				Vector vct = (Vector) dtm.getDataVector().elementAt(table.getSelectedRow());
				setVisible(false);
				Edit_Student frame = new Edit_Student(vct);
				frame.setVisible(true);
				}
			}
		});
		btnEditStudent.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditStudent.setBounds(10, 327, 187, 39);
		contentPane.add(btnEditStudent);
		
		JButton button = new JButton("BACK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Mainmenu frame = new Mainmenu();
				frame.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(427, 333, 89, 37);
		contentPane.add(button);
		
		connect();
		setcom();
	}
}
