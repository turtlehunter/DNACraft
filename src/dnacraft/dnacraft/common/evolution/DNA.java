package dnacraft.common.evolution;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import dnacraft.common.Tally;
import dnacraft.common.entity.EntityMutant;
import dnacraft.common.evolution.dna.DNAChicken;
import dnacraft.common.evolution.dna.DNACreeper;
import dnacraft.common.evolution.dna.DNAEnderman;
import dnacraft.common.evolution.dna.DNAOcelot;
import dnacraft.common.evolution.dna.DNAPig;
import dnacraft.common.evolution.dna.DNASheep;
import dnacraft.common.evolution.dna.DNASpider;
import dnacraft.common.evolution.dna.DNAZombie;

public class DNA extends HashMap<String, Genome> {

	public static DNA pig = new DNAPig();
	public static DNA chicken = new DNAChicken();
	public static DNA zombie = new DNAZombie();
	public static DNA creeper = new DNACreeper();
	public static DNA enderman = new DNAEnderman();
	public static DNA spider = new DNASpider();
	public static DNA sheep = new DNASheep();
	public static DNA ocelot = new DNAOcelot();
	
	private static Random rnd = new Random();

	public DNA() {

	}

	public void addGenome(Genome genome) {
		put(genome.getType(), genome);
	}

	public Tally getActiveGeneTally(String type) {
		return getGeneTally(type, true);
	}

	public Tally getInactiveGeneTally(String type) {
		return getGeneTally(type, false);
	}
	
	public Tally getGeneTally(String type, boolean active) {
		Tally tally = new Tally();
		for (Gene gene : get(type)) {
			if (gene.isActive() == active) {
				tally.increment(gene.getTrait());
			}
		}
		return tally;
	}

	public int getLargestGene(String genome) {
		return getActiveGeneTally(genome).largest().getKey();
	}
	
	public double getAverageGene(String genome) {
		return getActiveGeneTally(genome).average();
	}

	public int getRandomWeightedGene(String genome) {
		return getActiveGeneTally(genome).randomWeighted().getKey();
	}

	public int getRandomWeightedGene(String genome, double pow) {
		return getActiveGeneTally(genome).randomWeighted(pow).getKey();
	}

	public NBTTagCompound toNBT() {
		NBTTagCompound compound = new NBTTagCompound();
		Genome genome;
		for (Map.Entry<String, Genome> entry : this.entrySet()) {
			genome = entry.getValue();
			compound.setTag(genome.getType(), genome.toNBT());
		}
		return compound;
	}

	public static DNA fromNBT(NBTTagCompound compound) {
		DNA dna = new DNA();
		Collection col = compound.getTags();
		Iterator it = col.iterator();
		while (it.hasNext()) {
			NBTBase nbt = (NBTBase) it.next();
			dna.put(nbt.getName(), Genome.fromNBT(((NBTTagCompound) nbt)));
		}
		return dna;
	}
	
	public double compareTo(DNA other) {
		
		Set<String> unique = new HashSet<String>();
		unique.addAll(keySet());
		unique.addAll(other.keySet());
		int matching = 0;
		for (String key : unique) {
			Genome otherGenome = other.get(key);
			Genome myGenome = get(key);
			if (myGenome != null && otherGenome != null) {
				matching += myGenome.compareTo(otherGenome);
			}
		}
		
		return ((double) matching / 40 ) / unique.size();
		
	}

	public static DNA merge(DNA dna1, DNA dna2) {

		DNA newDNA = new DNA();

		Set<String> unique = new HashSet<String>();
		unique.addAll(dna1.keySet());
		unique.addAll(dna2.keySet());
		for (String key : unique) {
			Genome child = new Genome(key);
			Genome father = dna1.get(key);
			Genome mother = dna2.get(key);
			if (mother != null && father != null) {
				child = mother.mergeWith(father);
			} else if (mother != null) {
				child = mother.clone();
			} else {
				child = father.clone();
			}
			newDNA.put(key, child);
		}

		return newDNA;
	}

	public DNA clone() {
		DNA newDNA = new DNA();
		for (Entry<String, Genome> entry : entrySet()) {
			newDNA.put(entry.getKey(), entry.getValue().clone());
		}
		return newDNA;
	}

	public static DNA mergeFragment(DNA stackDNA, DNA fragment) {

		if (stackDNA.size() <= 0) {
			return fragment.clone();
		}

		int item = rnd.nextInt(stackDNA.size());
		int i = 0;
		DNA newDNA = new DNA();
		for (Entry<String, Genome> entry : stackDNA.entrySet()) {
			Genome newGenome = entry.getValue().clone();
			if (i == item) {
				int rndTrait = rnd.nextInt(40);
				Genome fragmentGenome = fragment.get(entry.getKey());
				newGenome.set(rndTrait, fragmentGenome.get(rndTrait).clone());
			}
			newDNA.put(entry.getKey(), newGenome);
			i = i + 1;
		}

		return newDNA;
	}

	public void debug() {

		for (Entry<String, Genome> entry : entrySet()) {
			System.out.println("Genome: " + entry.getKey());
			Genome genome = entry.getValue();
			String str = "";
			if (genome != null) {
				for (int i = 0; i < genome.size(); i++) {
					Gene g = genome.get(i);
					str += g.getTrait() + ":" + (g.isActive() ? 1 : 0) + ",";
				}
				System.out.println(str);
			}
		}

	}
	
	public static DNA getDNAForEntity(Object entity) {
		
		if (entity instanceof EntityPig) {
			return DNA.pig;
		}else if (entity instanceof EntitySheep) {
			return DNA.sheep;
		}else if (entity instanceof EntityChicken) {
			return DNA.chicken;
		}else if (entity instanceof EntityMutant) {
			return ((EntityMutant)entity).dna.clone();
		}
		return null;
	}
}
