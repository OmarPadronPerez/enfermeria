package VISUAL;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellRenderConsultas extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.setOpaque(true);
        //System.out.println(table.getColumnCount());
        if (table.getColumnCount()>5) {
            if (table.getValueAt(row, 5).toString() =="si") {

                this.setBackground(Color.RED);
                this.setForeground(Color.WHITE);
            } else {
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        }
        return this;
    }
}
