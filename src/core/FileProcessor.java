package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList; 

public class FileProcessor 
{

   private String clearText;
   private String encodedText;
   private String freqTable;
   private String codeLegend;
   private String alphaSize;
   private String decodedText;
   private String container;
   
   public FileProcessor(String[] args)
   {
      this.clearText   = args[0];
      this.encodedText = args[1];
      this.freqTable   = args[2];
      this.codeLegend  = args[3];
      this.alphaSize   = args[4];
      this.decodedText = args[5];
      this.container   = args[6];
   }
   
   public void checkArguments(String[] args)
   {
      if (args.length != 7)
      {
        
         System.out.println("Invalid number of command line arguments");
         System.exit(1);
      }
      // confirm and echo command line args
      else
      {
         
         System.out.println(">> File path args successfully recieved.");
         System.out.println(">> Clear text input: " + this.clearText);
         System.out.println(">> Encoded text: " + this.encodedText); 
         System.out.println(">> Frequency table input: " + this.freqTable);
         System.out.println(">> Code legend: " + this.codeLegend);
         System.out.println(">> Alphabet size: " + this.alphaSize);
         System.out.println(">> Decoded text: " + this.decodedText);
         System.out.println(">> Container file path: " + this.container);
      }
   }  
   
   public PrintWriter readFile(String filePath)
   {
      PrintWriter writer;
   
      try
      { 
        
         writer = new PrintWriter(new FileWriter(filePath));
         
         return writer;
      }
      catch (Exception ioe)
      {
          System.err.println(ioe.toString());
          
          return null;
      }
   }  
   
   public void closeFile(PrintWriter file)
   {
      try
      {  
         file.close();
         System.out.println(">> " + "Output file successfully closed.");
        
      }
      catch (Exception e)
      {
         System.err.println(e.toString());
      }
      
   }  

   public String readFileToString(String filePath)
   {
       StringBuilder contentBuilder = new StringBuilder();
       
       try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
       {
           String sCurrentLine;
           while ((sCurrentLine = br.readLine()) != null)
           { 
               contentBuilder.append(sCurrentLine).append("\n");
           }
       }
       catch (IOException e)
       {
           e.printStackTrace(); 
       }
       
       return contentBuilder.toString(); 
   }  
   
   public char[] extractAlpha(String filePath, int alphaSize)
   {
      char[] fullStringCharArray = filePath.toCharArray();
      char[] alphaCharsArray = new char[alphaSize];
      
      int counter = 0;
      for (int i = 0; i < fullStringCharArray.length; i++)
      {
         if (fullStringCharArray[i] >= 65 & fullStringCharArray[i] <= 90)
         {
            alphaCharsArray[counter] = fullStringCharArray[i];
            counter++;
         }
         
      } 
      
      return alphaCharsArray;
        
   }  
   
    public int[] extractFreqValues()
    {
       ArrayList<String> freqStringsList = new ArrayList<>();
       
       try (BufferedReader br = new BufferedReader(
             new FileReader(this.freqTable))) 
       {
           while (br.ready()) 
           {
              freqStringsList.add(br.readLine());
           }
       }
       catch (Exception e)
       {
          System.err.println(e.toString());
       }
       
       String[] freqStringsArray = new String[freqStringsList.size()];
       freqStringsArray = freqStringsList.toArray(freqStringsArray);
       
       // remove all non frequency value characters
       for (int i = 0; i < freqStringsArray.length; i++)
       {
          
          // remove white spaces 
          freqStringsArray[i] = freqStringsArray[i].replace(" ", "");
          
          // remove hyphen signs
          freqStringsArray[i] = freqStringsArray[i].replace("-", "");
          
          // remove non ASCII characters
          freqStringsArray[i] = 
                freqStringsArray[i].replaceAll("[^\\p{ASCII}]", "");
          
          // remove letter characters
          freqStringsArray[i] = freqStringsArray[i].replaceAll("[A-Z]", "");
       }
       
       int[] freqIntArray = new int[freqStringsArray.length]; 
       
       for (int i = 0; i < freqStringsArray.length; i++)
       {     
          freqIntArray[i] = Integer.parseInt(freqStringsArray[i]); 
       }
       
       return freqIntArray;
    } 
    
    public String[] extractCodeValues()
    {
        ArrayList<String> codeStringsList = new ArrayList<>();
       
        try (BufferedReader br = new BufferedReader(
              new FileReader(this.codeLegend))) 
        {
            while (br.ready()) 
            {
                codeStringsList.add(br.readLine());
            }
        }
        catch (Exception e)
        { 
           System.err.println(e.toString());
        }
        
        String[] codeStringsArray = new String[codeStringsList.size()];
        
        codeStringsArray = codeStringsList.toArray(codeStringsArray);
        
        for (int i = 0; i < codeStringsArray.length; i++)
        {
           // remove hyphen sign
           codeStringsArray[i] = codeStringsArray[i].replace(" - ", "");
           
           // remove letter characters
           codeStringsArray[i] = codeStringsArray[i].replaceAll("[A-Z]", "");
        }
        
        return codeStringsArray;
     }  

} 
