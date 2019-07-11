package Sempel.FormDirectory.DirectoruViewDebt;

public class PersenViewDebt {

    private Integer idView;
    private String View;
    private Boolean deletedView;

    PersenViewDebt(Integer idView, String nameView, Boolean deletedView){
        this.idView = idView;
        this.View = nameView;
        this.deletedView = deletedView;
    }

    public void setId(Integer value){ this.idView = value;}
    public Integer getId(){return this.idView;}

    public void setView(String value){this.View = value;}
    public String getView(){return  this.View;}

    public void setDeleted(boolean value){this.deletedView = value;}
    public boolean getDeleted(){return deletedView;}


}
