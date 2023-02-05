package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	/* Essa classe � respons�vel por inst�nciar das daos, ela ter� um m�todo que
	 ir� retornar o tipo da interface mas internamente vai inst�ncia uma implementa��o */
	
	public static SellerDao createSellerDao(){
		
		/*inst�ncia da implementa��o, quando for inst�nciar na main, basta chamar SellerDao
		e dar new Dao factory que ir� chamar a interface que tem a implementa��o internamente*/
		
		return new SellerDaoJDBC();
		
	};

}
