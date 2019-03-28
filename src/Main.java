import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static List<SJF> sjf = new ArrayList<>();
	public static double alfa;
	
	public static void main (String args []) throws IOException{
		readFile();
	}
	
	public static void readFile() throws IOException{
		FileReader reader = new FileReader("file.txt");
		BufferedReader buffer = new BufferedReader(reader);
		
		String alfaString = buffer.readLine();
		alfa = Double.parseDouble(alfaString.replaceAll(",", "."));
		
		String line = buffer.readLine();
		
		while(line != null){
			String [] regex = line.trim().split("\\s+|\\/");
			SJF process = new SJF();
			
			for(int i=0; i<regex.length; i++){
				if(i==0){
					process.burst = Double.parseDouble(regex[i]);
				}else if(i==1){
					process.runtime = Double.parseDouble(regex[i]);
				}else{
					process.waitingTime = Double.parseDouble(regex[i]);
				}
			}
			line = buffer.readLine();
			sjf.add(process);
		}
		buffer.close();
		readProcess();
	}
	
	public static void readProcess(){
		for(SJF s: sjf){
			if(s.runtime == 0){
				System.out.println("terminou");
			}else{
				double newBurst = alfa*s.runtime + (1-alfa) * s.burst;
				System.out.println(newBurst);
			}
		}
	}
}