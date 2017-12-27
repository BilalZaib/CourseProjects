import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
public class Hostels extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Enroll_Student es;
	private ResultSet rs;
	private java.sql.Statement st;
	private DefaultTableModel dtm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					Hostels frame = new Hostels();
					frame.setVisible(true);
	}

	void getHosteldate(){
		Object[] row = new Object[5];
		String qr = "SELECT * FROM `hostel`";
		int	max_ID=0; 
		       try {
		    	st = es.cn.createStatement(); 
				rs = st.executeQuery(qr);
				for(int i = 0; rs.next(); i++){
					row[0] = rs.getString(1);
					row[1] = rs.getString(2);
					row[2] = rs.getString(3);
					row[3] = rs.getString(5);
					row[4] = rs.getString(4);
					dtm.addRow(row);
				}		
			} 
		       catch (SQLException e) {
				JOptionPane.showMessageDialog(contentPane, "Error to Execute Query\nDetails"+e);
			}
	}
	/**
	 * Create the frame.
	 */
	public Hostels() {
		es = new Enroll_Student();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAllHostels = new JLabel("All Hostels");
		lblAllHostels.setForeground(Color.BLUE);
		lblAllHostels.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblAllHostels.setBounds(10, 11, 187, 39);
		contentPane.add(lblAllHostels);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 626, 186);
		contentPane.add(scrollPane);
		
		table = new JTable();
		dtm = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Hostel ID", "Hostel Name", "Total Students", "Total Rooms", "Total Employees"
				}
			);
		table.setModel(dtm);
		table.getColumnModel().getColumn(1).setPreferredWidth(124);
		table.getColumnModel().getColumn(2).setPreferredWidth(83);
		table.getColumnModel().getColumn(4).setPreferredWidth(89);
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
		button.setBounds(253, 243, 89, 37);
		contentPane.add(button);
		es.connect();
		getHosteldate();
	}

}
