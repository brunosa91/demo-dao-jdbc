package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	/* Essa classe é responsável por instânciar das daos, ela terá um método que
	 irá retornar o tipo da interface mas internamente vai instância uma implementação */
	
	public static SellerDao createSellerDao(){
		
		/*instância da implementação, quando for instânciar na main, basta chamar SellerDao
		e dar new Dao factory que irá chamar a interface que tem a implementação internamente*/
		
		return new SellerDaoJDBC();
		
	};

}
