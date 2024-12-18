package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

import java.util.Locale;

public class Lift {
    private final DcMotor liftMotorLeft;
    private final DcMotor liftMotorRight;
    private final DcMotor shoulderMotor;
    private final Servo liftServoTilt;
    private final Servo rightHangServo;
    private final Servo leftHangServo;

    /**
     * Constructor for the Lift class.
     * <p>
     * Initializes the lift motors and sets their zero power behavior and run mode.
     *
     * @param liftMotorLeft  The left lift motor.
     * @param liftMotorRight The right lift motor.
     * @param liftServoTilt  The tilt lift servo.
     * @param rightHangServo The right hang servo.
     * @param leftHangServo  The left hang servo.
     */
    public Lift(DcMotor liftMotorLeft, DcMotor liftMotorRight, DcMotor shoulderMotor, Servo liftServoTilt, Servo rightHangServo, Servo leftHangServo) {
        this.liftMotorLeft = liftMotorLeft;
        this.liftMotorRight = liftMotorRight;
        this.shoulderMotor = shoulderMotor;
        this.liftServoTilt = liftServoTilt;
        this.rightHangServo = rightHangServo;
        this.leftHangServo = leftHangServo;
        this.liftMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.liftMotorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.shoulderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.liftMotorLeft.setDirection(DcMotor.Direction.REVERSE);
        this.liftMotorRight.setDirection(DcMotor.Direction.FORWARD);
        this.liftMotorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.liftMotorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.shoulderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Controls the lift motors to move the lift.
     *
     * @param speed The speed at which to run the lift motors.
     *              Positive values move the lift up, negative values move it down.
     */
    public void liftMove(double speed) {
        liftMotorLeft.setPower(speed);
        liftMotorRight.setPower(speed);
    }

    /**
     * Controls the tilting motion of the lift mechanism.
     * <p>
     * This method sets the power of the lift motor based on the provided speed.
     * It also ensures that the lift stays within its defined limits using {@link Constants#liftForwardLimit} and {@link Constants#liftBackwardLimit}.
     *
     * @param position The desired position of the lift motor.
     *              Positive values tilt the lift forward,
     *              negative values tilt it backward,
     *              and 0 stops the motor.
     */
    public void liftTilt(double position) {
        liftServoTilt.setPosition(liftServoTilt.getPosition() + position);
    }

    /**
     * Controls the movement of the shoulder motor.
     * <p>
     * This method sets the power of the shoulder motor based on the provided speed,
     * while respecting the defined forward and backward limits.
     * If the speed is positive, the motor moves forward until it reaches the forward limit.
     * If the speed is negative, the motor moves backward until it reaches the backward limit.
     * If the speed is zero, the motor stops.
     *
     * @param speed The desired speed of the shoulder motor.
     *              Positive values move the motor forward,
     *              negative values move it backward,
     *              and zero stops the motor.
     */
    public void shoulderMove(double speed) {
//        if (speed > 0) {
//            if (shoulderMotor.getCurrentPosition() < Constants.shoulderForwardLimit) {
//                shoulderMotor.setPower(speed);
//            }
//        } else if (speed < 0) {
//            if (shoulderMotor.getCurrentPosition() > Constants.shoulderBackwardLimit) {
//                shoulderMotor.setPower(speed);
//            }
//        } else {
//            shoulderMotor.setPower(0);
//        }
        shoulderMotor.setPower(speed);
    }

    // TODO: Document
    public void hang(double rightHang, double leftHang) {
        rightHangServo.setPosition(rightHang);
        leftHangServo.setPosition(leftHang);
    }

    public String getTelemetry() {
        return String.format(Locale.getDefault(), """
                Lift Motor Left: %d
                Lift Motor Right: %d
                Lift Motor Tilt: %f
                Shoulder Motor: %d""", liftMotorLeft.getCurrentPosition(), liftMotorRight.getCurrentPosition(), liftServoTilt.getPosition(), shoulderMotor.getCurrentPosition());
    }
}
