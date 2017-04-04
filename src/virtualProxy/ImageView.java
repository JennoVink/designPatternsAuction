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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

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
		
	}

	
	public void paintIcon(URL imageURL){
//		  EventQueue.invokeLater(new Runnable() {
//	            @Override
//	            public void run() {
//	                try {
//	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//	                }
//
//	                
//
//	            }
//	        });
		  
		    	
//          	final HttpURLConnection connection = (HttpURLConnection) imageURL
//          	        .openConnection();
//          	connection.setRequestProperty(
//          	    "User-Agent",
//          	    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
//          	final BufferedImage image = ImageIO.read(connection.getInputStream());
          	
		try 
		{  
			URL url = new URL("https://img.ifcdn.com/images/7f55ce61298ac48674b7ad85141806963ba5a2db1998415fe08407fdf66b2ac4_1.jpg");
			
            frame = new JFrame();
            JPanel jp = new JPanel();
            jp.setPreferredSize(new Dimension(400, 800));
            frame.getContentPane().add(jp);
            frame.setVisible(true);

            
            Icon icon = new ImageProxy(imageURL);
            System.out.println("Load image into frame...");
            JLabel picLabel = new JLabel(icon);
            frame.getContentPane().add(picLabel);
            
              
        } 
		catch (Exception exp) 
		{
              exp.printStackTrace();
        }
		
	}
	public ImageView getImageView()
	{
		return this;
	}

}
