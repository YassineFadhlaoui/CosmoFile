
public class GetFileExtension {
public String getExtension(String File){
	String s="";
	int index =(File.lastIndexOf("."));
	for(int i=index;i<File.length();i++){
		s+=File.charAt(i);
	}
	return s;
}
}
