import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Base64;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Base64;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.opencv.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;



public class ClientPane extends GridPane{

	 
	Socket s = null;
	//inputStreams
	InputStream is = null;
	BufferedReader br = null;
	//outputStreams
	OutputStream os = null;
	BufferedOutputStream bos = null;
	DataOutputStream dos = null;
 
 
	private ImageView imgViewTop;
 
 
	private Button btnUpload;
	private Button FindFace;
	private TextArea txtArea;
	private ImageView imgView;
	private ImageView imgView2;
	private String ImageName="";
	private  TextField txtArea4 ;
	private  TextField txtArea5 ;
 	int f=0;
 	String ApiName;	
	Mat src;
	Label  ls ;
	
	String path;
	String name;
	 
	public ClientPane(Stage mys) {
		setupGUI();
		
		FindFace.setOnAction(jop->{ ///  facial detection button
			
			f=0;
			 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		        
		        txtArea4.appendText("check Imageresults folder for image Processed ");
		         src =Imgcodecs.imread(path);          /// load image path
		        
		        String myxml ="myxml/lbpcascade_frontalface.xml";
		        CascadeClassifier no= new CascadeClassifier(myxml); // add style sheet for face detection
		        
		        MatOfRect myface = new MatOfRect();
		        no.detectMultiScale(src, myface);
		        
		        for(Rect mr: myface.toArray()) { // create rectangles
		     	   
		        	/// draw red rectangles around faces
		     	   Imgproc.rectangle(src, new Point(mr.x,mr.y), new Point(mr.x+mr.width,mr.y+mr.height) ,new Scalar(3,5,255),3);
		     	   f++;
		        }
		        System.out.print("Number of faces found : "+ f+"\n");
		        String NumberF =Integer.toString(f); /// show number of face to console
		        txtArea5.setText(NumberF);
		        
		        ApiName= name+"VV2.jpg" ;
		        
		                             /// save new images with red rectangles around faces in Imageresults folder
		        Imgcodecs.imwrite("Imageresults/"+ ApiName, src);   
		        
		        
		       Image mu = new Image("file:Imageresults/"+ApiName);  /// set imageview with image
		         
		     	imgView.setImage( mu); /// display image
		     	imgView.maxHeight(100);
				imgView.maxWidth(100);
		     
		     txtArea4.setText("check Imageresults folder for image Processed ");
		    
		     
		});
		
		btnUpload.setOnAction(aa->{ /// upload button
			
			FileChooser fc = new FileChooser();   /// open file chooser window
			File newF = fc.showOpenDialog(null);
			path =newF.getPath();
			name=newF.getName();
			
			fc.setTitle(  "Select Image");
			
			
			System.out.print(name+"   "+ path);
			
			Image readIMG = new Image("file:"+path); /// set imageview with image
			
			imgView.setImage(readIMG);/// display image
			
			imgView.maxHeight(100);
			imgView.maxWidth(100)
 					 
		;
		});
	
		 		
	}
	
	private void setupGUI() { //// set up gui
		
	 	setStyle("-fx-background-color:lightblue;") ; // set color of pane

		setHgap(6);
		setVgap(6);
		setAlignment(Pos.TOP_LEFT);
		 txtArea4= new TextField() ;
		 txtArea5= new TextField() ;
		imgViewTop = new ImageView();
		
		ls = new Label(" Number of face found :");
		 
		
		FindFace = new Button("Find Faces");
		btnUpload = new Button("Upload Image ");
	 
		txtArea = new TextArea();
		txtArea.setPrefHeight(30);
		txtArea5.setEditable(false);
		
		imgView2 = new ImageView();
		
 		
	 Image readIMG = new Image("file:upload.png"); /// set temporary image
		 
		imgView = new ImageView();
		imgView.setImage(readIMG);
		
 
		//add(txtArea, 0,1);
		add(btnUpload,0,2);
	//	add(btnReduceSize,0,10);
	    add(imgView, 0,11);
		//add(imgView2, 0,5);
		add(FindFace, 0,6);
		 
		add(txtArea4,0,7);
	    add(ls,0,8);
		add(txtArea5,0,9);


		
		}

}
