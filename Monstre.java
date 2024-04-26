import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Monstre extends Case{
	public int pv;

	public Monstre(){
		this.pv=(int)(Math.random()*10)+15;
		this.valeurs=this.pv;
		try {
			this.image = ImageIO.read(new File("img/monster.png"));
		}catch (IOException e){}
	}

	public Monstre(int val){
		this.pv=val;
		this.valeurs=this.pv;
		try {
			this.image = ImageIO.read(new File("img/monster.png"));
		}catch (IOException e){}
	}

	@Override
	public String getLabel(){
		return "Monstre";
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