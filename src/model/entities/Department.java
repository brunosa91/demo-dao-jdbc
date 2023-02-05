package model.entities;

import java.io.Serializable;
import java.util.Objects;


public class Department implements Serializable {
	
	/* Implementes serializable
	 * Para os objetos serem transformados em sequ�ncia de bytes, para que o objeto
	 * possa ser gravado em arquivos, trafeg�vel em rede..
	  
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	// construtor pad�o
	
	public Department() {
		
	}
	
	//construtor com argumentos

	public Department(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	//getter and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/* Hashcode and equal para que o objeto seja comparado pelo conte�do 
		e n�o pela refer�ncia*/

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(id, other.id);
	}
	//toStrin facilita a impress�o dos valores do objeto quando for testado.
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
