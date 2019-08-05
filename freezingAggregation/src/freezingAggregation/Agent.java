package freezingAggregation;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Bag;

public class Agent implements Steppable {
	int x; // x coordinate
	int y; // y coordinate
	int xdir; 
	int ydir;
	boolean frozen;

	public Agent(int x, int y, int xdir, int ydir, boolean frozen) {
		super();
		this.x = x;
		this.y = y;
		this.xdir = xdir;
		this.ydir = ydir;
		this.frozen = frozen;
	}

	public int boundX(Environment state, int x) {
		if(x<0) {
			xdir = -xdir;
			return 0;
		} else if(x>state.gridWidth - 1) {
			xdir = -xdir;
			return state.gridWidth - 1;
		} else {
			return x;
		}
	}
	
	public int boundY(Environment state, int y) {
		if(y<0) {
			ydir = -ydir;
			return 0;
		} else if(y>state.gridHeight - 1) {
			ydir = -ydir;
			return state.gridHeight - 1;
		} else {
			return y;
		}
	}
	
	public void moveTor(Environment state) {
		if(state.random.nextBoolean(state.p) && !(this.frozen)) {
			xdir = state.random.nextInt(3) - 1;
			ydir = state.random.nextInt(3) - 1;
		}
		placeAgent(state);
	}
	
	public void moveBond(Environment state) {
		if(state.random.nextBoolean(state.p) && !(this.frozen)) {
			xdir = state.random.nextInt(3) - 1;
			ydir = state.random.nextInt(3) - 1;
		}
		placeAgent(state);
	}
	
	public void placeAgent(Environment state) {
		if(state.locationEmpty) {
			int tempx = 0;
			int tempy = 0;
			if(state.bounded) {
				tempx = boundX(state,x+xdir);
				tempy = boundY(state,y+ydir);
			}
			else if(state.bounded == false) {
				tempx = state.space.stx(x+xdir);
				tempy = state.space.sty(y+ydir);
			} 
			Bag location = state.space.getObjectsAtLocation(tempx, tempy);
			if(location == null) {
				x = tempx;
				y = tempy;
				state.space.setObjectLocation(this, x, y);
			}
			else if (location != null)  {
				for(int i = 0; i < location.numObjs; i++) {
					Object o = location.objs[i];
					Agent a = (Agent)o;
					if(a.frozen) {
						this.frozen = true;
						state.space.setObjectLocation(this, x, y);
					}
				}
			}
		} else {
			int tempx = state.space.stx(x+xdir);
			int tempy = state.space.sty(y+ydir);
			Bag location = state.space.getObjectsAtLocation(tempx, tempy);
			if((location != null)) {
				for(int i = 0; i < location.numObjs; i++) {
					Object o = location.objs[i];
					Agent a = (Agent)o;
					if(a.frozen) {
						this.frozen = true;
						state.space.setObjectLocation(this, x, y);
					}
				}
			}	
			state.space.setObjectLocation(this, tempx, tempy);
		}
	}
	
	@Override
	public void step(SimState state) {
		if(this.frozen) {
			return;
		}
		if(((Environment)state).bounded) {
			moveBond((Environment)state);
		} else {
			moveTor((Environment)state);
		}
	}

}
