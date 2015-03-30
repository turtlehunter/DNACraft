package dnacraft.common.evolution.dna;

import dnacraft.common.evolution.DNA;
import dnacraft.common.evolution.Genome;
import dnacraft.common.evolution.Trait;

public class DNAOcelot extends DNA {
	
	public DNAOcelot() {
		
		addGenome(Genome.createGenomeOfType(Genome.BODY_TYPE, Trait.ANIMAL_OCELOT));
		addGenome(Genome.createGenomeOfType(Genome.HEAD_TYPE, Trait.ANIMAL_OCELOT));
		addGenome(Genome.createGenomeOfType(Genome.WING_TYPE, Trait.ANIMAL_OCELOT));
		addGenome(Genome.createGenomeOfType(Genome.ARM_TYPE, Trait.ANIMAL_OCELOT));
		addGenome(Genome.createGenomeOfType(Genome.LEG_TYPE, Trait.ANIMAL_OCELOT));
		addGenome(Genome.createGenomeOfType(Genome.TAIL_TYPE, Trait.ANIMAL_OCELOT));
		
		addGenome(Genome.createGenomeOfType(Genome.AGGRESSION, 2));
		addGenome(Genome.createGenomeOfType(Genome.TERRITORIALILTY, 2));
		addGenome(Genome.createGenomeOfType(Genome.COLOR, Trait.COLOR_YELLOW));
		
		addGenome(Genome.createGenomeOfType(Genome.DAMAGE, 2));
		addGenome(Genome.createGenomeOfType(Genome.HEALTH, 10));
		
		addGenome(Genome.createGenomeOfType(Genome.DROP_AMOUNT, 0));
		addGenome(Genome.createGenomeOfType(Genome.DROP_TYPE, Trait.DROP_NOTHING));
		
		addGenome(Genome.createGenomeOfType(Genome.OFFSPRING, 1));
	}
}
