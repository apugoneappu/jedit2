package com.example.speech;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
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

import org.drjekyll.fontchooser.*;

//text to speech
import com.example.texttospeech.*;
import com.google.protobuf.ByteString;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.JLabel;

import javax.swing.text.Document;
import javax.swing.text.html.*;

class ClockPane extends JPanel {

    private JLabel clock = new JLabel();
    // clock.setBorder(BorderFactory.createLineBorder(Color.black));

    public ClockPane() {
        setLayout(new BorderLayout());
        tickTock();
        add(clock, BorderLayout.WEST);
        Timer timer = new Timer(500, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            tickTock();
          }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }

    public void tickTock() {
        clock.setText(DateFormat.getDateTimeInstance().format(new Date()));
    }

}

public class TextDemo extends JPanel implements ActionListener{
    String file = "";
    String audiofile = "";
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
    static Color defaultColor;

    Clipboard clipboard;
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


        editorPane.setFont(new Font("Seravek", Font.PLAIN, 20));
        defaultColor = editorPane.getBackground();

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

        /*
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
        */

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

            try {
                new SynthesizeText().createFile(text, "./resources/output.mp3");
            }
            catch (Exception e) {
                System.out.println(e);
            }

            new AudioTest().play("./resources/output.mp3");

        }

        if (evt.getActionCommand().equals("text2speechFile")) {

            String text = editorPane.getText();

            //if the audio file hasn't been saved even once
            if (audiofile.isEmpty()) {

                try{
                    final JFileChooser fc = new JFileChooser();

                    // Creates the dialogue box
                    int r = fc.showSaveDialog(null); 
                    // if the user selects a file 
                    if (r == JFileChooser.APPROVE_OPTION) 
                    { 
                        // set the label to the path of the selected file 
                        audiofile = fc.getSelectedFile().getAbsolutePath(); 

                        try {
                            new SynthesizeText().createFile(text, audiofile);
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }

                    }

                }
                catch(Exception e)
                {
                    System.out.println("Exception:"+e);
                }
            }

            //if the audio file has been saved, overwrie
            else {
                try {
                    new SynthesizeText().createFile(text, audiofile);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }


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
                    String layeredText = (String)t.getTransferData(DataFlavor.stringFlavor);
                    
                    String[] text = layeredText.split("`");
                    editorPane.getDocument().insertString(editorPane.getCaretPosition(), text[0], null);
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
            }

        }
        

        if (evt.getActionCommand().equals("clipboard_1")) {

            try {
                Transferable t = clipboard.getContents(null);
                if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String layeredText = (String)t.getTransferData(DataFlavor.stringFlavor);
                    
                    String[] text = layeredText.split("`");
                    if( text.length>1 )
                        editorPane.getDocument().insertString(editorPane.getCaretPosition(), text[1], null);
                    else
                        editorPane.getDocument().insertString(editorPane.getCaretPosition(), "", null);

                }
            }
            catch (Exception ex) {
                System.out.println(ex);
            }

        }

        if (evt.getActionCommand().equals("clipboard_2")) {

            try {
                Transferable t = clipboard.getContents(null);
                if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String layeredText = (String)t.getTransferData(DataFlavor.stringFlavor);
                    
                    String[] text = layeredText.split("`");
                    if( text.length>2 )
                        editorPane.getDocument().insertString(editorPane.getCaretPosition(), text[2], null);
                    else
                        editorPane.getDocument().insertString(editorPane.getCaretPosition(), "", null);

                }
            }
            catch (Exception ex) {
                System.out.println(ex);
            }

        }

        if (evt.getActionCommand().equals("clipboard_3")) {

            try {
                Transferable t = clipboard.getContents(null);
                if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String layeredText = (String)t.getTransferData(DataFlavor.stringFlavor);
                    
                    String[] text = layeredText.split("`");
                    if( text.length>3 )
                        editorPane.getDocument().insertString(editorPane.getCaretPosition(), text[3], null);
                    else
                        editorPane.getDocument().insertString(editorPane.getCaretPosition(), "", null);

                }
            }
            catch (Exception ex) {
                System.out.println(ex);
            }

        }

        if (evt.getActionCommand().equals("clipboard_4")) {

            try {
                Transferable t = clipboard.getContents(null);
                if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String layeredText = (String)t.getTransferData(DataFlavor.stringFlavor);
                    
                    String[] text = layeredText.split("`");
                    if( text.length>4 )
                        editorPane.getDocument().insertString(editorPane.getCaretPosition(), text[4], null);
                    else
                        editorPane.getDocument().insertString(editorPane.getCaretPosition(), "", null);

                }
            }
            catch (Exception ex) {
                System.out.println(ex);
            }

        }

        if (evt.getActionCommand().equals("clipboard_5")) {

            try {
                Transferable t = clipboard.getContents(null);
                if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String layeredText = (String)t.getTransferData(DataFlavor.stringFlavor);
                    
                    String[] text = layeredText.split("`");
                    if( text.length>5 )
                        editorPane.getDocument().insertString(editorPane.getCaretPosition(), text[5], null);
                    else
                        editorPane.getDocument().insertString(editorPane.getCaretPosition(), "", null);

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
                    String text = ""; 
                    Transferable t = clipboard.getContents(null);
                    if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        text = (String)t.getTransferData(DataFlavor.stringFlavor);
                        // editorPane.getDocument().insertString(editorPane.getCaretPosition(), text, null);
                    }
                    String layeredText="";
                    
                    if(text.isEmpty())
                        layeredText = editorPane.getSelectedText();
                    else
                        layeredText = editorPane.getSelectedText() + "`" + text;
                    

                    StringSelection data = new StringSelection(layeredText);
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

            try {
            FontDialog.showDialog(editorPane);

            }
            catch(Exception e)
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


        // For exit command
        if (evt.getActionCommand().equals("exit")) {

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
                    else {
                        return;
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
            System.exit(0);

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
                    System.out.println(e);
                }
            } 

            if (filename.equals("")) {
                editorPane.setText(str); 
                return;
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

        if (evt.getActionCommand().equals("toggledarkmode")) {
            //if in white mode, go to dark mode
            if (editorPane.getBackground() == defaultColor) {
                editorPane.setBackground(Color.LIGHT_GRAY);
            }

            //if not in white mode, go to white mode
            else {
                editorPane.setBackground(defaultColor);
            }

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

        frame.add(new ClockPane(),BorderLayout.SOUTH );
        frame.pack();
        frame.setVisible(true);

        frame.setPreferredSize(new Dimension(800, 500));
        TextDemo tD = new TextDemo();
        frame.getContentPane().add(tD);

        JMenuBar menuBar;
        JMenu editMenu, fileMenu, viewMenu, formatMenu, clipboardMenu, speechMenu;

        JMenuItem clipboardMenuItem_1, clipboardMenuItem_2, clipboardMenuItem_3, clipboardMenuItem_4, clipboardMenuItem_5;
        JMenuItem newMenuItem, saveMenuItem, openMenuItem, exitMenuItem; 
        JMenuItem selectAllMenuItem, undoMenuItem, redoMenuItem, cutMenuItem, copyMenuItem, pasteMenuItem, fandrMenuItem;
        JMenuItem fullScreenMenuItem, toggleDarkModeMenuItem;
        JMenuItem fontMenuItem;
        JMenuItem t2sMenuItem, s2tMenuItem, t2sSaveAudioMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();






        //Build the file menu
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.getAccessibleContext().setAccessibleDescription(
                "The File menu contains options like new, open and save.");
        menuBar.add(fileMenu);

        //a group of JMenuItems
        newMenuItem = new JMenuItem("New",
                KeyEvent.VK_N);
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_N, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        newMenuItem.getAccessibleContext().setAccessibleDescription(
                "If the current file is not saved, ask the user to save. Closed the saved file and open a new document.");
        newMenuItem.addActionListener(tD);
        newMenuItem.setActionCommand("new");
        fileMenu.add(newMenuItem);

        openMenuItem = new JMenuItem("Open",
                KeyEvent.VK_O);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openMenuItem.getAccessibleContext().setAccessibleDescription(
                "Open a file from the disk");
        openMenuItem.addActionListener(tD);
        openMenuItem.setActionCommand("open");
        fileMenu.add(openMenuItem);


        saveMenuItem = new JMenuItem("Save",
                KeyEvent.VK_S);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveMenuItem.getAccessibleContext().setAccessibleDescription(
                "If the current file is not saved, ask the user to save. Otherwise, overwrite the current saved file.");
        saveMenuItem.addActionListener(tD);
        saveMenuItem.setActionCommand("save");
        fileMenu.add(saveMenuItem);

        exitMenuItem = new JMenuItem("Exit",
                KeyEvent.VK_E);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exitMenuItem.getAccessibleContext().setAccessibleDescription(
                "Exits the program asking to save the file if not saved");
        exitMenuItem.addActionListener(tD);
        exitMenuItem.setActionCommand("exit");
        fileMenu.add(exitMenuItem);




        //Build the view menu
        viewMenu = new JMenu("View");
        viewMenu.setMnemonic(KeyEvent.VK_V);
        viewMenu.getAccessibleContext().setAccessibleDescription(
                "The view menu contains options like full screen mode and changing themes.");
        menuBar.add(viewMenu);

        //a group of JMenuItems
        fullScreenMenuItem = new JMenuItem("Full Screen",
                KeyEvent.VK_F);
        fullScreenMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_F, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK + ActionEvent.META_MASK));
        fullScreenMenuItem.getAccessibleContext().setAccessibleDescription(
                "Change the document to full screen mode.");
        fullScreenMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    frame.setExtendedState(JFrame.NORMAL); 
                }
                if (frame.getExtendedState() == JFrame.NORMAL) {
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                }
            }
        }); 
        viewMenu.add(fullScreenMenuItem);

        toggleDarkModeMenuItem = new JMenuItem("Toggle Dark Mode",
                KeyEvent.VK_D);
        toggleDarkModeMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_D, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK + ActionEvent.META_MASK));
        toggleDarkModeMenuItem.getAccessibleContext().setAccessibleDescription(
                "Toggles the dark mode");
        toggleDarkModeMenuItem.addActionListener(tD); 
        toggleDarkModeMenuItem.setActionCommand("toggledarkmode");
        viewMenu.add(toggleDarkModeMenuItem);







        //Build the edit menu.
        editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.getAccessibleContext().setAccessibleDescription(
                "The Edit menu contains editing options like undo, redo, cut, copy and paste, find and replace functionality.");
        menuBar.add(editMenu);

        //a group of JMenuItems
        selectAllMenuItem = new JMenuItem("Select All",
                KeyEvent.VK_A);
        selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_A, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        selectAllMenuItem.getAccessibleContext().setAccessibleDescription(
                "Selects all the text of the document.");
        selectAllMenuItem.addActionListener(tD);
        selectAllMenuItem.setActionCommand("selectAll");
        editMenu.add(selectAllMenuItem);

        undoMenuItem = new JMenuItem("Undo",
                KeyEvent.VK_U);
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_U, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        undoMenuItem.getAccessibleContext().setAccessibleDescription(
                "Undoes the last operation.");
        undoMenuItem.addActionListener(tD);
        undoMenuItem.setActionCommand("undo");
        editMenu.add(undoMenuItem);

        redoMenuItem = new JMenuItem("Redo",
                KeyEvent.VK_R);
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_R, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        redoMenuItem.getAccessibleContext().setAccessibleDescription(
                "Redo the last undo operation.");
        redoMenuItem.addActionListener(tD);
        redoMenuItem.setActionCommand("redo");
        editMenu.add(redoMenuItem);

        cutMenuItem = new JMenuItem("Cut",
                KeyEvent.VK_X);
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_X, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        cutMenuItem.getAccessibleContext().setAccessibleDescription(
                "Cut the selected text, or cut all text if no selection is made.");
        cutMenuItem.addActionListener(tD);
        cutMenuItem.setActionCommand("cut");
        editMenu.add(cutMenuItem);

        copyMenuItem = new JMenuItem("Copy",
                KeyEvent.VK_C);
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_C, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        copyMenuItem.getAccessibleContext().setAccessibleDescription(
                "Copy the selected text, or copy all text if no selection is made.");
        copyMenuItem.addActionListener(tD);
        copyMenuItem.setActionCommand("copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem = new JMenuItem("Paste",
                KeyEvent.VK_P);
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_V, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        pasteMenuItem.getAccessibleContext().setAccessibleDescription(
                "Paste text from the system clipboard to the current carret position, or if carret is not present, append to the document.");
        pasteMenuItem.addActionListener(tD);
        pasteMenuItem.setActionCommand("paste");
        editMenu.add(pasteMenuItem);


        fandrMenuItem = new JMenuItem("Find and Replace",
                KeyEvent.VK_F);
        fandrMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_F, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        fandrMenuItem.getAccessibleContext().setAccessibleDescription(
                "Find and replace text in the document");
        fandrMenuItem.addActionListener(tD);
        fandrMenuItem.setActionCommand("fandr");
        editMenu.add(fandrMenuItem);






        //Build the format menu
        formatMenu = new JMenu("Format");
        formatMenu.setMnemonic(KeyEvent.VK_O);
        formatMenu.getAccessibleContext().setAccessibleDescription(
                "The Format menu contains options like changing font family, size etc..");
        menuBar.add(formatMenu);

        fontMenuItem = new JMenuItem("Font",
                KeyEvent.VK_F);
        fontMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_D, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        fontMenuItem.getAccessibleContext().setAccessibleDescription(
                "Changes the font of the document text");
        fontMenuItem.addActionListener(tD);
        fontMenuItem.setActionCommand("font");
        formatMenu.add(fontMenuItem);













        //Build the speech menu
        speechMenu = new JMenu("Speech");
        speechMenu.setMnemonic(KeyEvent.VK_S);
        speechMenu.getAccessibleContext().setAccessibleDescription(
                "The speech menu contains text to speech and speech to text options.");
        menuBar.add(speechMenu);

        t2sMenuItem = new JMenuItem("Text to Speech",
                KeyEvent.VK_T);
        t2sMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_T, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        t2sMenuItem.getAccessibleContext().setAccessibleDescription(
                "Reads the document.");
        t2sMenuItem.addActionListener(tD);
        t2sMenuItem.setActionCommand("text2speech");
        speechMenu.add(t2sMenuItem);

        s2tMenuItem = new JMenuItem("Speech to Text",
                KeyEvent.VK_S);
        s2tMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        s2tMenuItem.getAccessibleContext().setAccessibleDescription(
                "Listens to the user and adds the text to the document.");
        s2tMenuItem.addActionListener(tD);
        s2tMenuItem.setActionCommand("speech2text");
        speechMenu.add(s2tMenuItem);

        t2sSaveAudioMenuItem = new JMenuItem("Save as audio file",
                KeyEvent.VK_O);
        t2sSaveAudioMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_O, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        t2sSaveAudioMenuItem.getAccessibleContext().setAccessibleDescription(
                "Saves the audio of the current document in .mp3 format");
        t2sSaveAudioMenuItem.addActionListener(tD);
        t2sSaveAudioMenuItem.setActionCommand("text2speechFile");
        speechMenu.add(t2sSaveAudioMenuItem);




        //Build the clipboard menu
        clipboardMenu = new JMenu("Clipboard");
        clipboardMenu.setMnemonic(KeyEvent.VK_C);
        clipboardMenu.getAccessibleContext().setAccessibleDescription(
                "The Clipboard menu contains options to paste multiple selections.");
        menuBar.add(clipboardMenu);

        clipboardMenuItem_1 = new JMenuItem("Clipboard 1",
                KeyEvent.VK_1);
        clipboardMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        clipboardMenuItem_1.getAccessibleContext().setAccessibleDescription(
                "Paste text from the system clipboard to the current carret position, or if carret is not present, append to the document.");
        clipboardMenuItem_1.addActionListener(tD);
        clipboardMenuItem_1.setActionCommand("clipboard_1");
        clipboardMenu.add(clipboardMenuItem_1);

        clipboardMenuItem_2 = new JMenuItem("Clipboard 2",
                KeyEvent.VK_2);
        clipboardMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_2, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        clipboardMenuItem_2.getAccessibleContext().setAccessibleDescription(
                "Paste text from the system clipboard to the current carret position, or if carret is not present, append to the document.");
        clipboardMenuItem_2.addActionListener(tD);
        clipboardMenuItem_2.setActionCommand("clipboard_2");
        clipboardMenu.add(clipboardMenuItem_2);

        clipboardMenuItem_3 = new JMenuItem("Clipboard 3",
                KeyEvent.VK_3);
        clipboardMenuItem_3.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_3, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        clipboardMenuItem_3.getAccessibleContext().setAccessibleDescription(
                "Paste text from the system clipboard to the current carret position, or if carret is not present, append to the document.");
        clipboardMenuItem_3.addActionListener(tD);
        clipboardMenuItem_3.setActionCommand("clipboard_3");
        clipboardMenu.add(clipboardMenuItem_3);

        clipboardMenuItem_4 = new JMenuItem("Clipboard 4",
                KeyEvent.VK_4);
        clipboardMenuItem_4.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_4, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        clipboardMenuItem_4.getAccessibleContext().setAccessibleDescription(
                "Paste text from the system clipboard to the current carret position, or if carret is not present, append to the document.");
        clipboardMenuItem_4.addActionListener(tD);
        clipboardMenuItem_4.setActionCommand("clipboard_4");
        clipboardMenu.add(clipboardMenuItem_4);

        clipboardMenuItem_5 = new JMenuItem("Clipboard 5",
                KeyEvent.VK_5);
        clipboardMenuItem_5.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_5, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        clipboardMenuItem_5.getAccessibleContext().setAccessibleDescription(
                "Paste text from the system clipboard to the current carret position, or if carret is not present, append to the document.");
        clipboardMenuItem_5.addActionListener(tD);
        clipboardMenuItem_5.setActionCommand("clipboard_5");
        clipboardMenu.add(clipboardMenuItem_5);



        frame.setJMenuBar(menuBar);

        //Add contents to the window.

        frame.setIconImage(new ImageIcon("./resources/yo.png").getImage());



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
