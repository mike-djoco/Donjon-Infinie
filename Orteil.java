public class Orteil extends CaseBonus{
    protected String Proprietaire;
    
    public Orteil(){
        this.Proprietaire="Majdi";
    }

    public Orteil(Boolean bave){
        this.Proprietaire="Taha";
    }

    @Override
	public String getLabel(){
		return "Orteil";
	}
}
