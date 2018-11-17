package parser_persona;

import java.util.ArrayList;

public class Libro {
	
	private String titulo;
	private String editor;
	private int paginas;
	private int anyo;
	private ArrayList<String> autores = new ArrayList<String>();
	
	public Libro () {
		
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public int getAnyo() {
		return anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	
	public ArrayList<String> getAutores() {
		return autores;
	}
	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}
	
	public void print () {
		System.out.println("Titulo "+titulo);
		System.out.println("Editor "+editor);
		System.out.println("Año "+anyo);
		System.out.println("Páginas "+paginas);
		
		for(int i =0; i<autores.size(); i++) {
			System.out.println("Autor : "+autores.get(i));
		}
		System.out.println("\n");
	}

}
