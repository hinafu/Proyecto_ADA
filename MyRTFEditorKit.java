import javax.swing.text.*;
import javax.swing.text.rtf.*;
import java.awt.*;

public class MyRTFEditorKit extends RTFEditorKit {
  private MutableAttributeSet attr = null;
  private Font font = new Font("Consolas", Font.PLAIN, 12);

  public void initializeDefaultFont() {
    if(attr == null) attr = new SimpleAttributeSet();

    StyleConstants.setFontFamily(attr,font.getFontName());
    StyleConstants.setFontSize(attr,font.getSize());
    StyleConstants.setForeground(attr,Color.black);
    /*
    if(Default.RTF_FONT.isBold()) StyleConstants.setBold(attr,true);
    else StyleConstants.setBold(attr,false);

    if(Default.RTF_FONT.isItalic()) StyleConstants.setItalic(attr,true);
    else StyleConstants.setItalic(attr,false);
    */
    MutableAttributeSet iattr = getInputAttributes();
    iattr.removeAttribute(attr);
    iattr.addAttributes(attr);
  }
}