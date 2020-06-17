package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import Interface.IArquivoController;
import model.entity.Pessoa;

public class ArquivoController implements IArquivoController {

	@Override
	public void verifDir() throws IOException {
		String path = "C:\\TEMP";
		File dir = new File(path);
		if (!dir.exists() || !dir.isDirectory()) {
			if (dir.mkdir()) {
				System.out.println("Diretório Temp Criado com sucesso!");
			} else {
				System.err.println("Erro!");
			}
		}
	}

	@Override
	public boolean verifRegistro() throws IOException {
		String path = "C:\\TEMP\\registro.cvs";
		File file = new File(path);
		return file.exists();
	}

	@Override
	public void mostraCadastros() throws IOException {
		verifDir();
		String path = "C:\\TEMP\\registro.csv";
		File file = new File(path);
		if (verifRegistro()) {
			FileInputStream stream = new FileInputStream(file);
			InputStreamReader flow = new InputStreamReader(stream);
			BufferedReader reader = new BufferedReader(flow);
			String line = reader.readLine();
			StringBuffer buffer = new StringBuffer(line);
			line = reader.readLine();
			while (line != null) {
				String[] auxs = line.split(";");
				buffer.append("Código: " + auxs[0] + "|Nome: " + auxs[1] + "|Email: " + auxs[2]);
				buffer.append("\n");
				line = reader.readLine();
			}
			reader.close();
			flow.close();
			stream.close();
			String mostra = buffer.toString();
			JOptionPane.showMessageDialog(null, mostra);
		} else {
			System.err.println("Não existem cadastros!");
		}

	}

	@Override
	public Fila cadastrar(Fila fila) {
		Pessoa data = registrarPessoa();
		fila.adicionar(data);
		return fila;
	}

	@Override
	public Fila salvar(Fila fila) throws IOException {
		verifDir();
		String path = "C:\\TEMP\\registro.csv";
		File file = new File(path);
		if (verifRegistro()) {
			String save = preparar(fila);
			FileWriter writer = new FileWriter(file, true);
			PrintWriter printer = new PrintWriter(writer);
			printer.write(save);
			printer.flush();
			printer.close();
			writer.close();

		} else {
			String save = "Código;Nome;Email\n";
			save += preparar(fila);
			FileWriter writer = new FileWriter(file);
			PrintWriter printer = new PrintWriter(writer);
			printer.write(save);
			printer.flush();
			printer.close();
			writer.close();
		}

		return fila;

	}

	private String preparar(Fila fila) {
		StringBuffer buffer = new StringBuffer();
		String preparo;
		Pessoa pessoa = fila.remover();
		do {
			buffer.append(pessoa.getCod() + ";" + pessoa.getNome() + ";" + pessoa.getEmail());
			buffer.append("\n");
			pessoa = fila.remover();
		} while (pessoa != null);
		preparo = buffer.toString();
		return preparo;

	}

	private Pessoa registrarPessoa() {
		Pessoa data = new Pessoa();
		int cod = Integer.parseInt(JOptionPane.showInputDialog("Insira código da pessoa: "));
		String nome = JOptionPane.showInputDialog("Insira o nome da pessoa: ");
		String email = JOptionPane.showInputDialog("Insira o Email da pessoa: ");
		data.setCod(cod);
		data.setNome(nome);
		data.setEmail(email);
		return data;
	}

}
