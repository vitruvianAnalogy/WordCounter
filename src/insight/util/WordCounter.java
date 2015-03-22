package insight.util;

import java.io.*;
import java.util.*;


import insight.Folder;

public final class WordCounter {
	
	private static TreeMap<String,Integer> dataFrame = new TreeMap<String,Integer>();
	
	public static TreeMap<String, Integer> getDataFrame() {
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
				line = line.toLowerCase();
				line = line.replace("[0-9'-_]","");
//				line = line.replaceAll("[0-9]","");
//				line = line.replace("-", "");
//				line = line.replace("_", "");
				String[] tokens = line.split("[ ,;.\\t:?\"]");
				setDataFrame(tokens);
				
//				Pattern onlyWords = Pattern.compile("[a-z]+");
//				Matcher match = onlyWords.matcher(line);
//				String[] tokens = match.matches(line);
				
			}
			reader.close();
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
		String inputFolder = "E:\\Java\\Nishant\\InsightCoding\\wc_input";
		String outputFolder = "E:\\Java\\Nishant\\InsightCoding\\wc_output";		
		Folder myInputFolder = new Folder(inputFolder,"input");
		Folder myOutputFolder = new Folder(outputFolder,"output");

		//Print list of files in input folder
		System.out.println("\nFollowing are the list of input files");
		myInputFolder.printListOfFiles();
		
		for (String myFileName : myInputFolder.getListOfFiles()){
			System.out.println("\nProcessing " + myFileName);
			preprocessing(inputFolder, myFileName);
			System.out.println(myFileName+" is done");
		}
		
		System.out.println("\nEvery file ran out");
		
		System.out.println("Now printing creating output folder");
		
	}	

}
