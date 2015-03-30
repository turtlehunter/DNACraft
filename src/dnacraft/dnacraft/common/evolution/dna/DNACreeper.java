package dnacraft.common.evolution.dna;

import dnacraft.common.evolution.DNA;
import dnacraft.common.evolution.Genome;
import dnacraft.common.evolution.Trait;

public class DNACreeper extends DNA {
	
	public DNACreeper() {
		
		addGenome(Genome.createGenomeOfType(Genome.BODY_TYPE, Trait.MONSTER_CREEPER));
		addGenome(Genome.createGenomeOfType(Genome.HEAD_TYPE, Trait.MONSTER_CREEPER));
		addGenome(Genome.createGenomeOfType(Genome.WING_TYPE, Trait.MONSTER_CREEPER));
		addGenome(Genome.createGenomeOfType(Genome.ARM_TYPE, Trait.MONSTER_CREEPER));
		addGenome(Genome.createGenomeOfType(Genome.LEG_TYPE, Trait.MONSTER_CREEPER));
		addGenome(Genome.createGenomeOfType(Genome.TAIL_TYPE, Trait.MONSTER_CREEPER));
		
		addGenome(Genome.createGenomeOfType(Genome.AGGRESSION, 7));
		addGenome(Genome.createGenomeOfType(Genome.TERRITORIALILTY, 7));
		addGenome(Genome.createGenomeOfType(Genome.COLOR, Trait.COLOR_GREEN));
		
		addGenome(Genome.createGenomeOfType(Genome.DAMAGE, 2));
		addGenome(Genome.createGenomeOfType(Genome.HEALTH, 20));
		
		addGenome(Genome.createGenomeOfType(Genome.DROP_AMOUNT, 1, 2));
		addGenome(Genome.createGenomeOfType(Genome.DROP_TYPE, Trait.DROP_GUNPOWDER));

		
		addGenome(Genome.createGenomeOfType(Genome.OFFSPRING, 1));
	}
}
