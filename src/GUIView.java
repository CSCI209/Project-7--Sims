import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * @author lambertk, Shepard Sims, Andrew Baca, Trip Calihan, Peter Blair
 *
 * The main view for the game of Free Cell. 
 * 
 * It displays 16 panels in two groups of 8 panels, a bottom and a top group.
 * It also displays a button, labeled "New Game,"  at the bottom, which resets
 * the game when pressed.
 * 
 * The panels in the top correspond to the free cells and home cells, 4 each. 
 * They will display only the top card or the wire frame, if empty. The bottom
 * panels represent the 8 tableaux and will display all cards in their respective
 * stacks, or their wire frame if empty.
 * 
 * Move cards with mouse clicks from tableaux to other cells to play.
 * 
 * (Equivalent to AppView.)
 */

public class GUIView extends JFrame{
	private static Color DefaultColor = new Color(20, 200, 20);
	private ArrayList<CellPanel> TopCellPanels;
	private ArrayList<CellPanel> TableauxPanels;
	private FreeCellModel model;
	private CellPanel clickedPanel;
	
	public GUIView(FreeCellModel game) {
		model = game;
		setBackground(DefaultColor);
		clickedPanel = null;
		this.setTitle("Free Cell");
		JButton button = new JButton("New Game");
// Creating Panels (Top row  of panels (Free cell panels (4) and then Home 
// cell panels (4)) and then the bottom row (Tableaux panels (8)))
		
		TopCellPanels = new ArrayList<CellPanel>();
		TableauxPanels = new ArrayList<CellPanel>();
		
		for (int i=0; i<4; i++) {
			TopCellPanels.add(new CellPanel(game.getFreeCell(i)));
		}
		
		for (int i=0; i<4; i++) {
			TopCellPanels.add(new CellPanel(game.getHomeCell(i)));
		}
		
		for (int i=0; i<8; i++) {
			TableauxPanels.add(new CellPanel(game.getTableau(i)));
		}
		
// Add CellListener for each panel
		
		for (int i=0; i<8; i++) {
			addMouseListener(new CellListener(TopCellPanels.get(i)));
		}
		
		for (int i=0; i<8; i++) {
			addMouseListener(new CellListener(TableauxPanels.get(i)));
		}
		
// Add Button Action
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent l) {
    			game.reset();
    			for (i=0; i<8; i++) {
    				panel1.setCard(null);
        			panel2.setCard(null);
    			}
    		}
    	
    });
	}
	
}