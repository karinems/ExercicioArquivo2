package Interface;

import java.io.IOException;

import controller.Fila;

public interface IArquivoController {
	public void verifDir() throws IOException;

	public boolean verifRegistro() throws IOException;

	public void mostraCadastros() throws IOException;

	public Fila cadastrar(Fila fila);

	public Fila salvar(Fila fila) throws IOException;

}
