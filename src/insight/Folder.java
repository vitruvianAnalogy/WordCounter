package insight;
import java.io.File;
import java.util.*;

public class Folder extends File{
	
	private TreeSet<String> listOfFiles;

	public TreeSet<String> getListOfFiles() {
		return listOfFiles;
	}

	//Constructor
	public Folder(String folderName,String type) {
		super(folderName);		
		listOfFiles = new TreeSet<String>();
		
		//Keep track of files
		if(this.isDirectory()){
			if(type.equalsIgnoreCase("input"))
				this.enumerateList();
			if(type.equalsIgnoreCase("output")){
				listOfFiles.add("wc_result.txt");
				listOfFiles.add("med_result.txt");
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
	
	public void writeFilesInFolder(TreeMap<String,Integer> myTreeMap){
		
	}

}
