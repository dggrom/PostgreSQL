package Sempel.FormDirectory;

public class PersenKontragent {

    private int id;
    private String name;

    PersenKontragent(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public void setId(int value){ id = value;}
    public int getId(){ return id;}

    public void setName(String value){ name = value;}
    public String getName(){ return name;}
}
