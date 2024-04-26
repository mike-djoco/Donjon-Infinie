import javax.swing.*;
import java.awt.*;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class VueCase extends JPanel{

	protected Case casee;
	protected int c;

	@Override
	public void paintComponent(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();
		
		secondPinceau.setColor(new Color(76, 76, 76));
		secondPinceau.fillRect(0, 0, this.getWidth()+1, this.getHeight()+1);	

		secondPinceau.setColor(new Color(217, 217, 217));
		int xx = this.getWidth();
		int yy = this.getHeight();
		secondPinceau.fillRect(10, 10, xx-20, yy-20);

		Font f = new Font("Poppins", Font.BOLD, 14);
		secondPinceau.setFont(f);
		FontMetrics taille = secondPinceau.getFontMetrics(f);
		secondPinceau.setColor(Color.black);

		if(this.casee.getLabel()=="Hero"){
			secondPinceau.drawImage( this.casee.image, 0 , 0 , this.getWidth() , this.getHeight() , this );

			Heros h = (Heros) this.casee;
			if (h.armures != null) {
				secondPinceau.drawImage( h.varmure, 0 , 0 , this.getWidth() , this.getHeight() , this );
			}
			if (h.arme != null) {
				if (h.arme.getLabel() == "Masse") {
					secondPinceau.drawImage( h.vmasse, 0 , 0 , this.getWidth() , this.getHeight() , this );
				}else if (h.arme.getLabel() == "Arc") {
					secondPinceau.drawImage( h.varc, 0 , 0 , this.getWidth() , this.getHeight() , this );
				}else if (h.arme.getLabel() == "Hache") {
					secondPinceau.drawImage( h.vaxe, 0 , 0 , this.getWidth() , this.getHeight() , this );
				}else if (h.arme.getLabel() == "Arme") {
					secondPinceau.drawImage( h.varme, 0 , 0 , this.getWidth() , this.getHeight() , this );
				}
			}
		}else if(this.casee.getLabel()=="Monstre" || this.casee.getLabel()=="Ogre" || this.casee.getLabel()=="Archer"){
			secondPinceau.drawImage( this.casee.image, 0 , 0 , this.getWidth() , this.getHeight() , this );
			secondPinceau.setColor(new Color(181, 74, 74));
			String life = this.casee.getLabelPv()+" / "+this.casee.getValeur();
			int x = (this.getWidth() - taille.stringWidth(life)) / 2;
			int y = (this.getHeight() - taille.getHeight()) + taille.getAscent() -10;
			secondPinceau.drawString(life, x, y);
		}else if(this.casee.getLabel()=="Arme" || this.casee.getLabel()=="Hache"  || this.casee.getLabel()=="Arc"  || this.casee.getLabel()=="Masse"){
			secondPinceau.drawImage( this.casee.image, 0 , 0 , this.getWidth() , this.getHeight() , this );
			secondPinceau.setColor(new Color(181, 74, 74));
			String points = this.casee.getValeur()+"";
			int x = (this.getWidth() - taille.stringWidth(points)) / 2;
			int y = (this.getHeight() - taille.getHeight()) + taille.getAscent() -10;
			secondPinceau.drawString(points, x, y);
		}else if(this.casee.getLabel()=="Armure"){
			secondPinceau.drawImage( this.casee.image, 0 , 0 , this.getWidth() , this.getHeight() , this );
			secondPinceau.setColor(new Color(64, 114, 241));
			String points = this.casee.getValeur()+"";
			int x = (this.getWidth() - taille.stringWidth(points)) / 2;
			int y = (this.getHeight() - taille.getHeight()) + taille.getAscent() -10;
			secondPinceau.drawString(points, x, y);
		}else if(this.casee.getLabel()=="Potion"){
			secondPinceau.drawImage( this.casee.image, 0 , 0 , this.getWidth() , this.getHeight() , this );
			secondPinceau.setColor(new Color(44, 214, 16));
			String points = this.casee.getValeur()+"";
			int x = (this.getWidth() - taille.stringWidth(points)) / 2;
			int y = (this.getHeight() - taille.getHeight()) + taille.getAscent() -10;
			secondPinceau.drawString(points, x, y);
		}else if(this.casee.getLabel()=="Or"){
			secondPinceau.drawImage( this.casee.image, 0 , 10 , this.getWidth() , this.getHeight() , this );
			String points = this.casee.getValeur()+"";
			int x = (this.getWidth() - taille.stringWidth(points)) / 2;
			int y = (this.getHeight() - taille.getHeight()) + taille.getAscent() -10;
/*
			secondPinceau.setColor(new Color(230, 230, 230)); 
			secondPinceau.fillRect(0-1, y, this.getWidth()+1, y+taille.getHeight()+1);
*/
			secondPinceau.setColor(new Color(188, 151, 18));
			secondPinceau.drawString(points, x, y);
		}else if(this.casee.getLabel()=="Meteore"){
			secondPinceau.drawImage( this.casee.image, 0 , 0 , this.getWidth() , this.getHeight() , this );
		}else if(this.casee.getLabel()=="Case"){
			String emptys = "La case est vide";
			int x = (this.getWidth() - taille.stringWidth(emptys)) / 2;
			int y = (this.getHeight() - taille.getHeight())/ 2 + taille.getAscent() ;
/*
			secondPinceau.setColor(new Color(230, 230, 230));
			secondPinceau.fillRect(0-1, y, this.getWidth()+1, y-taille.getAscent()+1);
*/
			secondPinceau.setColor(Color.red);
			secondPinceau.drawString(emptys, x, y);
		}
		
  	}

  	public VueCase(int i){
		this.casee = new Case();
		this.casee = this.casee.newRandomCase();
		this.c = i;
  	}

	public VueCase(int i, boolean hero){
		if (hero) {
			this.casee = new Heros();
		} else{
			this.casee = new Case();
		}
		this.c = i;
  	}

}