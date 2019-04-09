package com.example.speech;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.datatransfer.*;

public class TextDemo extends JPanel implements ActionListener {

    String file = "";
    protected JEditorPane editorPane;
    protected JButton button_t2s;
    protected JButton button_s2t;
    protected JButton button_open;
    protected JButton button_save;
    protected JButton button_new;
    protected JButton button_stop;
    protected JButton button_paste;
    protected JButton button_copy;
    protected JButton button_cut;
    protected JButton button_fandr;
    protected JButton button_undo;
    protected JButton button_redo;
    protected JButton button_selectAll;
    protected JButton button_font;

    Clipboard clipboard;

    //TranscriberDemo speech2Text;
    synthesisTest text2Speech;

    public TextDemo() {

        super(new GridBagLayout());

        text2Speech = new synthesisTest();

        clipboard = Toolkit.getDefaultToolkit( ).getSystemClipboard( );
        button_t2s = new JButton("Text To Speech");
        button_s2t = new JButton("Speech To Text");
        button_open = new JButton("Open");
        button_save = new JButton("Save");
        button_new = new JButton("New");
        button_stop = new JButton("Stop");
        button_paste = new JButton("Paste");
        button_copy = new JButton("Copy");
        button_cut = new JButton("Cut");
        button_fandr = new JButton("Find and Replace");
        button_undo = new JButton("Undo");
        button_redo = new JButton("Redo");
        button_selectAll = new JButton("Select All");
        button_font = new JButton("Font");

        editorPane = new JEditorPane();
        JScrollPane scrollPane = new JScrollPane(editorPane);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();

        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5,5,2,5);
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        add(scrollPane, c);

        GridBagConstraints c_button = new GridBagConstraints();
        c_button.insets = new Insets(2, 5, 5, 2);
        c_button.fill = GridBagConstraints.BOTH;
        c_button.weightx = 1;
        c_button.weighty = 0.1;
        c_button.gridwidth = 1;
        c_button.gridheight = 1;

        c_button.gridx = 0;
        c_button.gridy = 2;
        add(button_t2s, c_button);

        c_button.gridx = 1;
        c_button.gridy = 2;
        add(button_s2t, c_button);

        c_button.gridx = 2;
        c_button.gridy = 2;
        add(button_stop, c_button);

        c_button.gridx = 0;
        c_button.gridy = 0;
        add(button_open, c_button);

        c_button.gridx = 1;
        c_button.gridy = 0;
        add(button_save, c_button);

        c_button.gridx = 2;
        c_button.gridy = 0;
        add(button_new, c_button);

        c_button.gridx = 4;
        c_button.gridy = 2;
        add(button_paste, c_button);

        c_button.gridx = 1;
        c_button.gridy = 3;
        add(button_copy, c_button);

        c_button.gridx = 2;
        c_button.gridy = 3;
        add(button_cut, c_button);

        c_button.gridx = 0;
        c_button.gridy = 4;
        add(button_fandr, c_button);

        c_button.gridx = 4;
        c_button.gridy = 0;
        add(button_font, c_button);


        c_button.gridx = 1;
        c_button.gridy = 4;
        add(button_undo, c_button);

        c_button.gridx = 2;
        c_button.gridy = 4;
        add(button_redo, c_button);

        c_button.gridx = 4;
        c_button.gridy = 4;
        add(button_selectAll, c_button);

        button_t2s.addActionListener(this);
        button_t2s.setActionCommand("text2speech");

        button_s2t.addActionListener(this);
        button_s2t.setActionCommand("speech2text");

        button_open.addActionListener(this);
        button_open.setActionCommand("open");

        button_save.addActionListener(this);
        button_save.setActionCommand("save");

        button_new.addActionListener(this);
        button_new.setActionCommand("new");

        button_stop.addActionListener(this);
        button_stop.setActionCommand("stop");

        button_paste.addActionListener(this);
        button_paste.setActionCommand("paste");

        button_copy.addActionListener(this);
        button_copy.setActionCommand("copy");

        button_cut.addActionListener(this);
        button_cut.setActionCommand("cut");

        button_fandr.addActionListener(this);
        button_fandr.setActionCommand("fandr");

        button_selectAll.addActionListener(this);
        button_selectAll.setActionCommand("selectAll");

