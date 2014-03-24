package es.mgamallo.FirmaArchivo;


import java.io.File;

import javax.swing.JOptionPane;

public class VentanaDialogo extends javax.swing.JFrame {

    /**
     * Creates new form VentanaDialogo
     */
		
	static String rutaCertificado ="";
	static String clavePin = "";
	static boolean usuarioDeUrgencias = false;
 	static String usuario;
	
    public VentanaDialogo() {
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
        progresoCarpetaJL.setText("Archivo 1 de 100");

        progresoTotalJL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        progresoTotalJL.setText("Carpeta 1 de 1");

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
    	

    	    	
    	File ficheroCertificado;
    	rutaCertificado ="";
    	
    	String passwordZip = "";
     	
    	if(args.length>0){
    		usuario = args[0];
    		if(args[1].equals("urgencias")){
    			usuarioDeUrgencias = true;
    			System.out.println("Urgencias");
    		}
    		else{
    			usuarioDeUrgencias = false;
    			System.out.println("Documentacion");
    		}
    	}
    	else{
    		
    		System.out.println("no hay argumentos");
    		
    		int opcion = JOptionPane.showOptionDialog(null, "¿Qué documentación vas a firmar?", "Selector de documentación", 
    				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, new Object[] {"Urgencias","Documentación"}, "Documentación");
    		
    		if(opcion == JOptionPane.OK_OPTION){
    			usuarioDeUrgencias = true;
    		}else{
    			usuarioDeUrgencias = false;
    		}
    		
    		System.out.println(usuarioDeUrgencias);


        	SelectorUsuario selector = new SelectorUsuario(null, true);
        	usuario = selector.getUsuario();
    	}	
    		// String nombreCertificado = "cal\\certificados\\" + usuario.toLowerCase() + ".pfx";
    		
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
        			}
    			}
    			else{
    				JOptionPane.showMessageDialog(null, "Clave del zip erronea");
    				System.exit(0);
    			}
 
    			
    		}
   	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaDialogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaDialogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaDialogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaDialogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
                VentanaDialogo ventana = new VentanaDialogo();
                ventana.setVisible(true);
                
                
                Worker worker = new Worker(usuario, ventana.progresoCarpetaJL, ventana.progresoTotalJL, ventana.barraProgresoCarpeta, ventana.barraProgresoTotal,rutaCertificado,clavePin,usuarioDeUrgencias);
                worker.execute();
                
                
                System.out.println("onde ando");
            }
        });
    }

    // Variables declaration - do not modify                     
    public javax.swing.JProgressBar barraProgresoCarpeta;
    public javax.swing.JProgressBar barraProgresoTotal;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JLabel progresoCarpetaJL;
    public javax.swing.JLabel progresoTotalJL;
    // End of variables declaration                   
}

