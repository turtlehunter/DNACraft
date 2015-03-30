package dnacraft.client.model;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dnacraft.api.IMobDefinition;
import dnacraft.common.entity.EntityMutant;

@SideOnly(Side.CLIENT)
public class ModelMutant extends ModelBase {

	public HashMap<Integer, IMobDefinition> mobs = new HashMap<Integer, IMobDefinition>();

	public void register(IMobDefinition mobdef) {
		mobs.put(mobdef.getTrait(), mobdef);
	}

	public ModelMutant() {

	}

	@Override
	public void setTextureOffset(String par1Str, int par2, int par3) {
		super.setTextureOffset(par1Str, par2, par3);
	}

	public void render(Entity entity, float legSwing, float prevLegSwing,
			float wingSwing, float yaw, float pitch, float scale) {

		EntityRenderer renderEngine = Minecraft.getMinecraft().entityRenderer;
		EntityMutant mutant = (EntityMutant) entity;
		IMobDefinition body = this.mobs.get(mutant.getBodyModel());
		IMobDefinition legs = this.mobs.get(mutant.getLegsModel());
		IMobDefinition head = this.mobs.get(mutant.getHeadModel());
		IMobDefinition wings = this.mobs.get(mutant.getWingsModel());
		IMobDefinition tail = this.mobs.get(mutant.getTailModel());
		IMobDefinition arms = this.mobs.get(mutant.getArmsModel());

		int legheight = legs.getLegHeight();

		body.renderBody(entity, legSwing, prevLegSwing, wingSwing, yaw, pitch,
				scale, legheight);

		head.renderHead(entity, legSwing, prevLegSwing, wingSwing, yaw, pitch,
				scale, legheight, body.getBodyHeight(),
				body.getHeadAttachmentPoint());

		legs.renderLegs(entity, legSwing, prevLegSwing, wingSwing, yaw, pitch,
				scale, body.getBodyHeight(),
				body.getLegAttachmentPoints(legs.getNumberOfLegs()));

		wings.renderWings(entity, legSwing, prevLegSwing, wingSwing, yaw,
				pitch, scale, legheight, body.getBodyHeight(),
				body.getWingAttachmentPoints());
		
		arms.renderArms(entity, legSwing, prevLegSwing, wingSwing, yaw, pitch,
				scale, legheight, body.getBodyHeight(),
				body.getArmAttachmentPoints());
		
		tail.renderTail(entity, legSwing, prevLegSwing, wingSwing, yaw, pitch,
				scale, legheight, body.getBodyHeight(),
				body.getTailAttachmentPoint());

	}

}
