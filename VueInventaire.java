import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JComponent;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class VueInventaire extends JComponent{

    protected VueCase []TabInv = new VueCase[9];
    protected int selectionner;

    @Override
	public void paintComponent(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque()) {
			secondPinceau.setColor(Color.black);
		}
  	}
    
    public  VueInventaire() {
        for (int i = 0; i < this.TabInv.length; i++) {
            this.TabInv[i] = new VueCase(50+i);
            this.add(this.TabInv[i]);
            this.TabInv[i].repaint();
        }
    }

    public void maj(VuePlateau vPlateau, int Heros){
		Heros h = (Heros) vPlateau.tabVueCase[Heros].casee;
        this.setLayout(new GridLayout(3,3));
		
        for (int i = 0; i < this.TabInv.length; i++) {
            this.TabInv[i].casee = h.Inventaire[i];
            this.add(this.TabInv[i]);
            this.TabInv[i].repaint();
        }
	}

}
