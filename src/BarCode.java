import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.oned.*;

public class BarCode{

	private String progress;
	private ArrayList<Book> library = null;
	private static String folder;
	
	public BarCode(ArrayList<Book> books, String folder){
		library = new ArrayList<Book>();
		this.folder = folder;
		for(Book book: books){
			library.add(book);
		}
	}
	public BarCode(String folder){
		this.folder = folder;
	}
	
	public void createCodes() throws WriterException, IOException, FileNotFoundException{
		progress = " ";
		//ArrayList<Book> books = Library.readFromList("C:\\Users\\Paul\\Desktop\\Speech Archive\\DI second shelf.txt");
		//archive.createBookArchive("C:\\Users\\Paul\\Documents\\hiA-C.txt");
		

		for(int i = 0; i < library.size(); i++){
			generateBarCode(library.get(i));
		}
		progress = " Completed";
		
		
		
		
	}
	public static ArrayList<String> getList(String file) throws FileNotFoundException{
		ArrayList<String> books = new ArrayList<String>();
		Scanner s = new Scanner(new File(file));
		while(s.hasNext()){
			String data = s.nextLine();
			books.add(data);
		}
		s.close();
		return books;
	}
	public static void generateBarCode(Book book) throws WriterException, IOException, FileNotFoundException{

		String name = book.getName();
		long code = book.getCode();
		//long at = checkSum(code);

		int width  = 250;
		int height = 65; 
		String imgFormat = "png";
		String text = Long.toString(code);
		BitMatrix bitMatrix = new EAN13Writer().encode(text, BarcodeFormat.EAN_13, width, height);
		MatrixToImageWriter.writeToStream(bitMatrix, imgFormat, new FileOutputStream(new  File(folder + "\\" + name + "_.png")));
		//System.out.println(code);
	}
	public static long checkSum(long code){
		String a = Long.toString(code);
		int sum1 = 0;
		int sum2 = 0;
		for(int i = a.length() - 1; i >= 0; i--){
			if(i % 2 != 0){
				int num = Integer.parseInt(a.substring(i, i + 1));
				sum1 += num;
			}
		}
		for(int i = a.length() - 2; i >= 0; i--){
			if(i % 2 == 0){
				int num = Integer.parseInt(a.substring(i, i + 1));
				sum2 += num;
			}
		}
		int sum = sum1 * 3 + sum2;
		int checkSum = 10 - (sum % 10);
		if (checkSum == 10) checkSum = 0;
		a = a + Integer.toString(checkSum);
		return Long.parseLong(a);
	}
	public String getProgress(){
		return progress;
	}
}