package org.firstinspires.ftc.teamcode.RelicRecovery;

/**
 * Created by User on 10/6/2017.
 */
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class GGHardware
{

    public final double PIVOT_MIN_RANGE = 0.00;
    public final double PIVOT_MID_RANGE = 0.45;
    public final double PIVOT_MAX_RANGE = 0.95;
    public final double CLAW_MIN_RANGE = 0.90;
    public final double CLAW_MID_RANGE = 0.45;
    public final double CLAW_MAX_RANGE = 0.10;

    public DcMotor frontleft, frontright, backleft, backright ,lift1, lift2, belt1, belt2;
    public DcMotorController fdrive, bdrive, lift, attatchment;
    public ServoController Servo;
    public Servo pivot, claw;
    public float x, y, z, w, pwr;
    public static double deadzone = 0.2;
    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public GGHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;
        fdrive = hwMap.dcMotorController.get("fdrive");
        frontleft = hwMap.dcMotor.get("fleft"); //3
        frontright = hwMap.dcMotor.get("fright"); //2
        bdrive = hwMap.dcMotorController.get("bdrive");
        backleft = hwMap.dcMotor.get("bleft"); //4
        backright = hwMap.dcMotor.get("bright"); //1
        Servo = hwMap.servoController.get("claw");
        pivot = hwMap.servo.get("pivot");
        //claw = hwMap.servo.get("claw");
        lift = hwMap.dcMotorController.get("lift");
        lift1 = hwMap.dcMotor.get("lift1");
        //lift2 = hwMap.dcMotor.get("lift2");
        attatchment = hwMap.dcMotorController.get("attachment");
        belt1 = hwMap.dcMotor.get("belt1");
        belt2 = hwMap.dcMotor.get("belt2");

        frontleft.setDirection(DcMotor.Direction.REVERSE);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        //lift2.setDirection(DcMotor.Direction.REVERSE);

        //lift1.setPower(0.0);
        //lift2.setPower(0.0);
    }
    boolean reachedPosition = false;



    public void forwBakw(double motorPwr)
    {
        frontright.setPower(motorPwr);
        frontleft.setPower(motorPwr);
        backright.setPower(motorPwr);
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
        frontright.setPower(1);
        frontleft.setPower(-1);
        backright.setPower(1);
        backleft.setPower(-1);
    }

    public void turnLeft()
    {
        frontright.setPower(-1);
        frontleft.setPower(1);
        backright.setPower(-1);
        backleft.setPower(1);
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
