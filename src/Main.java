

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import com.google.zxing.WriterException;

public class Main extends Application{
    private TreeView<String> treeView;
    private File selectedFile;
    private File selectedFolder;
    private Library library;
    private BarCode barCode;
    private boolean fileChoosen = false;
    private int shiftY = 100;
    private String sProgress = "";
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage){
        Group root = new Group();
        Button button = new Button("File");
        Button folder = new Button("Folder");
        Button file = new Button("Create File");
        Button create = new Button("Create Images");
        Button createImage = new Button("Create Image");
        button.setLayoutX(20);
        button.setLayoutY(50);
        createImage.setLayoutX(150);
        createImage.setLayoutY(300);
        create.setLayoutX(250);
        create.setLayoutY(120);
        file.setLayoutX(50);
        file.setLayoutY(120);
        folder.setLayoutX(20);
        folder.setLayoutY(10);
        Text startIndex = new Text("Enter start index:");
        Text index = new Text("Enter index: ");
        Text name = new Text("Enter name: ");
        name.setX(20);
        name.setY(270);
        index.setX(20);
        index.setY(220);
        startIndex.setX(20);
        startIndex.setY(100);
        TextField newName = new TextField();
        TextField newFile = new TextField();
        TextField individual = new TextField();
        newName.setLayoutX(85);
        newName.setLayoutY(255);
        newFile.setLayoutX(115);
        newFile.setLayoutY(85);
        individual.setLayoutX(85);
        individual.setLayoutY(205);
        Text text = new Text("File: ");
        Text folderText = new Text("Folder: ");
        text.setX(80);
        text.setY(70);
        folderText.setX(80);
        folderText.setY(30);
        FileChooser fileChooser = new FileChooser();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new ExtensionFilter("All Files", "*.*"));
        button.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event){
        		selectedFile = fileChooser.showOpenDialog(primaryStage);
        		text.setText("File: " + selectedFile.getAbsolutePath());
        	}
        });
        if (selectedFile != null) {
           
        }
        folder.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event){
        		selectedFolder = directoryChooser.showDialog(primaryStage);
        		folderText.setText("Folder: " + selectedFolder.getAbsolutePath());
        	}
        });
        file.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event){
        		if(selectedFile != null && startIndex != null && selectedFolder != null){
        			
        			library = new Library(selectedFile.getAbsolutePath(), Long.parseLong(newFile.getText()), selectedFolder.getAbsolutePath());
        			//barCode = new BarCode(library.getBooks());
        			
        			try{
        				library.execute();
        				//barCode.createCodes();
        			} catch(FileNotFoundException e){
        				
        			} catch(IOException e){
        				
        			} //catch(WriterException e){
        				
        			//}
        			
        		}
        	}
        });
        create.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event){
        		if(selectedFile != null && startIndex != null && selectedFolder != null){
        			
        			library = new Library(selectedFile.getAbsolutePath(), Long.parseLong(newFile.getText()), selectedFolder.getAbsolutePath());
        			barCode = new BarCode(library.getBooks(), selectedFolder.getAbsolutePath());
        			
        			try{
        				library.execute();
        				barCode.createCodes();
        			} catch(FileNotFoundException e){
        				
        			} catch(IOException e){
        				
        			} catch(WriterException e){
        				
        			}
        			
        		}
        	}
        });
        createImage.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event){
        		if(individual != null && newName != null && selectedFolder != null){
        			barCode = new BarCode(selectedFolder.getAbsolutePath());
        			library = new Library(selectedFolder.getAbsolutePath());
        			Book book = new Book(newName.getText(), BarCode.checkSum(100000000000L + Long.parseLong(individual.getText())));
        			try{
        				BarCode.generateBarCode(book);
        				Library.printIndividual(book);
        			} catch(IOException e){
        				
        			} catch(WriterException e){
        				
        			}
        		}
        	}
        });
        
        
        root.getChildren().addAll(button, text, newFile, startIndex, create, file, folderText, individual, index, name, newName, createImage, folder);
        primaryStage.setTitle("Barcode Creator");
        primaryStage.setScene(new Scene(root,400,400));
        primaryStage.show();
    }
}
