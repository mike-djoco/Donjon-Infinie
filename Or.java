import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Or extends CaseBonus{

	public Or(){
		this.valeurs = (int)(Math.random()*30)+11;
		try {
			this.image = ImageIO.read(new File("img/gold.png"));
		}catch (IOException e){}
	}

	public Or(int i){
		this.valeurs = i;
		try {
			this.image = ImageIO.read(new File("img/gold.png"));
		}catch (IOException e){}
	}

	@Override
	public String getLabel(){
		return "Or";
	}

	@Override
	public int getValeur(){
		int res = this.valeurs;
		return res;
	}

}