package Sempel.FormDirectory.DirectoryKontragent;

public class PersenKontragent {

    private String id;
    private String name;
    private Boolean deleted;

    PersenKontragent(String id, String name, Boolean deleted){
        this.id = id;
        this.name = name;
        this.deleted = deleted;
    }

    public void setId(String value){ id = value;}
    public String getId(){ return id;}

    public void setName(String value){ name = value;}
    public String getName(){ return name;}

    public void setDeleted(Boolean value){deleted = value;}
    public Boolean getDeleted(){ return  deleted;}
}
