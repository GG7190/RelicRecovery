package org.firstinspires.ftc.teamcode.RelicRecovery.Teleop;

        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorController;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.hardware.ServoController;
        import com.qualcomm.robotcore.util.Range;

        import org.firstinspires.ftc.teamcode.RelicRecovery.GGHardware;


@TeleOp(name="MTeleOp",group="TeleOp")
public class MechDrive extends LinearOpMode {

    GGHardware robot           = new GGHardware();

    @Override
    public void runOpMode(){
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            getJoyVals();

            robot.pwr = robot.y; //this can be tweaked for exponential power increase

            robot.frontright.setPower(Range.clip(robot.pwr - robot.x + robot.z, -1, 1));
            robot.backleft.setPower(Range.clip(robot.pwr - robot.x - robot.z, -1, 1));
            robot.frontleft.setPower(Range.clip(robot.pwr + robot.x - robot.z, -1, 1));
            robot.backright.setPower(Range.clip(robot.pwr + robot.x + robot.z, -1, 1));


            if (gamepad2.x) {
                robot.claw1.setPosition(robot.CLAW1_MAX_RANGE);
                robot.claw2.setPosition(robot.CLAW2_MAX_RANGE);

            }
            if (gamepad2.b) {
                robot.claw1.setPosition(robot.CLAW1_MIN_RANGE);
                robot.claw2.setPosition(robot.CLAW2_MIN_RANGE);

            }
            if (gamepad2.y) {
                robot.lift1.setPower(.5);
                //lift2.setPower(.9);
            }
            if (gamepad2.a) {
                robot.lift1.setPower(-.5);
                //lift2.setPower(-.9);
            }
            else
            {
                robot.lift1.setPower(0);
            }
        }
    }

public void getJoyVals() {
    float pwrFactor = (float)1;
    float pwrFactorY = (float)0.5;
    robot.y = pwrFactorY * gamepad1.right_stick_y; // joyval = -1 to 1; forward right joy 0 = stop -1 = reverse 1 = forward
    robot.x = pwrFactor * gamepad1.right_stick_x; //-1 to 1
    robot.z = pwrFactor * gamepad1.left_stick_x;  //-1 to 1
    robot.w = pwrFactor * gamepad1.left_stick_y;  //-1 to 1
        //updates joystick values

        if (Math.abs(robot.x) < robot.deadzone) robot.x = 0;
        if (Math.abs(robot.y) < robot.deadzone) robot.y = 0;
        if (Math.abs(robot.z) < robot.deadzone) robot.z = 0;
        if (Math.abs(robot.w) < 0.9) robot.w = 0;
        //checks deadzones
        }

}