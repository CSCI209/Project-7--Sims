import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * @author Shepherd Sims, Andrew Bacca, Trip Calihan, Peter Blair
 * File: AbstractCellPanel.java
 * 
 * Implements the Cell Panel class and defines responsibilities.
 * @param a cell. (All cell panels have a cell parameter.)
 * 
 */

public class AbstractCellPanel extends JPanel{
	protected Cell cell;
	private ViewInformer VI;
	
	/**
	 * Constructor for AbstractCellPanel.
	 * @param a cell.
	 */
	
	public AbstractCellPanel(Cell c, ViewInformer vi) {
		cell = c;
		VI = vi;
		addMouseListener(new AbstractCellPanel.PanelListener(this));
	}
	
	/**
	 * Method for paint the cell in the GUI. 
	 * All CellPanels will display the same background when their cells are empty.
	 * @param Graphics.
	 */
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		}
	
	/**
	 * Returns cell contained by cell panel.
	 * @return the cell of the panel.
	 */
	
	public Cell getCell() {
		return cell;
	}
	
	/**
	 * Creates the Panel Listener Class and defines responsibilities.
	 */
	
	private class PanelListener extends java.awt.event.MouseAdapter {
	    private AbstractCellPanel panel;
	    
		/**
		 * Constructor for PanelListerner.
		 * @param acp - AbstractCellPanel
		 */
	    
	    private PanelListener(AbstractCellPanel acp) {
	      panel = acp;
	    }
	    
		/**
		 * method for a panel being clicked in the gui  
		 * @param e - MouseEvent
		 */	 
	    
	    public void mousePressed(MouseEvent e) {
	      VI.panelPressed(panel);
	    }
	}
}
