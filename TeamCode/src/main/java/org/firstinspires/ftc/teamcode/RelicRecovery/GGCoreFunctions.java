package org.firstinspires.ftc.teamcode.RelicRecovery;

import org.firstinspires.ftc.teamcode.RelicRecovery.GGHardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.robocol.TelemetryMessage;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryInternal;

/**
 * Created by User on 10/9/2017.
 */

public class GGCoreFunctions extends GGHardware
{
    boolean reachedPosition = false;



    public void forwBakw(double motorPwr)
    {
        frontright.setPower(Range.clip(motorPwr*1.5, -1, 1));
        frontleft.setPower(motorPwr);
        backright.setPower(Range.clip(motorPwr*1.5, -1, 1));
        backleft.setPower(motorPwr);
    }


    public void driftRight()
    {
        frontright.setPower(1);
        frontleft.setPower(-1);
        backright.setPower(-1);
        backleft.setPower(1);
    }

    public void driftLeft()
    {
        frontright.setPower(-1);
        frontleft.setPower(1);
        backright.setPower(1);
        backleft.setPower(-1);
    }


    public void turnRight()
    {
        frontright.setPower(.75);
        frontleft.setPower(-.75);
        backright.setPower(.75);
        backleft.setPower(-.75);
    }

    public void turnLeft()
    {
        frontright.setPower(-.75);
        frontleft.setPower(.75);
        backright.setPower(-.75);
        backleft.setPower(.75);
    }


    public void runEncodersUntil(int encoderAmount)
    {
        reachedPosition = false;

        while(!reachedPosition)
        {
            if(Math.abs(backleft.getCurrentPosition()) > encoderAmount)
            {
                reachedPosition = true;
            }
            else
            {
                //telemetry.addData("Encoder Count", backleft.getCurrentPosition());
                //telemetry.update();
            }

        }
    }

    public void resetEncoders()
    {
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runWithEncoders()
    {
        if(backleft != null)
        {
            backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }


}

