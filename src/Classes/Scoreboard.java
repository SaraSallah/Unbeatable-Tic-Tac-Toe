package Classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Scoreboard 
{
    
    public static void createPlayer(String username) throws IOException
    {
        try
        {
            File parentDir = new File("C:\\Scoreboard\\");
            parentDir.mkdir();
            String filename=username+".txt";
            File myfile=new File(parentDir,filename);
            FileWriter f=new FileWriter(myfile,false);
            f.write("0");
            f.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static int getPlayerScore(String username) throws FileNotFoundException, IOException
    {
        int score;
        
        File fr = new File("C:\\Scoreboard\\"+username+".txt");
        if(!fr.exists())
        {
            createPlayer(username);
            score=0;
        }
        else
        {
            Scanner sc=new Scanner(fr);
            String s=sc.nextLine();
            score=Integer.parseInt(s);
        }
        return score;
    }
    
    public static void updateScore(String username,int score) throws IOException
    {
        String sc=Integer.toString(score);
        File fold = new File(username+".txt");
        fold.delete();
        File parentDir = new File("C:\\Scoreboard\\");
        parentDir.mkdir();
        String filename=username+".txt";
        File myfile=new File(parentDir,filename);
        FileWriter w=new FileWriter(myfile,false);
        w.write(sc);
        w.close();
    }
    
    public static ArrayList<String> updateTable() throws FileNotFoundException, IOException
    {
        ArrayList<String> arrlist = new ArrayList <String >();
        ArrayList<Integer  > arrlistt = new ArrayList <Integer >();
        ArrayList<String> arrlast = new ArrayList<String>();
        String name="",path="";
        
        File folder=new File("C:\\Scoreboard");
        //class file has a listoffiles list that take thez
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) 
        {
            if (listOfFiles[i].isFile()) 
            {
                //get name of file
                name=listOfFiles[i].getName();
                //take only what is before txt and put it in nm
                StringTokenizer tok=new StringTokenizer(name,".");
                //nm:the string before .txt
                String nm=tok.nextToken();
               
                //get path
                path=listOfFiles[i].getCanonicalPath();
                Scanner sc = new Scanner(new File(path));
                String stvalue=sc.next();
                nm=nm+":"+stvalue;
               // value:the int existing in the file 
               int value=Integer.parseInt(stvalue);
                arrlist.add(nm);
                arrlistt.add(value);
                sc.close();
            }
        }
        Collections.sort(arrlistt, Collections.reverseOrder());
        for(int i =0;i<arrlistt.size();i++)
        {
            for(int j=0;j<arrlist.size();j++)
            {
                if(arrlistt.get(i) == getScore(arrlist.get(j))&&!arrlast.contains(arrlist.get(j)))
                {
                    arrlast.add(arrlist.get(j));
                    break; 
                }
            }
        }
        return arrlast;
    }    
    public static String getName(String element)
    {
        StringTokenizer split = new StringTokenizer(element,":");
        return split.nextToken();
    }
    public static int getScore(String element)
    {
        StringTokenizer split = new StringTokenizer(element,":");
        split.nextToken();
        return Integer.parseInt(split.nextToken());
    }   
} 


