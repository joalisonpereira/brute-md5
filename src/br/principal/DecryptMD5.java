package br.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;

public class DecryptMD5
{
  private File wordlist;
  private boolean status = true;
  private long num_palavras;
  
  public DecryptMD5(File file)
  {
    if (file.exists()) {
      wordlist = file;
      status = false;
    } else {
      System.out.println("Arquivo não existente.");
    }
  }
  
  public void decrypt(String md5) {
    try {
      BufferedReader br = new BufferedReader(new java.io.FileReader(getWordList()));
      while (br.ready()) {
        String palavra = br.readLine();
        num_palavras += 1L;
        if (criptografa(palavra).equals(md5)) {
          System.out.println("\nHash encontrado!");
          System.out.println("Senha: " + palavra);
          break;
        }
        
        System.out.println(palavra + " NOT MATCHED.");
      }
      
      System.out.println("Números de palavras testadas: " + num_palavras);
      br.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public File getWordList()
  {
    return wordlist;
  }
  
  public boolean isEmpty() {
    return status;
  }
  
  public static String stringHexa(byte[] bytes)
  {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      int parteAlta = (bytes[i] >> 4 & 0xF) << 4;
      int parteBaixa = bytes[i] & 0xF;
      if (parteAlta == 0)
        s.append('0');
      s.append(Integer.toHexString(parteAlta | parteBaixa));
    }
    return s.toString();
  }
  
  public static String criptografa(String senha)
  {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(senha.getBytes());
      byte[] hashMd5 = md.digest();
      return stringHexa(hashMd5);
    }
    catch (java.security.NoSuchAlgorithmException e) {
      System.out.println("Problema no cyper");
    }
    return null;
  }
}
