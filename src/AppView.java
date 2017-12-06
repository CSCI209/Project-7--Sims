import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * @author lambertk, Shepard Sims, Andrew Baca, Trip Calihan, Peter Blair
 * File: AppView.java
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
 */

public class AppView extends javax.swing.JFrame
{
  public static final Color DefaultColor = new Color(75, 175, 100);
  private ArrayList<AbstractCellPanel> homeCellPanels;
  private ArrayList<AbstractCellPanel> freeCellPanels;
  private ArrayList<AbstractCellPanel> tableauPanels;
  private JButton button;
  private FreeCellGame game;
  private AbstractCellPanel selectedPanel;
  private ViewInformer VI;
  private int HighScore;
  private JTextArea counter = new JTextArea("Moves: " + 0);
  
  public AppView(FreeCellGame g)
  {
	  HighScore = 1000;
	  setTitle("Free Cell");
	  selectedPanel = null;
	  game = g;
	  VI = new AppView.AppViewInformer();
	  Container c = getContentPane();
	  GridBagLayout layout = new GridBagLayout();
	  c.setLayout(layout);
	  GridBagConstraints gbc = new GridBagConstraints();
	  c.setBackground(DefaultColor);
	  
	  JTextArea HighScoreLabel = new JTextArea();
	  HighScoreLabel.setText("Best: " + HighScore);
	  gbc.gridwidth = 4;
	  gbc.gridx = 2;
	  HighScoreLabel.setOpaque(true);
	  HighScoreLabel.setBackground(DefaultColor);
	  layout.setConstraints(HighScoreLabel, gbc);
	  c.add(HighScoreLabel);

	  JButton button = new javax.swing.JButton("Press here to Start a New Game!");
	  button.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			  button.setText("New Game");
			  counter.setText("Moves: 0");
			  game.reset();
			  HighScoreLabel.setText("Best Score: " + HighScore);
			  repaint();
		  }
	  });
	    gbc.fill = 0;
	    gbc.gridwidth = 8;
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    layout.setConstraints(button, gbc);
	    c.add(button);

		  button.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e){
				  game.reset();
				  HighScoreLabel.setText("Best Score: " + HighScore);
				  repaint();
			  }
		  });
		  
		  gbc.gridwidth = 2;
		  gbc.gridx = 4;
		  counter.setOpaque(true);
		  counter.setBackground(DefaultColor);
		  layout.setConstraints(counter, gbc);
		  c.add(counter);
		  
		  
		     
		  javax.swing.JLabel Label1 = new javax.swing.JLabel("Free Cells");
		  Label1.setForeground(DefaultColor);
		  gbc.gridwidth = 2;
		  gbc.weighty = .05;
		  gbc.weightx = .05;
		  gbc.gridx = 1;
		  gbc.gridy = 0;
		  Label1.setForeground(Color.black);
		  Label1.setOpaque(true);
		  Label1.setBackground(DefaultColor);
		  layout.setConstraints(Label1, gbc);
		  c.add(Label1);
		    
		  javax.swing.JLabel Label2 = new javax.swing.JLabel("Home Cells");
		  gbc.gridwidth = 2;
		  gbc.gridx = 5;
		  gbc.gridy = 0;
		  Label2.setOpaque(true);
		  Label2.setBackground(DefaultColor);
		  layout.setConstraints(Label2, gbc);
		  c.add(Label2);
		  
		  TopCellPanels(gbc, layout, c);
		  TableauPanels(gbc, layout, c);
  }

  
  /**
   * Fills ArrayList TopCellPanels with Cells from homeCellPanels and freeCellPanels, then 
   * sets constraints and adds them to the container to view
   * @param gbc - Grid Bag Constraints
   * @param layout - Grid Bag layout for viewing
   * @param c - container which holds all components to be viewed
   */
  private void TopCellPanels(GridBagConstraints gbc, GridBagLayout layout, Container c)
  {
	  homeCellPanels = new ArrayList<AbstractCellPanel>();
	  freeCellPanels = new ArrayList<AbstractCellPanel>();
	  gbc.fill = 1;
	  gbc.weightx = 1;
	  gbc.weighty = .35;
	  gbc.gridx = 0;
	  gbc.gridy = 1;
	  gbc.gridwidth = 1;
	  for (int i = 0; i < 4; i++) {
		  freeCellPanels.add(new TopCellPanel(game.getFreeCell(i), VI));
		  freeCellPanels.get(i).setBackground(DefaultColor);
		  layout.setConstraints(freeCellPanels.get(i), gbc);
		  gbc.gridx += 1;
		  c.add(freeCellPanels.get(i));
	  }
	  for (int i = 0; i < 4; i++) {
		  homeCellPanels.add(new TopCellPanel(game.getHomeCell(i), VI));
		  homeCellPanels.get(i).setBackground(DefaultColor);
		  layout.setConstraints(homeCellPanels.get(i), gbc);
		  gbc.gridx += 1;
		  c.add(homeCellPanels.get(i));
	  }
  }
  
  /**
   * Fills ArrayList TableauPanels with Cells from Tableaux, then 
   * sets constraints and adds them to the container to view
   * @param gbc - Grid Bag Constraints
   * @param layout - Grid Bag layout for viewing
   * @param c - container which holds all components to be viewed
   */
  private void TableauPanels(GridBagConstraints gbc, GridBagLayout layout, Container c)
  {
	  tableauPanels = new ArrayList<AbstractCellPanel>();
	  gbc.fill = 1;
	  gbc.weightx = 1;
	  gbc.weighty = 1;
	  gbc.gridx = 0;
	  gbc.gridy = 2;
	  for (int i = 0; i < 8; i++) {
		  tableauPanels.add(new TableauPanel(game.getTableau(i), VI));
		  tableauPanels.get(i).setBackground(DefaultColor);
		  layout.setConstraints(tableauPanels.get(i), gbc);
		  gbc.gridx += 1;
		  c.add(tableauPanels.get(i));
	  }
  }
  

  private class AppViewInformer implements ViewInformer {
	  private AppViewInformer() {}
	    
	  public void panelPressed(AbstractCellPanel panel) { 
	    	
		  if (game.cantMove()) {
			  javax.swing.JOptionPane.showMessageDialog(AppView.this, "No more moves available");
		  } 
		  else if (game.allDone()) {
	    		javax.swing.JOptionPane.showMessageDialog(AppView.this, "You win! \nScore: " + game.getCount());
		  }
		  else if (selectedPanel == null) {
			  selectedPanel = panel;
		  } 
		  else if (selectedPanel == panel) {
			  selectedPanel = null;
		  }
		  else if (game.moveCard(selectedPanel.getCell(), panel.getCell())) { 
			  selectedPanel.repaint();
			  panel.repaint();
			  counter.setText("Moves: " + game.getCount());
			  selectedPanel = null;
			  if (game.allDone()) {
				  javax.swing.JOptionPane.showMessageDialog(AppView.this, "You win! \nScore: " + game.getCount());
				  if (game.getCount() < HighScore) {
					  HighScore = game.getCount();
				  }
			  }
			  
		  } 
		  else {
			  selectedPanel = null;
			  javax.swing.JOptionPane.showMessageDialog(AppView.this, "Illegal move: Penalty +1");
			  counter.setText("Moves: " + game.getCount());
		  }
	  }
  }

}