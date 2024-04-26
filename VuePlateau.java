import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JComponent;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class VuePlateau extends JComponent{

	protected int vuePoints=10;
	protected Boolean inplay = true;

	protected VueCase []tabVueCase = new VueCase[25];

	@Override
	public void paintComponent(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque()) {
			secondPinceau.setColor(Color.black);
		}
  	}

	public VuePlateau(){
		super();
		this.setLayout(new GridLayout(5,5));

		for(int i=0; i<25; i++){
            if(i==12){
                this.tabVueCase[i]= new VueCase(i, true);
            }else{
                this.tabVueCase[i]= new VueCase(i);
            }
        }

		for(int i=0; i<25; i++){
            this.add(this.tabVueCase[i]);
        }
		
	}

}