/**
 * @author antoinemansour 40250454
 * COMP249
 * Assignment 3
 * 29 march 2023
 * 
 */

///Assignment 3
//Written by Antoine mansour




/**
 * import java io and util files
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Part1 class that contains do_part1 method
 * @author antoinemansour
 *
 */
public class Part1 {
	
	
	
	/**
	 * do_part1 method that reads 2 input files, with 2 while loops and seperates them via their specific genre.
	 * @param reader, reads the main files containing all the books files
	 * @param bookread, reads with the help of a while loop, all the books files and each book in them.
	 * @param pw, printwriter that writes each synthax error in a separate file.
	 * @param CCB, HCB, MTV, MRB, NEB, OTR, SSM, TPA, all printwriters that read the books and will write them in a seperate file with their genres.
	 *@return void
	 *
	 */
    public static void do_part1()  {
    	
    	//String input file
        String inputFilename = "/Users/antoinemansour/Desktop/A3/part1_input_file_names.txt";
        //Define scanner as null
        Scanner reader=null;
        
      
        
//try catch block to see if the input file is correct and has the right path.
        try {
            reader = new Scanner(new FileInputStream(inputFilename));
        }
        
        catch(FileNotFoundException e){
        	
        	System.err.println("File was not found.");
        	System.exit(0);
        }

        
        //all printwriters.
        PrintWriter pw=null;
        PrintWriter CCB=null;
        PrintWriter HCB=null;
        PrintWriter MTV=null;
        PrintWriter MRB=null;
        PrintWriter NEB=null;
        PrintWriter OTR=null;
        PrintWriter SSM=null;
        PrintWriter TPA=null;
        
        //try catch block to see if the input files have the right path
        try {
        CCB=new PrintWriter(new FileOutputStream("src/Cartoons_Comics_Books.csv.txt"));
        HCB=new PrintWriter(new FileOutputStream("src/Hobbies_Collectibles_Books.csv.txt"));
        MTV=new PrintWriter(new FileOutputStream("src/Movies_TV.csv.txt"));
        MRB=new PrintWriter(new FileOutputStream("src/Music_Radio_Books.csv.txt"));
        NEB=new PrintWriter(new FileOutputStream("src/Nostalgia_Eclectic_Books.csv.txt"));
        OTR=new PrintWriter(new FileOutputStream("src/Old_Time_Radio.csv.txt"));
        SSM=new PrintWriter(new FileOutputStream("src/Sports_Sports_Memorabilia.csv.txt"));
        TPA=new PrintWriter(new FileOutputStream("src/Trains_Planes_Automobiles.csv.txt"));
        pw=new PrintWriter(new FileOutputStream("src/syntax_Error_file.csv.txt"));
        	
        }
        
        
catch(FileNotFoundException e){
        	
        	System.err.println("File was not found.");
        	System.exit(0);
        }
        
            // Keep track of number of book records in each genre-based output file
            int ccb = 0;
            int hcb = 0;
            int mtv = 0;
            int mrb = 0;
            int neb = 0;
            int otr = 0;
            int ssm = 0;
            int tpa = 0;
            
            int missingtitle=0;
            
        
            
            
            
         boolean a =true;     
         String file=""; 
       
         int t=0;
          //while loop that reads every input book file (1995-2010)
         while(a) {  
        	 
        	 //if the input file has a nextline, then continue, if not while loop ends.
          if(reader.hasNextLine())  {
        	  
        	 
        	  
        		int missingauthor=0;
        		int missingISBN=0;
        		int missingenre=0;
        		int missingyear=0;
        		int synthaxerror=0;
        		int missingprice=0;
        	  
        		
        		//file is the the book information
        		file=reader.nextLine();
        	
        	//reads each book
        		Scanner bookRead=null;
        		
             	
             	
             	try {
             		bookRead = new Scanner (new FileInputStream("/Users/antoinemansour/Desktop/A3/"+file));
             		
             	}
             	
             	
             	
             	
             	
             	
             	  catch(FileNotFoundException e) {
             		  
             		  System.out.println("This file does not exist.");
             	  }
                 
        	
        	
        	
        	t++;
        	
            	boolean noBooks=true;
            	int error=0;
            	
            	
            	//while there is no books continue
            	while(noBooks) {
            		
            	if(bookRead.hasNextLine()) {	
            		
            		
            		
            	 String book=bookRead.nextLine();
            	  
            	 //check if the title is double quoted
            	 String[] elements = book.split("\",");
            	
            	//if there is no double quote, read
            	if (elements.length==1) {
            		//seperate each element into an array of field
            		String []field=elements[0].split(",");
            		
            		int numfield = 0;
            		
            		
            		//try and and catch the errors
            		try {
            			
            			for(int i =0; i<field.length; i++) {
							if(field[i]!="") {
								numfield++;
							}
							else {
								continue;
							}
						}
						if(numfield < 6) {
							if(error==0) {
								pw.println("syntax error in file: " + file +"\n"+
										   "====================");
								error=1;
							}
							throw new TooFewFieldsException();
							
						}
						if(numfield > 6) {
							if(error==0) {
								pw.println("syntax error in file: " + file +"\n"+
										   "====================");
								error=1;
							}
							throw new TooManyFieldsException();
						}
            		
            		if( field[4].equals("CCB")|| field[4].equals("HCB")||field[4].equals("MTV")||field[4].equals("MRB")||field[4].equals("NEB")||field[4].equals("OTR")||field[4].equals("SSM")||field[4].equals("TPA")){
            			
            			if( field[4].equals("CCB")){
            				CCB.println(book);
            				ccb++;
            				CCB.flush();
            				
            			}
            			
            			if( field[4].equals("HCB")){
            				HCB.println(book);
            				hcb++;
            				HCB.flush();
            				
            			}
            			if( field[4].equals("MTV")){
            				MTV.println(book);
            				mtv++;
            				MTV.flush();
            				
            			}
            			if( field[4].equals("MRB")){
            				MRB.println(book);
            				mrb++;
            				MRB.flush();
            				
            			}
            			if( field[4].equals("NEB")){
            				NEB.println(book);
            				neb++;
            				NEB.flush();
            				
            			}
            			if( field[4].equals("OTR")){
            				OTR.println(book);
            				otr++;
            				OTR.flush();
            				
            			}
            			if( field[4].equals("SSM")){
            				SSM.println(book);
            				ssm++;
            				SSM.flush();
            				
            			}
            			if( field[4].equals("TPA")){
            				TPA.println(book);
            				tpa++;
            				TPA.flush();
            				
            			}
            			
            			
            		
            		
            		
            		}
            		
            		
            		else {
            			
            			if (error==0) {
            				
            				pw.println("Syntax error in file: "+file+"\n");
            				pw.println("================================");
            				
            				error=1;
            				
            			}
            			throw new UnknownGenreException();
            			
            		}
            		
            		
            		
            		}
            		
            		
            		//missing fields are caught here with the help of the array. Check if null.
            		catch(TooFewFieldsException e) {
            			try {
            				throw new MissingFieldException();
            			}
            			
            			catch(MissingFieldException e1) {
            				
            				 error=0;
            				 
            				
            			
            				 	if(field[0]=="") {
									pw.println("Error: Missing Title");
									error++;
									missingtitle++;
								}
            				 	if(field[1]=="") {
									pw.println("Error: Missing Author");
									error++;
								}
								if(field[2]=="") {
									pw.println("Error: Missing Price");
									error++;
								}
								if(field[3]=="") {
									pw.println("Error: Missing ISBN");
									error++;
								}
								if(field[4]=="") {
									pw.println("Error: Missing Genre");
									error++;
								}
	            			
            			
            			if(error==0 && numfield ==5) {
							pw.println("Error: Missing Year");
						}
						if(error==1 && numfield ==4) {
							pw.println("Error: Missing Year");
						}
						if(error==2 && numfield ==3) {
							pw.println("Error: Missing Year");
						}
						if(error==3 && numfield ==2) {
							pw.println("Error: Missing Year");
						}
						if(error==4 && numfield ==1) {
							pw.println("Error: Missing Year");
						}
						if(error==5 && numfield ==0) {
							pw.println("Error: Missing Year");
						}
            			
            			
            			
            			pw.println("Record "+book+"\n");
            			synthaxerror++;
            			pw.flush();
            			
            		}
            		}
            		
            		
            		
            		
            		
            	
            		
            		
            	catch (TooManyFieldsException e) {
            		
            		pw.println("Error: Too many fields");
            		pw.println("Record "+ book + "\n");
            		synthaxerror++;
            		pw.flush();
            		
            		
            		
            	}
            	
            	
            	
            	catch (UnknownGenreException e) {
            		pw.println("Error: Invalid type of genre");
            		pw.println("Record "+ book + "\n");
            		synthaxerror++;
            		pw.flush();
            	}
            	}
            	
            	
            	
            	
            	
            	
            	
            	//if title is quoted
            		if (elements.length==2) {
            		
            		String []field=elements[1].split(",");
            		
            		int numfield = 0;
            		
            		
            		
            		try { //try to catch errors.
            			
            			if(elements[0]!="") {
							numfield++;
						}
            			
            			
            			for(int i =0; i<field.length; i++) {
							if(field[i]!="") {
								numfield++;
							}
						}
						if(numfield < 6) {
							if(error==0) {
								pw.println("syntax error in file: " + file +"\n"+
										   "====================");
								numfield=1;
							}
							throw new TooFewFieldsException();
						}
						if(numfield > 6) {
							if(error==0) {
								pw.println("syntax error in file: " + file +"\n"+
										   "====================");
								error=1;
							}
							throw new TooManyFieldsException();
						}
            		
            		//check for equality and write in corresponding genre file.
            		if( field[3].equals("CCB")|| field[3].equals("HCB")||field[3].equals("MTV")||field[3].equals("MRB")||field[3].equals("NEB")||field[3].equals("OTR")||field[3].equals("SSM")||field[3].equals("TPA")){
            			
            			if( field[3].equals("CCB")){
            				CCB.println(book);
            				ccb++;
            				CCB.flush();
            				
            			}
            			
            			if( field[3].equals("HCB")){
            				HCB.println(book);
            				hcb++;
            				HCB.flush();
            				
            			}
            			if( field[3].equals("MTV")){
            				MTV.println(book);
            				mtv++;
            				MTV.flush();
            				
            			}
            			if( field[3].equals("MRB")){
            				MRB.println(book);
            				mrb++;
            				MRB.flush();
            				
            			}
            			if( field[3].equals("NEB")){
            				NEB.println(book);
            				neb++;
            				NEB.flush();
            				
            			}
            			if( field[3].equals("OTR")){
            				OTR.println(book);
            				otr++;
            				OTR.flush();
            				
            			}
            			if( field[3].equals("SSM")){
            				SSM.println(book);
            				ssm++;
            				SSM.flush();
            				
            			}
            			if( field[3].equals("TPA")){
            				TPA.println(book);
            				tpa++;
            				TPA.flush();
            				
            			}
            			
            			
            		
            		
            		
            		}
            		
            		
            		else {
            			
            			if (error==0) {
            				
            				pw.println("Syntax error in file: "+file+"\n");
            				pw.println("================================");
            				
            				error=1;
            				
            			}
            			throw new UnknownGenreException();
            			
            		}
            		
            		
            		
            		}
            		
            		
            		
            		catch(TooFewFieldsException e) {
            			try {
            				throw new MissingFieldException();
            			}
            			
            			catch(MissingFieldException e1) {
            				
            				 error=0;
            			
            			
            				 
            			
            			if (elements[0]=="") {
            				pw.println("Error: Missing title");
            				error++;
            				missingtitle++;
            			}
            			
            			if (field[0]=="") {
            				pw.println("Error: Missing author");
            				error++;
            				missingauthor++;
            			}
            			if (field[1]=="") {
            				pw.println("Error: Missing price");
            				error++;
            				missingprice++;
            			}
            			if (field[2]=="") {
            				pw.println("Error: Missing ISBN");
            				error++;
            				missingISBN++;
            			}
            			if (field[3]=="") {
            				pw.println("Error: Missing genre");
            				error++;
            				missingenre++;
            			}
            			
            			
            			if(error==0 && numfield ==5) {
							pw.println("Error: Missing Year");
						}
						if(error==1 && numfield ==4) {
							pw.println("Error: Missing Year");
						}
						if(error==2 && numfield ==3) {
							pw.println("Error: Missing Year");
						}
						if(error==3 && numfield ==2) {
							pw.println("Error: Missing Year");
						}
						if(error==4 && numfield ==1) {
							pw.println("Error: Missing Year");
						}
						if(error==5 && numfield ==0) {
							pw.println("Error: Missing Year");
						}
            			
            				
            			}pw.println("Record "+book+"\n");
            			synthaxerror++;
            			pw.flush();
            		}
            		
            		
            	catch (TooManyFieldsException e) {
            		
            		pw.println("Error: Too many fields");
            		pw.println("Record "+ book + "\n");
            		synthaxerror++;
            		pw.flush();
            		
            	}
            	
            	catch (UnknownGenreException e) {
            		pw.println("Error: Invalid type of genre");
            		pw.println("Record "+ book + "\n");
            		synthaxerror++;
            		pw.flush();
            		
            	}
            	
            	
            	
            	
            		
            	}
            		
            	}
            	
            	//if book has no next lines, then while loop ends and we have read all the files 
            	if(bookRead.hasNextLine()==false) {
            		noBooks=false;
            		
            	
            	
            	}
            	}
            	
            	
            	
            	bookRead.close();
            	}
            	
          else {
        	  
        	//if main has no next lines, then while loop ends and we have read all the files 
        	  if(reader.hasNextLine()==false) {
          		a=false;
          		
        	  
          }
          
          
            }
                          
                            
                            
                        }
            //close all the scanners
            CCB.close();
            HCB.close();
            MTV.close();
            MRB.close();
            NEB.close();
            OTR.close();
            SSM.close();
            TPA.close();
            reader.close();
            
            
            // Print summary of book records written to each genre-based output file
           
          
            
      
       
    
    }}
        
      

       
        
        
        
        
        
        
        
        
        
                    
           
          
            
            
            

           
        
        
       

        
        
        
