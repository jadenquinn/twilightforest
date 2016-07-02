package twilightforest.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.entity.ai.EntityAITFMagicAttack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityTFSlimeBeetle extends EntityMob
{

	public EntityTFSlimeBeetle(World world) {
		super(world);
		//texture = TwilightForestMod.MODEL_DIR + "slimebeetle.png";
		//moveSpeed = 0.23F;
		setSize(0.9F, 1.75F);

		this.tasks.addTask(0, new EntityAISwimming(this));
		//this.tasks.addTask(2, new EntityAITFFireBreath(this, this.moveSpeed, 5F, 30, 0.1F));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 3.0F, 1.25F, 2.0F));
		this.tasks.addTask(3, new EntityAITFMagicAttack(this, 1.0F, EntityAITFMagicAttack.SLIME, 30));
		//this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed, false));
		this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));

	}

	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
    }

    @Override
	protected String getLivingSound()
    {
        return null;
    }

    @Override
	protected String getHurtSound()
    {
        return "mob.spider.say";
    }

    @Override
	protected String getDeathSound()
    {
        return "mob.spider.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
	protected void playStepSound(BlockPos pos, Block var4)
    {
        this.worldObj.playSoundAtEntity(this, "mob.spider.step", 0.15F, 1.0F);
    }

    @Override
	public void onLivingUpdate()
    {
    	super.onLivingUpdate();

//    	// dribble slime particles out of mouth
//    	Vec3 look = this.getLookVec();
//
//    	double dist = 0.9;
//    	double px = this.posX + look.xCoord * dist;
//    	double py = this.posY + 0.25 + look.yCoord * dist;
//    	double pz = this.posZ + look.zCoord * dist;
//
//		worldObj.spawnParticle("slime", px, py, pz, 0, 0, 0);

    }

    @Override
    public void onDeath(DamageSource par1DamageSource) {
    	super.onDeath(par1DamageSource);
    	if (par1DamageSource.getSourceOfDamage() instanceof EntityPlayer) {
    		((EntityPlayer)par1DamageSource.getSourceOfDamage()).addStat(TFAchievementPage.twilightHunter);
    	}
    }

    public int getAttackStrength(Entity par1Entity)
    {
        return 4;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize() 
	{
		return 1.1F;
	}

	@Override
	public float getEyeHeight() {
		return 0.25F;
	}

    @Override
	public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }
    
    @Override
	protected Item getDropItem()
    {
        return Items.SLIME_BALL;
    }
	
}
