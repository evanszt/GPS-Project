import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
/**
 * This class builds a GUI for the project that displays a graph,
 * creates two separate JRadioButton groups that allow the user to input either
 * to use distance/time cost and to return the symbol or address of the directions
 * Additionally the GUI outputs those directions for the user in a readable format.
 * @author zacha
 *
 */
public class GUIWindow extends JPanel {
JFrame window = new JFrame("GPS Application");


public GUIWindow() {
	window.setBounds(10, 10, 700, 700);
	window.setAlwaysOnTop(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setResizable(false);
	JPanel mainPanel = new JPanel();
	mainPanel.setLayout(new BorderLayout());
	JPanel p1 = new JPanel();
	JPanel p3 = new JPanel();
	JTextArea outputText = new JTextArea(10,10);
	outputText.setEditable(false);
	Graph g = new Graph("MapInformationXY.txt");
	ButtonGroup g1 = new ButtonGroup();
	JRadioButton distCostBut = new JRadioButton("Use Distance Cost");
	JRadioButton timeCostBut = new JRadioButton("Use Time Cost");
	distCostBut.setActionCommand("UseDist");
	timeCostBut.setActionCommand("UseTime");
	g1.add(timeCostBut);
	g1.add(distCostBut);
	ButtonGroup g2 = new ButtonGroup();
	JRadioButton useSymbol = new JRadioButton("Return Symbols");
	JRadioButton useAdd = new JRadioButton("Return Address");
	g2.add(useSymbol);
	g2.add(useAdd);
	useSymbol.setActionCommand("useSymbol");
	useAdd.setActionCommand("useAddress");
	p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
	JTextField buttonTitles = new JTextField("Input Options");
	buttonTitles.setEditable(false);
	buttonTitles.setMaximumSize(new Dimension(243, 20));
	p1.add(buttonTitles);
	p1.add(distCostBut, BorderLayout.NORTH);
	p1.add(timeCostBut, BorderLayout.SOUTH);
	p1.add(useAdd, BorderLayout.LINE_END);
	p1.add(useSymbol, BorderLayout.LINE_END);
	int j = 0;
	String[] symList = new String[26];
	for (int i = 65; i < 85; i++) {
		char c = (char) i;
		String s = Character.toString(c);
		symList[j] = s;
		j++;
	}
	JScrollPane starter = new JScrollPane();
	JScrollPane ender = new JScrollPane();
	JList startList = new JList(symList);
	JList endList = new JList(symList);
	startList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	endList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	startList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
	endList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
	startList.setVisibleRowCount(4);
	endList.setVisibleRowCount(4);
	starter.setViewportView(startList);
	starter.setMaximumSize(new Dimension(180,79));
	ender.setViewportView(endList);
	ender.setMaximumSize(new Dimension(180,79));
	JTextField titleStart = new JTextField("Select Starting Location");
	titleStart.setEditable(false);
	titleStart.setMaximumSize(new Dimension(243,20));
	JTextField titleEnd = new JTextField("Select Ending Location");
	titleEnd.setMaximumSize(new Dimension(243,20));
	titleEnd.setEditable(false);
	p1.add(titleStart);
	p1.add(starter);
	p1.add(titleEnd);
	p1.add(ender);
	JButton btn = new JButton("Submit");
	p1.add(btn, BorderLayout.EAST);
	btn.setActionCommand("Enter");
	Vertex key;
	btn.addActionListener(new ActionListener() {
		String symbStart;
		String symbEnd;
		public void actionPerformed(ActionEvent e) {
			Path finalPath;
			String p;
			char c = 0;
			if (e.getActionCommand().equals("Enter")) 
				outputText.setText("Directions: \n");
				symbStart = (String) startList.getSelectedValue();
				symbEnd = (String) endList.getSelectedValue();
				Vertex start = g.getVertex(symbStart);
				Vertex end = g.getVertex(symbEnd);
				if (distCostBut.isSelected()) {
					g.setUseDistCost(true);
					if (useSymbol.isSelected()) {
						g.setReturnAddress(false);
						finalPath = Dijkstra.shortestPath(g, start, end, true);
						if (finalPath == null) {
							outputText.append("No valid path available!");
							return;
						}
						for (int i = 0; i < finalPath.pathStr.length(); i++) {
							c = finalPath.pathStr.charAt(i);
							p = Character.toString(c);
							
							outputText.append(g.getVertex(p).getSymbol());
							outputText.append("\n");
						}
					} else {
						g.setReturnAddress(true);
						finalPath = Dijkstra.shortestPath(g, start, end, true);
						if (finalPath == null) {
							outputText.append("No valid path available!");
							return;
						}
						for (int i = 0; i < finalPath.pathStr.length(); i++) {
							c = finalPath.pathStr.charAt(i);
							p = Character.toString(c);
							outputText.append(g.getVertex(p).getAddress());
							outputText.append("\n");
						}
					}
				} else {
					g.setUseDistCost(false);
					if (useSymbol.isSelected()) {
						g.setReturnAddress(false);
						finalPath = Dijkstra.shortestPath(g, start, end, false);
						if (finalPath == null) {
							outputText.append("No valid path available!");
							return;
						}
						for (int i = 0; i < finalPath.pathStr.length(); i++) {
							c = finalPath.pathStr.charAt(i);
							p = Character.toString(c);
							outputText.append(g.getVertex(p).getSymbol());
							outputText.append("\n");
						}
					} else {
						g.setReturnAddress(true);
						finalPath = Dijkstra.shortestPath(g, start, end, false);
						if (finalPath == null) {
							outputText.append("No valid path available!");
							return;
						}
						for (int i = 0; i < finalPath.pathStr.length(); i++) {
							c = finalPath.pathStr.charAt(i);
							p = Character.toString(c);
							outputText.append(g.getVertex(p).getAddress());
							outputText.append("\n");
						}
					}
				}
				outputText.append("Total Cost: " + finalPath.cost);
			}
	});
	JPanel p2 = new JPanel();
	p2.setPreferredSize(new Dimension(500,500));
	BufferedImage graphPic;
	try {
		graphPic = ImageIO.read(new File("FinalProjectGraph_Final_400x400.png"));
		JLabel picLabel = new JLabel(new ImageIcon(graphPic));
		p2.add(picLabel);
	} catch (IOException e) {
		e.printStackTrace();
	}
	p3.add(outputText);
	mainPanel.add(p1, BorderLayout.WEST);
	mainPanel.add(p2, BorderLayout.EAST);
	mainPanel.add(p3, BorderLayout.SOUTH);
	window.add(mainPanel);	
window.setVisible(true);
}
	


//======================================================

public static void main(String[] args) { 
	new GUIWindow(); 
	Graph g = new Graph("MapInformationXY.txt");
	}


//======================================================


}
