import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class All_Employees extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBox;
	private DefaultTableModel dtm;
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					All_Employees frame = new All_Employees();
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
	
	void cmbAction(){
		dtm.setRowCount(0);
		Object[] row = new Object[7];
		String qr= "";
		if(comboBox.getSelectedItem().toString().equals("ALL"))
			qr = "SELECT * FROM `employee`";
		else
			qr = "SELECT * FROM `employee` where employee.h_id ="+comboBox.getSelectedIndex();
		 try {
			st = cn.createStatement(); 
			rs = st.executeQuery(qr);
			for(int i = 0; rs.next(); i++){
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(6);
				row[3] = rs.getString(3);
				row[4] = rs.getString(4);
				row[5] = rs.getString(5);
				row[6] = rs.getString(7);
				dtm.addRow(row);
			}		
		} 
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
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
				max_ID--;
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
	public All_Employees() {
		setTitle("All Employees");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAllEmployees = new JLabel("All Employees");
		lblAllEmployees.setForeground(Color.BLUE);
		lblAllEmployees.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblAllEmployees.setBounds(10, 11, 187, 39);
		contentPane.add(lblAllEmployees);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 91, 542, 216);
		contentPane.add(scrollPane);
		
		table = new JTable();
		dtm = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Employee ID", "Employee Name", "Job", "Date of Birth", "Contact", "Address", "Pay"
				}
			);
		table.setModel(dtm);
		table.getColumnModel().getColumn(0).setPreferredWidth(94);
		table.getColumnModel().getColumn(1).setPreferredWidth(116);
		table.getColumnModel().getColumn(2).setPreferredWidth(66);
		table.getColumnModel().getColumn(3).setPreferredWidth(93);
		table.getColumnModel().getColumn(4).setPreferredWidth(84);
		table.getColumnModel().getColumn(5).setPreferredWidth(88);
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("Select Hostel");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 49, 97, 23);
		contentPane.add(label);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbAction();
			}
		});
		comboBox.setBounds(117, 46, 130, 26);
		contentPane.add(comboBox);
		
		JButton button = new JButton("BACK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Mainmenu frame = new Mainmenu();
				frame.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(427, 318, 89, 37);
		contentPane.add(button);
		
		JButton btnDeleteStudent = new JButton("Delete Employee");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1){
					JOptionPane.showMessageDialog(contentPane, "Select a ROW to Delete");
				}
				else{
				Vector vct = (Vector) dtm.getDataVector().elementAt(table.getSelectedRow());
				String qr = "";
			    try {
			    	st = cn.createStatement(); 
			    	qr = "SELECT employee.h_id FROM employee WHERE employee.e_id = "+Integer.parseInt((vct.elementAt(0).toString()));
					rs = st.executeQuery(qr);
					rs.next();
					if(rs.getString(1)!=null){
					int max_ID=Integer.parseInt(rs.getString(1));	
					updatetables(max_ID);
					}
					qr = "DELETE FROM employee where e_id = "+ Integer.parseInt((vct.elementAt(0).toString()));
					st.execute(qr);
					JOptionPane.showMessageDialog(contentPane, "Employee Successfully Deleted");
					cmbAction();
					}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
				}
				}
			}
		});
		btnDeleteStudent.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDeleteStudent.setBounds(41, 318, 156, 37);
		contentPane.add(btnDeleteStudent);
		
		connect();
		setcom();
		cmbAction();
	}

}
