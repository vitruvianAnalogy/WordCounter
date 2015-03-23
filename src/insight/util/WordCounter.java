package insight.util;

import java.io.*;
import java.util.*;
import insight.Folder;

public final class WordCounter {
	
	//Stores the number of words per line
	private static ArrayList<Integer> numberOfWords = new ArrayList<Integer>();
	//Setter
	public static void setNumberOfWords(int element) {
		numberOfWords.add(element);
	}

	//Stores the running Median
	private static ArrayList<Float> runningMedian = new ArrayList<Float>();
	//Setter
	public static void setRunningMedian() {

		//Calculating the median
		int length = numberOfWords.size();
		float median = 0.0f;
		if(length%2==0){
			median = numberOfWords.get((length/2)-1) + numberOfWords.get(length/2);
			median = median/2;
		}
		else{
			median = numberOfWords.get(length/2); 
		}
		
		runningMedian.add(median);
	}

	//Stores the Key value pairs with each token as a key and number occurrences as value
	private static TreeMap<String,Integer> dataFrame = new TreeMap<String,Integer>();	
	//Getter
	public static TreeMap<String, Integer> getDataFrame() {
		return dataFrame;
	}
	//Setter
	public static void setDataFrame(ArrayList<String> tokens) {
		setNumberOfWords(tokens.size());
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

	//Tokenizing
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
				ArrayList<String> tokensList= new ArrayList<String>();
				//Fix: Stopping from "" being considered as a token

				for(String token : tokens){
					if(!token.equals("")){
						tokensList.add(token);
					}
				}

				//String[] tokens = line.split("[ 0-9_\\-,;.\\t:?\"]+");
				
				//Setting the key token pairs
				setDataFrame(tokensList);
				
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
		
		//Run through all the input files
		for (String myFileName : myInputFolder.getListOfFiles()){
			System.out.println("\nProcessing " + myFileName);
			preprocessing(inputFolder, myFileName);
			System.out.println(myFileName+" is done");
		}
		
		//Writing output files
		System.out.println("\nAll Files Processed");		
		System.out.println("Now creating output files");
		myOutputFolder.writeRunningMedian(runningMedian);
		myOutputFolder.writeWordCount(dataFrame);

	}	

}
