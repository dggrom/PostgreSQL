package Sempel.FormDirectory.DirectoryKontragent;

public class PersenKontragent {

    private Integer id;
    private String name;
    private Boolean deleted;

    public PersenKontragent(Integer id, String name, Boolean deleted){
        this.id = id;
        this.name = name;
        this.deleted = deleted;
    }

    public void setId(Integer value){ id = value;}
    public Integer getId(){ return id;}

    public void setName(String value){ name = value;}
    public String getName(){ return name;}

    public void setDeleted(Boolean value){deleted = value;}
    public Boolean getDeleted(){ return  deleted;}
}
