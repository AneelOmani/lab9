package hello;

public class orderdetail {
	public int orderid;
	public String itemdesc;
	orderdetail()
	{
		orderid=0;
		itemdesc="";
	}
	void setorderid(int x)
	{
		orderid=x;
	}
	int getorderid()
	{
		return orderid;
	}
	void setitemdesc(String y)
	{
		itemdesc=y;
	}
	String getitemdesc()
	{
		return itemdesc;
	}

}
