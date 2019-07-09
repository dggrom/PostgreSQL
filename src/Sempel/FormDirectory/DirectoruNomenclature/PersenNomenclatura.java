package Sempel.FormDirectory.DirectoruNomenclature;

public class PersenNomenclatura {

	private Integer id;
	private String name;
	private boolean deleted;
	
	public PersenNomenclatura(Integer id, String name, boolean deleted) {
		this.id = id;
		this.name = name;
		this.deleted = deleted;
	}
	
	public void setId(Integer value) {id = value;}
	public Integer getId() {return id;}
	
	public void setName(String value) {name = value;}
	public String getName() {return name;}
	
	public void setDeleted(boolean value) {deleted = value;}
	public boolean getDeleted() {return deleted;}
	
}
