package com.priyanshbalyan.someprojects;

public class Products
{
	int _id ;
	String _productname ;
	
	public Products(){
		
	}
	
	public Products(String productname){
		this._productname = productname ;
	}

	public void setId(int id)
	{
		this._id = id;
	}

	public void setProductname(String productname)
	{
		this._productname = productname;
	}
	
	public int getId()
	{
		return _id;
	}

	public String getProductname()
	{
		return _productname;
	}

	
}
