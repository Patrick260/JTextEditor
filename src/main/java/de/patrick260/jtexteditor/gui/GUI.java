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

public final class GUI extends JFrame {

    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 600;

    private static final String TITLE = "JTextEditor";


    public GUI() {

        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        final JMenuBar menuBar = new JMenuBar();

        final JMenu fileMenu = new JMenu("File");

        final JMenuItem menuItem_new = new JMenuItem("New");
        final JMenuItem menuItem_open = new JMenuItem("Open");
        final JMenuItem menuItem_save = new JMenuItem("Save");
        final JMenuItem menuItem_saveAs = new JMenuItem("Save as");

        fileMenu.add(menuItem_new);
        fileMenu.add(menuItem_open);
        fileMenu.add(menuItem_save);
        fileMenu.add(menuItem_saveAs);

        final JMenu editMenu = new JMenu("Edit");

        final JMenuItem menuItem_undo = new JMenuItem("Undo");
        final JMenuItem menuItem_redo = new JMenuItem("Redo");
        final JMenuItem menuItem_cut = new JMenuItem("Cut");
        final JMenuItem menuItem_copy = new JMenuItem("Copy");
        final JMenuItem menuItem_paste = new JMenuItem("Paste");
        final JMenuItem menuItem_delete = new JMenuItem("Delete");
        final JMenuItem menuItem_selectAll = new JMenuItem("Select All");

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

        pack();

        setLocationRelativeTo(null);

    }

}