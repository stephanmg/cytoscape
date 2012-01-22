public class KatzPlugin extends CytoscapePlugin {

    public KatzPlugin() {
        KatzPlugin.MolbiPluginAction action = new KatzPlugin.MolbiPluginAction();
        action.setPreferredMenu("Plugins.Molbi");
        Cytoscape.getDesktop().getCyMenus().addAction(action);
    }

    public String describe() {
        return "Plugin to calculate the topological Katz Index for networks";
    }

    public class MolbiPluginAction extends CytoscapeAction {

        public MolbiPluginAction() {
            super("Katz's Index (alpha = 0.1)");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            run();
        }
