import java.util.*;
import java.io.*;

public class Library{

	private String path;
	private long startIndex;
	private static String folder;
	private ArrayList<Book> library = null;

	public Library(String path, long startIndex, String folder){
		library = new ArrayList<Book>();
		this.path = path;
		this.folder = folder;
		this.startIndex = startIndex;
		readFromList(path);
	}
	public Library(String folder){
		this.folder = folder;
	}
	public void readFromList(String file) {
		//ArrayList<Book> books = new ArrayList<Book>();

		try{
			long code = 100000000000L + startIndex;
			Scanner s = new Scanner(new File(file));
			while(s.hasNext()){
				String name = s.nextLine().toUpperCase();
				Book book = new Book(name, BarCode.checkSum(code));
				library.add(book);
				code++;
			}
			s.close();
		} catch(FileNotFoundException e){}
		
	}
	
	
	public void execute() throws FileNotFoundException{
		File data = new File(folder + "\\data.txt");
		PrintStream out = new PrintStream(data);
		//ArrayList<Book> books = readFromList(path);
		ArrayList<String> errors = getErrors(library);
		out.println("***THIS IS THE DATA FILE***");
		out.println();
		out.println("Number of books " + library.size());
		out.println();
		out.println("Number of duplicates " + errors.size());
		for(int i = 0; i < errors.size(); i++){
			out.println(errors.get(i));
		}
		out.println();
		for(int i = 0; i < library.size(); i++){
			out.println(library.get(i));
		}
		out.println();
		for(int i = 0; i < library.size(); i++){
			out.println(library.get(i).toLine());
		}
	}
	public static void printIndividual(Book book) throws FileNotFoundException{
		File data = new File(folder + "\\data.txt");
		PrintStream out = new PrintStream(data);
		//ArrayList<Book> books = readFromList(path);
		out.println("***THIS IS THE DATA FILE***");
		out.println();
		out.println(book);

	}
	
	public static void print(ArrayList<Book> books) {

		try{
			File f = new File("sqbooklib.txt");
			PrintStream out = new PrintStream(f);
			for(Book book: books){
				out.println(book);
			}
		} catch(FileNotFoundException e){}
	}
	public ArrayList<String> getErrors(ArrayList<Book> books){
		Set<String> test = new HashSet<String>();
		ArrayList<String> fin = new ArrayList<String>();
		ArrayList<Book> actual = new ArrayList<Book>();
		for(int i = 0; i < books.size(); i++){
			if(!test.add(books.get(i).getName())){
				fin.add(i + 1 + " " + books.get(i).getName());
				
			}
			

		}
		return fin;
	}
	public ArrayList<Book> getBooks(){
		return library;
	}


}