        button_font.addActionListener(this);
        button_font.setActionCommand("font");
        UndoRedo undo = new UndoRedo(editorPane, button_undo, button_redo);
    }

    public void actionPerformed(ActionEvent evt) {

        //System.out.println(evt.getActionCommand());

        // For text to speech command
        if (evt.getActionCommand().equals("text2speech")) {
            String text = editorPane.getText();
            text2Speech.read(text);
        }

        // For stoping the text to speech conversion
        if (evt.getActionCommand().equals("stop")) {
            //text2Speech.stop();
        }

        // For speech to text command    
        if (evt.getActionCommand().equals("speech2text")) {

            try {
                Recognize speech2Text = new Recognize();
                String text = speech2Text.text;
                editorPane.setText(editorPane.getText() + " " + text);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }

        // For open command
        if (evt.getActionCommand().equals("open")) {

            String filename = "";
            try{
                final JFileChooser fc = new JFileChooser();

                // Creates the dialogue box
                int r = fc.showOpenDialog(null); 

                // if the user selects a file 
                if (r == JFileChooser.APPROVE_OPTION) 
                { 
                    // set the label to the path of the selected file 
                    filename = fc.getSelectedFile().getAbsolutePath(); 
                } 

                File file = new File(filename); 
                BufferedReader br = new BufferedReader(new FileReader(file)); 
                String text; 
                while ((text = br.readLine()) != null) 
                    editorPane.setText(editorPane.getText() + " \n" + text); 

            }catch(Exception e)
            {
                System.out.println("Exception:"+e);
            }

        }
        if (evt.getActionCommand().equals("paste")) {

            try {
                Transferable t = clipboard.getContents(null);
                if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String text = (String)t.getTransferData(DataFlavor.stringFlavor);
                    editorPane.getDocument().insertString(editorPane.getCaretPosition(), text, null);
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
            }

        }

        if (evt.getActionCommand().equals("copy")) {

            //if no text is selected, copy all
            if (editorPane.getSelectedText() == null) {
                try {
                    StringSelection data = new StringSelection(editorPane.getText());
                    clipboard.setContents(data, data);
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }

            //some text is selected, copy only that
            else {
                try {
                    StringSelection data = new StringSelection(editorPane.getSelectedText());
                    clipboard.setContents(data, data);
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }

        if (evt.getActionCommand().equals("cut")) {

            //if no text is selected, cut all
            if (editorPane.getSelectedText() == null) {

                try {
                    StringSelection data = new StringSelection(editorPane.getText());
                    clipboard.setContents(data, data);
                    editorPane.setText("");
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }

            //some text is selected, cut only that
            else {
                try {
                    StringSelection data = new StringSelection(editorPane.getSelectedText());
                    clipboard.setContents(data, data);
                    editorPane.replaceSelection("");
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }

        if (evt.getActionCommand().equals("fandr")) {
            Snippet fandr = new Snippet(editorPane);
        }

        if (evt.getActionCommand().equals("selectAll")) {
            editorPane.selectAll();
        }

        // For font command
        if (evt.getActionCommand().equals("font")) {

            String filename = "";
            try{
                final JFileChooser fc = new JFileChooser("./fonts");

                // Creates the dialogue box
                int r = fc.showOpenDialog(null); 

                // if the user selects a file 
                if (r == JFileChooser.APPROVE_OPTION) 
                { 
                    // set the label to the path of the selected file 
                    filename = fc.getSelectedFile().getName(); 
                } 

                editorPane.setFont(new Font(filename,0,14));
                // editorPane.setSelectedTextColor(Color.green);

                // String text; 
                // while ((text = br.readLine()) != null) 
                //   editorPane.setText(editorPane.getText() + " \n" + text); 

            }catch(Exception e)
            {
                System.out.println("Exception:"+e);
            }

        }

        // For font size command
        if (evt.getActionCommand().equals("font_size")) {

            String filename = "";
            try{
                final JFileChooser fc = new JFileChooser("/home/shrey/jedit/voce/samples/synthesisTest/java/fonts");

                // Creates the dialogue box
                int r = fc.showOpenDialog(null); 

                // if the user selects a file 
                if (r == JFileChooser.APPROVE_OPTION) 
                { 
                    // set the label to the path of the selected file 
                    filename = fc.getSelectedFile().getName(); 
                } 

                editorPane.setFont(new Font(filename,0,40));
                // editorPane.setSelectedTextColor(Color.green);

                // String text; 
                // while ((text = br.readLine()) != null) 
                //   editorPane.setText(editorPane.getText() + " \n" + text); 

            }catch(Exception e)
            {
                System.out.println("Exception:"+e);
            }

        }
        // For save command
        if (evt.getActionCommand().equals("save")) {

            String str = editorPane.getText(); 
            String filename = "";
            if(file.isEmpty())
            {
                try{
                    final JFileChooser fc = new JFileChooser();

                    // Creates the dialogue box
                    int r = fc.showSaveDialog(null); 
                    // if the user selects a file 
                    if (r == JFileChooser.APPROVE_OPTION) 
                    { 
                        // set the label to the path of the selected file 
                        filename = fc.getSelectedFile().getAbsolutePath(); 
                    }
                    file = filename;

                }catch(Exception e)
                {
                    System.out.println("Exception:"+e);
                }
            }
            else 
                filename = file;
            try{ 
                // attach a file to FileWriter  
                FileWriter fw = new FileWriter(filename); 

                // read character wise from string and write into FileWriter  
                for (int i = 0; i < str.length(); i++) 
                    fw.write(str.charAt(i)); 

                fw.close(); 
            }catch(Exception e)
            {
                System.out.println("Exception:"+e);        
            }

        }

        // For new command
        if (evt.getActionCommand().equals("new")) {

            // Before clearing all text prompt to save existing data
            String str = editorPane.getText(); 
            String filename = "";

            if(file.isEmpty())
            {
                try{
                    final JFileChooser fc = new JFileChooser();

                    // Creates the dialogue box
                    int r = fc.showSaveDialog(null); 

                    // if the user selects a file 
                    if (r == JFileChooser.APPROVE_OPTION) 
                    { 
                        // set the label to the path of the selected file 
                        filename = fc.getSelectedFile().getAbsolutePath(); 
                    }

                    // attach a file to FileWriter  
                    FileWriter fw = new FileWriter(filename); 

                    // read character wise from string and write into FileWriter  
                    for (int i = 0; i < str.length(); i++) 
                        fw.write(str.charAt(i)); 

                    fw.close(); 

                }catch(Exception e)
                {
                    System.out.println("Exception:"+e);
                }
            } 

            file = "";
            str = "";
            editorPane.setText(str); 
        }


    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("TextDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension(800, 500));

        //Add contents to the window.
        frame.getContentPane().add(new TextDemo());

        frame.setIconImage(new ImageIcon("yo.png").getImage());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
