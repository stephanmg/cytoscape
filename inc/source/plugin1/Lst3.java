        private void run() {

            CyNetwork network = Cytoscape.getCurrentNetwork();
            CyNetworkView view = Cytoscape.getCurrentNetworkView();
            int N = network.getNodeCount() + 1;

            if (N == 1) {
                JOptionPane.showMessageDialog(view.getComponent(), "No network/view loaded.");
                return;
            }

            double[][] A = new double[N][N];
            for (double[] row : A) Arrays.fill(row, 0.0);

            for (CyEdge edge : (List<CyEdge>) network.edgesList()) {
                int i = Math.abs(edge.getSource().getRootGraphIndex());
                int j = Math.abs(edge.getTarget().getRootGraphIndex());
                A[i][j] = 1;
            }
