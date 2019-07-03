package Sempel.FormDirectory.DirectoryKontragent;

public class PersenKontragent {

    private int id;
    private String name;
    private Boolean deleted;

    PersenKontragent(Integer id, String name, Boolean deleted){
        this.id = id;
        this.name = name;
        this.deleted = deleted;
    }

    public void setId(int value){ id = value;}
    public int getId(){ return id;}

    public void setName(String value){ name = value;}
    public String getName(){ return name;}

    public void setDeleted(Boolean value){deleted = value;}
    public Boolean getDeleted(){ return  deleted;}
}
