//This File was created with the Minecraft-SMP Modelling Toolbox 1.5.4.1
// Copyright (C) 2013 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 3.0.x+

// Model Checklist
//    Model: 
//    - Check Left/Right    [ ]  (Left = + / Right = -)
//    - Code Cleaned        [ ]
//    - Coverted to Version [ ]

package com.flansmod.client.model.ww2;

import com.flansmod.client.model.ModelPlane;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.client.tmt.Coord2D;
import com.flansmod.client.tmt.Shape2D;

public class ModelMustang extends ModelPlane
{
	int textureX = 512;
	int textureY = 512;

	public ModelMustang()
	{

// Nose
		noseModel = new ModelRendererTurbo[8];
		noseModel[0] = new ModelRendererTurbo(this,  35,  461, textureX, textureY); // NS.Nose
		noseModel[1] = new ModelRendererTurbo(this,  0,  0, textureX, textureY); // NS.Core/NoseTransitionTop
		noseModel[2] = new ModelRendererTurbo(this,  280,  440, textureX, textureY); // NS.Core/NoseTransitionBottom
		noseModel[3] = new ModelRendererTurbo(this,  280,  400, textureX, textureY); // NS.NoseAirIntake
		noseModel[4] = new ModelRendererTurbo(this,  280,  350, textureX, textureY); // NS.EngineThingiesLeft
		noseModel[5] = new ModelRendererTurbo(this,  280,  350, textureX, textureY); // NS.EngineThingiesRight
		noseModel[6] = new ModelRendererTurbo(this,  0,  180, textureX, textureY); // NS.FrontAngle
		noseModel[7] = new ModelRendererTurbo(this,  0,  480, textureX, textureY); // PR.NoseCone

		noseModel[0].addBox(0F, 0F, 0F, 14, 9, 10, 0F); // NS.Nose
		noseModel[0].setRotationPoint(-65F, -20.5F, -5F);

		noseModel[1].addBox(0F, 0F, 0F, 22, 12, 12, 0F); // NS.Core/NoseTransitionTop
		noseModel[1].setRotationPoint(-58F, -22.5F, -6F);
		noseModel[1].rotateAngleZ = 0.05235988F;

		noseModel[2].addBox(0F, -6F, 0F, 20, 7, 12, 0F); // NS.Core/NoseTransitionBottom
		noseModel[2].setRotationPoint(-56F, -9F, -6F);
		noseModel[2].rotateAngleZ = -0.06981317F;

		noseModel[3].addBox(0F, -3F, 0F, 10, 7, 10, 0F); // NS.NoseAirIntake
		noseModel[3].setRotationPoint(-63F, -15F, -5F);
		noseModel[3].rotateAngleZ = -0.3141593F;

		noseModel[4].addBox(0F, 0F, -1.5F, 16, 2, 3, 0F); // NS.EngineThingiesLeft
		noseModel[4].setRotationPoint(-61F, -18F, 4.5F);
		noseModel[4].rotateAngleY = 0.06981317F;

		noseModel[5].addBox(0F, 0F, -1.5F, 16, 2, 3, 0F); // NS.EngineThingiesRight
		noseModel[5].setRotationPoint(-61F, -18F, -4.5F);
		noseModel[5].rotateAngleY = -0.06981317F;

		noseModel[6].addBox(0F, -0.5F, -5F, 8, 3, 10, 0F); // NS.FrontAngle
		noseModel[6].setRotationPoint(-65F, -20F, 0F);
		noseModel[6].rotateAngleZ = 0.2443461F;

		noseModel[7].addBox(-2F, -3.5F, -3.5F, 7, 7, 7, 0F); // PR.NoseCone
		noseModel[7].setRotationPoint(-70F, -16F, 0F);



// Body
		bodyModel = new ModelRendererTurbo[21];
		bodyModel[0] = new ModelRendererTurbo(this,  280,  470, textureX, textureY); // CR.CoreFront
		bodyModel[1] = new ModelRendererTurbo(this,  350,  300, textureX, textureY); // CR.Intake
		bodyModel[2] = new ModelRendererTurbo(this,  450,  415, textureX, textureY); // CR.ControlPanel
		bodyModel[3] = new ModelRendererTurbo(this,  440,  490, textureX, textureY); // CR.FrontLeftSideTube
		bodyModel[4] = new ModelRendererTurbo(this,  440,  490, textureX, textureY); // CR.FrontRightSideTube
		bodyModel[5] = new ModelRendererTurbo(this,  420,  460, textureX, textureY); // CR.FrontTopTube
		bodyModel[6] = new ModelRendererTurbo(this,  430,  490, textureX, textureY); // CR.FrontLeftTube
		bodyModel[7] = new ModelRendererTurbo(this,  430,  490, textureX, textureY); // CR.FrontRightTube
		bodyModel[8] = new ModelRendererTurbo(this,  420,  480, textureX, textureY); // CR.RightTopSideTube
		bodyModel[9] = new ModelRendererTurbo(this,  420,  480, textureX, textureY); // CR.LeftSideTube
		bodyModel[10] = new ModelRendererTurbo(this,  410,  445, textureX, textureY); // CR.BackRightTube
		bodyModel[11] = new ModelRendererTurbo(this,  410,  445, textureX, textureY); // CR.BackLeftTube
		bodyModel[12] = new ModelRendererTurbo(this,  451,  371, textureX, textureY); // CR.CoreBack
		bodyModel[13] = new ModelRendererTurbo(this,  430,  340, textureX, textureY); // CR.CoreFloor
		bodyModel[14] = new ModelRendererTurbo(this,  460,  310, textureX, textureY); // CR.CoreWallLeft
		bodyModel[15] = new ModelRendererTurbo(this,  390,  500, textureX, textureY); // CR.UnderJoystick
		bodyModel[16] = new ModelRendererTurbo(this,  410,  370, textureX, textureY); // CR.ChairBottom
		bodyModel[17] = new ModelRendererTurbo(this,  420,  390, textureX, textureY); // CR.ChairTop
		bodyModel[18] = new ModelRendererTurbo(this,  460,  310, textureX, textureY); // CR.CoreWallRight
		bodyModel[19] = new ModelRendererTurbo(this,  430,  420, textureX, textureY); // CR.Antenna
		bodyModel[20] = new ModelRendererTurbo(this,  0,  0, textureX, textureY); // CR.CockpitBottom

		bodyModel[0].addBox(0F, 0F, 0F, 12, 17, 14, 0F); // CR.CoreFront
		bodyModel[0].setRotationPoint(-36F, -23.5F, -7F);

		bodyModel[1].addBox(0F, 0F, 0F, 22, 5, 12, 0F); // CR.Intake
		bodyModel[1].setRotationPoint(-18F, -7F, -6F);
		bodyModel[1].rotateAngleZ = 0.03490658F;

		bodyModel[2].addBox(0F, 0F, 0F, 12, 8, 12, 0F); // CR.ControlPanel
		bodyModel[2].setRotationPoint(-35F, -22F, -6F);
		bodyModel[2].rotateAngleZ = 0.1745329F;

		bodyModel[3].addBox(-0.5F, 0F, -0.5F, 1, 10, 1, 0F); // CR.FrontLeftSideTube
		bodyModel[3].setRotationPoint(-17.5F, -30F, 3.8F);
		bodyModel[3].rotateAngleX = 0.2443461F;

		bodyModel[4].addBox(-0.5F, 0F, -0.5F, 1, 10, 1, 0F); // CR.FrontRightSideTube
		bodyModel[4].setRotationPoint(-17.5F, -30F, -3.8F);
		bodyModel[4].rotateAngleX = -0.2443461F;

		bodyModel[5].addBox(0F, 0F, 0F, 1, 1, 8, 0F); // CR.FrontTopTube
		bodyModel[5].setRotationPoint(-17F, -30.3F, -4F);
		bodyModel[5].rotateAngleZ = -1.396263F;

		bodyModel[6].addBox(-0.5F, 0F, -0.5F, 1, 12, 1, 0F); // CR.FrontLeftTube
		bodyModel[6].setRotationPoint(-17.5F, -29.7F, 3F);
		bodyModel[6].rotateAngleZ = -0.9773844F;

		bodyModel[7].addBox(-0.5F, 0F, -0.5F, 1, 12, 1, 0F); // CR.FrontRightTube
		bodyModel[7].setRotationPoint(-17.5F, -29.7F, -3F);
		bodyModel[7].rotateAngleZ = -0.9773844F;

		bodyModel[8].addBox(-0.5F, -0.5F, -0.5F, 10, 1, 1, 0F); // CR.RightTopSideTube
		bodyModel[8].setRotationPoint(-17F, -29.4F, -3.8F);
		bodyModel[8].rotateAngleX = -0.6632251F;

		bodyModel[9].addBox(-0.5F, -0.5F, -0.5F, 10, 1, 1, 0F); // CR.LeftSideTube
		bodyModel[9].setRotationPoint(-17F, -29.4F, 3.9F);
		bodyModel[9].rotateAngleX = 0.6632251F;

		bodyModel[10].addBox(-0.5F, -1.5F, -0.5F, 18, 1, 1, 0F); // CR.BackRightTube
		bodyModel[10].setRotationPoint(-8F, -28.2F, -3.8F);
		bodyModel[10].rotateAngleY = 0.122173F;
		bodyModel[10].rotateAngleZ = -0.3839724F;

		bodyModel[11].addBox(-0.5F, -0.5F, -0.5F, 18, 1, 1, 0F); // CR.BackLeftTube
		bodyModel[11].setRotationPoint(-8F, -29.2F, 3.8F);
		bodyModel[11].rotateAngleY = -0.122173F;
		bodyModel[11].rotateAngleZ = -0.3839724F;

		bodyModel[12].addBox(0F, 0F, 0F, 11, 17, 13, 0F); // CR.CoreBack
		bodyModel[12].setRotationPoint(-7F, -23.5F, -6.5F);

		bodyModel[13].addBox(0F, 0F, 0F, 18, 1, 16, 0F); // CR.CoreFloor
		bodyModel[13].setRotationPoint(-24F, -7.5F, -8F);

		bodyModel[14].addBox(0F, 0F, 0F, 23, 17, 1, 0F); // CR.CoreWallLeft
		bodyModel[14].setRotationPoint(-24F, -23.5F, 6F);

		bodyModel[15].addBox(0F, 0F, -0.5F, 13, 1, 1, 0F); // CR.UnderJoystick
		bodyModel[15].setRotationPoint(-18.5F, -9F, 0F);

		bodyModel[16].addBox(0F, 0F, 0F, 8, 2, 7, 0F); // CR.ChairBottom
		bodyModel[16].setRotationPoint(-18F, -12F, -3.5F);

		bodyModel[17].addBox(0F, 0F, 0F, 2, 10, 10, 0F); // CR.ChairTop
		bodyModel[17].setRotationPoint(-9.5F, -26F, -5F);

		bodyModel[18].addBox(0F, 0F, 0F, 23, 17, 1, 0F); // CR.CoreWallRight
		bodyModel[18].setRotationPoint(-24F, -23.5F, -7F);

		bodyModel[19].addBox(0F, 0F, 0F, 1, 12, 1, 0F); // CR.Antenna
		bodyModel[19].setRotationPoint(20F, -33F, -0.5F);

		bodyModel[20].addBox(0F, 0F, 0F, 2, 9, 4, 0F); // CR.CockpitBottom
		bodyModel[20].setRotationPoint(-24F, -16F, -2F);



// Bay
		bayModel = new ModelRendererTurbo[7];
		bayModel[0] = new ModelRendererTurbo(this,  300,  150, textureX, textureY); // BY.BayRight
		bayModel[1] = new ModelRendererTurbo(this,  300,  150, textureX, textureY); // BY.BayLeft
		bayModel[2] = new ModelRendererTurbo(this,  300,  210, textureX, textureY); // BY.Core/Bayconnector
		bayModel[3] = new ModelRendererTurbo(this,  342,  211, textureX, textureY); // BY.BayBottom
		bayModel[4] = new ModelRendererTurbo(this,  425,  200, textureX, textureY); // BY.WheelHolder
		bayModel[5] = new ModelRendererTurbo(this,  182,  180, textureX, textureY); // BY.IntakeSucker
		bayModel[6] = new ModelRendererTurbo(this,  300,  250, textureX, textureY); // BY.DorsalFinButtress

		bayModel[0].addBox(0F, 0F, -4F, 45, 8, 6, 0F); // BY.BayRight
		bayModel[0].setRotationPoint(2F, -23F, -2.4F);
		bayModel[0].rotateAngleY = 0.06981317F;
		bayModel[0].rotateAngleZ = -0.05235988F;

		bayModel[1].addBox(0F, 0F, -4F, 45, 8, 6, 0F); // BY.BayLeft
		bayModel[1].setRotationPoint(2F, -23F, 4.4F);
		bayModel[1].rotateAngleY = -0.06981317F;
		bayModel[1].rotateAngleZ = -0.05235988F;

		bayModel[2].addBox(0F, 0F, 0F, 12, 3, 6, 0F); // BY.Core/Bayconnector
		bayModel[2].setRotationPoint(3F, -23.4F, -3F);

		bayModel[3].addBox(0F, -6F, 0F, 34, 8, 7, 0F); // BY.BayBottom
		bayModel[3].setRotationPoint(17.5F, -9.4F, -3.5F);
		bayModel[3].rotateAngleZ = 0.1815142F;

		bayModel[4].addBox(-3F, -1F, 0F, 12, 4, 1, 0F); // BY.WheelHolder
		bayModel[4].setRotationPoint(24F, -8F, -0.5F);
		bayModel[4].rotateAngleZ = 0.1745329F;

		bayModel[5].addBox(0F, -14F, 0F, 17, 14, 10, 0F); // BY.IntakeSucker
		bayModel[5].setRotationPoint(4F, -3F, -5F);
		bayModel[5].rotateAngleZ = 0.3141593F;

		bayModel[6].addBox(0F, 0F, 0F, 17, 5, 2, 0F); // BY.DorsalFinButtress
		bayModel[6].setRotationPoint(24F, -22F, -1F);
		bayModel[6].rotateAngleZ = 0.2443461F;



// Tail
		tailModel = new ModelRendererTurbo[8];
		tailModel[0] = new ModelRendererTurbo(this,  180,  235, textureX, textureY); // TL.Dorsal/FlapCapital
		tailModel[1] = new ModelRendererTurbo(this,  180,  250, textureX, textureY); // TL.DorsalFinFront
		tailModel[2] = new ModelRendererTurbo(this,  180,  265, textureX, textureY); // TL.DorsalFiller1
		tailModel[3] = new ModelRendererTurbo(this,  180,  300, textureX, textureY); // TL.FinAngleLeft
		tailModel[4] = new ModelRendererTurbo(this,  180,  340, textureX, textureY); // TL.LeftFlapHinge
		tailModel[5] = new ModelRendererTurbo(this,  180,  300, textureX, textureY); // TL.FinAngleRight
		tailModel[6] = new ModelRendererTurbo(this,  180,  340, textureX, textureY); // TL.RightFlapHinge
		tailModel[7] = new ModelRendererTurbo(this,  180,  375, textureX, textureY); // TL.MiddleCoverUp

		tailModel[0].addBox(0F, 0F, 0F, 6, 7, 2, 0F); // TL.Dorsal/FlapCapital
		tailModel[0].setRotationPoint(44F, -39F, -1F);

		tailModel[1].addBox(2F, 0F, 0F, 20, 4, 2, 0F); // TL.DorsalFinFront
		tailModel[1].setRotationPoint(37F, -18F, -1F);
		tailModel[1].rotateAngleZ = 1.27409F;

		tailModel[2].addBox(0F, 0F, 0F, 8, 12, 2, 0F); // TL.DorsalFiller1
		tailModel[2].setRotationPoint(42F, -32F, -1F);

		tailModel[3].addBox(0F, 0F, 0F, 5, 2, 24, 0F); // TL.FinAngleLeft
		tailModel[3].setRotationPoint(30F, -20F, 5F);
		tailModel[3].rotateAngleY = -0.3490658F;

		tailModel[4].addBox(0F, 0F, 0F, 7, 2, 24, 0F); // TL.LeftFlapHinge
		tailModel[4].setRotationPoint(38F, -20F, 4F);

		tailModel[5].addBox(0F, 0F, -24F, 5, 2, 24, 0F); // TL.FinAngleRight
		tailModel[5].setRotationPoint(30F, -20F, -5F);
		tailModel[5].rotateAngleY = 0.3490658F;

		tailModel[6].addBox(0F, 0F, 0F, 7, 2, 24, 0F); // TL.RightFlapHinge
		tailModel[6].setRotationPoint(38F, -20F, -28F);

		tailModel[7].addBox(0F, 0F, 0F, 5, 2, 26, 0F); // TL.MiddleCoverUp
		tailModel[7].setRotationPoint(34F, -20F, -13F);



// Left Wing
		leftWingModel = new ModelRendererTurbo[6];
		leftWingModel[0] = new ModelRendererTurbo(this,  0,  0, textureX, textureY); // LW.LeftWingMain
		leftWingModel[1] = new ModelRendererTurbo(this,  390,  0, textureX, textureY); // LW.LeftWingBackCenter
		leftWingModel[2] = new ModelRendererTurbo(this,  390,  20, textureX, textureY); // LW.LeftWingFlapSupport
		leftWingModel[3] = new ModelRendererTurbo(this,  390,  80, textureX, textureY); // LW.LeftWingOuterTip
		leftWingModel[4] = new ModelRendererTurbo(this,  20,  80, textureX, textureY); // LW.LeftWingForwards
		leftWingModel[5] = new ModelRendererTurbo(this,  20,  110, textureX, textureY); // LW.LeftWingCorner

		leftWingModel[0].addBox(0F, -1F, 0F, 16, 2, 68, 0F); // LW.LeftWingMain
		leftWingModel[0].setRotationPoint(-30F, -8F, 7F);
		leftWingModel[0].rotateAngleX = 0.06981317F;
		leftWingModel[0].rotateAngleY = -0.01745329F;

		leftWingModel[1].addBox(15F, -1F, -8F, 15, 2, 13, 0F); // LW.LeftWingBackCenter
		leftWingModel[1].setRotationPoint(-30F, -8F, 7F);
		leftWingModel[1].rotateAngleX = 0.06981317F;
		leftWingModel[1].rotateAngleY = 0.1919862F;

		leftWingModel[2].addBox(17F, -1F, 5F, 11, 2, 42, 0F); // LW.LeftWingFlapSupport
		leftWingModel[2].setRotationPoint(-30F, -8F, 7F);
		leftWingModel[2].rotateAngleX = 0.06981317F;
		leftWingModel[2].rotateAngleY = 0.1919862F;

		leftWingModel[3].addBox(20F, -1F, 47F, 10, 2, 16, 0F); // LW.LeftWingOuterTip
		leftWingModel[3].setRotationPoint(-30F, -8F, 7F);
		leftWingModel[3].rotateAngleX = 0.06981317F;
		leftWingModel[3].rotateAngleY = 0.1919862F;

		leftWingModel[4].addBox(25F, -1F, 0F, 10, 2, 70, 0F); // LW.LeftWingForwards
		leftWingModel[4].setRotationPoint(-60F, -7.6F, 7F);
		leftWingModel[4].rotateAngleX = 0.06981317F;
		leftWingModel[4].rotateAngleY = -0.06981317F;

		leftWingModel[5].addBox(0F, -1F, -15F, 10, 2, 15, 0F); // LW.LeftWingCorner
		leftWingModel[5].setRotationPoint(-34F, -8.4F, 18F);
		leftWingModel[5].rotateAngleX = 0.01745329F;
		leftWingModel[5].rotateAngleY = -0.3141593F;



// Right Wing
		rightWingModel = new ModelRendererTurbo[6];
		rightWingModel[0] = new ModelRendererTurbo(this,  200,  0, textureX, textureY); // RW.RightWingMain
		rightWingModel[1] = new ModelRendererTurbo(this,  390,  0, textureX, textureY); // RW.RightWingBackCenter
		rightWingModel[2] = new ModelRendererTurbo(this,  390,  20, textureX, textureY); // RW.RightWingFlapSupport
		rightWingModel[3] = new ModelRendererTurbo(this,  390,  80, textureX, textureY); // RW.RightWingOuterTip
		rightWingModel[4] = new ModelRendererTurbo(this,  20,  80, textureX, textureY); // RW.RightWingForwards
		rightWingModel[5] = new ModelRendererTurbo(this,  20,  110, textureX, textureY); // RW.RightWingCorner

		rightWingModel[0].addBox(0F, -1F, -69F, 16, 2, 69, 0F); // RW.RightWingMain
		rightWingModel[0].setRotationPoint(-30F, -8F, -7F);
		rightWingModel[0].rotateAngleX = -0.06981317F;
		rightWingModel[0].rotateAngleY = 0.01745329F;

		rightWingModel[1].addBox(15F, -1F, -6F, 15, 2, 13, 0F); // RW.RightWingBackCenter
		rightWingModel[1].setRotationPoint(-30F, -8F, -7F);
		rightWingModel[1].rotateAngleX = -0.06981317F;
		rightWingModel[1].rotateAngleY = -0.1919862F;

		rightWingModel[2].addBox(17F, -1F, -48F, 11, 2, 42, 0F); // RW.RightWingFlapSupport
		rightWingModel[2].setRotationPoint(-30F, -8F, -7F);
		rightWingModel[2].rotateAngleX = -0.06981317F;
		rightWingModel[2].rotateAngleY = -0.1919862F;

		rightWingModel[3].addBox(20F, -1F, -64F, 10, 2, 16, 0F); // RW.RightWingOuterTip
		rightWingModel[3].setRotationPoint(-30F, -8F, -7F);
		rightWingModel[3].rotateAngleX = -0.06981317F;
		rightWingModel[3].rotateAngleY = -0.1919862F;

		rightWingModel[4].addBox(25F, -1F, -71F, 10, 2, 70, 0F); // RW.RightWingForwards
		rightWingModel[4].setRotationPoint(-60F, -7.6F, -7F);
		rightWingModel[4].rotateAngleX = -0.06981317F;
		rightWingModel[4].rotateAngleY = 0.06981317F;

		rightWingModel[5].addBox(0F, -1F, 0F, 10, 2, 15, 0F); // RW.RightWingCorner
		rightWingModel[5].setRotationPoint(-34F, -8.3F, -18F);
		rightWingModel[5].rotateAngleX = -0.01745329F;
		rightWingModel[5].rotateAngleY = 0.3141593F;



// Yaw Flap
		yawFlapModel = new ModelRendererTurbo[3];
		yawFlapModel[0] = new ModelRendererTurbo(this,  420,  420, textureX, textureY); // YF.JoyStick
		yawFlapModel[1] = new ModelRendererTurbo(this,  410,  420, textureX, textureY); // YF.JoyStickGrip
		yawFlapModel[2] = new ModelRendererTurbo(this,  200,  480, textureX, textureY); // YF.TailYawFlap

		yawFlapModel[0].addBox(-0.5F, -0.5F, -0.5F, 1, 11, 1, 0F); // YF.JoyStick
		yawFlapModel[0].setRotationPoint(-18F, -19F, 0F);

		yawFlapModel[1].addBox(-1F, -0.5F, -0.5F, 1, 2, 1, 0F); // YF.JoyStickGrip
		yawFlapModel[1].setRotationPoint(-18F, -19F, 0F);

		yawFlapModel[2].addBox(0F, 0F, -1F, 8, 26, 2, 0F); // YF.TailYawFlap
		yawFlapModel[2].setRotationPoint(50F, -39F, 0F);



// Pitch Flap Left
		pitchFlapLeftModel = new ModelRendererTurbo[1];
		pitchFlapLeftModel[0] = new ModelRendererTurbo(this,  200,  430, textureX, textureY); // LF.LeftTailFlap

		pitchFlapLeftModel[0].addBox(0F, -0.5F, 0F, 4, 1, 22, 0F); // LF.LeftTailFlap
		pitchFlapLeftModel[0].setRotationPoint(45F, -19F, 5F);



// Pitch Flap Right
		pitchFlapRightModel = new ModelRendererTurbo[1];
		pitchFlapRightModel[0] = new ModelRendererTurbo(this,  200,  430, textureX, textureY); // RF.RightTailFlap

		pitchFlapRightModel[0].addBox(0F, -0.5F, 0F, 4, 1, 22, 0F); // RF.RightTailFlap
		pitchFlapRightModel[0].setRotationPoint(45F, -19F, -27F);



// Pitch Flap Left Wing
		pitchFlapLeftWingModel = new ModelRendererTurbo[1];
		pitchFlapLeftWingModel[0] = new ModelRendererTurbo(this,  0,  220, textureX, textureY); // LF.LeftWingFlap

		pitchFlapLeftWingModel[0].addBox(0F, -1F, -20.5F, 2, 1, 42, 0F); // LF.LeftWingFlap
		pitchFlapLeftWingModel[0].setRotationPoint(-7.5F, -9.5F, 37F);
		pitchFlapLeftWingModel[0].rotateAngleX = 0.06981317F;
		pitchFlapLeftWingModel[0].rotateAngleY = 0.1919862F;



// Pitch Flap Right Wing
		pitchFlapRightWingModel = new ModelRendererTurbo[1];
		pitchFlapRightWingModel[0] = new ModelRendererTurbo(this,  0,  220, textureX, textureY); // RF.RightWingFlap

		pitchFlapRightWingModel[0].addBox(0F, -1F, -22.5F, 2, 1, 42, 0F); // RF.RightWingFlap
		pitchFlapRightWingModel[0].setRotationPoint(-7.5F, -9.5F, -37F);
		pitchFlapRightWingModel[0].rotateAngleX = -0.06981317F;
		pitchFlapRightWingModel[0].rotateAngleY = -0.1919862F;



// Tail Wheel
		tailWheelModel = new ModelRendererTurbo[1];
		tailWheelModel[0] = new ModelRendererTurbo(this,  0,  300, textureX, textureY); // LGT.BackWheel

		tailWheelModel[0].addBox(0F, 0F, 0F, 4, 4, 2, 0F); // LGT.BackWheel
		tailWheelModel[0].setRotationPoint(27F, -6F, -1F);



// Left Wing Wheel
		leftWingWheelModel = new ModelRendererTurbo[2];
		leftWingWheelModel[0] = new ModelRendererTurbo(this,  0,  320, textureX, textureY); // LGLW.Stick
		leftWingWheelModel[1] = new ModelRendererTurbo(this,  0,  345, textureX, textureY); // LGLW.Wheel

		leftWingWheelModel[0].addBox(0F, 0F, -1F, 3, 12, 2, 0F); // LGLW.Stick
		leftWingWheelModel[0].setRotationPoint(-27F, -8F, 26F);
		leftWingWheelModel[0].rotateAngleX = 0.03490658F;
		leftWingWheelModel[0].rotateAngleZ = -0.2617994F;

		leftWingWheelModel[1].addBox(-3F, 0F, -1.5F, 6, 6, 3, 0F); // LGLW.Wheel
		leftWingWheelModel[1].setRotationPoint(-28.5F, 3.5F, 26.5F);
		leftWingWheelModel[1].rotateAngleX = 0.03490658F;
		leftWingWheelModel[1].rotateAngleZ = -0.03490658F;



// Right Wing Wheel
		rightWingWheelModel = new ModelRendererTurbo[2];
		rightWingWheelModel[0] = new ModelRendererTurbo(this,  0,  320, textureX, textureY); // LGRW.Stick
		rightWingWheelModel[1] = new ModelRendererTurbo(this,  0,  345, textureX, textureY); // LGRW.Wheel

		rightWingWheelModel[0].addBox(0F, 0F, -1F, 3, 12, 2, 0F); // LGRW.Stick
		rightWingWheelModel[0].setRotationPoint(-27F, -8F, -26F);
		rightWingWheelModel[0].rotateAngleX = -0.03490658F;
		rightWingWheelModel[0].rotateAngleZ = -0.2617994F;

		rightWingWheelModel[1].addBox(-3F, 0F, -1.5F, 6, 6, 3, 0F); // LGRW.Wheel
		rightWingWheelModel[1].setRotationPoint(-28.5F, 3.5F, -26.5F);
		rightWingWheelModel[1].rotateAngleX = -0.03490658F;
		rightWingWheelModel[1].rotateAngleZ = -0.03490658F;



// Right Wing Position 1
		rightWingPos1Model = new ModelRendererTurbo[1];
		rightWingPos1Model[0] = new ModelRendererTurbo(this,  0,  220, textureX, textureY); // LF.LeftWingFlap

		rightWingPos1Model[0].addBox(0F, -1F, -20.5F, 2, 1, 42, 0F); // LF.LeftWingFlap
		rightWingPos1Model[0].setRotationPoint(-7.5F, -9.5F, 37F);
		rightWingPos1Model[0].rotateAngleX = 0.06981317F;
		rightWingPos1Model[0].rotateAngleY = 0.1919862F;

// Propeller
		propellerModels = new ModelRendererTurbo[0][0];
		
		

		translateAll(0, 0, 0);


		flipAll();
	}

	// Replace with your propeller function
	private ModelRendererTurbo[] makeProp(int i, int j, int k)
	{
		ModelRendererTurbo[] prop = new ModelRendererTurbo[0];
		return prop;
	}
}
