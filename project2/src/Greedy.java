import java.util.*;
import java.io.*; 

public class Greedy {
	public static void main(String[] args){
		int count = 0;
		int[] data = readFiles(args[0]);
		MaxPQInterface pq = new MaxPQ(new IntegerComparator());

		for (int i = 0;i<data.length;i++){
			if (data[i]>=0 && data[i]<=1000000){
				count += data[i];
			}else {
				System.out.println("Folder size out of bounds...");
				System.exit(0);
			}
			
		}
		double a = count/1000000.0;
		System.out.println("\nSum of all folders : " + a + " TB " );
		Disk head = new Disk(0); //dhmiourgoume ton prwto disko kai ton vazoume mesa stin pq
		Disk temp=head;
		pq.insert(head.getFreeSpace()); 
		int i = 0;
		int max = 0;
		while(i < data.length) {
			max = Integer.parseInt(pq.peek().toString()); //vazoume ton megisto elefthero xwro
			temp = maxDiskId(max, head); //vriskoume ton disko me ton megisto elefthero xwro
			if (data[i] <= temp.getFreeSpace()){
				temp.addFolder(data[i]);
				pq.getMax();
				pq.insert(temp.getFreeSpace());
				i++;
			} else {
				temp = Insert(head, pq.getSize());
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
		
		// pio apodotikos tropos na to kanoume xwris tin compareTO() 
		/* for (int h = 0; h < p.length; h++) {
			max = Integer.parseInt(pq.getMax().toString());
			temp = maxDiskId(max, head);
			p[h] = temp;
		}
		for (int l = 0; l < p.length; l++){
			p[l].printDisk();
		} 
		*/
		
		//akoma pio apodotikos tropos na emfanisoume tous diskous xwris tin compareTO()
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
	
	
}

