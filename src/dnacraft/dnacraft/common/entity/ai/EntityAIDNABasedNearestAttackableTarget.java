package dnacraft.common.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import dnacraft.common.entity.EntityMutant;

public class EntityAIDNABasedNearestAttackableTarget extends EntityAINearestAttackableTarget {

	private int unknown_b;
	private int unknown_c;
	private int unknown_g;
	
	public EntityAIDNABasedNearestAttackableTarget(
			EntityLiving par1EntityLiving, Class par2Class, float par3,
			int par4, boolean par5) {
		super(par1EntityLiving, par2Class, par3, par4, par5);
	}
	
	@Override
    public void startExecuting()
    {
        this.unknown_b = 0;
        this.unknown_c = 0;
        this.unknown_g = 0;
        super.startExecuting();
    }
	

	
    protected boolean isSuitableTarget(EntityLiving par1EntityLiving, boolean par2)
    {
        if (par1EntityLiving == null)
        {
            return false;
        }
        else if (par1EntityLiving == this.taskOwner)
        {
            return false;
        }
        else if (!par1EntityLiving.isEntityAlive())
        {
            return false;
        }
        else if (!this.taskOwner.canAttackClass(par1EntityLiving.getClass()))
        {
            return false;
        }
        else if (!((EntityMutant)this.taskOwner).wantsToAttack(par1EntityLiving))
        {
        	return false;
        }
        else
        {
            if (this.taskOwner instanceof EntityTameable && ((EntityTameable)this.taskOwner).isTamed())
            {
                if (par1EntityLiving instanceof EntityTameable && ((EntityTameable)par1EntityLiving).isTamed())
                {
                    return false;
                }

                if (par1EntityLiving == ((EntityTameable)this.taskOwner).getOwner())
                {
                    return false;
                }
            }
            else if (par1EntityLiving instanceof EntityPlayer && !par2 && ((EntityPlayer)par1EntityLiving).capabilities.disableDamage)
            {
                return false;
            }

            if (!this.taskOwner.isWithinHomeDistance(MathHelper.floor_double(par1EntityLiving.posX), MathHelper.floor_double(par1EntityLiving.posY), MathHelper.floor_double(par1EntityLiving.posZ)))
            {
                return false;
            }
            else if (this.shouldCheckSight && !this.taskOwner.getEntitySenses().canSee(par1EntityLiving))
            {
                return false;
            }
            else
            {
                if (--this.unknown_c <= 0)
                {
                    this.unknown_b = 0;
                }

                if (this.unknown_b == 0)
                {
                    this.unknown_b = this.func_75295_a(par1EntityLiving) ? 1 : 2;
                }

                if (this.unknown_b == 2)
                {
                    return false;
                }

                return true;
            }
        }
    }
    private boolean func_75295_a(EntityLiving par1EntityLiving)
    {
        this.unknown_c = 10 + this.taskOwner.getRNG().nextInt(5);
        PathEntity var2 = this.taskOwner.getNavigator().getPathToEntityLiving(par1EntityLiving);

        if (var2 == null)
        {
            return false;
        }
        else
        {
            PathPoint var3 = var2.getFinalPathPoint();

            if (var3 == null)
            {
                return false;
            }
            else
            {
                int var4 = var3.xCoord - MathHelper.floor_double(par1EntityLiving.posX);
                int var5 = var3.zCoord - MathHelper.floor_double(par1EntityLiving.posZ);
                return (double)(var4 * var4 + var5 * var5) <= 2.25D;
            }
        }
    }
}
