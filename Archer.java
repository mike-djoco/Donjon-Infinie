import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Archer extends Monstre {
    
    public Archer(){
		this.pv=(int)(Math.random()*45)+10;
		this.valeurs=this.pv;
		try {
			this.image = ImageIO.read(new File("img/archer.png"));
		}catch (IOException e){}
	}

	public Archer(int val){
		this.pv=val;
		this.valeurs=this.pv;
		try {
			this.image = ImageIO.read(new File("img/archer.png"));
		}catch (IOException e){}
	}

	// test first commit on branch NewMonstre
	@Override
	public String getLabel(){
		return "Archer";
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
