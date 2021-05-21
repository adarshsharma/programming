package leetcode;

/**
 * Created by adarsh.sharma on 29/07/18.
 */
public class ReadNCharacters {
    char[] read = new char[4];
    int k = 0;
    String str;
    int idx;

    public ReadNCharacters(String str) {
        this.str = str;
        this.idx = 0;
    }

    public int read(char[] buf, int n) {
        int readCount = 0;
        int i = 0;
        if(k>0) {
            int min = Math.min(n, k);
            for(; i< min; i++) {
                buf[i] = read[i];
                readCount++;
            }
            while(i<k) {
                read[i-n] = read[i];
                i++;
            }
            k = k - min;
            n = n - min;
        }

        if(n>0) {
            char[] temp = new char[4];
            while(n/4>=1) {
                int c = read4(temp);
                System.arraycopy(temp, 0, buf, i, c);
                readCount+=c;
                if(c<4) {
                    return readCount;
                } else {
                    i+=4;
                    n-=4;
                }
            }

            if(n>0) {
                int c = read4(temp);
                if(c>0) {
                    System.arraycopy(temp, 0, buf, i, Math.min(n, c));
                    readCount+=Math.min(n, c);
                    System.arraycopy(temp, Math.min(n, c), read, 0, c - Math.min(n, c));
                    k = c - Math.min(n, c);
                }
            }

        }

        return readCount;
    }

    private int read4(char[] temp) {
        if(idx>=str.length()) {
            return 0;
        }
        String s = str.substring(idx, 4);
        idx+=s.length();
        System.arraycopy(s.toCharArray(), 0, temp, 0, s.length());
        return s.length();
    }

    public static void main(String[] args) {
        ReadNCharacters r = new ReadNCharacters("abcde");
        char[] ch = new char[1];
        r.read(ch, 1);
        System.out.println(ch);
        r.read(ch, 1);
        System.out.println(ch);
        r.read(ch, 1);
        System.out.println(ch);
        r.read(ch, 1);
        System.out.println(ch);
        r.read(ch, 1);
        System.out.println(ch);
        r.read(ch, 1);
        System.out.println(ch);
    }
}
