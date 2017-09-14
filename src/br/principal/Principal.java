package br.principal;

import java.util.Scanner;

public class Principal {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		DecryptMD5 obj = null;

		ASCIIArtGenerator artGen = new ASCIIArtGenerator();
		System.out.println("\n");
		artGen.printTextArt("BruteMD5", 12);
		System.out.println("\nDigite o caminho da sua wordlist: ");
		do {
			java.io.File entrada = new java.io.File(in.nextLine());
			obj = new DecryptMD5(entrada);
		} while (obj.isEmpty());
		System.out.println("");
		System.out.println("Digite a sua hash: ");
		String md5 = in.nextLine();
		if(md5.length()<32 || md5.length()>32){
			System.out.println("Hash inválida.");
			System.exit(0);
		}
		obj.decrypt(md5);

		in.close();
	}
}
