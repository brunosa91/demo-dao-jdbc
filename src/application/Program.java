package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		// chamou a factory que chama a interface com a implementação interna.
		SellerDao SellerDao = DaoFactory.createSellerDao();
		
		
		Seller seller = SellerDao.findById(3);
		
		System.out.println(seller);
		
		
	}

}
