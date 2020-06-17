package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import Interface.IArquivoController;
import controller.ArquivoController;
import controller.Fila;

public class Main {
	public static void main(String[] args) {
		int opc = 0;
		Fila fila = new Fila();
		IArquivoController arq = new ArquivoController();
		while (opc != 9) {
			opc = Integer.parseInt(
					JOptionPane.showInputDialog("Menu:\n" + "1-Cadastrar Pessoas\n" + "2-Mostrar cadastros não salvos\n"
							+ "3-Salvar\n" + "4-Mostrar cadastros\n" + "9-Sair\n" + "Digite uma opção\n"));
			switch (opc) {
			case 1:
				fila = arq.cadastrar(fila);
				break;
			case 2:
				JOptionPane.showInputDialog(null, fila.mostrar());
				break;
			case 3:
				try {
					fila = arq.salvar(fila);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					arq.mostraCadastros();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 9:
				System.out.println("Saindo...");
				break;
			default:
				System.err.println("Opção Inválida");
			}
		}

	}
}
