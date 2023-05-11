/**
 * @author antoinemansour 40250454
 * COMP249
 * Assignment 3
 * 29 march 2023
 * 
 */

///Assignment 3
//Written by Antoine mansour


//import all java files that help us.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Class Book implementing serializable for the serizialation of the objects. 
 * Contains do_part2 and do_part3 method.
 * 
 * @author antoinemansour
 *
 */
public class Book implements Serializable {
    private String title;
    private static String author;
    private double price;
    private String isbn;
    private String genre;
    private int year;
    
    
    /**
     * check method that will check each input file and serialize it.
     * @param reader
     * @param oos
     * @param pw
     * @param file
     * @param output
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
   public static Book[] check(Scanner reader, ObjectOutputStream oos, PrintWriter pw, String file, String output ) throws FileNotFoundException, IOException {
		
	   
	   //reader Scanner that will read the input file
		reader=new Scanner (new FileInputStream(file));
		oos=new ObjectOutputStream(new FileOutputStream(output));
		//destination output file
		
		
		boolean	nobooks=true;
        int error=0;
        Book []bookArray=new Book[0];
        int books=0;
        //while loop that checks if file contains a next line
        while (nobooks) {
      
         
         if(reader.hasNextLine()) {
        	 
        	 
        	  
        	 
        	  String book = reader.nextLine();
        	  
       //like part1, check for double quotes 	  
      String [] elements=book.split("\",");
      
      //if no double quotes
      if (elements.length==1) {
  		
  		String []field=elements[0].split(",");
  		int sum = 0;
		int x = 0;
  		
  		try {
  			
  		double price=Double.parseDouble(field[2]);	
  		int year=Integer.parseInt(field[5]);
  		
  		
  		
  	//VERIFY PRICE
		if(Double.parseDouble(field[2]) < 0) {
			if(error == 0) {
				pw.println("semantic error in file: " + file +"\n"+
						   "====================\n");
				error = 1;
			}
			throw new BadPriceException();
		}
  		
  		
  		
  		
  		
  	//VERIFY YEAR
		if(Integer.parseInt(field[5]) < 1995 || Integer.parseInt(field[5]) > 2010) {
			if(error == 0) {
				pw.println("semantic error in file: " + file +"\n"+
						   "====================\n");
				error = 1;
			}
			throw new BadYearException();
			
		}
  		
  		 sum=0;	
  		String[] isbn=field[3].split("");
  		
  		
  		
  		boolean correct = true;
		sum = 0;
		
		//ISBN 10 DIGITS SUM VERIFICATION
		if(isbn.length == 10) {
			for(int i = 0, count = 10; i<isbn.length; i++, count--) {
				sum += (count*Integer.parseInt(isbn[i]));
			}
		}
		
		//ISBN 13 DIGITS SUM VERIFICATION
		if(isbn.length == 13) {
			for(int i = 0; i<isbn.length; i++) {
				if(i%2 == 0) {
					sum += Integer.parseInt(isbn[i]);
				}
				else {
					sum += (3*Integer.parseInt(isbn[i]));
				}
			}
		}
		// VERIFY IF THE SUM AGREES WITH THE SPECIFIED CONDITIONS
		if(sum%11 == 0 && isbn.length == 10 || sum % 10 == 0 && isbn.length == 13) {
			//APPEND TO CORRESPONDING BINARY FILE

			
		}
		else {
			if(isbn.length == 10) {
				if(error == 0) {
					pw.println("semantic error in file: " + file +"\n"+
							   "====================\n");
					error = 1;
				}
				throw new BadIsbn10Exception();
			}
			if(isbn.length == 13) {
				if(error == 0) {
					pw.println("semantic error in file: " + file +"\n"+
							   "====================\n");
					error = 1;
				}
				throw new BadIsbn13Exception();
			}
		}
  		
  		
		//CREATE BOOK OBJECT	
		String title = field[0];
		String author = field[1];
		double pricee = Double.parseDouble(field[2]); // parseDouble
		String ISBN = field[3];
		String genre = field[4];
		int yearr = Integer.parseInt(field[5]); // parseInt
  		
  		//create binary file
  		Book binarybook= new Book(title,author,pricee,isbn,genre,yearr);
  	books++;
  	Book[] temp=bookArray;
  	bookArray= new Book[books];
  	
  	
  	
  	
  	
  	
  	for (int i=0;i<temp.length;i++) {
  		bookArray[i]=temp[i];
  	}
  			bookArray[temp.length]=binarybook;
  		
      
  		
  	
  		}	
  			//catch exceptions
  		catch(NumberFormatException e) {

			if(error == 0) {
				pw.println("semantic error in file: " + file +"\n"+
						   "====================\n");
				error = 1;
			}
			pw.println("Isbn doesn't contain all digits that it should hold\n"+
					   "Record: "+book+"\n");
			
			pw.flush();
			continue;
		}
  		
  		
  		catch(BadIsbn10Exception e) {
  			
  			pw.println("Bad ISBN 10: Total sum of digits is not a multiple of 11.");
  			pw.println("Record: "+book+"\n");
  			pw.flush();
  			
  			
  		}
  		
  		
  		catch(BadIsbn13Exception e) {
  			
  			pw.println("Bad ISBN 13: Total sum of digits is not a multiple of 10.");
  			pw.println("Record: "+book+"\n");
  			pw.flush();
  			
  			
  		}
  		
  		
  		catch(BadPriceException e) {
  			
  			pw.println("Invalid price: book price cannot be negative.");
  			pw.println("Record: "+book+"\n");
  			pw.flush();
  			
  			
  		}
  		
  		
  		catch(BadYearException e) {
  			
  			pw.println("Invalid year: book cannot be older then 1995 and younger then 2010.");
  			pw.println("Record: "+book+"\n");
  			pw.flush();
  			
  			
  		}
  		
  	
  		
  		
  		
      }
        	 
        	 
      
      //if file is in quotes.
      else if(elements.length==2) {
    		
    		String []field=elements[1].split(",");
    		int sum = 0;
  		int x = 0;
    		
    		try {
    			
    		
    		
    		
    		
    	//VERIFY PRICE
  		if(Double.parseDouble(field[1]) < 0) {
  			if(error == 0) {
  				pw.println("semantic error in file: " + file +"\n"+
  						   "====================\n");
  				error = 1;
  			}
  			throw new BadPriceException();
  		}
    		
    		
    		
    		
    		
    	//VERIFY YEAR
  		if(Integer.parseInt(field[4]) < 1995 || Integer.parseInt(field[4]) > 2010) {
  			if(error == 0) {
  				pw.println("semantic error in file: " + file +"\n"+
  						   "====================\n");
  				error = 1;
  			}
  			throw new BadYearException();
  			
  		}
    		
    		 sum=0;	
    		String[] isbn=field[2].split("");
    		
    		
    		
    		boolean correct = true;
  		sum = 0;
  		
  		//ISBN 10 DIGITS SUM VERIFICATION
  		if(isbn.length == 10) {
  			for(int i = 0, count = 10; i<isbn.length; i++, count--) {
  				sum += (count*Integer.parseInt(isbn[i]));
  			}
  		}
  		
  		//ISBN 13 DIGITS SUM VERIFICATION
  		if(isbn.length == 13) {
  			for(int i = 0; i<isbn.length; i++) {
  				if(i%2 == 0) {
  					sum += Integer.parseInt(isbn[i]);
  				}
  				else {
  					sum += (3*Integer.parseInt(isbn[i]));
  				}
  			}
  		}
  		// VERIFY IF THE SUM AGREES WITH THE SPECIFIED CONDITIONS
  		if(sum%11 == 0 && isbn.length == 10 || sum % 10 == 0 && isbn.length == 13) {
  			//APPEND TO CORRESPONDING BINARY FILE

  			
  		}
  		else {
  			if(isbn.length == 10) {
  				if(error == 0) {
  					pw.println("semantic error in file: " + file +"\n"+
  							   "====================\n");
  					error = 1;
  				}
  				throw new BadIsbn10Exception();
  			}
  			if(isbn.length == 13) {
  				if(error == 0) {
  					pw.println("semantic error in file: " + file +"\n"+
  							   "====================\n");
  					error = 1;
  				}
  				throw new BadIsbn13Exception();
  			}
  		}
    		
    		
  		//CREATE BOOK OBJECT	
  		String title = elements[0];
  		String author = field[0];
  		double pricee = Double.parseDouble(field[1]); // parseDouble
  		String ISBN = field[2];
  		String genre = field[3];
  		int yearr = Integer.parseInt(field[4]); // parseInt
    		
    		
    		Book binarybook= new Book(title,author,pricee,isbn,genre,yearr);
    	books++;
    	Book[] temp=bookArray;
    	bookArray= new Book[books];
    	
    	
    	
    	
    	
    	
    	for (int i=0;i<temp.length;i++) {
    		bookArray[i]=temp[i];
    	}
    			bookArray[temp.length]=binarybook;
    		
        
    		
    	
    		}	
    			
    		catch(NumberFormatException e) {

  			if(error == 0) {
  				pw.println("semantic error in file: " + file +"\n"+
  						   "====================\n");
  				error = 1;
  			}
  			pw.println("Isbn doesn't contain all digits that it should hold\n"+
  					   "Record: "+book+"\n");
  			
  			pw.flush();
  			continue;
  		}
    		
    		
    		catch(BadIsbn10Exception e) {
    			
    			pw.println("Bad ISBN 10: Total sum of digits is not a multiple of 11.");
    			pw.println("Record: "+book+"\n");
    			pw.flush();
    			
    			
    		}
    		
    		
    		catch(BadIsbn13Exception e) {
    			
    			pw.println("Bad ISBN 13: Total sum of digits is not a multiple of 10.");
    			pw.println("Record: "+book+"\n");
    			pw.flush();
    			
    			
    		}
    		
    		
    		catch(BadPriceException e) {
    			
    			pw.println("Invalid price: book price cannot be negative.");
    			pw.println("Record: "+book+"\n");
    			pw.flush();
    			
    			
    		}
    		
    		
    		catch(BadYearException e) {
    			
    			pw.println("Invalid year: book cannot be older then 1995 and younger then 2010.");
    			pw.println("Record: "+book+"\n");
    			pw.flush();
    			
    			
    		}
    		
    	
    		
    		
    		
        }
    	 	 
         }
         else {
     		nobooks=false;
     		oos.writeObject(bookArray);
     	
     	
     	}
            
        }
        reader.close();
    		oos.close();
    		return bookArray;
        }
    
	/**
	 * Book constructor	
	 * @param title2
	 * @param author2
	 * @param price2
	 * @param isbn2
	 * @param genre2
	 * @param year2
	 */
    public Book(String title2, String author2, double price2, String[] isbn2, String genre2, int year2) {
		// TODO Auto-generated constructor stub
	}

