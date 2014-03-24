package es.mgamallo.FirmaArchivo;

import java.io.File;

public class TestFichero {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File archivoOrigen = new File(
				"K:\\DIGITALIZACIÓN\\00 DOCUMENTACION\\02 Revisado\\20140307 0912 22 Ju ped OCR Asun\\0007_DOC_OCR_20140307_091204 @546117 @PED @Eco r.pdf");
		
		String nombrePdf = archivoOrigen.getName();	
		System.out.println(nombrePdf);
		
		int ultimaR = nombrePdf.lastIndexOf(".");
		
		String nuevoNombrePdf = nombrePdf.substring(0,ultimaR) + "_firmado.pdf";
		System.out.println(nuevoNombrePdf);
		
		String nombreCarpetaPdf = archivoOrigen.getParentFile().getName();
		System.out.println(nombreCarpetaPdf);
		
		String rutaCarpetaAbuela = archivoOrigen.getParentFile().getParentFile().getParentFile().getAbsolutePath();
		
		System.out.println(rutaCarpetaAbuela);
		
		String rutaFinalDirectorio = rutaCarpetaAbuela + "\\03 Firmado\\" + nombreCarpetaPdf + " prueba\\" ;
		
		File crearCarpetaDestino = new File(rutaFinalDirectorio);
		crearCarpetaDestino.mkdirs();
		
		System.out.println(rutaFinalDirectorio + nuevoNombrePdf);
		
		String rutaFinalCompleta = rutaFinalDirectorio + nuevoNombrePdf;
	}

}
