package com.flansmod.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;

import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.DriveablePosition;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.PilotGun;
import com.flansmod.common.driveables.VehicleType;

public class RenderVehicle extends Render
{
    public RenderVehicle()
    {
        shadowSize = 0.5F;
    }

    public void render(EntityVehicle vehicle, double d, double d1, double d2, float f, float f1)
    {
    	bindEntityTexture(vehicle);
    	VehicleType type = vehicle.getVehicleType();
        GL11.glPushMatrix();
        {
	        GL11.glTranslatef((float)d, (float)d1, (float)d2);
	        float dYaw = (vehicle.axes.getYaw() - vehicle.prevRotationYaw);
	        for(; dYaw > 180F; dYaw -= 360F) {}
	        for(; dYaw <= -180F; dYaw += 360F) {}
	        float dPitch = (vehicle.axes.getPitch() - vehicle.prevRotationPitch);
	        for(; dPitch > 180F; dPitch -= 360F) {}
	        for(; dPitch <= -180F; dPitch += 360F) {}
	        float dRoll = (vehicle.axes.getRoll() - vehicle.prevRotationRoll);
	        for(; dRoll > 180F; dRoll -= 360F) {}
	        for(; dRoll <= -180F; dRoll += 360F) {}
	        GL11.glRotatef(180F - vehicle.prevRotationYaw - dYaw * f1, 0.0F, 1.0F, 0.0F);
	        GL11.glRotatef(vehicle.prevRotationPitch + dPitch * f1, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(vehicle.prevRotationRoll + dRoll * f1, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
	
			float modelScale = type.modelScale;
			GL11.glPushMatrix();
			{
				GL11.glScalef(modelScale, modelScale, modelScale);
		        ModelVehicle modVehicle = (ModelVehicle)type.model;
				if(modVehicle != null)
					modVehicle.render(vehicle, f1);
				
				GL11.glPushMatrix();
				if(type.turretOrigin != null && vehicle.isPartIntact(EnumDriveablePart.turret) && vehicle.seats != null && vehicle.seats[0] != null)
				{
					dYaw = (vehicle.seats[0].looking.getYaw() - vehicle.seats[0].prevLooking.getYaw());
			        for(; dYaw > 180F; dYaw -= 360F) {}
			        for(; dYaw <= -180F; dYaw += 360F) {}
		    		float yaw = vehicle.seats[0].prevLooking.getYaw() + dYaw * f1;
		    		
		    		GL11.glTranslatef(type.turretOrigin.x, type.turretOrigin.y, type.turretOrigin.z);
					GL11.glRotatef(-yaw, 0.0F, 1.0F, 0.0F);
					GL11.glTranslatef(-type.turretOrigin.x, -type.turretOrigin.y, -type.turretOrigin.z);
					
					if(modVehicle != null)
						modVehicle.renderTurret(0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, vehicle, f1);
					
					if(FlansMod.DEBUG)
					{						
						//Render shoot points
						GL11.glColor4f(0F, 0F, 1F, 0.3F);
						for(DriveablePosition point : type.shootPointsPrimary)			
							if(point.part == EnumDriveablePart.turret)
								renderAABB(AxisAlignedBB.getBoundingBox(point.position.x - 0.25F, point.position.y - 0.25F, point.position.z - 0.25F, point.position.x + 0.25F, point.position.y + 0.25F, point.position.z + 0.25F));
						
						GL11.glColor4f(0F, 1F, 0F, 0.3F);
						for(DriveablePosition point : type.shootPointsSecondary)	
							if(point.part == EnumDriveablePart.turret)
								renderAABB(AxisAlignedBB.getBoundingBox(point.position.x - 0.25F, point.position.y - 0.25F, point.position.z - 0.25F, point.position.x + 0.25F, point.position.y + 0.25F, point.position.z + 0.25F));
					}
				}
				GL11.glPopMatrix();
			}
			GL11.glPopMatrix();
			
			if(FlansMod.DEBUG)
			{
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glColor4f(1F, 0F, 0F, 0.3F);
				GL11.glScalef(1F, 1F, 1F);
				for(DriveablePart part : vehicle.getDriveableData().parts.values())
				{
					if(part.box == null)
						continue;
					
					renderAABB(AxisAlignedBB.getBoundingBox(part.box.x, part.box.y, part.box.z, (part.box.x + part.box.w), (part.box.y + part.box.h), (part.box.z + part.box.d)));
				}
				//GL11.glColor4f(0F, 1F, 0F, 0.3F);
				//if(type.barrelPosition != null)
				//	renderAABB(AxisAlignedBB.getBoundingBox(type.barrelPosition.x - 0.25F, type.barrelPosition.y - 0.25F, type.barrelPosition.z - 0.25F, type.barrelPosition.x + 0.25F, type.barrelPosition.y + 0.25F, type.barrelPosition.z + 0.25F));
				
				//Render shoot points
				GL11.glColor4f(0F, 0F, 1F, 0.3F);
				for(DriveablePosition point : type.shootPointsPrimary)			
					if(point.part != EnumDriveablePart.turret)
						renderAABB(AxisAlignedBB.getBoundingBox(point.position.x - 0.25F, point.position.y - 0.25F, point.position.z - 0.25F, point.position.x + 0.25F, point.position.y + 0.25F, point.position.z + 0.25F));
				
				GL11.glColor4f(0F, 1F, 0F, 0.3F);
				for(DriveablePosition point : type.shootPointsSecondary)	
					if(point.part != EnumDriveablePart.turret)
						renderAABB(AxisAlignedBB.getBoundingBox(point.position.x - 0.25F, point.position.y - 0.25F, point.position.z - 0.25F, point.position.x + 0.25F, point.position.y + 0.25F, point.position.z + 0.25F));

				
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1F, 1F, 1F, 1F);
			}
        }
        GL11.glPopMatrix();
    }

    @Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        render((EntityVehicle)entity, d, d1, d2, f, f1);
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return FlansModResourceHandler.getTexture(((EntityVehicle)entity).getVehicleType());
	}
}
