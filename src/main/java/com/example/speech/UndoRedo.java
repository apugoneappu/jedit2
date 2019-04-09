package com.example.speech;

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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.datatransfer.*;

public class UndoRedo extends JFrame {

    protected UndoManager undoManager = new UndoManager();
    protected JButton undoButton;
    protected JButton redoButton;
    JEditorPane editorPane;

    public UndoRedo(JEditorPane ePane, JButton un, JButton re) {

        editorPane = ePane;
        undoButton = un;
        redoButton = re;

        undoButton.setEnabled(false);
        redoButton.setEnabled(false);

        editorPane.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    public void undoableEditHappened(UndoableEditEvent e) {
                        undoManager.addEdit(e.getEdit());
                        updateButtons();
                    }
                });

        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    undoManager.undo();
                } catch (CannotRedoException cre) {
                    //cre.printStackTrace();
                }
                updateButtons();
            }
        });

        redoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    undoManager.redo();
                } catch (CannotRedoException cre) {
                    //cre.printStackTrace();
                }
                updateButtons();
            }
        });

        //setSize(400, 300);
        //setVisible(true);
    }

    public void updateButtons() {
        //undoButton.setText(undoManager.getUndoPresentationName());
        //redoButton.setText(undoManager.getRedoPresentationName());
        undoButton.setEnabled(undoManager.canUndo());
        redoButton.setEnabled(undoManager.canRedo());
    }

}
