        private void run() {
            CyNetwork network = Cytoscape.getCurrentNetwork();
            CyNetworkView view = Cytoscape.getCurrentNetworkView();
            int N = network.getNodeCount();

            if (N == 0) {
                JOptionPane.showMessageDialog(view.getComponent(), "No network/view loaded.");
                return;
            }

            Double IRandic = 0.0;
            for (CyNode node : (List<CyNode>) network.nodesList())
                // otherwise Randic's Index will be +infinity!
                if (network.getDegree(node) != 0)
                  IRandic += Math.pow(network.getDegree(node), -0.5);

            JOptionPane.showMessageDialog(view.getComponent(), "All done.\nRandic Index " + IRandic);
            view.redrawGraph(false, true);
        }
    }
}
