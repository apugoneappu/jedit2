package com.example.speech;

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

import java.util.regex.Matcher; 
import java.util.regex.Pattern;

public class Snippet implements ActionListener {

    String find, replace;
    JButton btnFind;
    JButton btnReplace;
    JButton btnFindRegex;
    JButton btnReplaceAll;

    JTextField txtFind; 
    JTextField txtReplace;
    JEditorPane editorPane;

    Snippet(JEditorPane ePane) {

        editorPane = ePane;
        JDialog frDialog = new JDialog();
        frDialog.setLayout(new GridLayout(4,4));

        txtFind = new JTextField();
        txtReplace = new JTextField();
        btnFind = new JButton("Find");
        btnReplace = new JButton("Replace");
        btnReplaceAll = new JButton("Replace All");
        btnFindRegex = new JButton("Use Regex");

        frDialog.add(new JLabel("Find: "));
        frDialog.add(txtFind);
        frDialog.add(new JLabel(""));
        frDialog.add(btnFind);
        frDialog.add(new JLabel(""));
        frDialog.add(new JLabel(""));
        frDialog.add(new JLabel(""));
        frDialog.add(btnFindRegex);
        frDialog.add(new JLabel("Replace with: "));
        frDialog.add(txtReplace);
        frDialog.add(new JLabel(""));
        frDialog.add(btnReplace);
        frDialog.add(new JLabel(""));
        frDialog.add(new JLabel(""));
        frDialog.add(new JLabel(""));
        frDialog.add(btnReplaceAll);

        frDialog.pack();
        frDialog.setVisible(true);

        btnFind.addActionListener(this);
        btnFind.setActionCommand("find");

        btnFindRegex.addActionListener(this);
        btnFindRegex.setActionCommand("findRegex");

        btnReplace.addActionListener(this);
        btnReplace.setActionCommand("replace");

        btnReplaceAll.addActionListener(this);
        btnReplaceAll.setActionCommand("replaceAll");
    }

    public void actionPerformed(ActionEvent evt) {


        //for find and highlight
        if (evt.getActionCommand().equals("find")) {
            String find = txtFind.getText();
            String text = editorPane.getText();
            if (text.contains(find) == true) {

                //highlight all matches

                javax.swing.text.DefaultHighlighter.DefaultHighlightPainter highlightPainter = new javax.swing.text.DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                try {
                        int index = text.indexOf(find);
                    for (; index < text.length() && index != -1;) {
                        editorPane.getHighlighter().addHighlight(index, index + find.length(), highlightPainter);
                        index = text.indexOf(find, index+find.length())   ;
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        if (evt.getActionCommand().equals("findRegex")) {
            String find = txtFind.getText();
            String text = editorPane.getText();

            Pattern pattern = Pattern.compile(find, Pattern.CASE_INSENSITIVE); 
            Matcher m = pattern.matcher(text); 


            while (m.find()){

                //highlight all matches
                try {
                        javax.swing.text.DefaultHighlighter.DefaultHighlightPainter highlightPainter = new javax.swing.text.DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                        editorPane.getHighlighter().addHighlight(m.start(), m.end()-1, highlightPainter);
                    }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        //for replace
        if (evt.getActionCommand().equals("replaceAll")) {
            String find = txtFind.getText();
            String replace = txtReplace.getText();
            String text = editorPane.getText();
            editorPane.setText(text.replace(find, replace));
        }

        if (evt.getActionCommand().equals("replace")) {
            String find = txtFind.getText();
            String replace = txtReplace.getText();
            String text = editorPane.getText();
            editorPane.setText(text.replaceFirst(find, replace));
        }


    }

    public static void show(JFrame frame) {
        frame.setVisible(true);
    }
}
