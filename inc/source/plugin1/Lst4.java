            Matrix M = new Matrix(A);
            Matrix I = Matrix.identity(N, N);
            Matrix IVec = new Matrix(N, 1, 1.0);

            double alpha = 0.1; // emulates degree centrality
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
