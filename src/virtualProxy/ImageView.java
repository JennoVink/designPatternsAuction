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
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

public class ImageView{

	private JFrame frame;
	private JLabel picLabel;
	
	private URLConnection uc;

	/**
	 * Create the application.
	 */
	public ImageView() {
		frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        picLabel = new JLabel();
        frame.getContentPane().add(picLabel);
	}

	/**
	 * Paints the icon (/current product image) according to the @param imageURL. 
	 * @param imageURL
	 */
	public void paintIcon(URL imageURL)	{	
		try 
		{  
            //set the proper user agent
            System.setProperty("http.agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
                       
            Icon icon = new ImageProxy(imageURL);
            picLabel.setIcon(icon);
            
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
