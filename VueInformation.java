import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JComponent;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class VueInformation extends JComponent{
    protected String vie = "VIE : 999/999";
    protected String armure = "ARMURE : 99";
    protected String attaque = "ATTAQUE : 99";
    protected String point = "POINT : 99";

    @Override
	public void paintComponent(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque()) {
			secondPinceau.setColor(Color.black);
		}
        Color DGray = new Color(76, 76, 76);
        Color LGray = new Color(217, 217, 217);
        Color VGreen = new Color(44, 214, 16);
        Color ABlue = new Color(64, 114, 241);
        Color ARed = new Color(181, 74, 74);
        Color POrange = new Color(188, 151, 18);

        secondPinceau.setColor(LGray);
        secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
        secondPinceau.setColor(DGray);
        secondPinceau.fillRect(5, 5, this.getWidth()-10, this.getHeight()-10);
        
        Font f = new Font("Poppins", Font.BOLD, 20);
		secondPinceau.setFont(f);
		FontMetrics taille = secondPinceau.getFontMetrics(f);
        int ecart = (this.getWidth() - taille.stringWidth(this.vie) - taille.stringWidth(this.armure) - taille.stringWidth(this.attaque) - taille.stringWidth(this.point))/5;
        int xvie = ecart;
        int xarmure = xvie +  taille.stringWidth(this.vie) + ecart;
        int xattaque = xarmure +  taille.stringWidth(this.armure) + ecart;
        int xpoint = xattaque +  taille.stringWidth(this.attaque) + ecart;
        int y = (this.getHeight() - taille.getHeight()) / 2 + taille.getAscent();

        secondPinceau.setColor(VGreen);
        secondPinceau.drawString(this.vie, xvie, y);
        secondPinceau.setColor(ABlue);
        secondPinceau.drawString(this.armure, xarmure, y);
        secondPinceau.setColor(ARed);
        secondPinceau.drawString(this.attaque, xattaque, y);
        secondPinceau.setColor(POrange);
        secondPinceau.drawString(this.point, xpoint, y);
  	}

    public VueInformation(VuePlateau VP, int Heros){
        super();
        Heros h = (Heros) VP.tabVueCase[Heros].casee;
        this.vie = "VIE : "+h.getPv()+"/"+h.getMaxPv();
        if (h.armures == null) {
            this.armure = "ARMURE : 0";
        }else {
            this.armure = "ARMURE : "+h.armures.getValeur();
        }
        if (h.arme == null) {
            this.attaque = "ATTAQUE : 0";
        }else {
            this.attaque = "ATTAQUE : "+h.arme.getValeur();
        }
        this.point = "POINT : "+h.getValeur();
    }

    public void updateVueInformation(VuePlateau VP, int Heros) {
        Heros h = (Heros) VP.tabVueCase[Heros].casee;
        this.vie = "VIE : "+h.getPv()+"/"+h.getMaxPv();
        if (h.armures == null) {
            this.armure = "ARMURE : 0";
        }else {
            this.armure = "ARMURE : "+h.armures.getValeur();
        }
        if (h.arme == null) {
            this.attaque = "ATTAQUE : 0";
        }else {
            this.attaque = "ATTAQUE : "+h.arme.getValeur();
        }
        this.point = "POINT : "+h.getValeur();
        this.repaint();
    }
}
