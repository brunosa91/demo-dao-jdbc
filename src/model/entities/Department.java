package model.entities;

import java.io.Serializable;
import java.util.Objects;


public class Department implements Serializable {
	
	/* Implementes serializable
	 * Para os objetos serem transformados em sequência de bytes, para que o objeto
	 * possa ser gravado em arquivos, trafegável em rede..
	  
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	// construtor padão
	
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
	
	/* Hashcode and equal para que o objeto seja comparado pelo conteúdo 
		e não pela referência*/

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
	//toStrin facilita a impressão dos valores do objeto quando for testado.
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
