package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	//conex�o com db e deixando o conn dipon�vel em toda classe

	public SellerDaoJDBC(Connection conn) {

		this.conn = conn;

	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st =  conn.prepareStatement("SELECT seller.*,department.Name as DepName \r\n"
					+ "FROM seller INNER JOIN department \r\n"
					+ "ON seller.DepartmentId = department.Id \r\n"
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();	
			/* quando a query � executada o result set aponta para posi��o zero
			e nela n�o h� objeto, ent�o � necess�rio fazer uma l�gica para que
			o rs olhe para posi��o 1
			*/
			
			if(rs.next()) {
				/* caso rs.next de true e fale que tem um objeto, vamos instanciar
				A entidade Department pois al�m de vir o Seller tem que vir tbm
				qual department ele est� associado
				*/
				Department dep =  new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				
				/*Agora ser� inst�nciado Seller e apontando pro department
				 * 
				 * */
				
				Seller obj = new Seller();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBasesalary(rs.getDouble("BaseSalary"));
				obj.setBirthdate(rs.getDate("BirthDate"));
				obj.setDepartment(dep);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			// CONEX�O MANT�M ABERTA POIS PODE FAZER OUTRAS OPERA��ES
			// FECHA CONEX�O NO PRAGRAM
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
