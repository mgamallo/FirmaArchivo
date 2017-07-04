package es.mgamallo.FirmaArchivo;

import java.util.TreeMap;

public class Renombre {

	static public String getNombreXedocPdf(String nombreArchivo){
		
		String[] parametros = nombreArchivo.split("@");
		
		String nhc = "";
		String servicio = "";
		String nombre = "";
		String titulo = "";
		String fecha = "";
		
		String codigo = "";
		String nombreIndexado = "";
		
		if(parametros.length == 4){
			// Continuamos
			nhc = parametros[1].trim();
			
			servicio = parametros[2].trim();
			
			if( servicio.length() > 4){
				fecha =servicio.substring(servicio.length() - 8);
				servicio = servicio.substring(0, servicio.length()- 9);
			}
			
			nombre = parametros[3].substring(0, parametros[3].lastIndexOf(" r.pdf"));
			 nombreIndexado = nombre;
			
			int aux;
			if((aux = nombre.indexOf("(")) != -1){
				nombreIndexado = nombreIndexado.substring(0, aux).trim();
				titulo = nombre.substring(aux + 1, nombre.indexOf(")"));
				nombre = nombre.substring(0, aux-1);
			}
		}
		
		/*
		String resultado = parametros[0] + "@v1_" + nhc 
				                        // + " @v2_" + getCodigoTipo(nombreIndexado, servicio, Inicio.titXedoc)
				                         + " @v2_" + nombreIndexado
				                         + " @v4_" + servicio
				                         + " @v5_" + titulo
				                         + " @v6_" + fecha
				                       //  + " r.pdf";
										 + ".pdf";
		*/
		
		String resultado = parametros[0] + "r_f "  + " @p2_" + nombreIndexado + " @p4_" + servicio ;
		
		if(titulo.length() > 0){
			resultado +=  (" @p5_" + titulo);
		}
		
		if(fecha.length() > 0){
			resultado +=  (" @p6_" + fecha);
		}
		
		resultado +=(" @v1_" + nhc +"_.pdf");
		
		return resultado;
	}
	
	
	
	private static String getCodigoTipo(String nombre, String servicio, TreeMap<String, String[]> index){
		
		String codigo = "";
		
		System.out.println(nombre);
		
		if(index.containsKey(nombre)){
			
			String codigos[] = index.get(nombre);
			
			System.out.println(codigos.length);
			
			if(codigos.length > 0){
				if(servicio.equals(Inicio.HOSP)){
					codigo = codigos[2];
				}
				else if(servicio.equals(Inicio.URG)){
					codigo = codigos[5];
				}
				else if(servicio.equals(Inicio.CIA)){
					codigo = codigos[3];
				}
				else{
					codigo = codigos[1];
				}
			}
			
		}

		if(codigo.length() == 1){
			codigo = Inicio.SIN_TIPO;
		}
		else if(codigo.length() > 1){
			codigo = codigo.substring(1);
		}
		
		System.out.println(codigo);
		System.out.println("__________________________");

		
		return codigo;
	}
	
	
	
	static public void main(String args[]){
		
		String ejemplo = "040_D_20170412_153315 206A $459 @176484 @HOSP @Nota ingreso r.pdf";
		String ejemplo2 = "005_D_20170417_084400 99A $464 @193231 @OFTC 04022013 @Evolutivo r.pdf";
		String ejemplo3 = "041_D_20170417_085716 164A $465 @20574 @CIA 01010001 @Anamnese (Cuestionario) r.pdf";
		
		System.out.println(ejemplo);
		System.out.println(Renombre.getNombreXedocPdf(ejemplo));
		System.out.println();
		System.out.println(ejemplo2);
		System.out.println(Renombre.getNombreXedocPdf(ejemplo2));
		System.out.println();
		System.out.println(ejemplo3);
		System.out.println(Renombre.getNombreXedocPdf(ejemplo3));
		System.out.println();
		
	}
}
