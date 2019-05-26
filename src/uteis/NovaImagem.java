package uteis;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class NovaImagem {

	int altura;
	int largura;

	public NovaImagem() {
	}

	public NovaImagem(int altura, int largura) {

		this.altura = altura;
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public NovaImagem redimensionaImagem(ImageIcon imageIcon, JLabel label) throws IOException {

		NovaImagem novaImagem = new NovaImagem();

		int larguraOriginal = 0;
		int alturaOriginal = 0;

		alturaOriginal = imageIcon.getIconHeight();
		larguraOriginal = imageIcon.getIconWidth();

		int alturaNova = 0;
		int larguraNova = 0;

		if (alturaOriginal >= label.getHeight() && larguraOriginal >= label.getWidth()) {

			if (alturaOriginal > larguraOriginal) {

				alturaNova = label.getHeight();
				larguraNova = (alturaNova * larguraOriginal) / alturaOriginal;

			} else if (larguraOriginal > alturaOriginal) {

				larguraNova = label.getWidth();
				alturaNova = (larguraNova * alturaOriginal) / larguraOriginal;

			}

		} else if (alturaOriginal > label.getHeight()) {

			alturaNova = label.getHeight();
			larguraNova = (alturaNova * larguraOriginal) / alturaOriginal;

		} else if (larguraOriginal > label.getWidth()) {

			larguraNova = label.getWidth();
			alturaNova = (larguraNova * alturaOriginal) / larguraOriginal;

		} else {

			alturaNova = alturaOriginal;
			larguraNova = larguraOriginal;
		}

		novaImagem.setAltura(alturaNova);
		novaImagem.setLargura(larguraNova);

		return novaImagem;

	}

	public NovaImagem redimensionaImagemSalvar(String string, JLabel label) throws IOException {

		NovaImagem novaImagem = new NovaImagem();

		File imagem = new File(string);
		BufferedImage img = null;
		int larguraOriginal = 0;
		int alturaOriginal = 0;

		try {

			img = ImageIO.read(imagem);
			larguraOriginal = img.getWidth();
			alturaOriginal = img.getHeight();

		} catch (IOException e) {

			e.printStackTrace();
		}

		int alturaNova = 0;
		int larguraNova = 0;

		if (alturaOriginal >= label.getHeight()) {

			alturaNova = label.getHeight();
			larguraNova = (alturaNova * larguraOriginal) / alturaOriginal;

		} else if (larguraOriginal >= label.getWidth()) {

			larguraNova = label.getWidth();
			alturaNova = (larguraNova * alturaOriginal) / larguraOriginal;

		} else if (alturaOriginal < label.getHeight()) {

			alturaNova = label.getHeight();
			larguraNova = (alturaNova * larguraOriginal) / larguraOriginal;

		}

		novaImagem.setAltura(alturaNova);
		novaImagem.setLargura(larguraNova);

		return novaImagem;

	}
}
