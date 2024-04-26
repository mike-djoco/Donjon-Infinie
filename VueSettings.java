import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class VueSettings extends JComponent{
	protected JButton last;
	protected JButton newgame;
	protected JLabel tittle;
	protected JLabel info;

    @Override
	public void paintComponent(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque()) {
			secondPinceau.setColor(Color.black);
		}
        secondPinceau.setColor(new Color(76, 76, 76));
		secondPinceau.fillRect(0, 0, this.getWidth()+1, this.getHeight()+1);
  	}

    public VueSettings(){
		super();
		this.last = new JButton("Reprendre La Partie");
		this.newgame = new JButton("Nouvelle Partie");

		this.setLayout(new GridBagLayout());
		GridBagConstraints contraintes = new GridBagConstraints();

		this.tittle = new JLabel("Donjon Infini v2.0");
		Font f = new Font("Poppins", Font.BOLD, 60);
		this.tittle.setFont(f);
		this.tittle.setForeground(new Color(217, 217, 217));
		contraintes.gridx = 1;
		contraintes.gridy = 3;
		contraintes.weightx = 2;
		contraintes.weighty = 1;
		contraintes.fill = GridBagConstraints.CENTER;
		this.add(this.tittle, contraintes);

		contraintes.gridx = 1;
		contraintes.gridy = 5;
		contraintes.weightx = 2;
		contraintes.weighty = 1;
		contraintes.fill = GridBagConstraints.CENTER;
		this.add(this.last, contraintes);

		contraintes.gridx = 1;
		contraintes.gridy = 6;
		contraintes.weightx = 2;
		contraintes.weighty = 1;
		contraintes.fill = GridBagConstraints.CENTER;
		this.add(this.newgame, contraintes);

		this.info = new JLabel("Creer Par Terence et Bourama-Mike");
		Font g = new Font("Poppins", Font.BOLD, 20);
		this.info.setFont(g);
		this.info.setForeground(new Color(217, 217, 217));
		contraintes.gridx = 1;
		contraintes.gridy = 10;
		contraintes.weightx = 2;
		contraintes.weighty = 1;
		contraintes.fill = GridBagConstraints.CENTER;
		this.add(this.info, contraintes);

		this.repaint();
	}
}
