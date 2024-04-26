import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Ogre extends Monstre {
    
    public Ogre(){
		this.pv=(int)(Math.random()*25)+10;
		this.valeurs=this.pv;
		try {
			this.image = ImageIO.read(new File("img/ogre.png"));
		}catch (IOException e){}
	}

	public Ogre(int val){
		this.pv= val;
		this.valeurs=this.pv;
		try {
			this.image = ImageIO.read(new File("img/ogre.png"));
		}catch (IOException e){}
	}

	@Override
	public String getLabel(){
		return "Ogre";
	}

	@Override
	public int getPv(){
		return this.pv;
	}

	@Override
	public void setPv(int t){
		this.pv=t;
	}

	@Override
	public String getLabelPv(){
		String pvres = Integer.toString(this.pv);
		return pvres;
	}
}