    /**
     * gettitle
     * @return the title of the book
     */
	public String getTitle() {
        return title;
    }    
	
	
	/**
	 * set the title of the book
	 * @param title
	 */
    public void setTitle(String title) {
        this.title = title;
    }
/**
 * 
 * @return the author of the book
 */
    public String getAuthors() {
        return author;
    }

    /**
     * set the author
     * @param authors
     */
    public void setAuthors(String authors) {
        this.author = authors;
    }

    /**
     * 
     * @return price of book
     */
    public double getPrice() {
        return price;
    }
/**
 * set price of book
 * @param price
 */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * get isbn of book
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * set isbn of book
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * get genre of book
     * @return the genre of the book
     */
    public String getGenre() {
        return genre;
    }

    /**
     * set the genre of the book
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
/**
 * 
 * @return the year of creation of the book
 */
    public int getYear() {
        return year;
    }
/**
 * set the year of the book
 * @param year
 */
    public void setYear(int year) {
        this.year = year;
    }

    // Override the equals() method to compare Book objects based on their isbn values
    
    /**
     * Equals method
     */
    @Override
    public boolean equals(Object obj) {
        if(obj==this)return true;
        if(obj==null)return false;
        if(obj.getClass()!=this.getClass())return false;
        
        Book b=(Book)obj;
        return this.getAuthors()==b.getAuthors() && this.getGenre()==b.getGenre()&& this.getIsbn()==b.getIsbn() && this.getPrice()==b.getPrice() && this.getTitle()==b.getTitle() && this.getYear()==b.getYear(); 
    	
    	
        
    }

