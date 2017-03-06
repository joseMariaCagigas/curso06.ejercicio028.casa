package es.cic.curso.curso06.ejercicio028.backend.utilities;

public class Validador {

	public Validador(){
		
	}
	
	public boolean validarEmail(String email){
		
		String patronEmail="^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$";
		
		return email.matches(patronEmail);
	}
	
	public boolean validarLongitudTexto(String numCaracteres){
		
		String patronNumCaracteres = "[a-zA-Z0-9]{1,50}";
		
		return (numCaracteres.matches(patronNumCaracteres));
		
	}
	
	public boolean validarNombreApellidos(String nombreApellidos){
		String patronNombreApellidos= "[a-zA-Z\\s]{1,50}";
		
		return (nombreApellidos.matches(patronNombreApellidos));
	}
	
	
	
	
}
