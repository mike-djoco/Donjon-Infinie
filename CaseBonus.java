/**
* Cette classe est 
*
* @author DJOCO & CHOUX-BEAUREGARD (Synergi)
*/
public class CaseBonus extends Case{
	
	public CaseBonus(){
		super();
	}

	@Override
	public String getLabel(){
		return "Case Bonus";
	}

	@Override
	public int getValeur(){
		int res = this.valeurs;
		return res;
	}

	@Override
	public CaseBonus newRandomCase(){
		int n = (int)(Math.random()*100)+1;
		if (n>=1 && n<=5) {
			return new Or();
		}else if (n>=6 && n<=55) {
			int m = (int)(Math.random()*100)+1;
			if (m>=1 && m<=40) {
				return new Arme(); 	
			}else if(m>=41 && m<=70){
				return new Hache();
			}else if(m>=71 && m<=85){
				return new Masse();
			}else if(m>=86 && m<=90){
				return new Meteore();
			}else{
				return new Arc();
			}
		}else if (n>=51 && n<=91) {
			return new Armure(); 
		}else{
			return new Potion();
		}
	}


}