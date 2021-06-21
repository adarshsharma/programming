package interviewBit;

public class CountUnhappyFriends1583 {

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        // Stores the friend index for each person
        int[] friends = new int[n];

        // stores the priority number for each friend for each person.
        // prefs[i][j] -> stores the priority order of j for ith person
        int[][] prefs = new int[n][n];

        for (int[] pair : pairs) {
            friends[pair[0]] = pair[1];
            friends[pair[1]] = pair[0];
        }

        for (int i = 0; i < n; i++) {
            int[] pref = preferences[i];
            for (int j = 0; j < pref.length; j++) {
                prefs[i][pref[j]] = j;
            }
        }

        int unhappyFriends = 0;
        for (int p = 0; p < n; p++) {
            // current friend of p
            int f = friends[p];

            // iterate through all friends which have higher preference than f. First fetch the
            // preference of f by prefs[p][f] then keep reducing the index to iterate all people
            // with higher preference.
            // for each of these persons, find their current friend's pref. find their preference
            // with p. if p has more preference than their current friends preference, p is unhppy.
            // increment the count and break.
            for (int j = prefs[p][f] - 1; j >= 0; j--) {
                int nf = preferences[p][j];
                int nff = friends[nf];
                int nffPref = prefs[nf][nff];
                int nfpPref = prefs[nf][p];
                if (nfpPref < nffPref) {
                    unhappyFriends++;
                    break;
                }
            }
        }

        return unhappyFriends;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] preferences = {{1, 2, 3}, {3, 2, 0}, {3, 1, 0}, {1, 2, 0}};
        int[][] pairs = {{0, 1}, {2, 3}};
        System.out.println(new CountUnhappyFriends1583().unhappyFriends(n, preferences, pairs));
    }

}
