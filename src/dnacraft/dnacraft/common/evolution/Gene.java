package dnacraft.common.evolution;

import java.util.Random;

public class Gene {
	
	private boolean active;
	private int trait;
	
	public static Random rnd = new Random();
	
	public Gene(int trait, boolean active) {
		this.trait = trait;
		this.active = active;
	}
	

	public Gene(Gene mother, Gene father) {
		this.active = rnd.nextBoolean() ? mother.isActive() : father.isActive();
		this.trait = rnd.nextBoolean() ? mother.getTrait() : father.getTrait();
	}
	
	public boolean isActive() {
		return active;
	}
	
	public int getTrait() {
		return trait;
	}
	
	public void setTrait(int trait) {
		this.trait = trait;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Gene clone() {
		return new Gene(this.trait, this.active);
	}
	
	public boolean matches(Gene gene) {
		return getTrait() == gene.getTrait();
	}
}
