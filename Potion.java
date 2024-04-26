import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Potion extends CaseBonus{

	public Potion(){
		this.valeurs= (int)(Math.random()*15)+10;
		try {
			this.image = ImageIO.read(new File("img/potion.png"));
		}catch (IOException e){}
	}

	public Potion(int val){
		this.valeurs= val;
		try {
			this.image = ImageIO.read(new File("img/potion.png"));
		}catch (IOException e){}
	}

	@Override
	public String getLabel(){
		return "Potion";
	}

	@Override
	public int getValeur(){
		int res = this.valeurs;
		return res;
	}

}