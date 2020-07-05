package Sempel.Reports.PriceNomen;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import sql.SettingConnectSQL;

public class DataReportPriceNomen {

	 	private String PATH           = "/Sempel/Reports/PriceNomen";
	    private String REPORT_pdf     = "/report.pdf";
	    private String REPORT_pattern = "/ReportsPrice_A4.jrxml";

	    private File                        				   reportPattern;
	    private Map<String, Object>         				   parameters;
	    private JRBeanCollectionDataSource  				   beanColDataSource;
	    private ObservableList<PersenReportPriceNomen>         dataBeanList;

	    private JasperDesign jasperDesign;
	    private JasperReport jasperReport;
	    private JasperPrint  jasperPrint ;	
	

	    public void creatREportPriceNomen(SettingConnectSQL SetCon) throws SQLException, JRException {
	    	
	    	ObservableList<PersenReportPriceNomen> dataBeanList = PersenReportPriceNomen.getMassivReportPrice(SetCon);
	    	
	    	parameters = new HashMap<String, Object>();
	    	parameters.put("DataReport", new Date());
	    	
	    	reportPattern = new File("/Sempel/Reports/PriceNomen/ReportsPrice_A4.jrxml");
	    	jasperDesign  = JRXmlLoader.load(reportPattern);
	    	jasperReport  = JasperCompileManager.compileReport(jasperDesign);
	        jasperPrint   = JasperFillManager.fillReport(jasperReport, 
	                                                     parameters, 
	                                                     beanColDataSource);
	        
	        JasperExportManager.exportReportToPdfFile(jasperPrint, 
	        		"/Sempel/Reports/PriceNomen" + REPORT_pdf);
	    	
	    }

}
