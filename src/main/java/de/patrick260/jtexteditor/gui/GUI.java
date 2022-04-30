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

        setJMenuBar(buildMenuBar());

        editor = new JEditorPane();

        add(buildScrollPane());

        pack();

        setLocationRelativeTo(null);

    }


    private JMenuBar buildMenuBar() {

        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(buildFileMenu());
        menuBar.add(buildEditMenu());

        return menuBar;

    }

    private JMenu buildFileMenu() {

        final JMenu fileMenu = new JMenu("File");

        final JMenuItem newFile = new JMenuItem("New");
        newFile.addActionListener(this::newFile);

        final JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this::openFile);

        final JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this::saveFile);

        final JMenuItem saveAs = new JMenuItem("Save as");
        saveAs.addActionListener(this::saveFileAs);

        final JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(this::quit);

        fileMenu.add(newFile);
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.addSeparator();
        fileMenu.add(quit);

        return fileMenu;

    }

    private JMenu buildEditMenu() {

        final JMenu editMenu = new JMenu("Edit");

        final JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(this::undo);

        final JMenuItem redo = new JMenuItem("Redo");
        redo.addActionListener(this::redo);

        final JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this::cut);

        final JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this::copy);

        final JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this::paste);

        final JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(this::delete);

        final JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.addActionListener(this::selectAll);

        editMenu.add(undo);
        editMenu.add(redo);
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);
        editMenu.add(delete);
        editMenu.add(selectAll);

        return editMenu;

    }

    private JScrollPane buildScrollPane() {

        final JScrollPane scrollPane = new JScrollPane(editor);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return scrollPane;

    }


    private void newFile(final ActionEvent event) {

        editor.setText(null);

        saveTo = null;

    }

    private void openFile(final ActionEvent event) {

        final JFileChooser fileChooser = new JFileChooser();
        final int action = fileChooser.showOpenDialog(this);

        if (action == JFileChooser.APPROVE_OPTION) {

            try {

                editor.setPage(fileChooser.getSelectedFile().toURI().toURL());

                saveTo = fileChooser.getSelectedFile();

            } catch (final IOException exception) {

                exception.printStackTrace();

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

            } catch (final IOException exception) {

                exception.printStackTrace();

            }

        } else {

            saveFileAs(event);

        }

    }

    private void saveFileAs(final ActionEvent event) {

        final JFileChooser fileChooser = new JFileChooser();
        final int action = fileChooser.showSaveDialog(this);

        if (action == JFileChooser.APPROVE_OPTION) {

            saveTo = fileChooser.getSelectedFile();

            saveFile(event);

        }

    }

    private void quit(final ActionEvent event) {

        System.exit(0);

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

        } catch (final UnsupportedFlavorException | IOException exception) {

            exception.printStackTrace();

        }

    }

    private void delete(final ActionEvent event) {

        editor.replaceSelection("");

    }

    private void selectAll(final ActionEvent event) {

        editor.selectAll();

    }

}
