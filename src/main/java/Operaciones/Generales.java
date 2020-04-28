/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Videla Araya
 */
public class Generales {
    
     public void limpiarFormulario(Component[] componentes) {
 
    for (int i = 0; i < componentes.length; i++) {
        if (componentes[i] instanceof JPanel) {
            limpiarFormulario(((JPanel) componentes[i]).getComponents());
        } else if (componentes[i] instanceof JViewport) {
            limpiarFormulario(((JViewport) componentes[i]).getComponents());
        } else if (componentes[i] instanceof JScrollPane) {
            limpiarFormulario(((JScrollPane) componentes[i]).getComponents());
        } else if (componentes[i] instanceof JTextField) {
            ((JTextField) componentes[i]).setText("");
        } else if (componentes[i] instanceof JTextArea) {
            ((JTextArea) componentes[i]).setText("");
        } else if (componentes[i] instanceof JPasswordField) {
            ((JPasswordField) componentes[i]).setText("");
        } else if (componentes[i] instanceof JCheckBox) {
            ((JCheckBox) componentes[i]).setSelected(false);
        } else if (componentes[i] instanceof JRadioButton) {
            ((JRadioButton) componentes[i]).setSelected(false);
        } else if (componentes[i] instanceof JToggleButton) {
            ((JToggleButton) componentes[i]).setSelected(false);
        } else if (componentes[i] instanceof JComboBox) {
            ((JComboBox) componentes[i]).setSelectedIndex(0);
        } else if (componentes[i] instanceof JSlider) {
            ((JSlider) componentes[i]).setValue(((JSlider) componentes[i]).getMinimum());
        } else if (componentes[i] instanceof JSpinner) {
            ((JSpinner) componentes[i]).setValue(0);
        } else if (componentes[i] instanceof JFormattedTextField) {
            ((JFormattedTextField) componentes[i]).setText("");
        } else if (componentes[i] instanceof JTable) {
            ((DefaultTableModel) (((JTable) componentes[i]).getModel())).setRowCount(0);
        }
}
    }
    
}
