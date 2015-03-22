package insight.util;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Pattern;

import insight.Folder;

public final class WordCounter {
	
	private static HashMap<String,Integer> dataFrame = new HashMap<String, Integer>();
	
	public static HashMap<String, Integer> getDataFrame() {
		return dataFrame;
	}

	public static void setDataFrame(String[] tokens) {
		for(String token : tokens){
			if(dataFrame.containsKey(token)){
				dataFrame.put(token, dataFrame.get(token) + 1 );
			}
			else
			{
				dataFrame.put(token, 1);
			}
		}
	}

	public static void preprocessing(String inputFolder, String inputFile){
		try{
			FileInputStream stream = new FileInputStream(inputFolder + "\\" + inputFile);
			DataInputStream dataStream = new DataInputStream(stream);
			BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));
			
			String line;
			while((line = reader.readLine())!=null){
				String[] tokens = line.split(Pattern.quote(" ,;.!@#$%^&*()\t:/?<>"));
				setDataFrame(tokens);
			}
		}
		catch(FileNotFoundException ex){
			System.out.println(inputFile + " doesn't exist in " + inputFolder);
		}
		catch(IOException ex){
			System.out.println("Input Stream failed");
		}
		
		
	}
	
	public static void main(String[] args){
		
		//Make objects for input and output folder
		String inputFolder = "C:\\Users\\Nishant\\Pictures\\Animals";
		String outputFolder = "C:\\Users\\Nishant\\Pictures\\AnimalsOutput";		
		Folder myInputFolder = new Folder(inputFolder,"input");
		Folder myOutputFolder = new Folder(outputFolder,"output");

		//Print list of files in input folder
		myInputFolder.printListOfFiles();
		
		for (String myFileName : myInputFolder.getListOfFiles()){
			preprocessing(inputFolder, myFileName);
		}
		
	}	

}
