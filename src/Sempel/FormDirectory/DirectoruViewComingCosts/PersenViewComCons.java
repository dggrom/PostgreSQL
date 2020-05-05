package Sempel.FormDirectory.DirectoruViewComingCosts;

public class PersenViewComCons {

    private Integer id;
    private String View;
    private Boolean Deleted;

    public PersenViewComCons(Integer id, String View, Boolean Deleted){
        this.id = id;
        this.View = View;
        this.Deleted = Deleted;
    }

    public void setId(Integer value){id = value;}
    public Integer getId(){return id;}

    public void setView(String value){View = value;}
    public String getView(){return View;}

    public void setDeleted(Boolean value){Deleted = value;}
    public Boolean getDeleted(){return Deleted;}

}
