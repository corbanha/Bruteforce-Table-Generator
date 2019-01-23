import java.io.FileNotFoundException;

import javax.swing.JFileChooser;


public class KeyTableGeneratorMain {

	public static void main(String[] args) {
		
		//Cracker.crack("4568");
		
		
		JFileChooser chooser = new JFileChooser();
		
		String filePath = "";
		
		do{
			System.out.println("Please Selected File Through Dialog");
			
			int happenings = chooser.showSaveDialog(null);
			
			if(happenings == JFileChooser.APPROVE_OPTION){
				filePath = chooser.getSelectedFile().getPath();
			}else if(happenings == JFileChooser.CANCEL_OPTION){
				System.exit(0);
			}else{
				filePath = "";
			}
		}while(filePath == "");
		
		try {
			//Generator.generateFile(filePath, Generator.LETTERS, 1, 6);
			Generator.smartGenerateFiles(filePath, "C:\\CompleteListOfEnglishWords.txt", 1, 2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
