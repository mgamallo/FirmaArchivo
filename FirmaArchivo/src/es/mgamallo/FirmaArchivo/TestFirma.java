package es.mgamallo.FirmaArchivo;

import java.io.File;

public class TestFirma {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File fichero = new File("temp");
		fichero.mkdirs();
		// Firma.firmar("pruebaurg.pdf", "temp\\firmado.pdf", "certificadomgg.pfx", "1519");
		Firma.firmarContenedorIExplore("pruebaurg.pdf", "temp\\firmado.pdf", "certificadomgg.pfx", "1519");
	}

}
