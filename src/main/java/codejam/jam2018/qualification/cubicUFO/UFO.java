package codejam.jam2018.qualification.cubicUFO;

/**
 * Created by adarsh.sharma on 07/04/18.
 */
public class UFO {
    public static void main(String[] args) {
//        double cos = .5*Math.cos(Math.toRadians(45));
//        System.out.println(cos);

//        double A = 1.414213;
//        double v = A*A-1;

//        double angleTwo = Math.asin(v);
//        System.out.println(Math.sin(angleTwo));
//        System.out.println(Math.toDegrees(angleTwo));
//        angle = Math.PI/4;
//        double angle = angleTwo/2;
//
//        System.out.println(0.5*Math.cos(angle)+ ","  + 0.5*Math.sin(angle) + "," + 0);
//        System.out.println(-0.5*Math.sin(angle)+ ","  + 0.5*Math.cos(angle) + "," + 0);
//        System.out.println("0, 0, " + 0.5);

        double A = 1.4142130000;
        double angle = getAngle(A, 0.0000000001);
        System.out.println(Math.toDegrees(angle));
        System.out.println(Math.sin(angle) + Math.cos(angle));
        System.out.println(A);

        System.out.println(Math.sin(Math.toRadians(45)) - Math.sin(Math.toRadians(44.94890391826629)));

        System.out.println(0.5*Math.cos(angle)+ ","  + 0.5*Math.sin(angle) + "," + 0);
        System.out.println(-0.5*Math.sin(angle)+ ","  + 0.5*Math.cos(angle) + "," + 0);
        System.out.println("0, 0, " + 0.5);

//        double A = (1.4142130*1.4142130) - 1;
//        double angle = getSinAngle(A, 0.0000000001)/2;
//        System.out.println(Math.toDegrees(angle));
//
//        System.out.println(Math.sin(Math.toRadians(45)) - Math.sin(angle));
//
//        System.out.println(0.5*Math.cos(angle)+ ","  + 0.5*Math.sin(angle) + "," + 0);
//        System.out.println(-0.5*Math.sin(angle)+ ","  + 0.5*Math.cos(angle) + "," + 0);
//        System.out.println("0, 0, " + 0.5);
    }

    private static double getAngle(double a, double prec) {
        double start = 0;
        double end = Math.PI / 4;
        double angle = (end - start) / 2;
        double p = angle / 2;

        double c = Math.cos(angle) + Math.sin(angle);
        double diff = c - a;

        while (Math.abs(diff) > prec) {
            if (diff < 0) {
                angle += p;
            } else {
                angle -= p;
            }
            p/=2;
            c = Math.cos(angle) + Math.sin(angle);
            diff = c - a;
        }

        return angle;
    }

    private static double getSinAngle(double a, double prec) {
        double start = 0;
        double end = Math.PI / 4;
        double angle = (end - start) / 2;
        double p = angle / 2;

        double c = Math.sin(angle);
        double diff = c - a;

        while (Math.abs(diff) > prec) {
            if (diff < 0) {
                angle += p;
            } else {
                angle -= p;
            }
            p/=2;
            c = Math.sin(angle);
            diff = c - a;
        }

        return angle;
    }
}
