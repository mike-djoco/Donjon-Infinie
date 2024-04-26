import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Masse extends Arme {
    
    public Masse(){
        this.valeurs = (int)(Math.random()*30)+15;
		try {
			this.image = ImageIO.read(new File("img/masse.png"));
		}catch (IOException e){}
    }

	public Masse(int val){
        this.valeurs = val;
		try {
			this.image = ImageIO.read(new File("img/masse.png"));
		}catch (IOException e){}
    }

    @Override
	public String getLabel(){
		return "Masse";
	}

	@Override
	public int getValeur(){
		int res = this.valeurs;
		return res;
	}
}
