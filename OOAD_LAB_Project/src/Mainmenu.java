import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mainmenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					Mainmenu frame = new Mainmenu();
					frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Mainmenu() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnHostels = new JButton("Hostels");
		btnHostels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Hostels frame = new Hostels();
				frame.setVisible(true);
			}
		});
		btnHostels.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHostels.setBounds(117, 102, 161, 31);
		contentPane.add(btnHostels);
		
		JButton btnEnrollStudent = new JButton("Enroll Student");
		btnEnrollStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Enroll_Student frame = new Enroll_Student();
				frame.setVisible(true);
			}
		});
		btnEnrollStudent.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEnrollStudent.setBounds(117, 144, 161, 31);
		contentPane.add(btnEnrollStudent);
		
		JButton btnEnrollEmployee = new JButton("Enroll Employee");
		btnEnrollEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Enroll_Employee frame = new Enroll_Employee();
				frame.setVisible(true);
			}
		});
		btnEnrollEmployee.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEnrollEmployee.setBounds(117, 186, 161, 31);
		contentPane.add(btnEnrollEmployee);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Search frame = new Search();
				frame.setVisible(true);
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSearch.setBounds(117, 270, 161, 31);
		contentPane.add(btnSearch);
		
		JButton btnStudentPayments = new JButton("Student Payments");
		btnStudentPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Student_Payments frame = new Student_Payments();
				frame.setVisible(true);
			}
		});
		btnStudentPayments.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStudentPayments.setBounds(117, 228, 161, 31);
		contentPane.add(btnStudentPayments);
		
		JLabel lblHostelManagementSystem = new JLabel("Hostel Management System");
		lblHostelManagementSystem.setForeground(Color.BLUE);
		lblHostelManagementSystem.setBackground(Color.DARK_GRAY);
		lblHostelManagementSystem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblHostelManagementSystem.setBounds(72, 11, 251, 38);
		contentPane.add(lblHostelManagementSystem);
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblMainMenu.setBounds(153, 60, 87, 31);
		contentPane.add(lblMainMenu);
		
		JButton btnExit = new JButton("Logout");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(296, 350, 107, 31);
		contentPane.add(btnExit);
		
		JButton btnNewButton = new JButton("All Employees");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				All_Employees frame = new All_Employees();
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(117, 308, 161, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit Account");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Account frame = new Account();
				frame.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(117, 347, 161, 31);
		contentPane.add(btnNewButton_1);
	}
}