    // Override the toString() method to return a string representation of a Book object
    @Override
    /**
     * toString method
     */
    public String toString() {
        return "Title: " + title + "\nAuthors: " + author + "\nPrice: $" + price
            + "\nISBN: " + isbn + "\nGenre: " + genre + "\nYear: " + year;
    
    }
    
    
    
/**
 * badisbn10 class
 * @author antoinemansour
 *
 */
static class BadIsbn10Exception extends Exception {
    public BadIsbn10Exception(String message) {
        super(message);
        
    }
    
    public BadIsbn10Exception() {
    	
    }
}
/**
 * badisbn13 class
 * @author antoinemansour
 *
 */
static class BadIsbn13Exception extends Exception {
    public BadIsbn13Exception(String message) {
        super(message);
    }
    public BadIsbn13Exception() {
    	
    }
    
}
/**
 * badprice class
 * @author antoinemansour
 *
 */
static class BadPriceException extends Exception {
    public BadPriceException(String message) {
        super(message);
    }
    
    public BadPriceException() {
    	
    }
    
}
/**
 * badyear class
 * @author antoinemansour
 *
 */
static class BadYearException extends Exception {
    public BadYearException(String message) {
        super(message);
    }
    
    public BadYearException( ) {
    	
    	
    	
    }
    
   
}

/**
 * do_part2 method
 * 
 */
    public static void do_part2()  {
    	
        String[] fileNames = {"Cartoons_Comics.csv.txt", "Hobbies_Collectibles.csv.txt", "Movies_TV_Books.csv.txt", "Music_Radio_Books.csv.txt", "Nostalgia_Eclectic_Books.csv.txt", "Old_Time_Radio_Books.csv.txt", "Sports_Sports_Memorabilia.csv.txt", "Trains_Planes_Automobiles.csv.txt"};
        String binaryFileSuffix = ".ser";
        
        
        
        
        // all the printwriter that will write into binary files
        
        PrintWriter pw=null;
    	Scanner reader=null;
    	ObjectOutputStream oos=null;
    	ObjectOutputStream cartoonsbinary=null;
    	ObjectOutputStream hobbiesbinary=null;
    	ObjectOutputStream moviesbinary=null;
    	ObjectOutputStream musicbinary=null;
    	ObjectOutputStream nostalgiabinary=null;
    	ObjectOutputStream oldbinary=null;
    	ObjectOutputStream sportsbinary=null;
    	ObjectOutputStream trainsbinary=null;
    	
    	
    	
    	Book[] tempBinary=null;
    	
    	
    	
    	try {
    		
    		
    		
    		try{
    			 pw=new PrintWriter(new FileOutputStream("src/semantic_error_file.txt"));
    			 
    			 cartoonsbinary=new ObjectOutputStream(new FileOutputStream("src/Cartoons_Comics.csv.ser.txt"));
    			 hobbiesbinary=new ObjectOutputStream(new FileOutputStream("src/Hobbies_Collectibles.csv.ser.txt"));
    			 moviesbinary=new ObjectOutputStream(new FileOutputStream("src/Movies_TV_Books.csv.ser.txt"));
    			 musicbinary=new ObjectOutputStream(new FileOutputStream("src/Music_Radio_Books.csv.ser.txt"));
    			 nostalgiabinary=new ObjectOutputStream(new FileOutputStream("src/Nostalgia_Eclectic_Books.csv.ser.txt"));
    			 oldbinary=new ObjectOutputStream(new FileOutputStream("src/Old_Time_Radio_Books.csv.ser.txt"));
    			 sportsbinary=new ObjectOutputStream(new FileOutputStream("src/Sports_Sports_Memorabilia.csv.ser.txt"));
    			 trainsbinary=new ObjectOutputStream(new FileOutputStream("src/Trains_Planes_Automobiles.csv.ser.txt"));
    	}
    		
    		
    		catch (FileNotFoundException e) {
    			
    			System.err.println("Could not find the file for the semantic errors.");
    			System.exit(0);
    			
    		}
    		
    		//Check emthod is applied here to serialize.
    		
    		tempBinary=check(reader,oos,pw,"src/Cartoons_Comics_Books.csv.txt","src/Cartoons_Comics.csv.ser.txt");
    		cartoonsbinary.writeObject(tempBinary);
    		
    		
    		tempBinary=check(reader,oos,pw,"src/Hobbies_Collectibles_Books.csv.txt","src/Hobbies_Collectibles.csv.ser.txt");
    		hobbiesbinary.writeObject(tempBinary);
    		
    		tempBinary=check(reader,oos,pw,"src/Movies_TV.csv.txt","src/Movies_TV_Books.csv.ser.txt");
    		moviesbinary.writeObject(tempBinary);
    		
    		tempBinary=check(reader,oos,pw,"src/Music_Radio_Books.csv.txt","src/Music_Radio_Books.csv.ser.txt");
    		musicbinary.writeObject(tempBinary);
    		
    		tempBinary=check(reader,oos,pw,"src/Nostalgia_Eclectic_Books.csv.txt","src/Nostalgia_Eclectic_Books.csv.ser.txt");
    		nostalgiabinary.writeObject(tempBinary);
    		
    		tempBinary=check(reader,oos,pw,"src/Old_Time_Radio.csv.txt","src/Old_Time_Radio_Books.csv.ser.txt");
    		oldbinary.writeObject(tempBinary);
    		
    		tempBinary=check(reader,oos,pw,"src/Sports_Sports_Memorabilia.csv.txt","src/Sports_Sports_Memorabilia.csv.ser.txt");
    		sportsbinary.writeObject(tempBinary);
    		
    		tempBinary=check(reader,oos,pw,"src/Trains_Planes_Automobiles.csv.txt","src/Trains_Planes_Automobiles.csv.ser.txt");
    		trainsbinary.writeObject(tempBinary);
    		
    		
    		//close 
    		cartoonsbinary.close();
    		hobbiesbinary.close();
    		moviesbinary.close();
    		musicbinary.close();
    		nostalgiabinary.close();
    		oldbinary.close();
    		sportsbinary.close();
    		trainsbinary.close();
    		
    	}
    	
    	
    	catch(FileNotFoundException e) {
    		System.err.println(e.getMessage());
    		
    		
    	}
    	
    	catch (IOException e) {
    		System.err.println(e.getMessage());
    	}
       
    }
    


