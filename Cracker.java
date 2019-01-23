import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;


public class Cracker {
	public static void crack(String passcodeToFind){
		JFileChooser chooser = new JFileChooser();

		
		String filePath = "";
		
		do{
			System.out.println("Please Selected File Through Dialog");
			
			int happenings = chooser.showOpenDialog(null);
			
			if(happenings == JFileChooser.APPROVE_OPTION){
				filePath = chooser.getSelectedFile().getPath();
			}else if(happenings == JFileChooser.CANCEL_OPTION){
				System.exit(0);
			}else{
				filePath = "";
			}
		}while(filePath == "");
		
		try{
			Scanner scanner = new Scanner(new File(filePath));
			Timer timer = new Timer();
			timer.start();
			
			for(long i = 0; scanner.hasNext(); i++){				
				if(scanner.next().equals(passcodeToFind)){
					timer.stop();
					System.out.println("Passcode found. Passcode: " + passcodeToFind + " Num tries: " + i + " Time: " + timer.toString());
					break;
				}
				
				if(i % 250000 == 0){
					System.out.println("Tries: " + i + " Elapsed Time: " + timer.toString());
				}
			}
			
			scanner.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
