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
/**
 * The ButtonColumn class provides a button in a specified column of a JTable.
 * When clicked, it triggers a custom action.
 */
public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    private static final long serialVersionUID = 1L;

    private JButton renderButton;
    private JButton editButton;
    private String text;
    private TableModel tableModel;
    private Action action;

    /**
     * Create the ButtonColumn with a specific action and column index.
     *
     * @param table   The JTable containing the column
     * @param action  The action to perform when the button is clicked
     * @param column  The column index where the button should appear
     */
    public ButtonColumn(JTable table, Action action, int column) {
        this.action = action;
        this.text = ""; // Default empty text

        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);

        table.getColumnModel().getColumn(column).setCellRenderer(this);
        table.getColumnModel().getColumn(column).setCellEditor(this);
        tableModel = table.getModel();
    }

    /**
     * Set the text shown on the buttons in this column.
     *
     * @param text Text to display on buttons
     * @return this instance for chaining
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

        // Invoke the action with the row number as the command string
        action.actionPerformed(new ActionEvent(tableModel, ActionEvent.ACTION_PERFORMED, String.valueOf(row)));
        fireEditingStopped(); // Stops cell editing
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