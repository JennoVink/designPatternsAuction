package virtualProxy;

import java.awt.Component;
import java.awt.Graphics;

public interface Icon {
	abstract int getIconWidth();
	abstract int getIconHeight();
	abstract void paintIcon(Component c, Graphics g, int x, int y);
}
