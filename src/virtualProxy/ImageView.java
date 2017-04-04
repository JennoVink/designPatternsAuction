package virtualProxy;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

public class ImageView{

	private JFrame frame;
	
	private URLConnection uc;

	public static void main(String[] args){
		ImageView view = new ImageView();
	}

	/**
	 * Create the application.
	 */
	public ImageView() {
//		initialize();
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
	                    
	                    frame = new JFrame();
	                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                    frame.getContentPane().add(label);
	                    frame.pack();
	                    frame.setLocation(200, 200);
	                    frame.setVisible(true);
	                    
	                } catch (Exception exp) {
	                    exp.printStackTrace();
	                }

	            }
	        });
		
	}
	
	public void paintIcon(URL imageURL){
		  EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                }

	                try {    	
	                	final HttpURLConnection connection = (HttpURLConnection) imageURL
	                	        .openConnection();
	                	connection.setRequestProperty(
	                	    "User-Agent",
	                	    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
	                	final BufferedImage image = ImageIO.read(connection.getInputStream());
	                	
	                    System.out.println("Load image into frame...");
	                    JLabel label = new JLabel(new ImageIcon(image));    
	                    
	                    frame = new JFrame();
	                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                    frame.getContentPane().add(label);
	                    frame.pack();
	                    frame.setLocation(200, 200);
	                    frame.setVisible(true);
	                    
	                } catch (Exception exp) {
	                    exp.printStackTrace();
	                }

	            }
	        });
		
	}

}
