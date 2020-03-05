import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class MyTime implements Time{
	
	public abstract void display();

	public String getTime(){
		String t = "";
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		t = dateFormat.format(cal.getTime());
		return t;
	}

	public String getDate(){
		String t = "";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/YY");
		Calendar cal = Calendar.getInstance();
		t = dateFormat.format(cal.getTime());
		return t;
	}

}