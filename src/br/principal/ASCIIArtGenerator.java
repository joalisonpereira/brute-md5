package br.principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ASCIIArtGenerator
{
  public static final int ART_SIZE_SMALL = 12;
  public static final int ART_SIZE_MEDIUM = 18;
  public static final int ART_SIZE_LARGE = 24;
  public static final int ART_SIZE_HUGE = 32;
  public ASCIIArtGenerator() {}
  
  public static enum ASCIIArtFont
  {
    ART_FONT_DIALOG("Dialog"),  ART_FONT_DIALOG_INPUT("DialogInput"), 
    ART_FONT_MONO("Monospaced"),  ART_FONT_SERIF("Serif"),  ART_FONT_SANS_SERIF("SansSerif");
    
    private String value;
    
    public String getValue() {
      return value;
    }
    
    private ASCIIArtFont(String value) {
      this.value = value;
    }
  }
  void printTextArt(String artText, int textHeight, ASCIIArtFont fontType, String artSymbol)
    throws Exception
  {
    String fontName = fontType.getValue();
    int imageWidth = findImageWidth(textHeight, artText, fontName);
    
    BufferedImage image = new BufferedImage(imageWidth, textHeight, 1);
    Graphics g = image.getGraphics();
    Font font = new Font(fontName, 1, textHeight);
    g.setFont(font);
    
    Graphics2D graphics = (Graphics2D)g;
    graphics.drawString(artText, 0, getBaselinePosition(g, font));
    
    for (int y = 0; y < textHeight; y++) {
      StringBuilder sb = new StringBuilder();
      for (int x = 0; x < imageWidth; x++)
        sb.append(image.getRGB(x, y) == Color.WHITE.getRGB() ? artSymbol : " ");
      if (!sb.toString().trim().isEmpty())
      {
        System.out.println(sb);
      }
    }
  }
  




  void printTextArt(String artText, int textHeight)
    throws Exception
  {
    printTextArt(artText, textHeight, ASCIIArtFont.ART_FONT_DIALOG, "#");
  }
  






  private int findImageWidth(int textHeight, String artText, String fontName)
  {
    BufferedImage im = new BufferedImage(1, 1, 1);
    Graphics g = im.getGraphics();
    g.setFont(new Font(fontName, 1, textHeight));
    return g.getFontMetrics().stringWidth(artText);
  }
  





  private int getBaselinePosition(Graphics g, Font font)
  {
    FontMetrics metrics = g.getFontMetrics(font);
    int y = metrics.getAscent() - metrics.getDescent();
    return y;
  }
}
