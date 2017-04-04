package virtualProxy;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Color;

public class ImageView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                }

	                try {
	                    String path = "http://chart.finance.yahoo.com/z?s=GOOG&t=6m&q=l";
	                    System.out.println("Get Image from " + path);
	                    URL url = new URL(path);
	                    BufferedImage image = ImageIO.read(url);
	                    System.out.println("Load image into frame...");
	                    JLabel picLabel = new JLabel();
	                    JFrame f = new JFrame();
	                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                    f.getContentPane().add(picLabel);
	                    f.pack();
	                    f.setLocation(200, 200);
	                    f.setVisible(true);
	                    
	                    
	                } catch (Exception exp) {
	                    exp.printStackTrace();
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
		  EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                }

	                try {
	                    String path = "https://i.ytimg.com/vi/gYeAscy46HA/maxresdefault.jpg";
	                    System.out.println("Get Image from " + path);
	                    URL url = new URL(path);
	                    BufferedImage image = ImageIO.read(url);
	                    System.out.println("Load image into frame...");
	                    JLabel label = new JLabel(new ImageIcon(image));
	                    JFrame f = new JFrame();
	                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                    f.getContentPane().add(label);
	                    f.pack();
	                    f.setLocation(200, 200);
	                    f.setVisible(true);
	                } catch (Exception exp) {
	                    exp.printStackTrace();
	                }

	            }
	        });
		
	}

}