    private static final String[] FILENAMES = {
            "1	Cartoons_Comics_Books.csv.ser      ",
            "2	Hobbies_Collectibles_Books.csv.ser ",
            "3	Movies_TV.csv.ser                  ",
            "4	Music_Radio_Books.csv.ser          ",
            "5	Nostalgia_Eclectic_Books.csv.ser   ",
            "6	Old_Time_Radio.csv.ser             ",
            "7	Sports_Sports_Memorabilia.csv.ser  ",
            "8	Trains_Planes_Automobiles.csv.ser ",
            "9 Exit        "
        };
        
    
    
    
    
    
    //view method that will read these binary files and deserialize.
    static Scanner scan=new Scanner (System.in);
    
    /**
     * Method that displays the book
     * @param view
     * @param choose
     * @param index
     * @param pointer
     * @param temp
     * @param filelength
     * @param currentFile
     * @param bookArray
     * @param in
     */
    public static void viewing(boolean view, boolean choose, int index, int pointer, int temp,int filelength, String currentFile, Book[]bookArray,Scanner in) {
 	   
 	view =true;
 	choose=true;
 	index=0;
 	pointer =0;
 	temp=0;
 	
 	   
 	  //while true, we will read the book object 
 	while(view) {
 		
 		
 		System.out.println("Viewing: " +currentFile+ " ("+filelength+" records)");
 		System.out.println();
 		
 		
 		
 		try {
 			while (choose) {
 				
 				
 				System.out.println("Please enter your choice: ");
 				 				try {
 					index =in.nextInt();
 					if(index>0) {
 						
 						for (int i=pointer;i<=(index-1)+ pointer;i++) {

 							if (i ==pointer-Math.abs(index)-1)
 								temp=i;
 						
 					
 					
 						
 						System.out.println(bookArray[i] + "\n (INDEX: "+i+") \n");
 						
 				}
 				pointer=temp;
 			}
 				
 		}
 		catch (InputMismatchException e) {
 			
 		String junk=in.nextLine();
 	
 		continue;
 		
 		}
 				//if n is positive
 		catch(ArrayIndexOutOfBoundsException e) {
 			if (index>0) {
 				
 				pointer=bookArray.length-1;
 				System.out.println("EOF has been reached");
 			//if n is negative	
 			}
 			if (index<0) {
 				pointer=0;
 				System.out.println("BOF has been reached");
 				
 				
 				
 			}
 			continue;
 		}
 			
 			
 		}
 	
 			
 		}
 		
 		catch(InputMismatchException e) {
 			
 			String junk=in.nextLine();
 			continue;
 			
 		}
 		String junk=in.nextLine();
 		view=false;
 	}   
 	  
 	   
    }

