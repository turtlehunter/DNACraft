package dnacraft.common.evolution;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;

public class Genome extends ArrayList<Gene> {
	
	public static int GENOME_SIZE = 40;
	private static Random rnd = new Random();

	public static Genome createGenomeOfType(String type, int... traits) {
		Genome genome = new Genome(type);
		for (int i = 0; i < GENOME_SIZE; i++) {
			genome.add(new Gene(
					traits[rnd.nextInt(traits.length)],
					rnd.nextBoolean()
			));
		}
		return genome;
	}

	public static String LEG_TYPE = "lt";
	public static String BODY_TYPE = "bt";
	public static String HEAD_TYPE = "ht";
	public static String WING_TYPE = "wt";
	public static String TAIL_TYPE = "tt";
	public static String ARM_TYPE = "at";

	public static String DROP_TYPE = "dt";
	public static String DROP_AMOUNT = "da";

	public static String COLOR = "c";

	public static String OFFSPRING = "o";
	public static String AGGRESSION = "a";
	public static String DAMAGE = "dm";
	public static String HEALTH = "h";
	
	public static String TERRITORIALILTY = "ter";

	private String type;

	public Genome(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public int compareTo(Genome genome) {
		Gene g;
		int matches = 0;
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).matches(genome.get(i)))
			{
				matches++;
			}
		}
		return matches;
	}
	
	public NBTTagCompound toNBT() {
		
		NBTTagCompound compound = new NBTTagCompound();
		
		int[] traitsList = new int[GENOME_SIZE];
		byte[] activeList = new byte[GENOME_SIZE];
		
		Gene g;
		
		for (int i = 0; i < this.size(); i++) {
			g = this.get(i);
			traitsList[i] = g.getTrait();
			activeList[i] = (byte) (g.isActive() ? 1 : 0);
		}
		
		compound.setByteArray("actives", activeList);
		compound.setIntArray("traits", traitsList);
		
		return compound;
	}
	
	public static Genome fromNBT(NBTTagCompound compound) {
		
		Genome genome = new Genome(compound.getName());
		int[] traits = compound.getIntArray("traits");
		byte[] actives = compound.getByteArray("actives");

		if (traits.length != GENOME_SIZE || actives.length != GENOME_SIZE )  {
			return null;
		}
		
		for (int i = 0; i < GENOME_SIZE; i++) {
			genome.add(
				new Gene(traits[i], actives[i] == 1)
			);
		}
		return genome;
	}
	
	public Genome clone() {
		Genome clone = new Genome(this.getType());
		for (int i = 0; i < this.size(); i++) {
			Gene parentGene = this.get(i);
			clone.add(parentGene.clone());
		}
		return clone;
	}
	
	public Genome mergeWith (Genome target) {
		Genome child = new Genome(this.getType());
		for (int i = 0; i < this.size(); i++) {
			child.add(new Gene(this.get(i), target.get(i)));
		}
		return child;
	}
}
