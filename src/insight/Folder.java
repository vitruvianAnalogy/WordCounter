package insight;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Folder extends File{
	private String folderPath;
	private TreeSet<String> listOfFiles;

	public TreeSet<String> getListOfFiles(){
		return listOfFiles;
	}

	//Constructor
	public Folder(String folderPath,String type) {
		super(folderPath);		
		this.folderPath = folderPath;

		//Keep track of files
		if(this.isDirectory()){
			if(type.equalsIgnoreCase("input")){
				listOfFiles = new TreeSet<String>();
				this.enumerateList();
				}
			}
		}
	
	//Keeps track of files in a folder
	public void enumerateList(){
		if(this.listFiles()!=null){
			for(File fileName : this.listFiles()){
				listOfFiles.add(fileName.getName());
			}
			System.out.println("Files loaded!");
		}
		else
		{
			System.out.println("Folder Empty!");
		}		
			
	}
	
	//Print the list of files in a folder
	public void printListOfFiles(){
		for(String fileName : listOfFiles){
			System.out.println(fileName);			
		}
	}
	
	//Writing the number of counts
	public void writeWordCount(TreeMap<String,Integer> myTreeMap){
		File wordCountFile = new File(folderPath+"\\"+"wc_result.txt");
	    try {
	        FileWriter writer = new FileWriter(wordCountFile);
	        BufferedWriter bufferedWriter = new BufferedWriter(writer);

	        System.out.println("Writing Counts... ");
	        bufferedWriter.write(myTreeMap.toString());
	        bufferedWriter.close();
	        System.out.println("Finished writing Counts");
	    } 
	    catch(IOException ex) {
	    	System.out.println("File writing failed. IO Exception thrown "+ex.getMessage());
	    }
	}
	
	public void writeRunningMedian(ArrayList<Float> runningMedian){
		File runningMedianFile = new File(folderPath+"\\"+"med_result.txt");
	    try {
	        FileWriter writer = new FileWriter(runningMedianFile);
	        BufferedWriter bufferedWriter = new BufferedWriter(writer);

	        System.out.println("Writing Median... ");
	        bufferedWriter.write(runningMedian.toString());
	        bufferedWriter.close();
	        System.out.println("Finished writing Median");
	    } 
	    catch(IOException ex) {
	    	System.out.println("File writing failed. IO Exception thrown "+ex.getMessage());
	    }
	}


}
