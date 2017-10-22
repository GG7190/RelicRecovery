package org.firstinspires.ftc.teamcode.RelicRecovery.Autonomous;
/**
 * Created by User on 10/8/2017.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.firstinspires.ftc.teamcode.RelicRecovery.GGCoreFunctions;
import org.firstinspires.ftc.teamcode.RelicRecovery.GGHardware;


@Autonomous(name="Test", group = "Autonomous")
public class Test extends LinearOpMode
{

    public DcMotor motor1;
    public DcMotorController motors;
    //GGHardware robot           = new GGHardware();

    @Override
    public void runOpMode() {
        //robot.init(hardwareMap);

        motor1 = hardwareMap.dcMotor.get("motor1");
        motors = hardwareMap.dcMotorController.get("motors");

        boolean finished = false;

        waitForStart();

        while (opModeIsActive()) {

            while (!finished) {

                resetEncoders();
                runWithEncoders();
                forwBackw(.75);
                runEncodersUntil(3000);
                forwBackw(0); //turns motors off
                stop();
                finished = true;

            }
        }
    }
    boolean reachedPosition = false;




    public void forwBackw(double motorPwr)
    {
        motor1.setPower(motorPwr);
    }

    /*
    public void driftRight()
    {
        robot.frontright.setPower(1);
        robot.frontleft.setPower(-1);
        robot.backright.setPower(-1);
        robot.backleft.setPower(1);
    }

    public void driftLeft()
    {
        robot.frontright.setPower(-1);
        robot.frontleft.setPower(1);
        robot.backright.setPower(1);
        robot.backleft.setPower(-1);
    }


    public void turnRight()
    {
        robot.frontright.setPower(1);
        robot.frontleft.setPower(-1);
        robot.backright.setPower(1);
        robot.backleft.setPower(-1);
    }

    public void turnLeft()
    {
        robot.frontright.setPower(-1);
        robot.frontleft.setPower(1);
        robot.backright.setPower(-1);
        robot.backleft.setPower(1);
    }
*/

    public void runEncodersUntil(int encoderAmount)
    {
        reachedPosition = false;

        while(!reachedPosition)
        {
            if(Math.abs(motor1.getCurrentPosition()) > encoderAmount)
            {
                reachedPosition = true;
            }
            else
            {

            }

        }
    }

    public void resetEncoders()
    {

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runWithEncoders()
    {
        if(motor1 != null)
        {
            motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}

