import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Arme extends CaseBonus{

	public Arme(){
		this.valeurs = (int)(Math.random()*15)+10;
		try {
			this.image = ImageIO.read(new File("img/sword.png"));
		}catch (IOException e){}
	}

	public Arme(int val){
		this.valeurs = val;
		try {
			this.image = ImageIO.read(new File("img/sword.png"));
		}catch (IOException e){}
	}

	@Override
	public String getLabel(){
		return "Arme";
	}

	@Override
	public int getValeur(){
		int res = this.valeurs;
		return res;
	}

}