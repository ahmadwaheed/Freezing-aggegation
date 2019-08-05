ƒpackage freezingAggregation;

import states.SimStateSparseGrid2D;

public class Environment extends SimStateSparseGrid2D {

	public int gridWidth = 50;
	public int gridHeight = 50;
	public int n = 50; // number of agents
	public double p = 0.1; //probability of moving randomly
	public boolean locationEmpty = true;
	public double pa = 0.9; // probability of aggregating
	public boolean bounded = false;
	
	public int getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public void setGridHeight(int gridHeight) {
		this.gridHeight = gridHeight;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public boolean isLocationEmpty() {
		return locationEmpty;
	}

	public void setLocationEmpty(boolean locationEmpty) {
		this.locationEmpty = locationEmpty;
	}

	public double getPa() {
		return pa;
	}

	public void setPa(double pa) {
		this.pa = pa;
	}
	
	public boolean isBounded() {
		return bounded;
	}

	public void setBounded(boolean bounded) {
		this.bounded = bounded;
	}

	public Environment(long seed) {
		super(seed);
	}
	
	public void makeAgents() {
		int midx = gridWidth / 2;  //to be in the middle from x coordinate
		int midy = gridHeight / 2; //to be in the middle from y coordinate
		Agent f = new Agent(midx, midy, 0, 0, true);  //placing single agent
		schedule.scheduleRepeating(f);  //scheduling agent
		space.setObjectLocation(f, midx, midy);  //setting location
		for(int i=0; i<n-1; i++) {  //For rest of (N - 1) agents
			int x = random.nextInt(gridWidth); 
			int y = random.nextInt(gridHeight);
			int xdir = random.nextInt(3) - 1;
			int ydir = random.nextInt(3) - 1;
			boolean frozen = false;
			Agent a = new Agent(x, y, xdir, ydir, frozen);
			schedule.scheduleRepeating(a);
			space.setObjectLocation(a, x, y);
		}
	}
	
	public void start() {
		super.start();
		this.makeSpace(gridWidth, gridHeight);
		makeAgents();
	}

}
