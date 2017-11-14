package hello;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Arrays;
public class food {
	public String item;
	public int price;
	
	food()
	{
		item="";
		price=0;
	}
	void setitem(String x)
	{
		item=x;
	}
	String getitem()
	{
		return item;
	}
	void setPrice(int y)
	{
		price=y;
	}
	int getprice()
	{
		return price;
	}
	public void main()
	{
		System.out.println("iam here ");
	}
}
