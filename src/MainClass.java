
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cars allCars=new Cars();
		//allCars.importCSV("parc-auto-2012.csv");
		allCars.importCSV("parc-auto-2013.csv");
		//allCars.importCSV("parc-auto-2014.csv");
		//allCars.importCSV("parc-auto-2015.csv");
		CarsRDF rdf=new CarsRDF();
		rdf.loadStructure("structure.ttl");
		for (CarRecord c : allCars.cars) {
			rdf.cubeObs(c);
		}
		rdf.writeModel("structure.xml");
	}

}
