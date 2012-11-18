import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;


public class window extends JTextComponent {
  private static int espacios,tabSize;
  static JEditorPane texto = new JEditorPane();

  /*
   * coger los caracteres hasta agarrar un '\n'. Sacar el tamaño de la tabulación
   * y si no está vacío imprimir, si está vacío reducir el tamaño de la tabulación
   */
  /*
  public static void onEnter() {
    if(nextBlock()) espacios += tabSize;
    else if(endBlock()) espacios -= tabSize;
    String spaces = new String();
    for(int i = 0; i < espacios; ++i) spaces += " ";
    System.out.printf("Hola :D\n");
    try {
      Document doc = texto.getDocument();
      int pos = texto.getCaretPosition();
      System.out.printf("ESTA ES LA POSICION: %d\n",pos);
      doc.insertString(pos,"\n" + spaces,null);
    } catch(BadLocationException exc) {
      exc.printStackTrace();
    }
  }
  */
  public static boolean nextBlock() {
    Document doc = texto.getDocument();
    int n = doc.getLength();
    try {
      String s = doc.getText(n - 1,1);
      if(s.equals(":")) return true;
    } catch(BadLocationException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean endBlock() {
    if(espacios == 0) return false;
    Document doc = texto.getDocument();
    //int n = doc.getLength();
    int pos = texto.getCaretPosition();
    try {
      String s = doc.getText(pos - espacios,espacios);
      System.out.println("ESTA ES LA CADENA: <" + s + ">");
      String aux = "";
      for(int i = 0; i < espacios; ++i) aux += " ";
      if(s.equals(aux)) return true;
    } catch(BadLocationException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static void onEnter() {
    try {
      int mouse = texto.getCaretPosition();
      int i = mouse - 1,n;
      Document doc = texto.getDocument();
      String whole = doc.getText(0,mouse), aux = "";
      while(0 <= i && whole.charAt(i) != '\n') {
        aux = whole.charAt(i) + aux;
        --i;
      }
      i = 0;
      n = aux.length();
      if(n == 0) {
        doc.insertString(mouse,"\n",null);
        return;
      }
      while(i < n && aux.charAt(i) == ' ') ++i;
      String espacios = "";
      //Si la línea solamente tiene espacios
      for(int j = 0; j < i; ++j) espacios += " ";
      if(aux.charAt(n - 1) == ':') espacios += "  ";
      else if (i == n) {
        String aux2 = "";
        int m = espacios.length() - 2;
        for(int j = 0; j < m; ++j) aux2 += " ";
        espacios = aux2;
      }
      doc.insertString(mouse,"\n" + espacios,null);
      System.out.println("Esta es la cadena que acabo de sacar: <" + aux + ">");
    } catch(BadLocationException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    espacios = 0;
    tabSize = 2; //Por default :D
    JFrame v = new JFrame("Test");
    //Características principales de la ventana
    v.setLayout(null);
    v.setSize(1000,500);
    v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //El editor de texto
    texto.setText("text");
    MyRTFEditorKit rtfkit = new MyRTFEditorKit();
    texto.setEditorKit(rtfkit);
    rtfkit.initializeDefaultFont();

    int condition = JComponent.WHEN_FOCUSED;
    InputMap iMap = texto.getInputMap(condition);
    ActionMap aMap = texto.getActionMap();

    String enter = "enter";
    iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),enter);
    aMap.put(enter,new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        onEnter();
      }
    });
    texto.setEditable(true);
    texto.setBounds(20,20,900,400);
    
    v.add(texto);

    //Cargar la ventana
    v.setVisible(true);
    obj test = new obj(128);
    System.out.printf("Hola mundo: %d\n",obj.getN());
  }
}