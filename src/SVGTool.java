import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.transcoder.image.TIFFTranscoder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;


public class SVGTool {
	String [] SupportedFormat={".png",".jpeg",".bmp",".gif",".jpg",".tiff",".tif",".raw",".pnm",".pdf",".html",".ico"};
	public void SVGConverterJPEG(String Path,String Target) throws TranscoderException, IOException{
		
	    JPEGTranscoder t = new JPEGTranscoder();
	    File file = new File(Path);
	    t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,new Float(.8));
	    @SuppressWarnings("deprecation")
		String svgURI  = new File(Path).toURL().toString();
	    TranscoderInput input = new TranscoderInput(svgURI);
	    OutputStream ostream = new FileOutputStream(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),".JPEG"));
	    TranscoderOutput output = new TranscoderOutput(ostream);
	    t.transcode(input, output);
	    ostream.flush();
	    ostream.close();
	}
	
public void SVGConverterTIFF(String Path,String Target) throws TranscoderException, IOException{
		
	    TIFFTranscoder t = new TIFFTranscoder();
	    File file = new File(Path);
	    t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,new Float(.8));
	    @SuppressWarnings("deprecation")
		String svgURI  = new File(Path).toURL().toString();
	    TranscoderInput input = new TranscoderInput(svgURI);
	    OutputStream ostream = new FileOutputStream(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),".tiff"));
	    TranscoderOutput output = new TranscoderOutput(ostream);
	    t.transcode(input, output);
	    ostream.flush();
	    ostream.close();
	}
public void SVGConverterPNG(String Path,String Target) throws TranscoderException, IOException{
	
    PNGTranscoder t = new PNGTranscoder();
    File file = new File(Path);
    t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,new Float(.8));
    @SuppressWarnings("deprecation")
	String svgURI  = new File(Path).toURL().toString();
    TranscoderInput input = new TranscoderInput(svgURI);
    OutputStream ostream = new FileOutputStream(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),".PNG"));
    TranscoderOutput output = new TranscoderOutput(ostream);
    t.transcode(input, output);
    ostream.flush();
    ostream.close();
}	
public String SVGToOtherFormat(String Path) throws TranscoderException, IOException{
	PNGTranscoder t = new PNGTranscoder();
    File file = new File(Path);
    t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,new Float(.8));
    @SuppressWarnings("deprecation")
	String svgURI  = new File(Path).toURL().toString();
    TranscoderInput input = new TranscoderInput(svgURI);
    String tmpPath;
    OutputStream ostream = new FileOutputStream(new getSystemInfos().getTmpDir()+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),".PNG"));
    TranscoderOutput output = new TranscoderOutput(ostream);
    t.transcode(input, output);
    tmpPath=new getSystemInfos().getTmpDir()+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),".PNG");
    ostream.flush();
    ostream.close();
   
	return tmpPath;	
}
public void SvgFinalConversion(String Path,String Target,String Extension){
	if(Extension.equalsIgnoreCase(".jpg")||Extension.equalsIgnoreCase(".bmp")||Extension.equalsIgnoreCase(".jpeg")||Extension.equalsIgnoreCase(".gif")||Extension.equalsIgnoreCase(".pnm")||Extension.equalsIgnoreCase(".tif")||Extension.equalsIgnoreCase(".tiff")||Extension.equalsIgnoreCase(".raw")){
		BufferedImage bufferedImage;
	
	try {
		
		File getTmpFile= new File(SVGToOtherFormat(Path));
		bufferedImage = ImageIO.read(getTmpFile);
		BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
				bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
				newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
				ImageIO.write(newBufferedImage, Extension.replace(".",""), new File(Target+"/"+getTmpFile.getName().replace(new GetFileExtension().getExtension(getTmpFile.getName()),Extension)));
				getTmpFile.delete();
	} catch (IOException | TranscoderException e) {
			e.printStackTrace();
 
		}
	}
}
public void SVGToPdf(String Path,String Target){
	try {
		String tmpFile=SVGToOtherFormat(Path);
		
		File file = new File(tmpFile);
		String NewName=String.valueOf(Target+"/"+file.getName().replace(new GetFileExtension().getExtension(file.getName()),""));
		Document document=new Document();
		 BufferedImage bufferedImage;
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(NewName+".pdf"));
		Image image1 = Image.getInstance(tmpFile); 
		 bufferedImage = ImageIO.read(new File(tmpFile));
			float absci=bufferedImage.getWidth();
			float ord=bufferedImage.getHeight();
	document.setPageSize(new Rectangle(absci,ord));
		document.open();
		image1.setAbsolutePosition(0f,0f);
 	    document.add(image1);
 	    document.close();
 	    writer.close();
 	    file.delete();
	} catch (Exception e) {
		//ERROR CLASS HERE 
	}
}
public void SVGToICO(String Path,String Target){
	try {
		String tmpico=SVGToOtherFormat(Path);
		ImageTool IT = new ImageTool();
		IT.ConvertToIco(tmpico, Target);
		File tmpicofile = new File(tmpico);
		tmpicofile.delete();
	} catch (TranscoderException | IOException e) {
		e.printStackTrace();
	}
	
}
public void SVGConverter(String Path,String Target,String SelectedExt){
	try {
			if(SelectedExt.equalsIgnoreCase(".jpeg")){SVGConverterJPEG(Path,Target);
			}else if(SelectedExt.equalsIgnoreCase(".tiff")){SVGConverterTIFF(Path,Target);
			}else if(SelectedExt.equalsIgnoreCase(".png")){SVGConverterPNG(Path,Target);
			}else if(SelectedExt.equalsIgnoreCase(".tif")||
					SelectedExt.equalsIgnoreCase(".gif")||
					SelectedExt.equalsIgnoreCase(".raw")||
					SelectedExt.equalsIgnoreCase(".pnm")||
					SelectedExt.equalsIgnoreCase(".bmp")||
					SelectedExt.equalsIgnoreCase(".jpg")){
				SvgFinalConversion(Path,Target,SelectedExt);
			}
			else if(SelectedExt.equalsIgnoreCase(".pdf")){
				SVGToPdf(Path,Target);
			}else if(SelectedExt.equalsIgnoreCase(".html")){
				ImageTool IT = new ImageTool();
				IT.ConvertImgToHtml(Path,Target);
			}else if(SelectedExt.equalsIgnoreCase(".ico")){
			SVGToICO(Path,Target);
			}
			
			}
	catch (TranscoderException | IOException fe) {
					fe.printStackTrace();
				} 
			
	
} 
	public  void InisilizeBox(Vector<String> format,String Extension){
		format.removeAllElements();
		for(int i =0;i<SupportedFormat.length;i++){
			format.addElement(SupportedFormat[i]);}
 }  
}
