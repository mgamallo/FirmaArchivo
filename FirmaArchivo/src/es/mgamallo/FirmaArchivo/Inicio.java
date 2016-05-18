package es.mgamallo.FirmaArchivo;


import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.TreeMap;

import javax.swing.JOptionPane;

public class Inicio extends javax.swing.JFrame {

    /**
     * Creates new form VentanaDialogo
     */
		
	static final String REVISION = "Revision2016.jar ";
	
	static String rutaCertificado ="";
	static String clavePin = "";
	static int tipoDeDocumentacion = 1;
 	static String usuario;
 	static boolean tarjeta = false;
 	
	LeerExcel leerExcel;
	static String[] listaNombresDocumentos;
	static String[] listaIanusXedoc;
 	
 	static int ianus_xedoc = 2;  	//  1 Ianus
 									//	2 Ianus y Xedoc
 									//  3 Xedoc
 	
 	static TreeMap<String, String> titIanus = new TreeMap<String, String>();
									
 	static String argumentos[];
	
    public Inicio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        progresoCarpetaJL = new javax.swing.JLabel();
        barraProgresoCarpeta = new javax.swing.JProgressBar(0,100);
        progresoTotalJL = new javax.swing.JLabel();
        barraProgresoTotal = new javax.swing.JProgressBar(0,100);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(252, 241, 213));
        setLocationRelativeTo(null);

        //setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(400, 200));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(248, 240, 240));

        progresoCarpetaJL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        progresoCarpetaJL.setText("Archivo ? de ?");

        progresoTotalJL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        progresoTotalJL.setText("Carpeta ? de ?");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(barraProgresoCarpeta, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .addComponent(barraProgresoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(progresoCarpetaJL))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(progresoTotalJL)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(progresoCarpetaJL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraProgresoCarpeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(progresoTotalJL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraProgresoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 194, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
    	

		LeerExcel leerExcel = new LeerExcel();
		
		leerExcel.leer("Documentos.xls");
		
		listaNombresDocumentos = leerExcel.getNombres();
		listaIanusXedoc = leerExcel.getIanusXedoc();
		new Inicio().setTitulosIanus();
    	    	
    	File ficheroCertificado;
    	rutaCertificado ="";
    	
    	String passwordZip = "";
 
    	
    	if(args.length>0){
    		
    		argumentos = args;
    		
    		usuario = args[0];
    		if(args[1].equals("urgencias")){
    			tipoDeDocumentacion = 0;
    			System.out.println("Urgencias");
    		}
    		else if(args[1].equals("saln�s")){
    			tipoDeDocumentacion = 2;
    			System.out.println("Salnes");
    		}
    		else if(args[1].equals("documentaci�n")){
    			tipoDeDocumentacion = 1;
    			System.out.println("Documentacion");
    		}
    		
    		int sel = JOptionPane.showOptionDialog(null, "Carpeta de destino.", "Carpeta destino", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Ianus","Ianus/Xedoc","Xedoc"}, "Ianus/Xedoc");    		
    		if(sel != -1){
    			if(sel == 0){
    				ianus_xedoc = 1;
    			}
    			else if(sel == 1){
    				ianus_xedoc = 2;
    			}
    			else if(sel == 2){
    				ianus_xedoc = 3;
    			}
    		}
    	}
    	else{
    		argumentos = null;
    		System.out.println("no hay argumentos");
    		
    		int opcion = JOptionPane.showOptionDialog(null, "�Qu� documentaci�n vas a firmar?", "Selector de documentaci�n", 
    				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, new Object[] {"Urgencias","Documentaci�n", "Saln�s"}, "Documentaci�n");

    		tipoDeDocumentacion = opcion;

    		
			SelectorUsuario selector = new SelectorUsuario(null, true);
        	usuario = selector.getUsuario();
    		
    		System.out.println(tipoDeDocumentacion);

    	}	
    		// String nombreCertificado = "cal\\certificados\\" + usuario.toLowerCase() + ".pfx";
    		
    	
    	int opcionToken = JOptionPane.showOptionDialog(null, "�Con qu� vas a firmar?", "Selector de firma", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, new Object[] {"Certificado","Tarjeta"}, "Certificado");
		
		if(opcionToken == JOptionPane.OK_OPTION){
			
       	
			
    		String nombreCertificado = "cal\\certificados\\" + usuario.toLowerCase() + ".zip";
    		
    		System.out.println(nombreCertificado);
    		ficheroCertificado = new File(nombreCertificado);
    		if(!ficheroCertificado.exists()){
    			JOptionPane.showMessageDialog(null, "No disponible el certificado");
    			System.exit(0);
    		}
    		else{
    			rutaCertificado = ficheroCertificado.getAbsolutePath().toString();
    			// JOptionPane.showMessageDialog(null, rutaCertificado);
    			
    			DialogoPassword dialogo = new DialogoPassword(" la clave del zip");

    			passwordZip = dialogo.getClave();
    			
    			UnZipPassword unzip = new UnZipPassword();
    			if(unzip.descomprimir(ficheroCertificado.getAbsolutePath().toString(), "cal\\certificados\\temp", passwordZip)){
    	   			// JOptionPane.showMessageDialog(null, passwordZip);
        			
        			dialogo = new DialogoPassword(" la clave del pin");

        			clavePin = dialogo.getClave();
        			// JOptionPane.showMessageDialog(null, passwordPin);
        			
        			nombreCertificado = "cal\\certificados\\temp\\" + usuario.toLowerCase() + ".pfx";
        			File f = new File(nombreCertificado);
        			if(f.exists()){
        				rutaCertificado = f.getAbsolutePath().toString();
        			}
        			else{
        				JOptionPane.showMessageDialog(null, "Certificado no disponible");
        				System.exit(0);
        			}
    			}
    			else{
    				JOptionPane.showMessageDialog(null, "Clave del zip erronea");
    				System.exit(0);
    			}
    		}
		}else if(opcionToken == JOptionPane.NO_OPTION){
			
			tarjeta = true;
			
			try {
				KeyStore ks = KeyStore.getInstance("Windows-MY");
				ks.load(null,null);
				
				Enumeration elist = ks.aliases();
				

				ArrayList<String> certificados = new ArrayList<String>();
				while(elist.hasMoreElements()){
					certificados.add((String) elist.nextElement());
				}
				
				Object seleccion = JOptionPane.showInputDialog(null, "Seleccione un certificado","Selector de certificados",
						JOptionPane.QUESTION_MESSAGE,null,certificados.toArray(),certificados.get(0));
				
				usuario = seleccion.toString();
				
				System.out.println(usuario);
				
				DialogoPassword dialogo = new DialogoPassword(" el pin.");

				clavePin = dialogo.getClave();
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else{
			System.exit(0);
		}

   	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
                Inicio ventana = new Inicio();
                ventana.setVisible(true);
                
                
                Worker worker = new Worker(usuario, ventana.progresoCarpetaJL, ventana.progresoTotalJL, ventana.barraProgresoCarpeta, ventana.barraProgresoTotal,rutaCertificado,clavePin,tipoDeDocumentacion,tarjeta);
                worker.execute();
                
                
                System.out.println("onde ando");
            }
        });
    }
    
    
    
	private void setTitulosIanus(){
		for(int i=0;i<listaNombresDocumentos.length;i++){
			if(listaIanusXedoc[i].toLowerCase().equals("n") || listaIanusXedoc[i].toLowerCase().equals("s")){
				titIanus.put(listaNombresDocumentos[i],listaIanusXedoc[i]);
			}
		}
		
		Iterator it = titIanus.keySet().iterator();
		while(it.hasNext()){
			String clave = (String) it.next();
			System.out.println("Nombre: " + clave + ". Valor: " + titIanus.get(clave));
		}
	}

    // Variables declaration - do not modify                     
    public javax.swing.JProgressBar barraProgresoCarpeta;
    public javax.swing.JProgressBar barraProgresoTotal;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JLabel progresoCarpetaJL;
    public javax.swing.JLabel progresoTotalJL;
    // End of variables declaration                   
}

