import javax.swing.*;

import java.awt.Color;
import java.awt.List;
import java.awt.event.*;

public class window {
  private static int espacios,tabSize;
  static JEditorPane texto = new JEditorPane();

  public static String cadenaLimpia(String s) {
    int n = s.length(),i = 0;
    while(i < n && s.charAt(i) == ' ') ++i;
    return s.substring(i);
  }
  
  public static void onEnter() {
    //if(nextBlock()) espacios += tabSize();
    String spaces = new String();
    for(int i = 0; i < espacios; ++i) spaces += " ";
    System.out.printf("Hola :D\n");
    //texto.setText("HOLAAAAAAAAA");
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