package leetcode;

public class NumberOfFullRounds5789 {

    public int numberOfRounds(String startTime, String finishTime) {
        String[] str = startTime.split(":");
        int sth = Integer.parseInt(str[0]);
        int stm = Integer.parseInt(str[1]);

        str = finishTime.split(":");
        int fth = Integer.parseInt(str[0]);
        int ftm = Integer.parseInt(str[1]);

        int m = stm % 15;
        if (m > 0) {
            stm += (15 - m);
            if (stm == 60) {
                stm = 0;
                sth++;
                if (sth == 24) {
                    sth = 0;
                }
            }
        }

        boolean overnight = (sth > fth) || (sth == fth && stm > ftm);

        if (overnight) {
            fth += 24;
        }

        m = ftm % 15;
        ftm -= m;

        return fth * 4 + (ftm / 15) - (sth * 4 + stm / 15);

    }

    public static void main(String[] args) {
        System.out.println(new NumberOfFullRounds5789().numberOfRounds("04:54",
            "18:51"));
    }
}
