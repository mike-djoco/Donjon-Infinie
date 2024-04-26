import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Case implements Serializable{
	public int valeurs;
	public transient BufferedImage image;

	public Case(){
		this.valeurs=0;
	}

	public String getLabel(){
		return "Case";
	}

	public String getLabelPv(){
		String pvres = "case dont have pv";
		return pvres;
	}

	public int getPv(){
		int pvres = -404;
		return pvres;
	}

	public int getMaxPv(){
		return -404;
	}

	public void setPv(int t){}

	public int getValeur(){
		int res = this.valeurs;
		return res;
	}

	public boolean Rencontrer(Case rencontrer){return false;}
 
	public Case newRandomCase(){
		int n = (int)(Math.random()*100)+1;
		if (n<65) {
			int m = (int)(Math.random()*100)+1;
			if (m>0 && m<51) {
				return new Monstre();
			}else if(m>50 && m<91){
				return new Ogre();
			}else{
				return new Archer();
			}
			
		}else {
			CaseBonus caseBonus = new CaseBonus();
			caseBonus = caseBonus.newRandomCase();
			return caseBonus;
		}
	}

}