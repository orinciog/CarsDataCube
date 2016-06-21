import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Vector;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.VCARD;

public class CarsRDF {
	
	Model model;
	String prefix="http://opendata.cs.pub.ro/resource/";
	Resource dataset;
	HashMap<String,Resource> dims;
	
	public CarsRDF(){
		model = ModelFactory.createDefaultModel();
		dims  = new HashMap<String,Resource>();
	}
	
	public void loadStructure(String ttlFile){
		 InputStream in = FileManager.get().open( ttlFile );
		 model.read(in, prefix, "TTL" );
		 
		 this.dataset=model.getResource("eg-rc:dataset");
		 dims.put("dimPeriod", model.getResource("eg-rc:dimPeriod"));
		 dims.put("dimRegion", model.getResource("eg-rc:dimRegion"));
		 dims.put("dimCarType", model.getResource("eg-rc:dimCarType"));
		 dims.put("dimCarCateg", model.getResource("eg-rc:dimCarCateg"));
	}
	
	public void cubeObs(CarRecord c){
		
		Map pr=model.getNsPrefixMap();
		Property ds=model.createProperty("qb:dataSet");
		Property dp=model.createProperty("eg-rc:dimPeriod");
		Property dr=model.createProperty("eg-rc:dimRegion");
		Property dct=model.createProperty("eg-rc:dimCarType");
		Property dcc=model.createProperty("eg-rc:dimCarCateg");
		model.createResource("eg-rc:"+c.toString())
			 .addProperty(ds, this.dataset);
			 
/*
 * eg:o51 a qb:Observation;
    qb:dataSet  eg:dataset-le3 ;
    eg-rc:refArea                 ex-geo:newport_00pr ;                  
    eg-rc:lifeExpectancy          77.0 ;
    .		
 */
		
	}
	
	public void writeModel(String ttlFile){
		try {
			FileOutputStream fs = new FileOutputStream(new File(ttlFile));
			model.write(fs,"RDF/XML");
		  	fs.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	
	}
	
/*	
	public Model transform()
	{
		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("opendata", prefix );
		model.setNsPrefix("geo","http://www.w3.org/2003/01/geo/wgs84_pos#");
		model.setNsPrefix("vcard", "http://www.w3.org/2001/vcard-rdf/3.0#");
		
		Property latProperty = model.createProperty("http://www.w3.org/2003/01/geo/wgs84_pos#lat");
		Property lonProperty = model.createProperty("http://www.w3.org/2003/01/geo/wgs84_pos#long");
		Property tipProperty = model.createProperty(Config.PROPERTY_ROOT+"tip_spital");
		Property adrProperty = model.createProperty(Config.PROPERTY_ROOT+"adresa_institutie");
		Resource hospitalDbpedia = model.createResource("http://dbpedia.org/ontology/Hospital");
		
		Resource resHospital = model.createResource(prefix+hospital.getTitle())
				.addLiteral(latProperty, hospital.getGeoInfo().getLatitude())
				.addLiteral(lonProperty, hospital.getGeoInfo().getLongitude()) 
				.addProperty(RDF.type, hospitalDbpedia)
				.addProperty(RDFS.label, hospital.getName())
				.addProperty(tipProperty, hospital.getCategory().getCategory())
				.addProperty(adrProperty, hospital.getGeoInfo().getFormattedAddress())
                .addProperty(VCARD.Locality, hospital.getTown())
                .addProperty(VCARD.Country, hospital.getCountry())
                .addProperty(VCARD.Region, hospital.getCounty());
		return model;
	}
*/
}
