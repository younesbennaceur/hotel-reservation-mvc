package src.View.outil;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.Action;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    private static final long serialVersionUID = 1L;

    private JButton renderButton;
    private JButton editButton;
    private String text;
    private TableModel tableModel;
    private Action action;

    /**
     * 
     *
     * @param table   
     * @param action  
     * @param column  
     */
    public ButtonColumn(JTable table, Action action, int column) {
        this.action = action;
        this.text = ""; 

        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);

        table.getColumnModel().getColumn(column).setCellRenderer(this);
        table.getColumnModel().getColumn(column).setCellEditor(this);
        tableModel = table.getModel();
    }

    /**
     * 
     *
     * @param text 
     * @return 
     */
    public ButtonColumn setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Handle button click event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int row = editButton.getParent() instanceof JTable ? 
                  ((JTable) editButton.getParent()).getSelectedRow() : -1;

        if (row < 0 || row >= tableModel.getRowCount()) {
            row = Integer.parseInt(editButton.getText());
        }

        
        action.actionPerformed(new ActionEvent(tableModel, ActionEvent.ACTION_PERFORMED, String.valueOf(row)));
        fireEditingStopped(); 
    }

    /**
     * Returns the component used for rendering the cell.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        renderButton.setText(text);
        return renderButton;
    }

    /**
     * Returns the component used for editing the cell.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        editButton.setText(text);
        return editButton;
    }

    /**
     * Returns the edited value.
     */
    @Override
    public Object getCellEditorValue() {
        return text;
    }
}