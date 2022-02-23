/*
    Copyright (C) 2022  Patrick260

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package de.patrick260.jtexteditor.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class GUI extends JFrame {

    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 600;

    private static final String TITLE = "JTextEditor";

    private final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    private final JEditorPane editor;

    private File saveTo;


    public GUI() {

        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        final JMenuBar menuBar = new JMenuBar();

        final JMenu fileMenu = new JMenu("File");

        final JMenuItem menuItem_new = new JMenuItem("New");
        menuItem_new.addActionListener(this::newFile);

        final JMenuItem menuItem_open = new JMenuItem("Open");
        menuItem_open.addActionListener(this::openFile);

        final JMenuItem menuItem_save = new JMenuItem("Save");
        menuItem_save.addActionListener(this::saveFile);

        final JMenuItem menuItem_saveAs = new JMenuItem("Save as");
        menuItem_saveAs.addActionListener(this::saveFileAs);

        fileMenu.add(menuItem_new);
        fileMenu.add(menuItem_open);
        fileMenu.add(menuItem_save);
        fileMenu.add(menuItem_saveAs);

        final JMenu editMenu = new JMenu("Edit");

        final JMenuItem menuItem_undo = new JMenuItem("Undo");
        menuItem_undo.addActionListener(this::undo);

        final JMenuItem menuItem_redo = new JMenuItem("Redo");
        menuItem_redo.addActionListener(this::redo);

        final JMenuItem menuItem_cut = new JMenuItem("Cut");
        menuItem_cut.addActionListener(this::cut);

        final JMenuItem menuItem_copy = new JMenuItem("Copy");
        menuItem_copy.addActionListener(this::copy);

        final JMenuItem menuItem_paste = new JMenuItem("Paste");
        menuItem_paste.addActionListener(this::paste);

        final JMenuItem menuItem_delete = new JMenuItem("Delete");
        menuItem_delete.addActionListener(this::delete);

        final JMenuItem menuItem_selectAll = new JMenuItem("Select All");
        menuItem_selectAll.addActionListener(this::selectAll);

        editMenu.add(menuItem_undo);
        editMenu.add(menuItem_redo);
        editMenu.add(menuItem_cut);
        editMenu.add(menuItem_copy);
        editMenu.add(menuItem_paste);
        editMenu.add(menuItem_delete);
        editMenu.add(menuItem_selectAll);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        editor = new JEditorPane();

        final JScrollPane scrollPane = new JScrollPane(editor);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane);

        pack();

        setLocationRelativeTo(null);

    }


    private void newFile(final ActionEvent event) {

        editor.setText(null);

        saveTo = null;

    }

    private void openFile(final ActionEvent event) {

        final JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showOpenDialog(this);

        if (i == JFileChooser.APPROVE_OPTION) {

            try {

                editor.setPage(fileChooser.getSelectedFile().toURI().toURL());

                saveTo = fileChooser.getSelectedFile();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

    private void saveFile(final ActionEvent event) {

        if (saveTo != null) {

            try {

                final FileWriter writer = new FileWriter(saveTo);
                writer.write(editor.getText());
                writer.flush();
                writer.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        } else {

            saveFileAs(event);

        }

    }

    private void saveFileAs(final ActionEvent event) {

        final JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showSaveDialog(this);

        if (i == JFileChooser.APPROVE_OPTION) {

            try {

                final FileWriter writer = new FileWriter(fileChooser.getSelectedFile());
                writer.write(editor.getText());
                writer.flush();
                writer.close();

                saveTo = fileChooser.getSelectedFile();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

    private void undo(final ActionEvent event) {



    }

    private void redo(final ActionEvent event) {



    }

    private void cut(final ActionEvent event) {

        clipboard.setContents(new StringSelection(editor.getSelectedText()), null);
        editor.replaceSelection("");

    }

    private void copy(final ActionEvent event) {

        clipboard.setContents(new StringSelection(editor.getSelectedText()), null);

    }

    private void paste(final ActionEvent event) {

        try {

            editor.replaceSelection(String.valueOf(clipboard.getData(DataFlavor.stringFlavor)));

        } catch (UnsupportedFlavorException | IOException e) {

            e.printStackTrace();

        }

    }

    private void delete(final ActionEvent event) {

        editor.replaceSelection("");

    }

    private void selectAll(final ActionEvent event) {

        editor.selectAll();

    }

}
