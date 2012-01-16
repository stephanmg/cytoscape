 /**
 * @author stephanmg
 * @date 01m13d12y
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.plugin;

import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import giny.model.Node;
import giny.view.NodeView;

import cytoscape.plugin.CytoscapePlugin;
import cytoscape.util.CytoscapeAction;
import cytoscape.Cytoscape;
import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.CyEdge;
import cytoscape.view.CyNetworkView;
import cytoscape.data.Semantics;

import Jama.Matrix;
import java.text.DecimalFormat;

/**
 * This is a sample Cytoscape plugin using core graph and data structures.
 * For each currently selected node in the graph view, the action method
 * of this plugin additionally selects the neighbors of that node.
 *
 * Note that selection is a property of the view of the graph, while neighbors
 * are a property of the graph itself. Thus this plugin must access both the
 * graph and its view.
 */
public class PluginEx extends CytoscapePlugin {

    /**
     * This constructor creates an action and adds it to the Plugins menu.
     */
    public PluginEx() {
        //create a new action to respond to menu activation
        PluginEx.MolbiPluginAction action = new PluginEx.MolbiPluginAction();
        //set the preferred menu
        action.setPreferredMenu("Plugins.Molbi");
        //and add it to the menus
        Cytoscape.getDesktop().getCyMenus().addAction(action);
    }

    /**
     * Gives a description of this plugin.
     */
    public String describe() {
        String sb = "no function yet";
        return sb;
    }

    /**
     * This class gets attached to the menu item.
     */
    public class MolbiPluginAction extends CytoscapeAction {

        /**
         * The constructor sets the text that should appear on the menu item.
         */
        public MolbiPluginAction() {
            super("PluginKatz (alpha = 0.1)");
        }

        /**
         * This method is called when the user selects the menu item.
         */
        public void actionPerformed(ActionEvent ae) {
            run();


        }

        private void run() {
            //get the network object; this contains the graph
            CyNetwork network = Cytoscape.getCurrentNetwork();
            //get the network view object
            CyNetworkView view = Cytoscape.getCurrentNetworkView();
          
            int N = network.getNodeCount()+1;
            if (N == 0) {
                 JOptionPane.showMessageDialog(view.getComponent(), "No network/view loaded.");
                 return;
            }
         
            double[][] A = new double[N][N];
            for (double[] row : A)
                Arrays.fill(row, 0.0);
          //JOptionPane.showMessageDialog(view.getComponent(), "Vor  FOR");
            for (CyEdge edge : (List<CyEdge>) network.edgesList()) {
               
               int i = Math.abs(edge.getSource().getRootGraphIndex());
                int j = Math.abs(edge.getTarget().getRootGraphIndex());
                //JOptionPane.showMessageDialog(view.getComponent(), "IN  FOR: "+ i +", "+j+"N: "+N);
               
                        
               A[i][j] = 1;

            }
//JOptionPane.showMessageDialog(view.getComponent(), "Nach FOR");
            Matrix M = new Matrix(A);
            Matrix I = Matrix.identity(N, N);
            Matrix IVec = new Matrix(N,1,1.0);

            double alpha = 0.1; // emulates degree centrality

            Matrix res = ((I.minus(M.transpose().times(alpha))).inverse()).minus(I).times(IVec);

            double[][] res2 = res.getArrayCopy();
            
             StringBuilder sb = new StringBuilder();
             DecimalFormat f= new DecimalFormat("#0.000");
            for (int i = 1; i < N; i++){
                if(i % 10 == 0){
                    sb.append("\n");
                }
                sb.append("C(").append(i).append("): ").append(f.format(res2[i][0])).append("   ");
            }
            


            JOptionPane.showMessageDialog(view.getComponent(), "All done.\n" + sb);
            //tell the view to redraw since we've changed the selection
            view.redrawGraph(false, true);
        }
    }
}