package freezingAggregation;

import java.awt.Color;

import states.GUIStateSparseGrid2D;
import states.SimStateSparseGrid2D;

public class AgentGUI extends GUIStateSparseGrid2D {

	public AgentGUI(SimStateSparseGrid2D state, int gridWidth, int gridHeight, Color backdrop, Color agentDefaultColor,
			boolean agentPortrayal) {
		super(state, gridWidth, gridHeight, backdrop, agentDefaultColor, agentPortrayal);
	}

	public static void main(String[] args) {
		AgentGUI.initialize(Environment.class, AgentGUI.class, 400, 400, Color.WHITE, Color.ORANGE, true);
		
	}
}
