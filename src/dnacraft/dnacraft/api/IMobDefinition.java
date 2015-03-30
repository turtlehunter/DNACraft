package dnacraft.api;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

public interface IMobDefinition {
	public void renderHead(Entity entity, float legSwing,
			float prevLegSwing, float wingSwing, float yaw,
			float pitch, float scale, int legHeight, int bodyHeight, Vec3 attachmentPoint);
	public void renderLegs(Entity entity, float legSwing,
			float prevLegSwing, float wingSwing, float yaw,
			float pitch, float scale, int bodyHeight,  Vec3[] attachmentPoints);
	public void renderBody(Entity entity, float legSwing,
			float prevLegSwing, float wingSwing, float yaw,
			float pitch, float scale, int legHeight);
	public void renderWings(Entity entity, float legSwing,
			float prevLegSwing, float wingSwing, float yaw,
			float pitch, float scale, int legHeight, int bodyHeight, Vec3[] attachmentPoints);
	public void renderArms(Entity entity, float legSwing,
			float prevLegSwing, float wingSwing, float yaw,
			float pitch, float scale, int legHeight, int bodyHeight, Vec3[] attachmentPoints);
	public void renderTail(Entity entity, float legSwing,
			float prevLegSwing, float wingSwing, float yaw,
			float pitch, float scale, int legHeight, int bodyHeight, Vec3 attachmentPoint);
	public Vec3[] getLegAttachmentPoints(int numLegs);
	public Vec3 getHeadAttachmentPoint();
	public Vec3 getTailAttachmentPoint();
	public Vec3[] getWingAttachmentPoints();
	public Vec3[] getArmAttachmentPoints();
	public int getTrait();
	public int getLegHeight();
	public int getBodyHeight();
	public int getNumberOfLegs();
}
