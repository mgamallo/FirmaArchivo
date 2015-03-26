package es.mgamallo.FirmaArchivo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class Worker extends SwingWorker<Double, Integer>{

	static final String RUTA = "j:/digitalización/00 documentacion/02 Revisado"; 
	static final String RUTAB = "h:/digitalización/00 documentacion/02 Revisado";
	static final String RUTAURG ="j:/DIGITALIZACIÓN/01 INFORMES URG (Colectiva)"; 
	static final String RUTAURGB ="H:/DIGITALIZACIÓN/01 INFORMES URG (Colectiva)";	
	
	private final JLabel conteoCarpeta;
	private final JLabel conteoTotal;
	private final JProgressBar progresoCarpeta;
	private final JProgressBar progresoTotal;
	
	private String certificado = "";
	private String password = "";
	private boolean usuarioUrgencias;
	private String usuario;
	private boolean tarjeta;
	private VentanaDialogo ventana;
	
	private String rutaDirectorio ="";
	
	private Filechooser fc;
	
	private int numPdfsTotal = 0;
	private int numPdfsIanus = 0;
	private int numPdfsXedoc = 0;
	
	public Worker(/* VentanaDialogo ventana */ String usuario, JLabel conteoCarpeta, JLabel conteoTotal, JProgressBar progresoCapeta, JProgressBar progresoTotal, String certificado, String password, boolean usuarioUrgencias, boolean tarjeta){
		this.conteoCarpeta =conteoCarpeta;
		this.conteoTotal = conteoTotal;
		this.progresoCarpeta = progresoCapeta;
		this.progresoTotal = progresoTotal;
		this.certificado = certificado;
		this.password = password;
		this.usuarioUrgencias = usuarioUrgencias;
		//this.ventana = ventana;
		this.usuario = usuario;
		this.tarjeta = tarjeta;
	}
	
	@Override
	protected Double doInBackground() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Estamos en doInBackground, en el hilo " + 
				Thread.currentThread().getName());
		
		if(usuarioUrgencias){
			rutaDirectorio = RUTAURG;
			if(!(new File(rutaDirectorio).exists()))
				rutaDirectorio = RUTAURGB;
			if(!tarjeta)
				rutaDirectorio += ("\\01 " + usuario + "\\02 Revisado");
		}
		else{
			rutaDirectorio = RUTA;
			if(!(new File(rutaDirectorio).exists()))
				rutaDirectorio = RUTAB;
		}
		
		//JOptionPane.showMessageDialog(null, rutaDirectorio);
		
		fc = new Filechooser();
		fc.seleccionManual(rutaDirectorio);
		
		int numeroCarpetas = fc.carpetas.length;
		int numeroPdfs = 0;
		int numeroPdfsAcumulados = 0;
		int numeroPdfsTotales = 0;
		for(int i=0;i < numeroCarpetas;i++){
			numeroPdfsTotales += fc.carpetas[i].pdfs.length;
		}
		
		// ventana.setVisible(true);
		
		//JOptionPane.showMessageDialog(null, certificado);
		//JOptionPane.showMessageDialog(null, certificado);
		
		
		for(int i=0;i<numeroCarpetas;i++){
		
			boolean pinCorrecto;
			numeroPdfs = fc.carpetas[i].pdfs.length;
			for(int j=0;j < numeroPdfs;j++){
				System.out.println("Primer fichero de la carpeta: \t" + fc.carpetas[i].pdfs[j].getName().toString());
				if(i == 0 && j == 0){
					JOptionPane.showMessageDialog(null, "Empieza la firma");
				}

				if(!tarjeta){
					pinCorrecto = Firma.firmar(fc.carpetas[i].pdfs[j].getAbsolutePath(), 
						renombrarFicheroFirmado(fc.carpetas[i].pdfs[j]), certificado, password);
				}
				else{
					pinCorrecto = Firma.firmarContenedorIExplore(fc.carpetas[i].pdfs[j].getAbsolutePath(), 
							renombrarFicheroFirmado(fc.carpetas[i].pdfs[j]), usuario, password);
				}
				// JOptionPane.showMessageDialog(null, pinCorrecto);	

				if(!pinCorrecto){
    				JOptionPane.showMessageDialog(null, "Fallo en la firma. Se aborta la firma.");
    				System.exit(0);
				}
				numeroPdfsAcumulados++;
				
				int porcentajePdfs =  ((j + 1)*100)/numeroPdfs;
				int porcentajeCarpetas = (numeroPdfsAcumulados*100)/ numeroPdfsTotales; 
				
				// Thread.sleep(100);
				
				publish(porcentajePdfs, porcentajeCarpetas,j+1,i+1,numeroPdfs,numeroCarpetas);
			}
		}
		
		return 100.0;
	}
	
	protected void done(){
		
		String texto = "Numero de pdfs firmados: " + numPdfsTotal;
		texto += "\nNumero de pdfs para Ianus: " + numPdfsIanus;
		texto += "\nNumero de pdfs para Xedoc: " + numPdfsXedoc;
		
		if(numPdfsTotal == (numPdfsIanus + numPdfsXedoc)){
			texto += "\nFirma finalizada con éxito.";
		}
		else{
			texto += "\nHay documentos que no han sido firmados!!";
		}
		
		JOptionPane.showMessageDialog(null, texto);
		System.out.println(certificado);
		File f = new File(certificado);
		if(f.delete())
			System.out.println("Certificado borrado");
		if(borrarCarpetas()){
			System.out.println("Carpetas revisadas borradas");
		}
		else{
			JOptionPane.showMessageDialog(null, "Errores en el borrado de las carpetas de revisado");
		}
		
		int opcion = JOptionPane.showOptionDialog(null, "¿Quieres seguir revisando?", "Finalizando", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, new Object[] {"Si","No"}, "No");
		
		if(opcion == JOptionPane.OK_OPTION){
			System.out.println("Queremos continuar.");
			
			try {
				
				String usuarioUrgenciasCadena = "false";
				
				if(usuarioUrgencias){
					usuarioUrgenciasCadena = "true";
				}
				
				String comando = "java -jar Revision2015.jar " + usuario 
						+ " " + usuarioUrgenciasCadena;
				
				System.out.println(comando);
				
				Runtime.getRuntime().exec(comando);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Salimos");
			System.exit(0);
			
		}
		else{
			System.out.println("Salimos");
			System.exit(0);
		}

	}
	
	protected void process(List<Integer> chunks){
		progresoCarpeta.setValue(chunks.get(0));
		progresoTotal.setValue(chunks.get(1));
		conteoCarpeta.setText("Pdf " + String.valueOf(chunks.get(2)) + " de " + String.valueOf(chunks.get(4)));
		conteoTotal.setText("Carpeta " + String.valueOf(chunks.get(3)) + " de " + String.valueOf(chunks.get(5)));
	}
	
	private String renombrarFicheroFirmado(File archivoOrigen){
		
		System.out.println(archivoOrigen.getAbsolutePath());
			
		String nombrePdf = archivoOrigen.getName();	
		System.out.println(nombrePdf);
		
		int ultimaR = nombrePdf.lastIndexOf(".");
		
		String nuevoNombrePdf = nombrePdf.substring(0,ultimaR) + "_f.pdf";
		System.out.println(nuevoNombrePdf);
		
		String nombreCarpetaPdf = archivoOrigen.getParentFile().getName();
		int quitarUsuario = nombreCarpetaPdf.lastIndexOf(" ");
		if(quitarUsuario != -1 || quitarUsuario != 0){
			nombreCarpetaPdf = nombreCarpetaPdf.substring(0,quitarUsuario);
		}
		
		/*          *********************************************************************
		 		

		 		
		 */
		
		String carpetaFirmado = "";
		
		numPdfsTotal++;
		if(VentanaDialogo.ianus_xedoc == 1){
			carpetaFirmado = "\\03 Firmado\\";
			numPdfsIanus++;
		}
		else if(VentanaDialogo.ianus_xedoc == 3){
			carpetaFirmado = "\\03 Firmado Xedoc\\";
			numPdfsXedoc++;
		}
		else{
			if(sePuedeSubirEnIanus(nombrePdf)){
				carpetaFirmado = "\\03 Firmado\\";
				numPdfsIanus++;
			}
			else{
				carpetaFirmado = "\\03 Firmado Xedoc\\";
				numPdfsXedoc++;
			//	nombreCarpetaPdf = nombreCarpetaPdf.replace("#", " ");
			}
		}

		
		String rutaCarpetaAbuela = archivoOrigen.getParentFile().getParentFile().getParentFile().getAbsolutePath();
		
		String rutaFinalDirectorio = rutaCarpetaAbuela +  carpetaFirmado /* "\\03 Firmado\\" */ + nombreCarpetaPdf + "\\" ;
		
	
		File crearCarpetaDestino = new File(rutaFinalDirectorio);
		crearCarpetaDestino.mkdirs();
		
		System.out.println(rutaFinalDirectorio + nuevoNombrePdf);
		
		String rutaFinalCompleta = rutaFinalDirectorio + nuevoNombrePdf;	
	
		return rutaFinalCompleta ;
		
	}
	
	private boolean borrarCarpetas(){
		
		boolean sinErrores = true;
		
		int numeroCarpetas = fc.carpetas.length;
		int numeroPdfs = 0;
		for(int i=0;i<numeroCarpetas;i++){

			numeroPdfs = fc.carpetas[i].pdfs.length;
			for(int j=0;j < numeroPdfs;j++){
				//System.out.println("\t" + fc.carpetas[i].pdfs[j].getName().toString());
				if(!fc.carpetas[i].pdfs[j].delete()){
					sinErrores = false;
					System.out.println("Error en " + fc.carpetas[i].pdfs[j].getName().toString());
				}
			}
			
			if(fc.carpetas[i].rutaCarpeta.delete()){
				//sinErrores = false;
				//System.out.println("Error en " + fc.carpetas[i].rutaCarpeta.getName().toString());
			}
			
		}
		
		return sinErrores;
		
	}
	
	
	
	
	private boolean sePuedeSubirEnIanus(String nombrePdf){
		
		String compruebaSeparador = nombrePdf;
		
		
		
		int inicio = nombrePdf.lastIndexOf("@") + 1;
		int fin = nombrePdf.lastIndexOf("r.pdf");
		
		nombrePdf = nombrePdf.substring(inicio,fin-1);
		
		if(VentanaDialogo.titIanus.containsKey(nombrePdf) || compruebaSeparador.contains("Separador") ){
			System.out.println(nombrePdf + " se puede subir a ianus.");
			
			return true;
		}
		
		System.out.println(nombrePdf + " no se puede subir a ianus.");
		return false;
	}
}
