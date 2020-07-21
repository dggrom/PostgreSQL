package Sempel.Reports.PriceNomen;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javafx.collections.ObservableList;
import sql.SettingConnectSQL;

public class DataReportPriceNomen {

	    private File                        				   reportPattern;
	    private Map<String, Object>         				   parameters;
	    private JRBeanCollectionDataSource  				   beanColDataSource;
	    private ObservableList<PersenReportPriceNomen>         dataBeanList;

	    private JasperDesign jasperDesign;
	    private JasperReport jasperReport;
	    private JasperPrint  jasperPrint ;	
	

	    public void creatREportPriceNomen(SettingConnectSQL SetCon) throws SQLException, JRException {
	    	
	    	ObservableList<PersenReportPriceNomen> dataBeanList = PersenReportPriceNomen.getMassivReportPrice(SetCon);
	    	
	    	/*
	    	parameters = new HashMap<String, Object>();
	    	parameters.put("DataReport", new Date());
	    	reportPattern = new File("ReportsPriceA4.jrxml");
	    	jasperDesign  = JRXmlLoader.load(reportPattern);
	    	jasperReport  = JasperCompileManager.compileReport(jasperDesign);
	        jasperPrint   = JasperFillManager.fillReport(jasperReport, 
	                                                     parameters, 
	                                                     beanColDataSource);
	        
	        JasperExportManager.exportReportToPdfFile(jasperPrint, 
	        		"/home/dggrom/Документы/java/EclipseProject/PostgreSQL/src/Sempel/Reports/PriceNomen/reportPDF.pdf");
	    	
	        */
	    	
	        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList); 
	        Map<String, Object> parameters = new HashMap<String, Object>(); 
	        parameters.put("DataReport", new Date()); 
	        File reportPattern = new File("ReportsPriceA4.jrxml"); 
	        JasperDesign jasperDesign = JRXmlLoader.load(reportPattern); 
	        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign); 
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, 
	                parameters, beanColDataSource); 
	        JasperExportManager.exportReportToPdfFile(jasperPrint, "reportPDF.pdf"); 
	    	
	        /*
	    	JasperReport jasperReport = JasperCompileManager
	                .compileReport("ReportsPriceA4.jrxml");
	  
	        Map<String, Object> parameters = new HashMap<String, Object>();
	  

	        JRDataSource dataSource = new JREmptyDataSource();
	  
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
	                parameters, dataSource);
	  
	  
	        JasperExportManager.exportReportToPdfFile(jasperPrint,
	                "/home/dggrom/Документы/java/EclipseProject/PostgreSQL/src/Sempel/Reports/PriceNomen/reportPDF.pdf");
	         
	        System.out.println("Done!");
	        */
	    }

}
