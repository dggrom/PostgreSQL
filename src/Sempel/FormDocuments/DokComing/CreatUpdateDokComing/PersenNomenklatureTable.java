package Sempel.FormDocuments.DokComing.CreatUpdateDokComing;

public class PersenNomenklatureTable {

	private String idNomen;
	private String nameNomen;
	
	public void setIdNomen(String value) {this.idNomen = value;}
	public String getIdNomen() {return this.idNomen;}
	
	public void setNameNomen(String value) {this.idNomen = value;}
	public String getNameNomen() {return this.nameNomen;}
	
	public PersenNomenklatureTable(String idNomen, String nameNomen) {
		this.idNomen = idNomen;
		this.nameNomen = nameNomen;
	}
}
