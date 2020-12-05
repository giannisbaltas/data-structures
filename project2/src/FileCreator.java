import java.io.*;

public class FileCreator {
    public static void main(String ar[]) {
        int x=0;
        try {
            File file=new File("data100010.txt");
            FileWriter filewriter=new FileWriter(file);
            BufferedWriter writer=new BufferedWriter(filewriter);

            for(int i=0;i<500;i++) {
                x=(int)(Math.random()*1000000);
                writer.write(" "+x);
            }
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {
            File file1=new File("data100010.txt");
            FileReader filereader=new FileReader(file1);
            BufferedReader reader=new BufferedReader(filereader);
            String y;
            while((y=reader.readLine())!=null) {
                System.out.println(y);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }



    }

}