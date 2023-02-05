package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	//conexão com db e deixando o conn diponível em toda classe

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
			/* quando a query é executada o result set aponta para posição zero
			e nela não há objeto, então é necessário fazer uma lógica para que
			o rs olhe para posição 1
			*/
			
			if(rs.next()) {
				/* caso rs.next de true e fale que tem um objeto, vamos instanciar
				A entidade Department pois além de vir o Seller tem que vir tbm
				qual department ele está associado
				*/
				Department dep =  instantiateDepartment(rs);
				/*Agora será instânciado Seller e apontando pro department
				 * 
				 * */
				
			    Seller obj = instantiateSeller(rs,dep);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			// CONEXÃO MANTÉM ABERTA POIS PODE FAZER OUTRAS OPERAÇÕES
			// FECHA CONEXÃO NO PRAGRAM
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		//acima foi propaga a exceção que já foi tratada
		
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
		
	}
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBasesalary(rs.getDouble("BaseSalary"));
		obj.setBirthdate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	@Override
	public List<Seller> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st =  conn.prepareStatement("SELECT seller.*,department.Name as DepName \r\n"
					+ "FROM seller INNER JOIN department \r\n"
					+ "ON seller.DepartmentId = department.Id\r\n"
					+ "ORDER BY Name");
			

			rs = st.executeQuery();	
			
			List<Seller> list = new ArrayList<>();
			
			// no map vai ser guardado os departamentos para testar no while
			// se o mesmo já existe para não trazer dois objetos repetidos
			Map<Integer, Department> map = new HashMap<>();
			/* quando a query é executada o result set aponta para posição zero
			e nela não há objeto, então é necessário fazer uma lógica para que
			o rs olhe para posição 1
			*/
			// nesse caso é usado o while porque o pode ter mais de um resultado
			// aí tem que percorrer para pegar todos
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				
				/* caso rs.next de true e fale que tem um objeto, vamos instanciar
				A entidade Department pois além de vir o Seller tem que vir tbm
				qual department ele está associado
				*/
				
				/*Agora será instânciado Seller e apontando pro department
				 * 
				 * */
				
			    Seller obj = instantiateSeller(rs,dep);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			// CONEXÃO MANTÉM ABERTA POIS PODE FAZER OUTRAS OPERAÇÕES
			// FECHA CONEXÃO NO PRAGRAM
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st =  conn.prepareStatement("SELECT seller.*,department.Name as DepName \r\n"
					+ "FROM seller INNER JOIN department \r\n"
					+ "ON seller.DepartmentId = department.Id\r\n"
					+ "WHERE DepartmentId = ?\r\n"
					+ "ORDER BY Name");
			
			st.setInt(1, department.getId());
			rs = st.executeQuery();	
			
			List<Seller> list = new ArrayList<>();
			
			// no map vai ser guardado os departamentos para testar no while
			// se o mesmo já existe para não trazer dois objetos repetidos
			Map<Integer, Department> map = new HashMap<>();
			/* quando a query é executada o result set aponta para posição zero
			e nela não há objeto, então é necessário fazer uma lógica para que
			o rs olhe para posição 1
			*/
			// nesse caso é usado o while porque o pode ter mais de um resultado
			// aí tem que percorrer para pegar todos
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				
				/* caso rs.next de true e fale que tem um objeto, vamos instanciar
				A entidade Department pois além de vir o Seller tem que vir tbm
				qual department ele está associado
				*/
				
				/*Agora será instânciado Seller e apontando pro department
				 * 
				 * */
				
			    Seller obj = instantiateSeller(rs,dep);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			// CONEXÃO MANTÉM ABERTA POIS PODE FAZER OUTRAS OPERAÇÕES
			// FECHA CONEXÃO NO PRAGRAM
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
	}

}
