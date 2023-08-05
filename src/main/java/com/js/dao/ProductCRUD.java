package com.js.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.js.dto.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductCRUD {
	
	 public static final String URL="jdbc:mysql://localhost:3306/product_store";
     public static final String USER="root";
     public static final String PWD="tanu";
     public static final String PATH="com.mysql.cj.jdbc.Driver";
     
     public static int insertProduct(Product p) {
    	 Connection c=null;
    	 String query = "insert into product values(?,?,?,?,?)";
    	 try {
			 Class.forName(PATH);
			 c = DriverManager.getConnection(URL,USER,PWD);
			 PreparedStatement ps = c.prepareStatement(query);
			 ps.setInt(1, p.getId());
			 ps.setString(2, p.getName());
			 ps.setString(3, p.getBrand());
			 ps.setDouble(4, p.getPrice());
			 ps.setInt(5, p.getQuantity());
			 return ps.executeUpdate();
		} catch (ClassNotFoundException e) {
		      e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
    	 finally {
    		 try {
    			 c.close();
    		 }catch(SQLException e){
    			 e.printStackTrace();
    		 }
    	 }
    	 return 0;
    }

	
     
     public int deleteProductById(int id) {
    	String query="delete from product where id=?" ;
    	Connection c=null;
    	try {
    		Class.forName(PATH);
			 c = DriverManager.getConnection(URL,USER,PWD);
			 PreparedStatement ps = c.prepareStatement(query);
			 ps.setInt(1, id);
			 return ps.executeUpdate();
    	}catch(ClassNotFoundException e){
    		e.printStackTrace();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	finally {
   		 try {
   			 c.close();
   		 }catch(SQLException e){
   			 e.printStackTrace();
   		 }
   	 }
		return 0;
	}

	public int updateProduct(int id,Product p) {
		String query="update product set name=?,brand=?,price=?,quantity=? where id=?";
		Connection c=null;
		try {
			Class.forName(PATH);
			c=DriverManager.getConnection(URL,USER,PWD);
			PreparedStatement ps=c.prepareStatement(query);
			ps.setString(1, p.getName());
			ps.setString(2, p.getBrand());
			ps.setDouble(3, p.getPrice());
			ps.setInt(4, p.getQuantity());
			ps.setInt(5, id);
			 return ps.executeUpdate();
		} catch (ClassNotFoundException e) {
		      e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
    	 finally {
    		 try {
    			 c.close();
    		 }catch(SQLException e){
    			 e.printStackTrace();
    		 }
    	 }
    	 return 0;
    }

	public int deleteByProductPrice(double price) {
		String query="delete from product where price=?";
		Connection c=null;
		try {
			Class.forName(PATH);
			c = DriverManager.getConnection(URL,USER,PWD);
			PreparedStatement ps=c.prepareStatement(query);
			ps.setDouble(1, price);
			return ps.executeUpdate();
		} catch (ClassNotFoundException e) {
		        e.printStackTrace();
		}catch (SQLException e) {
                e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		}
		return 0;
		
	}
	
	public Product GetProductById(int id) {
	String query="Select * from Product where id=?";
	Connection c=null;
	try {
		Class.forName(PATH);
		c=DriverManager.getConnection(URL,USER,PWD);
		PreparedStatement pc=c.prepareStatement(query);
		pc.setInt(1,id);
		ResultSet rs=pc.executeQuery();
		if (rs.next()) {
			Product p=new Product();
			p.setId(rs.getInt(1));
			p.setName(rs.getString(2));
			p.setBrand(rs.getString(3));
			p.setPrice(rs.getDouble(4));
			p.setQuantity(rs.getInt(5));
			return p;
		}
	} catch (ClassNotFoundException e) {
		 e.printStackTrace();
	}catch (SQLException e) {
         e.printStackTrace();
	}
	finally {
		 try {
			 c.close();
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
	 }
	 return null;
	}

	public ArrayList<Product>getAllProducts(){
		ArrayList<Product>a1=new ArrayList<Product>();
		String query="Select * from Product";
		Connection c=null;
		try {
			Class.forName(PATH);
			c=DriverManager.getConnection(URL,USER,PWD);
			PreparedStatement pc=c.prepareStatement(query);
			ResultSet rs=pc.executeQuery();
			while (rs.next()) {
				Product p=new Product();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setBrand(rs.getString(3));
				p.setPrice(rs.getDouble(4));
				p.setQuantity(rs.getInt(5));
				a1.add(p);//adding very product into array list
			}
			return a1;
		} catch (ClassNotFoundException e) {
			 e.printStackTrace();
		}catch (SQLException e) {
	         e.printStackTrace();
		}
		finally {
			 try {
				 c.close();
			 }catch(SQLException e){
				 e.printStackTrace();
			 }
		 }
		 return null;
	}

	public int[] batchExecutionToInsert(ArrayList<Product>products) {
		Connection c=null;
		String query="Insert into product values(?,?,?,?,?)";
		try {
			 Class.forName(PATH);
	         c=DriverManager.getConnection(URL, USER, PWD);
			PreparedStatement ps=c.prepareStatement(query);
			for(Product p:products) {
				ps.setInt(1, p.getId());
				ps.setString(2, p.getName());
				ps.setString(3, p.getBrand());
				ps.setDouble(4, p.getPrice());
				ps.setInt(5, p.getQuantity());
				
				ps.addBatch();
			}
			return ps.executeBatch();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return null;
	}

	
	public int[] batchExecutionToDelete(int[] arr) {
		Connection c=null;
        String query = "delete from product where id = ?";
        try {
            Class.forName(PATH);
            c = DriverManager.getConnection(URL, USER, PWD);
            PreparedStatement ps = c.prepareStatement(query);
            for(int x : arr) {
                ps.setInt(1, x);
               
                ps.addBatch();
            }
            return ps.executeBatch();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



	public int UpdateProduct(int id, Product pro) {
		// TODO Auto-generated method stub
		return 0;
	}
		
		
	}

	

	





