
public class CarRecord {
	
	public String county;
	public String natCat;
	public String eurCat;
	public String manufacturer;
	public String type;
	public int value;
	public int year;
	
	public CarRecord(){
		
	}
	
	public String toString(){
		return county+"_"+natCat+"_"+manufacturer+"_"+type+"_"+year;
	}
}
