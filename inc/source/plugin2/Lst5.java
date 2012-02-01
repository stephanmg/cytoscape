            Matrix M = new Matrix(A);
            Matrix I = Matrix.identity(N, N);
            Matrix IVec = new Matrix(N, 1, 1.0);
        
            double[] eigs = M.eig().getRealEigenvalues();
            double alpha;
            Arrays.sort(eigs);

            if (eigs[eigs.length - 1] == 0) alpha = 0.1; // emulates degree centrality
            else alpha = 1.0 / Math.abs(eigs[eigs.length - 1]);

            double[][] values = ((I.minus(M.transpose().times(alpha))).inverse()).minus(I).times(IVec).getArrayCopy();
            StringBuilder sb = new StringBuilder();
            DecimalFormat df = new DecimalFormat("#0.000");

            for (int i = 1; i < N; i++) {
                if (i % 10 == 0) sb.append("\n");
                sb.append("C(").append(i).append("): ").append(df.format(values[i][0])).append("   ");
            }

            JOptionPane.showMessageDialog(view.getComponent(), "All done.\n" + sb);
            view.redrawGraph(false, true);
        }
    }
}
