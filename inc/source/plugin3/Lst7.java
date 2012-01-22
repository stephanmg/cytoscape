public class RandicPlugin extends CytoscapePlugin {

    public RandicPlugin() {
        RandicPlugin.MolbiPluginAction action = new RandicPlugin.MolbiPluginAction();
        action.setPreferredMenu("Plugins.Molbi");
        Cytoscape.getDesktop().getCyMenus().addAction(action);
    }

    public String describe() {
        return  "Plugin to calculate the Index of Randic for networks";
    }

    public class MolbiPluginAction extends CytoscapeAction {

        public MolbiPluginAction() {
            super("Randic's Index");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            run();
        }
