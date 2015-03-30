package dnacraft.common.evolution;

public class RangeGene extends Gene {

	public RangeGene(int trait, boolean active) {
		super(trait, active);
	}
	
	public RangeGene(Gene mother, Gene father) {
		super(mother, father);
	}

}