    /**
     * dopart3 method
     */
        public static void do_part3() {
    		
        	
        	
        	
        	//all the input files that we will read from
        
        	ObjectInputStream cartoonsbinary=null;
        	ObjectInputStream hobbiesbinary=null;
        	ObjectInputStream moviesbinary=null;
        	ObjectInputStream musicbinary=null;
        	ObjectInputStream nostalgiabinary=null;
        	ObjectInputStream oldbinary=null;
        	ObjectInputStream sportsbinary=null;
        	ObjectInputStream trainsbinary=null;
        	
        	
        	
        	//all the book objects
        	Book[] cartoons=null;
        	Book[] hobbies =null;
        	Book[] movies=null;
        	Book[] music=null;
        	Book[]nostalgia=null;
        	Book[]oldtime=null;
        	Book[]sports=null;
        	Book[]train=null;
        	
        	
        	try {
        		cartoonsbinary=new ObjectInputStream(new FileInputStream("src/Cartoons_Comics.csv.ser.txt"));
        		hobbiesbinary=new ObjectInputStream(new FileInputStream("src/Hobbies_Collectibles.csv.ser.txt"));
        		moviesbinary=new ObjectInputStream(new FileInputStream("src/Movies_TV_Books.csv.ser.txt"));
        		musicbinary=new ObjectInputStream(new FileInputStream("src/Music_Radio_Books.csv.ser.txt"));
        		
        		nostalgiabinary=new ObjectInputStream(new FileInputStream("src/Nostalgia_Eclectic_Books.csv.ser.txt"));
        		oldbinary=new ObjectInputStream(new FileInputStream("src/Old_Time_Radio_Books.csv.ser.txt"));
        		sportsbinary=new ObjectInputStream(new FileInputStream("src/Sports_Sports_Memorabilia.csv.ser.txt"));
        		trainsbinary=new ObjectInputStream(new FileInputStream("src/Trains_Planes_Automobiles.csv.ser.txt"));
        		
        		
        		
        		cartoons=(Book[])cartoonsbinary.readObject();
        		hobbies=(Book[])hobbiesbinary.readObject();
        		movies=(Book[])moviesbinary.readObject();
        		music=(Book[])musicbinary.readObject();
        		
        		nostalgia=(Book[])nostalgiabinary.readObject();
        		oldtime=(Book[])oldbinary.readObject();
        		sports=(Book[])sportsbinary.readObject();
        		train=(Book[])trainsbinary.readObject();
        		
        		
        		
        	}
        	
        	
        	catch(FileNotFoundException e)
        	{
        		System.err.println("Could not find binary file.");
        		System.exit(0);
        	}
        	
        	catch(ClassNotFoundException e)
        	{
        		System.err.println("Could not find an object.");
        		System.exit(0);
        	}
        	
        	catch(IOException e)
        	{
        		System.err.println("Something went wrong.");
        		System.exit(0);
        	}
        	
        	
        	
        	
        String file="Cartoons_Comics_Books.csv.ser";
        int filelength=file.length();
        int selection=0;	
        	
        	
    boolean t=true;
    //display menu that continues until x is pressed.
    //will display the file chose and its length.
            while (t) {
                System.out.println("-----------------------------");
                System.out.println("          Main Menu");
                System.out.println("-----------------------------");
                System.out.println(" v  View the selected file: "+file+" ("+filelength+ " records)");
                      
                System.out.println(" s  Select a file to view");
                System.out.println(" x  Exit");
                System.out.println("-----------------------------");
                System.out.print("Enter Your Choice: ");
                System.out.println("");
                String choice = scan.nextLine();

                switch (choice) {
                
                
                //case viewing
                case "v":
                	
                	
                	boolean view=true;
                	boolean choose=true;
                	int index=0;
                	int pointer=0;
                	int temp=0;
                	
                	
                	
                	switch(file) {
                	
                	
                	//we will use the method
                	case "Cartoons_Comics_Books.csv.ser":
                		
    					viewing(view,choose,index,pointer,temp,filelength,file,cartoons,scan);
                		break;
                		
                	
                	case "Hobbies_Collectibles_Books.csv.ser":
                		viewing(view,choose,index,pointer,temp,filelength,file,hobbies,scan);
                		break;
                		
                	case "Movies_TV.csv.ser":
                		viewing(view,choose,index,pointer,temp,filelength,file,movies,scan);
                		break;
                		
                	case "Music_Radio_Books.csv.ser":
                		viewing(view,choose,index,pointer,temp,filelength,file,music,scan);
                		break;
                		
                	case "Nostalgia_Eclectic_Books.csv.ser":
                		viewing(view,choose,index,pointer,temp,filelength,file,nostalgia,scan);
                		break;
                		
                	case "Old_Time_Radio.csv.ser":
                		viewing(view,choose,index,pointer,temp,filelength,file,oldtime,scan);
                		break;
                		
                	case "Sports_Sports_Memorabilia.csv.ser":
                		viewing(view,choose,index,pointer,temp,filelength,file,sports,scan);
                		break;
                		
                	case "Trains_Planes_Automobiles.csv.ser":
                		viewing(view,choose,index,pointer,temp,filelength,file,train,scan);
                		break;
                		
                	
                	default:
                		
                	System.out.println("Sorry this is not a valid choice");
                	
                	
                	break;
                	}
                	
                	
               	
                case "s":  
                	//case s, we will choose the file.
                	boolean istrue=true;
                	
                	while(istrue) {
                	
                	
                	System.out.println("----------------------------");
                	System.out.println("	   File Sub-Menu						");
                	System.out.println("----------------------------");
                	
                	
                	System.out.println(FILENAMES[0]+cartoons.length+" records");
                	System.out.println(FILENAMES[1]+hobbies.length+" records");
                	System.out.println(FILENAMES[2]+movies.length+" records");
                	System.out.println(FILENAMES[3]+music.length+" records");
                	System.out.println(FILENAMES[4]+nostalgia.length+" records");
                	System.out.println(FILENAMES[5]+oldtime.length+" records");
                	System.out.println(FILENAMES[6]+sports.length+" records");
                	System.out.println(FILENAMES[7]+train.length+" records");
                	System.out.println(FILENAMES[8]);
                	
                	System.out.println("----------------------------");
                	
                	System.out.println("");
                	System.out.println("Enter your choice");
                	
                	
                	try {
						selection = scan.nextInt();
						
					}
					catch(InputMismatchException e) {
						String junk = scan.nextLine();
						continue;
					}
					String junk = scan.nextLine();
					istrue = false;
					
				
                
                	switch(selection){
    				
    				case 1:
    					file = "Cartoons_Comics_books.csv.ser";
    					filelength = cartoons.length;
    					break;
    					
    				case 2:
    					file = "Hobbies_Collectibles_Books.csv.ser";
    					filelength = hobbies.length;
    					break;
    				
    				case 3:
    					file = "Movies_TV.csv.ser";
    					filelength = movies.length;
    					break;
    				
    				case 4:
    					file = "Music_Radio_Book.csv.ser";
    					filelength = music.length;
    					break;
    				
    				case 5:
    					file = "Nostalgia_Eclectic_Books.csv.ser";
    					filelength = nostalgia.length;
    					break;
    					
    				case 6:
    					file = "Old_Time_Radio.csv.ser";
    					filelength = oldtime.length;
    					break;
    					
    				case 7:
    					file = "Sports_Sports_Memorabilia.csv.ser";
    					filelength = sports.length;
    					break;
    					
    				case 8:
    					file = "Trains_Planes_Automobiles.csv.ser";
    					filelength =train.length;
    					break;
    					
    				//USER EXITS BACK TO THE MENU
    				case 9:
    					break;
                	}
                	
                	
                	}
                	break;
                	//everything ends.
                case "x": 
                	System.out.println("Exit");
            
                	System.out.println("Thank you and have a good day!");
                	System.out.println("The program will now end.");
                
                	t=false;
                	System.exit(0);
                	break;
                
                case "X": 
                	System.out.println("Exit");
            
                	System.out.println("Thank you and have a good day!");
                	System.out.println("The program will now end.");
                
                	t=false;
                	System.exit(0);	
                	break;
                
                default: 
                	System.out.println("Sorry this is not a valid choice");
                
                }
               
                continue;
            }
        
            try {
    			cartoonsbinary.close();
    			hobbiesbinary.close();
    			moviesbinary.close();
    			musicbinary.close();
    			nostalgiabinary.close();
    			oldbinary.close();
    			sportsbinary.close();
    			trainsbinary.close();
    		}
    		catch(IOException e) {
    			System.out.println("Couldn't close binary reader");
    			System.exit(0);
    		}
        
        	}

      }
        
