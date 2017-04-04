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
			frame = new JFrame();
            frame.setVisible(true);
            frame.setSize(1000, 800);
            
            //kan alleen niet onze url laden?
			URL url = new URL("http://theslideshow.net/#simple/car");
			System.out.println(imageURL.toString());
            Icon icon = new ImageProxy(url);
            
            System.out.println("Load image into frame...");
            frame.getContentPane().add(new JLabel(icon));
            frame.setPreferredSize(new Dimension(JLabel.WIDTH, JLabel.HEIGHT)); 
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
