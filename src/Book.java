public class Book{

	private String name;
	private long code;
	private String status;

	public Book(String name){
		setName(name);
	}
	public Book(String name, long code){
		setName(name);
		setCode(code);
		status = "in";
		
	}
	public void setName(String name){
		this.name = name;
	}
	public void setCode(long code){
		this.code = code;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getName(){
		return name;
	}
	public long getCode(){
		return code;
	}
	public String getStatus(){
		return status;
	}
	public String toLine(){
		String spaceLength = "                                                                                    ";
		return "Book: " + name + spaceLength.substring(0, spaceLength.length() - name.length()) + "Code: " + code;
	}
	public String toString(){

		return "(" + code + ",\"" + name + "\",\"" + status + "\"),";
	}

}