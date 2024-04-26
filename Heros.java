import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class Heros extends Case{
	public transient BufferedImage varmure;
	public transient BufferedImage varme;
	public transient BufferedImage vaxe;
	public transient BufferedImage vmasse;
	public transient BufferedImage varc;

	public int pv;
	public Arme arme;
	public Armure armures;
	public int pvmax;
	public Meteore meteore;
	public Case Inventaire[] = new Case[9];


	public Heros(){
		this.pv=100;
		this.arme = null;
		this.armures = null;
		this.pvmax = this.pv;
		this.meteore = null;
		for (int i = 0; i < Inventaire.length; i++) {
			Inventaire[i] = new Case();
		}
		try {
			this.image = ImageIO.read(new File("img/player.png"));
			this.varme = ImageIO.read(new File("img/playersword.png"));
			this.vaxe = ImageIO.read(new File("img/playeraxe.png"));
			this.vmasse = ImageIO.read(new File("img/playermasse.png"));
			this.varc = ImageIO.read(new File("img/playerarc.png"));
			this.varmure = ImageIO.read(new File("img/playerarmor.png"));
		}catch (IOException e){}
	}

	public Heros(int pv, Arme arme, Armure armure, Meteore meteore, Case[] inventaire){
		this.pv=pv;
		this.arme = arme;
		this.armures = armure;
		this.pvmax = 100;
		this.meteore = meteore;
		for (int i = 0; i < Inventaire.length; i++) {
			this.Inventaire[i] = inventaire[i];
			switch (inventaire[i].getLabel()) {
				case "Or" :
					this.Inventaire[i] = new Or(inventaire[i].getValeur());
					break;
				case "Potion" :
					this.Inventaire[i] = new Potion(inventaire[i].getValeur());
					break;
				case "Arme" :
					this.Inventaire[i] = new Arme(inventaire[i].getValeur());
					break;
				case "Hache" :
					this.Inventaire[i] = new Hache(inventaire[i].getValeur());
					break;
				case "Arc" :
					this.Inventaire[i] = new Arc();
					break;
				case "Masse" :
					this.Inventaire[i] = new Masse(inventaire[i].getValeur());
					break;
				case "Armure" :
					this.Inventaire[i] = new Armure(inventaire[i].getValeur());
					break;
				case "Meteore" :
					this.Inventaire[i] = new Meteore();
					break;
			}
		}
		try {
			this.image = ImageIO.read(new File("img/player.png"));
			this.varme = ImageIO.read(new File("img/playersword.png"));
			this.vaxe = ImageIO.read(new File("img/playeraxe.png"));
			this.vmasse = ImageIO.read(new File("img/playermasse.png"));
			this.varc = ImageIO.read(new File("img/playerarc.png"));
			this.varmure = ImageIO.read(new File("img/playerarmor.png"));
		}catch (IOException e){}
	}

	@Override
	public String getLabel(){
		return "Hero";
	}

	@Override
	public String getLabelPv(){
		String pvres = Integer.toString(this.pv);
		return pvres;
	}

	@Override
	public int getPv(){
		return this.pv;
	}

	@Override
	public int getMaxPv(){
		return this.pvmax;
	}

	@Override
	public int getValeur(){
		return this.valeurs;
	}

	public boolean Rencontrer(Case rencontrer){
		boolean res=true;

		if(rencontrer.getLabel()=="Or"){
			this.valeurs=this.valeurs+rencontrer.getValeur();
			return res=true;
		}else if(rencontrer.getLabel()=="Potion"){
			if (this.pv>=this.pvmax) {
				int nbOr = 0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Or"){
						nbOr++;
					}
				}
				if (nbOr <=3) {
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel() == "Case"){
							this.Inventaire[i]=rencontrer;
							return true;
						}
					}
				}
			}

			this.pv+=rencontrer.getValeur();
			
			if(this.pv>this.pvmax){
				this.pv=this.pvmax;
			}
			return res=true;
		}else if(rencontrer.getLabel()=="Arme"){
			if(this.arme==null){
				int nbArme=0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
						nbArme++;
					}
				}
				if(nbArme==0){
					this.arme=(Arme)rencontrer;
					return true;
				}else{
					int valmax=rencontrer.valeurs;
					int nCase=10;
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
							if(valmax < Inventaire[i].valeurs){
								nCase = i;
								valmax = Inventaire[i].valeurs;
							}
						}
					}
					if(nCase == 10){
						this.arme = (Arme)rencontrer;
					}else{
						this.arme = (Arme)Inventaire[nCase];
						Inventaire[nCase] = rencontrer;
					}
				}
				
			}else if(this.arme.valeurs<rencontrer.valeurs){
				int nbArme = 0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
						nbArme++;
					}
				}
				if (nbArme <= 2) {
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel() == "Case"){
							this.Inventaire[i]=this.arme;
							this.arme=(Arme)rencontrer;
							return true;
						}
					}
				}
			}else {
				int nbArme = 0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
						nbArme++;
					}
				}
				if (nbArme <= 2) {
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel() == "Case"){
							this.Inventaire[i]=(Arme)rencontrer;
							return res=true;
						}
					}
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel()=="Arme"){
							Arme tempInv = (Arme)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}

						}else if(this.Inventaire[i].getLabel()=="Hache"){
							Hache tempInv = (Hache)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}else if(this.Inventaire[i].getLabel()=="Arc"){
							Arc tempInv = (Arc)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}else if(this.Inventaire[i].getLabel()=="Masse"){
							Masse tempInv = (Masse)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}
					}
				}
			}
			return res=true;
		}else if(rencontrer.getLabel()=="Hache"){
			if(this.arme==null){
				int nbArme=0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
						nbArme++;
					}
				}
				if(nbArme==0){
					this.arme=(Arme)rencontrer;
					return true;
				}else{
					int valmax=rencontrer.valeurs;
					int nCase=10;
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
							if(valmax < Inventaire[i].valeurs){
								nCase = i;
								valmax = Inventaire[i].valeurs;
							}
						}
					}
					if(nCase == 10){
						this.arme = (Arme)rencontrer;
					}else{
						this.arme = (Arme)Inventaire[nCase];
						Inventaire[nCase] = rencontrer;
					}
				}
				
			}else if(this.arme.valeurs<rencontrer.valeurs){
				int nbArme = 0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
						nbArme++;
					}
				}
				if (nbArme <= 2) {
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel() == "Case"){
							this.Inventaire[i]=this.arme;
							this.arme=(Arme)rencontrer;
							return true;
						}
					}
				}
			}else {
				int nbArme = 0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
						nbArme++;
					}
				}
				if (nbArme <= 2) {
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel() == "Case"){
							this.Inventaire[i]=(Arme)rencontrer;
							return res=true;
						}
					}
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel()=="Arme"){
							Arme tempInv = (Arme)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}

						}else if(this.Inventaire[i].getLabel()=="Hache"){
							Hache tempInv = (Hache)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}else if(this.Inventaire[i].getLabel()=="Arc"){
							Arc tempInv = (Arc)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}else if(this.Inventaire[i].getLabel()=="Masse"){
							Masse tempInv = (Masse)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}
					}
				}
			}
			return res=true;
		}else if(rencontrer.getLabel()=="Masse"){
			if(this.arme==null){
				int nbArme=0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
						nbArme++;
					}
				}
				if(nbArme==0){
					this.arme=(Arme)rencontrer;
					return true;
				}else{
					int valmax=rencontrer.valeurs;
					int nCase=10;
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
							if(valmax < Inventaire[i].valeurs){
								nCase = i;
								valmax = Inventaire[i].valeurs;
							}
						}
					}
					if(nCase == 10){
						this.arme = (Arme)rencontrer;
					}else{
						this.arme = (Arme)Inventaire[nCase];
						Inventaire[nCase] = rencontrer;
					}
				}
				
			}else if(this.arme.valeurs<rencontrer.valeurs){
				int nbArme = 0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
						nbArme++;
					}
				}
				if (nbArme <= 2) {
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel() == "Case"){
							this.Inventaire[i]=this.arme;
							this.arme=(Arme)rencontrer;
							return true;
						}
					}
				}
			}else {
				int nbArme = 0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
						nbArme++;
					}
				}
				if (nbArme <= 2) {
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel() == "Case"){
							this.Inventaire[i]=(Arme)rencontrer;
							return res=true;
						}
					}
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel()=="Arme"){
							Arme tempInv = (Arme)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}

						}else if(this.Inventaire[i].getLabel()=="Hache"){
							Hache tempInv = (Hache)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}else if(this.Inventaire[i].getLabel()=="Arc"){
							Arc tempInv = (Arc)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}else if(this.Inventaire[i].getLabel()=="Masse"){
							Masse tempInv = (Masse)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}
					}
				}
			}
			return res=true;
		}else if(rencontrer.getLabel()=="Arc"){
			if(this.arme==null){
				int nbArme=0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
						nbArme++;
					}
				}
				if(nbArme==0){
					this.arme=(Arme)rencontrer;
					return true;
				}else{
					int valmax=rencontrer.valeurs;
					int nCase=10;
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
							if(valmax < Inventaire[i].valeurs){
								nCase = i;
								valmax = Inventaire[i].valeurs;
							}
						}
					}
					if(nCase == 10){
						this.arme = (Arme)rencontrer;
					}else{
						this.arme = (Arme)Inventaire[nCase];
						Inventaire[nCase] = rencontrer;
					}
				}
				
			}else if(this.arme.valeurs<rencontrer.valeurs){
				int nbArme = 0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
						nbArme++;
					}
				}
				if (nbArme <= 2) {
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel() == "Case"){
							this.Inventaire[i]=this.arme;
							this.arme=(Arme)rencontrer;
							return true;
						}
					}
				}
			}else {
				int nbArme = 0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel()=="Arme" || this.Inventaire[i].getLabel()=="Hache"  || this.Inventaire[i].getLabel()=="Arc"  || this.Inventaire[i].getLabel()=="Masse"){
						nbArme++;
					}
				}
				if (nbArme <= 2) {
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel() == "Case"){
							this.Inventaire[i]=(Arme)rencontrer;
							return res=true;
						}
					}
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel()=="Arme"){
							Arme tempInv = (Arme)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}

						}else if(this.Inventaire[i].getLabel()=="Hache"){
							Hache tempInv = (Hache)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}else if(this.Inventaire[i].getLabel()=="Arc"){
							Arc tempInv = (Arc)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}else if(this.Inventaire[i].getLabel()=="Masse"){
							Masse tempInv = (Masse)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}
					}
				}
			}
			return res=true;
		}else if(rencontrer.getLabel()=="Armure"){
			if(this.armures==null){
				this.armures=(Armure)rencontrer;
			}else if(this.armures.valeurs<rencontrer.valeurs){
				int nbArmure = 0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel() == "Armure"){
						nbArmure++;
					}
				}
				if (nbArmure <= 2) {
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel() == "Case"){
							this.Inventaire[i]=this.armures;
							this.armures=(Armure)rencontrer;
							return true;
						}
					}
				}
				this.armures=(Armure)rencontrer;
			}else {
				int nbArmure = 0;
				for (int i = 0; i < this.Inventaire.length; i++) {
					if(this.Inventaire[i].getLabel() == "Armure"){
						nbArmure++;
					}
				}
				if (nbArmure <= 1) {
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel() == "Case"){
							this.Inventaire[i]=(Armure)rencontrer;
							return true;
						}
					}
					for (int i = 0; i < this.Inventaire.length; i++) {
						if(this.Inventaire[i].getLabel()=="Armure"){
							Armure tempInv = (Armure)this.Inventaire[i];
							if(tempInv.getValeur() < rencontrer.valeurs){
								this.Inventaire[i] = rencontrer;
							}
						}
					}
				}
			}
			return res=true;
		}else if(rencontrer.getLabel()=="Monstre"){
			if(this.arme==null){
				if (this.armures!=null) {
					int dif=this.armures.valeurs - Integer.parseInt(rencontrer.getLabelPv());
					if (dif <= 0) {
						this.pv -= dif;
						this.armures = null;
					}else{
						this.armures.valeurs = dif;
					}
				}else {
					this.pv-=Integer.parseInt(rencontrer.getLabelPv());
				}
				
				return res=true;
			}else{

				if (this.arme.valeurs == 999) {
					this.arme.valeurs = this.arme.valeurs - (this.arme.valeurs - Integer.parseInt(rencontrer.getLabelPv()));
					rencontrer.setPv(rencontrer.getPv()-this.arme.valeurs);
					this.arme = null;
				}else if (this.arme.valeurs > Integer.parseInt(rencontrer.getLabelPv())) {
					this.arme.valeurs = this.arme.valeurs - (this.arme.valeurs - Integer.parseInt(rencontrer.getLabelPv()));
					rencontrer.setPv(rencontrer.getPv()-this.arme.valeurs);
				}else {
					rencontrer.setPv(rencontrer.getPv()-this.arme.valeurs);
					this.arme.valeurs = 0;
					this.arme = null;
				}

				return res=false;
			}
		}else if(rencontrer.getLabel()=="Ogre"){
			if(this.arme==null){
				if (this.armures!=null) {
					int dif=this.armures.valeurs - Integer.parseInt(rencontrer.getLabelPv());
					if (dif <= 0) {
						this.pv -= dif;
						this.armures = null;
					}else{
						this.armures.valeurs = dif;
					}
				}else {
					this.pv-=Integer.parseInt(rencontrer.getLabelPv());
				}
				
				return res=true;
			}else{

				if (this.arme.valeurs == 999) {
					this.arme.valeurs = this.arme.valeurs - (this.arme.valeurs - Integer.parseInt(rencontrer.getLabelPv()));
					rencontrer.setPv(rencontrer.getPv()-this.arme.valeurs);
					this.arme = null;
				}else if (this.arme.valeurs > Integer.parseInt(rencontrer.getLabelPv())) {
					this.arme.valeurs = this.arme.valeurs - (this.arme.valeurs - Integer.parseInt(rencontrer.getLabelPv()));
					rencontrer.setPv(rencontrer.getPv()-this.arme.valeurs);
				}else {
					rencontrer.setPv(rencontrer.getPv()-this.arme.valeurs);
					this.arme.valeurs = 0;
					this.arme = null;
				}

				return res=false;
			}
		}else if(rencontrer.getLabel()=="Archer"){
			if(this.arme==null){
				if (this.armures!=null) {
					int dif=this.armures.valeurs - Integer.parseInt(rencontrer.getLabelPv());
					if (dif <= 0) {
						this.pv -= dif;
						this.armures = null;
					}else{
						this.armures.valeurs = dif;
					}
				}else {
					this.pv-=Integer.parseInt(rencontrer.getLabelPv());
				}
				
				return res=true;
			}else{

				if (this.arme.valeurs == 999) {
					this.arme.valeurs = this.arme.valeurs - (this.arme.valeurs - Integer.parseInt(rencontrer.getLabelPv()));
					rencontrer.setPv(rencontrer.getPv()-this.arme.valeurs);
					this.arme = null;
				}else if (this.arme.valeurs > Integer.parseInt(rencontrer.getLabelPv())) {
					this.arme.valeurs = this.arme.valeurs - (this.arme.valeurs - Integer.parseInt(rencontrer.getLabelPv()));
					rencontrer.setPv(rencontrer.getPv()-this.arme.valeurs);
				}else {
					rencontrer.setPv(rencontrer.getPv()-this.arme.valeurs);
					this.arme.valeurs = 0;
					this.arme = null;
				}

				return res=false;
			}
		}else if(rencontrer.getLabel()=="Meteore"){
			int nbMeteore = 0;
			for (int i = 0; i < this.Inventaire.length; i++) {
				if(this.Inventaire[i].getLabel() == "Meteore"){
					nbMeteore++;
				}
			}
			if(nbMeteore <= 1){
				for (int j = 0; j < this.Inventaire.length; j++) {
					if (this.Inventaire[j].getLabel()=="Case") {
						this.Inventaire[j] = rencontrer;
						break;
					}
				}
			}
		}
		
		return res;
	}
}