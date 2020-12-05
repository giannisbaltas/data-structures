import java.util.*;
import java.io.*; 

public class Sort {
	public static void main(String[] args){
		int count = 0;
		int[] arr = readFiles(args[0]);
		quickSort(arr, 0, arr.length-1);
		arr = reverse(arr, arr.length); 
		MaxPQInterface pq = new MaxPQ(new IntegerComparator());

		for (int i = 0;i<arr.length;i++){
			if (arr[i]>=0 && arr[i]<=1000000){
				count += arr[i];
			}else {
				System.out.println("Folder size out of bounds...");
				System.exit(0);
			}
			
		}
		double a = count/1000000.0;
		System.out.println("\nSum of all folders : " + a + " TB " );
		Disk head = new Disk(0);
		Disk temp=head;
		pq.insert(head.getFreeSpace());
		int i = 0;
		int max = 0;
		while(i < arr.length) {
			max = Integer.parseInt(pq.peek().toString()); //vazoume ton megisto elefthero xwro
			temp = maxDiskId(max, head); //vriskoume ton disko me ton megisto elefthero xwro
			if (arr[i] <= temp.getFreeSpace()){
				temp.addFolder(arr[i]);
				pq.getMax();
				pq.insert(temp.getFreeSpace());
				i++;
			} else {
				temp = Insert(head,pq.getSize());
				pq.insert(temp.getFreeSpace());
				
			}
			
		}
		System.out.println("Total number of disks used = " + pq.getSize());
		
		Disk tmp2 = head;
		Disk p[] = new Disk[pq.getSize()]; //vazoume ston pinaka tous disks gia na tous taksinomisoume
		int j = 0;
		while (tmp2.next != null) {
			p[j] = tmp2;
			j++;
			tmp2 = tmp2.next;
		}
		p[j] = tmp2;
		j++;
		
		Disk tmp3;
		for (int k = 0; k < p.length-1; k++){
			if (p[k].compareTo(p[k+1]) == -1) {
				tmp3 = p[k];
				p[k] = p[k+1];
				p[k+1] = tmp3;
			} 
		}
		for (int l = 0; l < p.length; l++){
			p[l].printDisk();
		}
		
		//pio apodotikos tropos na emfanisoume tous diskous
		
		/*while (pq.getSize() != 0) {
			max = Integer.parseInt(pq.getMax().toString());
			temp = maxDiskId(max, head);
			temp.printDisk();
		} */
		

	}
	
	public static Disk Insert(Disk head,int id) {
        Disk disk = new Disk(id);
        while(head.next != null){
			head = head.next;
        }
		head.next = disk;
		disk.next=null;
		return disk;
		
    }
	public static int[] readFiles(String text){
		try{
			File f = new File(text);
			Scanner s = new Scanner(f);
			int count = 0;
			int ctr = 0; 
			while(s.hasNextInt()){
				ctr++;
				s.nextInt();
			}
			int[] arr = new int[ctr];
			Scanner s1 = new Scanner(f);
			for(int i = 0;i<arr.length;i++){
				arr[i] = s1.nextInt(); // First folder when i=0;
				//count += arr[i];
			}
			//System.out.println("The sum of all folders is : " + count + " MB " );
			//for(int i = 0;i<arr.length;i++) System.out.println(arr[i]);
			return arr;
		}catch(Exception e){
			return null;
		}
    
	}
	
	
	public static Disk maxDiskId(int max, Disk head){ //epistrefei to id tou diskou me to megalutero keno xwro
		
		Disk temp=head;
		while(temp.next != null){
			if(temp.getFreeSpace() == max) {
				return temp;
			}
			temp = temp.next;
		}
		
		return temp;
	}
	
	


	public static void quickSort(int arr[], int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end);
	 
			quickSort(arr, begin, partitionIndex-1);
			quickSort(arr, partitionIndex+1, end);
		}
	}
 
    private static int partition(int arr[], int begin, int end) {
		int pivot = arr[end];
		int i = (begin-1);
	 
		for (int j = begin; j < end; j++) {
			if (arr[j] <= pivot) {
				i++;
	 
				int swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
			}
		}
	 
		int swapTemp = arr[i+1];
		arr[i+1] = arr[end];
		arr[end] = swapTemp;
	 
		return i+1;
	}
	
	// function that reverse the sorted array to decreasing array
	public static int[] reverse(int a[], int n) { 
        int[] b = new int[n]; 
        int j = n; 
        for (int i = 0; i < n; i++) { 
            b[j - 1] = a[i]; 
            j = j - 1; 
        }
		return b;
	}
	
}
