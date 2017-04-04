package virtualProxy;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
public class ImageProxy implements Icon {

	private ImageIcon imageIcon;
	private URL imageURL;
	private Thread retrievalThread;
	private boolean retrieving = false;
	
	public ImageProxy(URL url)
	{
		imageURL = url;
	}
	
	@Override
	public int getIconWidth() {
		if(imageIcon != null)
		{
			return imageIcon.getIconWidth();
		} 
		
		return 800;
		
	}

	@Override
	public int getIconHeight() {
		if (imageIcon != null) 
		{
			return imageIcon.getIconHeight();        
		} 
		
		return 600;        
			
	}

	public void paintIcon(final Component c, Graphics  g, int x,  int y) {
		System.out.println("Reached paintIcon()");
		if(imageIcon != null)
		{
			imageIcon.paintIcon(c, g, x, y);
		} 
		else 
		{
			g.drawString("Loading image, please wait...", x+300, y+190);
			if(!retrieving)
			{
				retrieving = true;
				retrievalThread = new Thread(new Runnable() 
				{
					public void run()
					{
						try 
						{
							
							imageIcon = new ImageIcon(imageURL, "Product Image");
							c.repaint();
							
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				});
				retrievalThread.start();
			}
		}
		
	}

//	@Override
//	public void paintIcon(Component c, Graphics g, int x, int y) {
//		ui.paintIcon(imageURL);
//		
//	}
}
