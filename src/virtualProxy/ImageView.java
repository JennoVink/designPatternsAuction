package virtualProxy;

import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

public class ImageView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageView window = new ImageView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImageView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//ImageIcon icon = new ImageIcon();
		try
		{
			URL url = new URL("https://cdn.meme.am/instances/38321563.jpg");
			JLabel picLabel = new JLabel((Icon) new ImageIcon(url));
		}
		catch(MalformedURLException e)
		{
			//Nice exceptions Java! NOT
		}
		
		
	}

}
