import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Armure extends CaseBonus{

	public Armure(){
		this.valeurs = (int)(Math.random()*45)+20;
		try {
			this.image = ImageIO.read(new File("img/armor.png"));
		}catch (IOException e){}
	}

	public Armure(int val){
		this.valeurs = val;
		try {
			this.image = ImageIO.read(new File("img/armor.png"));
		}catch (IOException e){}
	}

	@Override
		public String getLabel(){
		return "Armure";
	}

	@Override
	public int getValeur(){
		int res = this.valeurs;
		return res;
	}

}