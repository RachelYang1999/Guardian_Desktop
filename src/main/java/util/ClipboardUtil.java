package util;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * The util class for providing button copy service
 * @author Rachel Yang
 */
public class ClipboardUtil {

  /**
   * Set the text to the clipboard
   * @param text text to be copied
   */
  public void setClipboardString(String text) {
    // Get system clipboard
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    // Encapsulate text content
    Transferable trans = new StringSelection(text);
    // Set the text content to the system clipboard
    clipboard.setContents(trans, null);
  }

  /**
   * Get text from the clipboard (paste)
   * @return pasted text
   */
  public String getClipboardString() {
    // Get system clipboard
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    // Get the contents of the clipboard
    Transferable trans = clipboard.getContents(null);

    if (trans != null) {
      // Determine whether the content in the clipboard supports text
      if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
        try {
          // Get the text content in the clipboard
          String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
          return text;
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }
}
