import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Meteore extends Arme {
    
    public Meteore(){
		this.valeurs = 60;
		try {
			this.image = ImageIO.read(new File("img/meteore.png"));
		}catch (IOException e){}
	}

	@Override
	public String getLabel(){
		return "Meteore";
	}

	@Override
	public int getValeur(){
		int res = this.valeurs;
		return res;
	}
}
