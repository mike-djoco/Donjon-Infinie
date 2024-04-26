import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Controleur extends JFrame implements KeyListener, WindowListener{
    private int xHero = 2;
    private int yHero = 2;
    private int point = 0;
	private VuePlateau vuePlateau;
	private VueInventaire vueInv;
	private VueInformation vueInformation;
	private VueSettings vueSettings;
	private VueScore vueScore;
	private String PlanActuel;

    public Controleur(){
        this.setLocation(20, 20);
	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    this.setSize(900, 900);
		this.addKeyListener(this);
		this.setTitle("Donjon Infini v2.0");

		this.setLayout(new GridLayout(1,1));
		this.vuePlateau = new VuePlateau();
		this.vueSettings = new VueSettings();
		this.vueInformation = new VueInformation(vuePlateau, this.yHero * 5 + this.xHero);
		this.vueInv = new VueInventaire();
		this.vueScore = new VueScore();
		this.addWindowListener(this);
		
		this.vueInv.maj(this.vuePlateau, this.yHero*5 + this.xHero);
		
		this.vueSettings.newgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PlanActuel == "Settings") {
					vuePlateau = new VuePlateau();
					xHero=2;
					yHero=2;
					vueInformation.updateVueInformation(vuePlateau, xHero+yHero*5);
					redirection("Plateau");
					setFocusable(true);
				}
			}
		});
		this.vueSettings.last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PlanActuel == "Settings") {
					try{
						FileInputStream fi = new FileInputStream("save_plateau");
						ObjectInputStream save_plateau = new ObjectInputStream(fi);

						FileInputStream fi_position = new FileInputStream("save_position");
						DataInputStream save_position = new DataInputStream(fi_position);
						
						try {
							for (int i = 0; i < vuePlateau.tabVueCase.length; i++) {
								vuePlateau.tabVueCase[i].casee = (Case)save_plateau.readObject();	
								vuePlateau.tabVueCase[i].repaint();
								switch(vuePlateau.tabVueCase[i].casee.getLabel()){
									case "Hero" :
										Heros h_stocker=(Heros)vuePlateau.tabVueCase[i].casee;
										Heros h = new Heros(h_stocker.pv, h_stocker.arme, h_stocker.armures, h_stocker.meteore, h_stocker.Inventaire);
										vuePlateau.tabVueCase[i].casee=h;
										break;
									case "Or" :
										vuePlateau.tabVueCase[i].casee = new Or(vuePlateau.tabVueCase[i].casee.getValeur());
										break;
									case "Potion" :
										vuePlateau.tabVueCase[i].casee = new Potion(vuePlateau.tabVueCase[i].casee.getValeur());
										break;
									case "Arme" :
										vuePlateau.tabVueCase[i].casee = new Arme(vuePlateau.tabVueCase[i].casee.getValeur());
										break;
									case "Hache" :
										vuePlateau.tabVueCase[i].casee = new Hache(vuePlateau.tabVueCase[i].casee.getValeur());
										break;
									case "Arc" :
										vuePlateau.tabVueCase[i].casee = new Arc();
										break;
									case "Masse" :
										vuePlateau.tabVueCase[i].casee = new Masse(vuePlateau.tabVueCase[i].casee.getValeur());
										break;
									case "Armure" :
										vuePlateau.tabVueCase[i].casee = new Armure(vuePlateau.tabVueCase[i].casee.getValeur());
										break;
									case "Meteore" :
										vuePlateau.tabVueCase[i].casee = new Meteore();
										break;
									case "Monstre" :
										vuePlateau.tabVueCase[i].casee = new Monstre(vuePlateau.tabVueCase[i].casee.getPv());
										break;
									case "Archer" :
										vuePlateau.tabVueCase[i].casee = new Archer(vuePlateau.tabVueCase[i].casee.getPv());
										break;
									case "Ogre" :
										vuePlateau.tabVueCase[i].casee = new Ogre(vuePlateau.tabVueCase[i].casee.getPv());
										break;
								}
							}
							xHero = save_position.readInt();
							//System.out.println("Recuperer : "+xHero);
							yHero = save_position.readInt();
							//System.out.println("Recuperer : "+xHero);
						} catch (Exception fermeture) {
							System.out.println("Erreur lecture plat");
						}
			
						try{
							save_plateau.close();
						}catch(IOException e_lecture_plat){
							System.out.println("Erreur fermetture plat");
						}
			
					}catch(IOException ouverture){
						System.out.println("Erreur ouverture lecture plat");
					}
					redirection("Plateau");
					setFocusable(true);
				}
			}
		});

		for (int j = 0; j < this.vueInv.TabInv.length; j++) {
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (PlanActuel == "Inventaire") {
						int height = getHeight();
						int width = getWidth();
						int ncase = 4;
						if ((0 < e.getY()) && (e.getY() < height/3)) {
							if ((0 < e.getX()) && (e.getX() < width/3)) {
								ncase = 0;
							}else if ((width/3 < e.getX()) && (e.getX() < (width/3)*2)) {
								ncase = 1;
							}else if ((width/3)*2 < e.getX()) {
								ncase = 2;
							}
						}else if ((height/3 < e.getY()) && (e.getY() < (height/3)*2)) {
							if ((0 < e.getX()) && (e.getX() < width/3)) {
								ncase = 3;
							}else if ((width/3 < e.getX()) && (e.getX() < (width/3)*2)) {
								ncase = 4;
							}else if ((width/3)*2 < e.getX()) {
								ncase = 5;
							}
						}else if ((height/3)*2 < e.getY()) {
							if ((0 < e.getX()) && (e.getX() < width/3)) {
								ncase = 6;
							}else if ((width/3 < e.getX()) && (e.getX() < (width/3)*2)) {
								ncase = 7;
							}else if ((width/3)*2 < e.getX()) {
								ncase = 8;
							}
						}
						Heros h = (Heros)vuePlateau.tabVueCase[yHero*5+xHero].casee;
						if (h.Inventaire[ncase].getLabel()=="Arme" || h.Inventaire[ncase].getLabel()=="Hache"  || h.Inventaire[ncase].getLabel()=="Arc"  || h.Inventaire[ncase].getLabel()=="Masse") {
							if (h.arme == null) {
								h.arme = (Arme)h.Inventaire[ncase];
								h.Inventaire[ncase] = new Case();
							}else {
								Case temp = h.arme;
								h.arme = (Arme)h.Inventaire[ncase];
								h.Inventaire[ncase] = temp;
							}
						}else if (h.Inventaire[ncase].getLabel()=="Armure") {
							if (h.armures == null) {
								h.armures = (Armure)h.Inventaire[ncase];
							}else {
								Case temp = h.arme;
								h.armures = (Armure)h.Inventaire[ncase];
								h.Inventaire[ncase] = temp;
							}
						}else if (h.Inventaire[ncase].getLabel()=="Potion") {
							if (h.getPv() < h.getMaxPv()) {
								h.setPv(h.getPv()+h.Inventaire[ncase].getValeur());
								if (h.getPv() > h.getMaxPv()) {
									h.setPv(h.getMaxPv());
								}
							}
							
						}
						vueInv.TabInv[ncase].repaint();
						vuePlateau.tabVueCase[yHero*5+xHero].casee = h;
						vueInv.maj(vuePlateau, yHero*5+xHero);
					}else if (PlanActuel == "Plateau") {
						int width = (int)(getWidth());
						int height = (int)(getHeight()*0.9);
						int margin = (int)(getHeight()*0.1);
						int nbCase = 99;
						if ((e.getY() > (margin)) && (e.getY() < (margin+(height/5)))) {
							if (e.getX()< (width/5)) {
								nbCase = 0;
							}else if(e.getX()> (width/5) && e.getX()< ((width/5)*2)){
								nbCase = 1;
							}else if(e.getX()> ((width/5)*2) && e.getX()< ((width/5)*3)){
								nbCase = 2;
							}else if(e.getX()> ((width/5)*3) && e.getX()< ((width/5)*4)){
								nbCase = 3;
							}else if(e.getX()> ((width/5)*4)){
								nbCase = 4;
							}
						}else if ((e.getY() > (margin+(height/5))) && (e.getY() < (margin+((height/5)*2)))) {
							if (e.getX()< (width/5)) {
								nbCase = 5;
							}else if(e.getX()> (width/5) && e.getX()< (width/5)*2){
								nbCase = 6;
							}else if(e.getX()> ((width/5)*2) && e.getX()< (width/5)*3){
								nbCase = 7;
							}else if(e.getX()> ((width/5)*3) && e.getX()< (width/5)*4){
								nbCase = 8;
							}else if(e.getX()> ((width/5)*4)){
								nbCase = 9;
							}
						}else if ((e.getY() > (margin+((height/5)*2))) && (e.getY() < (margin+((height/5)*3)))) {
							if (e.getX()< (width/5)) {
								nbCase = 10;
							}else if(e.getX()> (width/5) && e.getX()< ((width/5)*2)){
								nbCase = 11;
							}else if(e.getX()> ((width/5)*2) && e.getX()< ((width/5)*3)){
								nbCase = 12;
							}else if(e.getX()> ((width/5)*3) && e.getX()< ((width/5)*4)){
								nbCase = 13;
							}else if(e.getX()> ((width/5)*4)){
								nbCase = 14;
							}
						}else if ((e.getY() > (margin+((height/5)*3))) && (e.getY() < (margin+((height/5)*4)))) {
							if (e.getX()< (width/5)) {
								nbCase = 15;
							}else if(e.getX()> (width/5) && e.getX()< ((width/5)*2)){
								nbCase = 16;
							}else if(e.getX()> ((width/5)*2) && e.getX()< ((width/5)*3)){
								nbCase = 17;
							}else if(e.getX()> ((width/5)*3) && e.getX()< ((width/5)*4)){
								nbCase = 18;
							}else if(e.getX()> ((width/5)*4)){
								nbCase = 19;
							}
						}else if (e.getY() > (margin+((height/5)*4))) {
							if (e.getX()< (width/5)) {
								nbCase = 20;
							}else if(e.getX()> (width/5) && e.getX()< ((width/5)*2)){
								nbCase = 21;
							}else if(e.getX()> ((width/5)*2) && e.getX()< ((width/5)*3)){
								nbCase = 22;
							}else if(e.getX()> ((width/5)*3) && e.getX()< ((width/5)*4)){
								nbCase = 23;
							}else if(e.getX()> ((width/5)*4)){
								nbCase = 24;
							}
						}
						if(nbCase == 99){}
						else{
							Heros h = (Heros)vuePlateau.tabVueCase[yHero*5+xHero].casee;
							if (h.arme != null) {
								if(h.arme.getLabel() == "Arc" ){
									if(nbCase>=0 || nbCase<=24){
										if (vuePlateau.tabVueCase[nbCase].casee.getLabel()=="Monstre" || vuePlateau.tabVueCase[nbCase].casee.getLabel()=="Ogre" || vuePlateau.tabVueCase[nbCase].casee.getLabel()=="Archer") {
											vuePlateau.tabVueCase[nbCase].casee = new Or(vuePlateau.tabVueCase[nbCase].casee.getValeur());
											vuePlateau.tabVueCase[nbCase].repaint();
											h.arme = null;
										}
									}else{
										System.out.println("impossible d'utiliser l'arc");
									}
								}
								vuePlateau.tabVueCase[yHero*5+xHero].casee = h;
								vuePlateau.tabVueCase[yHero*5+xHero].repaint();
							}
						}
					}
				}
			});
		}

		this.add(this.vueSettings);
		this.PlanActuel = "Settings";
		this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
		if (this.PlanActuel == "Plateau") {
			switch (e.getKeyCode()) {
				case 37:
					if (this.xHero-1<0) {
					}else{
						this.Rencontre((this.xHero)-1, this.yHero);
					}
				break;
				case 38:
					if (this.yHero-1<0) {
					}else{
						this.Rencontre(this.xHero, (this.yHero)-1);
					}
				break;
				case 39:
					if (this.xHero+1>4) {
					}else{
						this.Rencontre((this.xHero)+1, this.yHero);
					}
				break;
				case 40:
					if (this.yHero+1>4) {
					}else{
						this.Rencontre(this.xHero, (this.yHero)+1);
					}
				break;
				case 69:
				 	Heros h=(Heros)this.vuePlateau.tabVueCase[yHero*5+xHero].casee;
					for (int i = 0; i < h.Inventaire.length; i++) {
						if (h.Inventaire[i].getLabel() == "Meteore") {
							for (int j = 0; j < this.vuePlateau.tabVueCase.length; j++) {
								if(this.vuePlateau.tabVueCase[j].casee.getLabel() == "Monstre" || 
									this.vuePlateau.tabVueCase[j].casee.getLabel() == "Ogre" ||
									this.vuePlateau.tabVueCase[j].casee.getLabel() == "Archer"){
									this.vuePlateau.tabVueCase[j].casee = new Or(this.vuePlateau.tabVueCase[j].casee.getValeur());
									this.vuePlateau.tabVueCase[j].repaint();
								}
							}
							h.Inventaire[i]=new Case();
							break;
						}
					}
				break;
				case 27:
					/* Echap */
					this.redirection("Settings");
					this.setFocusable(true);
				break;
				case 73:
					/* Touche I */
					this.redirection("Inventaire");
					this.setFocusable(true);
				break;
			}
		}else if (this.PlanActuel == "Inventaire"){
			switch (e.getKeyCode()) {
				case 27:
					this.redirection("Plateau");
					this.setFocusable(true);
				break;
			}
		}else if (this.PlanActuel == "Settings"){
			switch (e.getKeyCode()) {
				case 27:
					this.redirection("Plateau");
					this.setFocusable(true);
				break;
			}
		}
		int depart = this.yHero*5 + this.xHero;
		if (this.vuePlateau.tabVueCase[depart].casee.getPv()<1) {
			this.vueScore.points = this.vuePlateau.tabVueCase[depart].casee.getValeur();
			this.redirection("Score");
			this.setFocusable(true);
			this.vueScore.repaint();

			javax.swing.Timer timer = new javax.swing.Timer(5000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Code exécuté après le délai de 5 secondes
					dispose();
				}
			});
			timer.setRepeats(false); // Pour n'exécuter le ActionListener qu'une seule fois
			timer.start();
			
		}
		this.vueInformation.updateVueInformation(this.vuePlateau, depart);
		
    }

	public void repaintComponent(int depart, int destination) {
		this.vuePlateau.tabVueCase[depart].repaint();
		this.vuePlateau.tabVueCase[destination].repaint();
		this.vuePlateau.repaint();
	}

    @Override
    public void keyReleased(KeyEvent e) {}

    private void Rencontre(int xNew, int yNew){
		Heros h= new Heros();
		int depart = this.yHero*5 + this.xHero;
		int destination = yNew*5 + xNew;
		h=(Heros)this.vuePlateau.tabVueCase[depart].casee;

		if(this.vuePlateau.tabVueCase[destination].casee.getLabel() == "Or"){
			this.point += this.vuePlateau.tabVueCase[destination].casee.getValeur();
		}
		if(h.Rencontrer(this.vuePlateau.tabVueCase[destination].casee)){
			this.vuePlateau.tabVueCase[depart].casee=h;
			this.vuePlateau.tabVueCase[destination].casee=this.vuePlateau.tabVueCase[depart].casee;
			this.vuePlateau.tabVueCase[depart].casee=new Case().newRandomCase();
			this.xHero=xNew;this.yHero=yNew;
		}
		if (this.vuePlateau.tabVueCase[destination].casee.getLabel() == "Monstre" && Integer.parseInt(this.vuePlateau.tabVueCase[destination].casee.getLabelPv()) <= 0) {
			this.vuePlateau.tabVueCase[destination].casee = new Or(this.vuePlateau.tabVueCase[destination].casee.getValeur());
		}
		if (this.vuePlateau.tabVueCase[destination].casee.getLabel() == "Ogre" && Integer.parseInt(this.vuePlateau.tabVueCase[destination].casee.getLabelPv()) <= 0) {
			this.vuePlateau.tabVueCase[destination].casee = new Or(this.vuePlateau.tabVueCase[destination].casee.getValeur());
		}
		if (this.vuePlateau.tabVueCase[destination].casee.getLabel() == "Archer" && Integer.parseInt(this.vuePlateau.tabVueCase[destination].casee.getLabelPv()) <= 0) {
			this.vuePlateau.tabVueCase[destination].casee = new Or(this.vuePlateau.tabVueCase[destination].casee.getValeur());
		}

		this.vuePlateau.tabVueCase[this.yHero*5 + this.xHero].casee = h;
		this.repaintComponent(depart, destination);

	}
	
	public void redirection(String PlanA){

		if (this.PlanActuel == "Plateau") {
			this.remove(this.vuePlateau);
			this.remove(this.vueInformation);
		}else if(this.PlanActuel == "Inventaire"){
			this.remove(this.vueInv);
		}else if(this.PlanActuel == "Settings"){
			this.remove(this.vueSettings);
		}else if(this.PlanActuel == "Score"){
			this.remove(this.vueScore);
		}

		this.PlanActuel = PlanA;
		this.setLayout(new GridLayout(1,1));
		if (this.PlanActuel == "Plateau") {
			this.setLayout(new GridBagLayout());
        	GridBagConstraints contraintes = new GridBagConstraints();
			contraintes.gridy = 0;
			contraintes.weightx = 1;
			contraintes.weighty = 1;
			contraintes.fill = GridBagConstraints.BOTH;
			this.add(this.vueInformation, contraintes);

			contraintes.gridx = 0;
			contraintes.gridy = 1;
			contraintes.weightx = 1;
			contraintes.weighty = 9;
			contraintes.fill = GridBagConstraints.BOTH;
			this.add(this.vuePlateau, contraintes);

			this.vuePlateau.addKeyListener(this);
			this.vuePlateau.repaint();
			this.repaint();
			this.setSize(this.getWidth()+1, this.getHeight());
			this.setSize(this.getWidth()-1, this.getHeight());
		}else if(this.PlanActuel == "Inventaire"){
			this.add(this.vueInv);
			this.vueInv.maj(this.vuePlateau, this.yHero*5+this.xHero);
			this.vueInv.repaint();
			this.setSize(this.getWidth()+1, this.getHeight());
			this.setSize(this.getWidth()-1, this.getHeight());
		}else if(this.PlanActuel == "Settings"){
			this.add(this.vueSettings);
		}else if(this.PlanActuel == "Score"){
			this.add(this.vueScore);
			this.vueScore.repaint();
			this.setSize(this.getWidth()+1, this.getHeight());
			this.setSize(this.getWidth()-1, this.getHeight());
		}

		this.repaint();
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			FileOutputStream is = new FileOutputStream("save_score");
			DataOutputStream save_score = new DataOutputStream(is);

			try {
				save_score.writeInt(this.point);
			}catch(IOException e_ecriture){
				System.out.println("Erreur d'ecriture");
			}

			try {
				save_score.close();
			} catch (IOException e_fermeture) {
				System.out.println("Erreur de fermetture");
			}
		}catch(IOException e_ouverture){
			System.out.println("Erreur d'ouverture");
		}

		if(Integer.parseInt(this.vuePlateau.tabVueCase[this.xHero+this.yHero*5].casee.getLabelPv()) > 0){
			try {
				FileOutputStream is = new FileOutputStream("save_plateau");
				ObjectOutputStream save_plateau = new ObjectOutputStream(is);

				FileOutputStream is2 = new FileOutputStream("save_position");
				DataOutputStream save_position = new DataOutputStream(is2);
	
				try {
					for (int i = 0; i < 25; i++) {
						save_plateau.writeObject(vuePlateau.tabVueCase[i].casee);
					}
					save_position.writeInt(xHero);
					//System.out.println("stocker:"+xHero);
					save_position.writeInt(yHero);
					//System.out.println("stocker:"+yHero);
				}catch(IOException e_ecriture_plat){
					System.out.println(e_ecriture_plat);
				}
	
				try {
					save_plateau.close();
					save_position.close();
				} catch (IOException e_fermeture_plat) {
					System.out.println("Erreur de fermetture plat");
				}
			}catch(IOException e_ouverture_plat){
				System.out.println("Erreur d'ouverture plat");
			}
			
		}

		this.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

}
