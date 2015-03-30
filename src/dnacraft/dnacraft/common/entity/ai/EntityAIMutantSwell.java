package dnacraft.common.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import dnacraft.common.entity.EntityMutant;


public class EntityAIMutantSwell extends EntityAIBase
{
    EntityMutant swellingMutant;

    EntityLiving mutantAttackTarget;

    public EntityAIMutantSwell(EntityMutant mutant)
    {
        this.swellingMutant = mutant;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLiving var1 = (EntityLiving) this.swellingMutant.getAttackTarget();
        return this.swellingMutant.getCreeperState() > 0 || var1 != null && this.swellingMutant.getDistanceSqToEntity(var1) < 9.0D;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.swellingMutant.getNavigator().clearPathEntity();
        this.mutantAttackTarget = (EntityLiving) this.swellingMutant.getAttackTarget();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.mutantAttackTarget = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (this.mutantAttackTarget == null)
        {
            this.swellingMutant.setCreeperState(-1);
        }
        else if (this.swellingMutant.getDistanceSqToEntity(this.mutantAttackTarget) > 49.0D)
        {
            this.swellingMutant.setCreeperState(-1);
        }
        else if (!this.swellingMutant.getEntitySenses().canSee(this.mutantAttackTarget))
        {
            this.swellingMutant.setCreeperState(-1);
        }
        else
        {
            this.swellingMutant.setCreeperState(1);
        }
    }
}

