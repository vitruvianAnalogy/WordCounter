package insight.util;

import java.io.*;
import java.util.*;
import insight.Folder;

public final class WordCounter {
	
	private static ArrayList<Integer> numberOfWords = new ArrayList<Integer>();
	public static void setNumberOfWords(int element) {
		numberOfWords.add(element);
	}

	private static ArrayList<Integer> runningMedian = new ArrayList<Integer>();
	public static void setRunningMedian() {
		int length = numberOfWords.size();
		int median = 0;
		if(length%2==0){
			median = numberOfWords.get((length/2)-1) + numberOfWords.get(length/2);
			median = median/2;
		}
		else{
			median = numberOfWords.get(length/2); 
		}
		runningMedian.add(median);
	}

	private static TreeMap<String,Integer> dataFrame = new TreeMap<String,Integer>();	
	public static TreeMap<String, Integer> getDataFrame() {
		return dataFrame;
	}
	public static void setDataFrame(String[] tokens) {
		setNumberOfWords(tokens.length);
		setRunningMedian();
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
				line = line.replaceAll("[0-9]+","");
				line = line.replaceAll("[_]+","");
				line = line.replaceAll("[-]+","");
				line = line.replaceAll("[']+","");
				String[] tokens = line.split("[ ,;.\\t:?\"]");
				setDataFrame(tokens);
				
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
		String inputFolder = args[0];
		String outputFolder = args[1];		
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
		
		System.out.println("\nAll Files Processed");		
		System.out.println("Now creating output files");
		myOutputFolder.writeRunningMedian(runningMedian);
		myOutputFolder.writeWordCount(dataFrame);

	}	

}
