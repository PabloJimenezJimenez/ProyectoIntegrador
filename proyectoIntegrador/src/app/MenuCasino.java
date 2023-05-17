package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class MenuCasino extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuCasino frame = new MenuCasino();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuCasino() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Ruleta de la suerte");
		btnNewButton.setBounds(238, 112, 252, 51);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("tragaperras");
		btnNewButton_1.setBounds(238, 223, 252, 45);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("<---");
		btnNewButton_2.setBounds(54, 285, 103, 66);
		contentPane.add(btnNewButton_2);
	}
}
