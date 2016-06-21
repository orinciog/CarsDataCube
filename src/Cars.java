import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Cars {
	public Vector<CarRecord> cars;
	
	public Cars(){
		cars=new Vector<CarRecord>();
	}
	
	public void importCSV(String csvFile){
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String[] dataFile = csvFile.split("-");
		int year=Integer.parseInt(dataFile[2].substring(0, 4));
		try {
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] data = line.split("\",\"");
				for (int i=0;i<data.length;i++){
					data[i]=data[i].replace("\"", "");
				}
				CarRecord car=new CarRecord();
				car.county=data[0];
				car.natCat=data[1];
				car.eurCat=data[2];
				car.manufacturer=data[3];
				car.type=data[4];
				if (car.type.equals("")) car.type="Other";
				try{
				car.value=Integer.parseInt(data[5]);
				cars.addElement(car);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
