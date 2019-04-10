package com.example.speech;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.datatransfer.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;


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
    protected UndoManager undoManager;

    Clipboard clipboard;

    //TranscriberDemo speech2Text;
    synthesisTest text2Speech;

    public TextDemo() {

        super(new GridBagLayout());

        text2Speech = new synthesisTest();

        undoManager = new UndoManager();

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

        editorPane.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    public void undoableEditHappened(UndoableEditEvent e) {
                        undoManager.addEdit(e.getEdit());
                        updateButtons();
                    }
                });



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

        button_undo.addActionListener(this);
        button_undo.setActionCommand("undo");

        button_redo.addActionListener(this);
        button_redo.setActionCommand("redo");

        button_undo.setEnabled(false);
        button_redo.setEnabled(false);

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

        if (evt.getActionCommand().equals("undo")) {
            try {
                undoManager.undo();
            } catch (CannotRedoException cre) {
                //cre.printStackTrace();
            }
            updateButtons();

        }
        if (evt.getActionCommand().equals("redo")) {
            try {
                undoManager.redo();
            } catch (CannotRedoException cre) {
                //cre.printStackTrace();
            }
            updateButtons();

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
        TextDemo tD = new TextDemo();
        frame.getContentPane().add(tD);

        JMenuBar menuBar;
        JMenu editMenu, fileMenu, formatMenu;
        JMenuItem newMenuItem, saveMenuItem, openMenuItem; 
        JMenuItem selectAllMenuItem, undoMenuItem, redoMenuItem, cutMenuItem, copyMenuItem, pasteMenuItem, fandrMenuItem;
        JMenuItem fontMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the file menu
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_A);
        fileMenu.getAccessibleContext().setAccessibleDescription(
                "The File menu contains options like new, open and save.");
        menuBar.add(fileMenu);

        //a group of JMenuItems
        newMenuItem = new JMenuItem("New",
                KeyEvent.VK_T);
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        newMenuItem.getAccessibleContext().setAccessibleDescription(
                "If the current file is not saved, ask the user to save. Closed the saved file and open a new document.");
        newMenuItem.addActionListener(tD);
        newMenuItem.setActionCommand("new");
        fileMenu.add(newMenuItem);

        openMenuItem = new JMenuItem("Open",
                KeyEvent.VK_T);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        openMenuItem.getAccessibleContext().setAccessibleDescription(
                "Open a file from the disk");
        openMenuItem.addActionListener(tD);
        openMenuItem.setActionCommand("open");
        fileMenu.add(openMenuItem);


        saveMenuItem = new JMenuItem("Save",
                KeyEvent.VK_T);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        saveMenuItem.getAccessibleContext().setAccessibleDescription(
                "If the current file is not saved, ask the user to save. Otherwise, overwrite the current saved file.");
        saveMenuItem.addActionListener(tD);
        saveMenuItem.setActionCommand("save");
        fileMenu.add(saveMenuItem);










        //Build the edit menu.
        editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_A);
        editMenu.getAccessibleContext().setAccessibleDescription(
                "The Edit menu contains editing options like undo, redo, cut, copy and paste, find and replace functionality.");
        menuBar.add(editMenu);

        //a group of JMenuItems
        selectAllMenuItem = new JMenuItem("Select All",
                KeyEvent.VK_T);
        selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        selectAllMenuItem.getAccessibleContext().setAccessibleDescription(
                "Selects all the text of the document.");
        selectAllMenuItem.addActionListener(tD);
        selectAllMenuItem.setActionCommand("selectAll");
        editMenu.add(selectAllMenuItem);

        undoMenuItem = new JMenuItem("Undo",
                KeyEvent.VK_T);
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        undoMenuItem.getAccessibleContext().setAccessibleDescription(
                "Undoes the last operation.");
        undoMenuItem.addActionListener(tD);
        undoMenuItem.setActionCommand("undo");
        editMenu.add(undoMenuItem);

        redoMenuItem = new JMenuItem("Redo",
                KeyEvent.VK_T);
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        redoMenuItem.getAccessibleContext().setAccessibleDescription(
                "Redo the last undo operation.");
        redoMenuItem.addActionListener(tD);
        redoMenuItem.setActionCommand("redo");
        editMenu.add(redoMenuItem);

        cutMenuItem = new JMenuItem("Cut",
                KeyEvent.VK_T);
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        cutMenuItem.getAccessibleContext().setAccessibleDescription(
                "Cut the selected text, or cut all text if no selection is made.");
        cutMenuItem.addActionListener(tD);
        cutMenuItem.setActionCommand("cut");
        editMenu.add(cutMenuItem);

        copyMenuItem = new JMenuItem("Copy",
                KeyEvent.VK_T);
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        copyMenuItem.getAccessibleContext().setAccessibleDescription(
                "Copy the selected text, or copy all text if no selection is made.");
        copyMenuItem.addActionListener(tD);
        copyMenuItem.setActionCommand("copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem = new JMenuItem("Paste",
                KeyEvent.VK_T);
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        pasteMenuItem.getAccessibleContext().setAccessibleDescription(
                "Paste text from the system clipboard to the current carret position, or if carret is not present, append to the document.");
        pasteMenuItem.addActionListener(tD);
        pasteMenuItem.setActionCommand("paste");
        editMenu.add(pasteMenuItem);

        fandrMenuItem = new JMenuItem("Find and Replace",
                KeyEvent.VK_T);
        fandrMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        fandrMenuItem.getAccessibleContext().setAccessibleDescription(
                "Find and replace text in the document");
        fandrMenuItem.addActionListener(tD);
        fandrMenuItem.setActionCommand("fandr");
        editMenu.add(fandrMenuItem);











        //Build the format menu
        formatMenu = new JMenu("Format");
        formatMenu.setMnemonic(KeyEvent.VK_A);
        formatMenu.getAccessibleContext().setAccessibleDescription(
                "The Format menu contains options like changing font family, size etc..");
        menuBar.add(formatMenu);

        fontMenuItem = new JMenuItem("Font",
                KeyEvent.VK_T);
        fontMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        fontMenuItem.getAccessibleContext().setAccessibleDescription(
                "Changes the font of the document text");
        fontMenuItem.addActionListener(tD);
        fontMenuItem.setActionCommand("font");
        formatMenu.add(fontMenuItem);

        frame.setJMenuBar(menuBar);

        //Add contents to the window.

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

    public void updateButtons() {
        button_undo.setEnabled(undoManager.canUndo());
        button_redo.setEnabled(undoManager.canRedo());
    }
}
