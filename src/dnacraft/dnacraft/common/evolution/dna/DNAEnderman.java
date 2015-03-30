package dnacraft.common.evolution.dna;

import dnacraft.common.evolution.DNA;
import dnacraft.common.evolution.Genome;
import dnacraft.common.evolution.Trait;

public class DNAEnderman extends DNA {
	
	public DNAEnderman() {
		
		addGenome(Genome.createGenomeOfType(Genome.BODY_TYPE, Trait.MONSTER_ENDERMAN));
		addGenome(Genome.createGenomeOfType(Genome.HEAD_TYPE, Trait.MONSTER_ENDERMAN));
		addGenome(Genome.createGenomeOfType(Genome.WING_TYPE, Trait.MONSTER_ENDERMAN));
		addGenome(Genome.createGenomeOfType(Genome.ARM_TYPE, Trait.MONSTER_ENDERMAN));
		addGenome(Genome.createGenomeOfType(Genome.LEG_TYPE, Trait.MONSTER_ENDERMAN));
		addGenome(Genome.createGenomeOfType(Genome.TAIL_TYPE, Trait.MONSTER_ENDERMAN));
		
		addGenome(Genome.createGenomeOfType(Genome.AGGRESSION, 6));
		addGenome(Genome.createGenomeOfType(Genome.TERRITORIALILTY, 6));
		addGenome(Genome.createGenomeOfType(Genome.COLOR, Trait.COLOR_BLACK));
		
		addGenome(Genome.createGenomeOfType(Genome.DAMAGE, 4));
		addGenome(Genome.createGenomeOfType(Genome.HEALTH, 40));
		
		addGenome(Genome.createGenomeOfType(Genome.DROP_AMOUNT, 1));
		addGenome(Genome.createGenomeOfType(Genome.DROP_TYPE, Trait.DROP_ENDERPEARL));
		
		
		addGenome(Genome.createGenomeOfType(Genome.OFFSPRING, 1));
	}
}
