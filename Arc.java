import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Arc extends Arme {
    
    public Arc(){
        this.valeurs = 999;
		try {
			this.image = ImageIO.read(new File("img/arc.png"));
		}catch (IOException e){}
    }

    @Override
	public String getLabel(){
		return "Arc";
	}

	@Override
	public int getValeur(){
		int res = this.valeurs;
		return res;
	}
}
