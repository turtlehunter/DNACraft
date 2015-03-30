package dnacraft.common.evolution.dna;

import dnacraft.common.evolution.DNA;
import dnacraft.common.evolution.Genome;
import dnacraft.common.evolution.Trait;

public class DNASheep extends DNA {
	
	public DNASheep() {
		
		addGenome(Genome.createGenomeOfType(Genome.BODY_TYPE, Trait.ANIMAL_SHEEP));
		addGenome(Genome.createGenomeOfType(Genome.HEAD_TYPE, Trait.ANIMAL_SHEEP));
		addGenome(Genome.createGenomeOfType(Genome.WING_TYPE, Trait.ANIMAL_SHEEP));
		addGenome(Genome.createGenomeOfType(Genome.ARM_TYPE, Trait.ANIMAL_SHEEP));
		addGenome(Genome.createGenomeOfType(Genome.LEG_TYPE, Trait.ANIMAL_SHEEP));
		addGenome(Genome.createGenomeOfType(Genome.TAIL_TYPE, Trait.ANIMAL_SHEEP));
		
		addGenome(Genome.createGenomeOfType(Genome.AGGRESSION, 0));
		addGenome(Genome.createGenomeOfType(Genome.TERRITORIALILTY, 0));
		addGenome(Genome.createGenomeOfType(Genome.COLOR, Trait.COLOR_WHITE));
		
		addGenome(Genome.createGenomeOfType(Genome.DAMAGE, 0));
		addGenome(Genome.createGenomeOfType(Genome.HEALTH, 8));
		
		addGenome(Genome.createGenomeOfType(Genome.DROP_AMOUNT, 1, 3));
		addGenome(Genome.createGenomeOfType(Genome.DROP_TYPE, Trait.DROP_WOOL));
		
		addGenome(Genome.createGenomeOfType(Genome.OFFSPRING, 1));
	}
}
