import java.util.*;


// A class 'Disk' that implements Comparable
public class Disk implements Comparable<Disk> {
	private ListInterface folders = new List();
	private int id;
	private int folderMB = 0;
	private int countFolders = 0;
	public Disk next;
	
	// Constructor
	public Disk(int id){
		this.id = id;
	}
	
	
	public void addFolder(int folder) {
		this.folders.insertAtFront(folder);
		this.folderMB = folderMB + folder;
		this.countFolders++;
	}
	
	// Used to sort disks by their capacity
	public int compareTo(Disk d){
		if ((1000000 - folderMB) > d.getFreeSpace())
			return 1;
		else if ((1000000 - folderMB) < d.getFreeSpace())
			return -1;
		return 0;
	}
	
	// Getter methods for accessing private data
	public int getId(){
		return id;
	}
	
	// return the free space(in MB) of the disk 
	public int getFreeSpace(){
		return 1000000 - folderMB;
	}

	public void printDisk(){
		System.out.println( "id " + id + " " + getFreeSpace() + ": " + folders.toString());
	}
	
}