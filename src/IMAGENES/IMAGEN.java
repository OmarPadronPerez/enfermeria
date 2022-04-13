package IMAGENES;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JDesktopPane;


public class IMAGEN
  extends JDesktopPane
{
  private Image fondo;
  
  public void paintComponent(Graphics g) {
    int height = (getSize()).height;
    int width = (getSize()).width;
    super.paintComponent(g);
    if (this.fondo != null) {
      g.drawImage(this.fondo, 0, 0, width, height, this);
    }
  }
  
  public void setBackgroung(String ruta) {
    setOpaque(false);
    URL direccion = ClassLoader.getSystemResource(ruta);
    Image temp = Toolkit.getDefaultToolkit().createImage(direccion);
    this.fondo = temp;
    setBackground(new Color(255, 255, 255, 0));
    repaint();
  }
  
  public void trasparente() { 
      setBackground(new Color(255, 255, 255, 0)); 
  }
}
