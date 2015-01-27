package es.mgamallo.FirmaArchivo;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.biff.WorkbookMethods;
import jxl.read.biff.BiffException;

public class LeerExcel {

	
	private String[] servicios;
	private String[] nombres;
	
	public String[] ianus_xedoc;

	
	
	public void leer(String archivoFuente){
		
		WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setEncoding("ISO-8859-1");
        wbSettings.setLocale(new Locale("es", "ES"));
        wbSettings.setExcelDisplayLanguage("ES"); 
        wbSettings.setExcelRegionalSettings("ES"); 
        wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
        
        Workbook archivoExcel;
		try {
			archivoExcel = Workbook.getWorkbook(new File(archivoFuente));
			
	        Sheet hoja = archivoExcel.getSheet(0);
	        
	        //	Obtiene los nombres
	        int numColumnas = 0;
	        int numFilas = 0;
	        
	        while(!hoja.getCell(numColumnas,0).getContents().toString().equals("@finH")){
	        	numColumnas++;
	        }
	        
	        while(!hoja.getCell(0,numFilas).getContents().toString().equals("@finV")){
	        	numFilas++;
	        }
	        
	        
	        System.out.println("numero de filas es... " + numFilas);
	        
	        System.out.println("Num filas = " + numFilas + ". Num columnas = " + numColumnas);
	        
	        servicios = new String[numColumnas - 13];
	        nombres = new String[numFilas - 1];
	        

	        
	        
	        
	        // Lista de todos los nombres
	        for(int fila = 0;fila<nombres.length;fila++){
	        	nombres[fila] = hoja.getCell(0,fila + 1).getContents().toString();
	        }
	        

	        

	        	        
	        
	        //  Ianus_Xedoc
	        ianus_xedoc = new String[nombres.length];
	        for(int fila=0;fila<nombres.length;fila++){
	        	ianus_xedoc[fila] = hoja.getCell(servicios.length + 3,fila+1).getContents().toString();
	        	System.out.println(ianus_xedoc[fila]);
	        }
	        
	        



	        
	        
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public String[] getNombres(){
		return nombres;
	}
	
	public String[] getIanusXedoc(){
		return ianus_xedoc;
	}

	
	static public void main(String args[]){
		LeerExcel leerExcel = new LeerExcel();
		leerExcel.leer("Documentos.xls");
	}

	
}
