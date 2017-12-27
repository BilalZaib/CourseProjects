import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class Search extends JFrame {

	private JPanel contentPane;
	private JTextField txtsname;
	private JTable table;
	private JComboBox cmbhostel;
	private JComboBox cmbroom;
	private DefaultTableModel dtm;
	public Connection cn;
	private Statement st;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					Search frame = new Search();
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
		 int  max_ID=0; 
	       try {
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
			cmbhostel.setModel(new DefaultComboBoxModel(str));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
		}
	        
			String[] str = new String[50];	
			str[0] = "-";
			if(!cmbhostel.getSelectedItem().toString().equals("ALL"))
				qr = "SELECT rooms.r_id FROM hostel JOIN rooms WHERE hostel.h_id = rooms.h_id and hostel.h_name = '"+cmbhostel.getSelectedItem().toString()+"'";
			else
				qr = "SELECT rooms.r_id FROM rooms";
			
			try {
			   	st = cn.createStatement(); 
				rs = st.executeQuery(qr);
				for(int i =1; rs.next(); i++){
					str[i] = rs.getString(1);
				}
				cmbroom.setModel(new DefaultComboBoxModel(str));
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
			}
	}
	
	void cmbAction(){
		String qr = "";
		if(cmbhostel.getSelectedItem().toString().equals("ALL") && cmbroom.getSelectedItem().toString().equals("-" )&& txtsname.getText().isEmpty()){
			qr = "SELECT * FROM `student` ";
		}
		else if(cmbhostel.getSelectedItem().toString().equals("ALL") && !cmbroom.getSelectedItem().toString().equals("-" )&& txtsname.getText().isEmpty()){
			qr = "SELECT * FROM `student` WHERE student.r_id = "+Integer.parseInt(cmbroom.getSelectedItem().toString());
		}
		else if(cmbhostel.getSelectedItem().toString().equals("ALL") && cmbroom.getSelectedItem().toString().equals("-" )&& !txtsname.getText().isEmpty()){
			qr = "SELECT * FROM `student` WHERE student.s_name LIKE '%"+txtsname.getText()+"%'";
		}
		else if(cmbhostel.getSelectedItem().toString().equals("ALL") && !cmbroom.getSelectedItem().toString().equals("-" )&& !txtsname.getText().isEmpty()){
			qr = "SELECT * FROM `student` WHERE student.s_name LIKE '%"+txtsname.getText()+"%' AND student.r_id =" + Integer.parseInt(cmbroom.getSelectedItem().toString());
		}
		else if(!cmbhostel.getSelectedItem().toString().equals("ALL") && cmbroom.getSelectedItem().toString().equals("-" )&& txtsname.getText().isEmpty()){
			qr = "SELECT * FROM `student` WHERE student.h_id =" +cmbhostel.getSelectedIndex();
		}
		else if(!cmbhostel.getSelectedItem().toString().equals("ALL") && !cmbroom.getSelectedItem().toString().equals("-" )&& txtsname.getText().isEmpty()){
			qr = "SELECT * FROM `student` WHERE student.h_id =" +cmbhostel.getSelectedIndex()+" AND student.r_id = " +Integer.parseInt(cmbroom.getSelectedItem().toString());
		}
		else if(!cmbhostel.getSelectedItem().toString().equals("ALL") && !cmbroom.getSelectedItem().toString().equals("-" )&& !txtsname.getText().isEmpty()){
			qr = "SELECT * FROM `student` WHERE student.h_id =" +cmbhostel.getSelectedIndex()+" AND student.r_id = " +Integer.parseInt(cmbroom.getSelectedItem().toString())+" AND student.s_name LIKE '%"+txtsname.getText()+"%'";
		}
		else if(!cmbhostel.getSelectedItem().toString().equals("ALL") && cmbroom.getSelectedItem().toString().equals("-" )&& !txtsname.getText().isEmpty()){
			qr = "SELECT * FROM `student` WHERE student.h_id =" +cmbhostel.getSelectedIndex()+" AND student.s_name LIKE '%"+txtsname.getText()+"%'";
		}
		
		dtm.setRowCount(0);
		Object[] row = new Object[7];
		 try {
			st = cn.createStatement(); 
			rs = st.executeQuery(qr);
			for(int i = 0; rs.next(); i++){
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(5);
				row[3] = rs.getString(3);
				row[4] = rs.getString(4);
				row[5] = rs.getString(8);
				row[6] = rs.getString(6);
				dtm.addRow(row);
			}		
		} 
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
			}
	}
	
	void updatetables(int t){
		String qr = "SELECT T_student from hostel where hostel.h_id ="+t;
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
	 */
	public Search() {
		setTitle("Search");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Select Hostel");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 49, 97, 23);
		contentPane.add(label);
		
		cmbhostel = new JComboBox();
		cmbhostel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String qr = "";
				String[] str = new String[50];	
				if(!cmbhostel.getSelectedItem().toString().equals("ALL"))
					qr = "SELECT rooms.r_id FROM hostel JOIN rooms WHERE hostel.h_id = rooms.h_id and hostel.h_name = '"+cmbhostel.getSelectedItem().toString()+"'";
				else
					qr = "SELECT rooms.r_id FROM rooms";
				
				str[0] = "-";
				try {
				   	st = cn.createStatement(); 
					rs = st.executeQuery(qr);
					for(int i =1; rs.next(); i++){
						str[i] = rs.getString(1);
					}
					cmbroom.setModel(new DefaultComboBoxModel(str));
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
				}
			}
		});
		cmbhostel.setBounds(131, 48, 130, 26);
		contentPane.add(cmbhostel);
		
		JLabel lblByStudentName = new JLabel("By Student Name");
		lblByStudentName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblByStudentName.setBounds(10, 123, 111, 23);
		contentPane.add(lblByStudentName);
		
		txtsname = new JTextField();
		txtsname.setColumns(10);
		txtsname.setBounds(131, 122, 130, 26);
		contentPane.add(txtsname);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbAction();
					
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSearch.setBounds(310, 120, 89, 29);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 163, 559, 146);
		contentPane.add(scrollPane);
		
		table = new JTable();
		dtm = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Student ID", "Student Name", "Date of Birth", "Address", "Contact", "Room No", "Payment"
				}
			);
		table.setModel(dtm);
		table.getColumnModel().getColumn(1).setPreferredWidth(97);
		table.getColumnModel().getColumn(3).setPreferredWidth(94);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("BACK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Mainmenu frame = new Mainmenu();
				frame.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(456, 320, 89, 37);
		contentPane.add(button);
		
		JLabel lblSearchStudent = new JLabel("Search Student");
		lblSearchStudent.setForeground(Color.BLUE);
		lblSearchStudent.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblSearchStudent.setBounds(10, 11, 187, 39);
		contentPane.add(lblSearchStudent);
		
		JLabel lblRoomNo = new JLabel("Room No");
		lblRoomNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRoomNo.setBounds(10, 83, 91, 23);
		contentPane.add(lblRoomNo);
		
		cmbroom = new JComboBox();
		cmbroom.setBounds(131, 85, 97, 26);
		contentPane.add(cmbroom);
		
		JButton btnDeleteStudent = new JButton("Delete Student");
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
			    	qr = "SELECT student.h_id FROM student WHERE student.s_id = "+Integer.parseInt((vct.elementAt(0).toString()));
					rs = st.executeQuery(qr);
					rs.next();
					if(rs.getString(1)!=null){
					int max_ID=Integer.parseInt(rs.getString(1));	
					updatetables(max_ID);
					}
					qr = "DELETE FROM student where s_id = "+ Integer.parseInt((vct.elementAt(0).toString()));
					st.execute(qr);
					JOptionPane.showMessageDialog(contentPane, "Student Successfully Deleted");
					cmbAction();
					}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+ex);
				}
				}
			}
		});
		btnDeleteStudent.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDeleteStudent.setBounds(20, 320, 156, 37);
		contentPane.add(btnDeleteStudent);
		
		connect();
		setcom();
	}
}
