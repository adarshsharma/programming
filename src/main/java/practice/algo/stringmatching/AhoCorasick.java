package practice.algo.stringmatching;

import java.util.*;

/**
 * Created by adarsh.sharma on 10/12/17.
 */
public class AhoCorasick {

    class Node {
        Character value;
        List<Integer> nextStates;
        Integer failState;
        List<String> outputs;
        List<Integer> outputIndexes;

        public Node() {
            nextStates = new ArrayList<>();
            failState = 0;
            outputs = new ArrayList<>();
            outputIndexes = new ArrayList<>();
        }

        public Node(Character value) {
            this();
            this.value = value;
        }
    }

    List<Node> adjList = new ArrayList<>();

    public void initTrie(List<String> keywords) {
        adjList.add(new Node());

        for (int i = 0; i < keywords.size(); i++) {
            addKeyword(i, keywords.get(i));
        }

        setFailTransitions();
    }

    private void addKeyword(Integer keywordIndex, String keyword) {
        Integer currentState = 0;
        Integer j = 0;

        Integer child = findNextState(currentState, keyword.charAt(j));

        while (child >= 0) {
            currentState = child;
            j++;
            if (j < keyword.length()) {
                child = findNextState(currentState, keyword.charAt(j));
            } else {
                break;
            }
        }

        for (int i = j; i < keyword.length(); i++) {
            Node node = new Node(keyword.charAt(i));
            adjList.add(node);
            adjList.get(currentState).nextStates.add(adjList.size() - 1);
            currentState = adjList.size() - 1;
        }

        adjList.get(currentState).outputs.add(keyword);
        adjList.get(currentState).outputIndexes.add(keywordIndex);
    }

    private void setFailTransitions() {
        Queue<Integer> queue = new LinkedList<>();

        for (Integer nextState : adjList.get(0).nextStates) {
            queue.add(nextState);
            adjList.get(nextState).failState = 0;
        }

        while (queue.size() > 0) {
            Integer r = queue.remove();
            for(Integer child: adjList.get(r).nextStates) {
                queue.add(child);
                Integer state = adjList.get(r).failState;

                while(findNextState(state, adjList.get(child).value) == -1 && state != 0) {
                    state = adjList.get(state).failState;
                }
                adjList.get(child).failState = findNextState(state, adjList.get(child).value);

                if(adjList.get(child).failState == -1) {
                    adjList.get(child).failState = 0;
                }

                adjList.get(child).outputs.addAll(adjList.get(adjList.get(child).failState).outputs);
                adjList.get(child).outputIndexes.addAll(adjList.get(adjList.get(child).failState).outputIndexes);
            }
        }

    }

    private Integer findNextState(Integer currentState, Character value) {
        for (Integer nextState : adjList.get(currentState).nextStates) {
            if (adjList.get(nextState).value == value) {
                return nextState;
            }
        }

        return -1;
    }

    public List<Integer> getKeywordFound(String d, int first, int last) {
        Integer currentState = 0;
        List<Integer> keywordFoundIndexes = new ArrayList<>();

        for(int i=0;i<d.length();i++) {
            while(findNextState(currentState, d.charAt(i)) == -1 && currentState != 0) {
                currentState = adjList.get(currentState).failState;
            }
            currentState = findNextState(currentState, d.charAt(i));

            if(currentState == -1) {
                currentState = 0;
            } else {
                for(int j: adjList.get(currentState).outputIndexes) {
                    if(j >= first && j <= last) {
                        keywordFoundIndexes.add(j);
                    }
                }
            }
        }

        return keywordFoundIndexes;
    }

    public static void main(String[] args) {
        AhoCorasick ahoCorasick = new AhoCorasick();
        ahoCorasick.initTrie(Arrays.asList("a", "ab", "bc", "bca", "c", "caa"));
        System.out.println("generation done");
    }
}
