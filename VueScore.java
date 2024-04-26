import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class VueScore extends JComponent{

    protected int points;

    @Override
	public void paintComponent(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque()) {
			secondPinceau.setColor(Color.black);
		}

        secondPinceau.setColor(new Color(76, 76, 76));
		secondPinceau.fillRect(0, 0, this.getWidth()+1, this.getHeight()+1);

        secondPinceau.setColor(new Color(217, 217, 217));
        Font f = new Font("Poppins", Font.BOLD, 22);
		secondPinceau.setFont(f);
		FontMetrics taille = secondPinceau.getFontMetrics(f);
        
        String pts = "Vous avez perdu avec un total de "+this.points+" points .";
        int x = (this.getWidth() - taille.stringWidth(pts)) / 2;
        int y = (this.getHeight() - taille.getHeight())/2 + taille.getAscent();
        secondPinceau.drawString(pts, x, y);
       
  	}
    
    public  VueScore() {
        super();
    }

}