package practice.algo.xmlParser;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

/**
 * Created by adarsh.sharma on 01/12/17.
 */
public class XMLParser {
    Document document;
    static int sNo = 0;

    class Node {
        Integer id;
        String tag;
        Map<String, String> attributeMap;
        String text;
        List<Node> elements;
        boolean isComment;

        public Node() {
            id = sNo + 1;
            sNo++;
            attributeMap = new HashMap<>();
            elements = new ArrayList<>();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) {
                return false;
            }
            Node other = (Node) obj;

            if (!StringUtils.equals(tag, other.tag)) {
                return false;
            }
            if (!StringUtils.equals(text, other.text)) {
                return false;
            }

            if (!attributeMap.equals(other.attributeMap)) {
                return false;
            }

            if (isComment != other.isComment) {
                return false;
            }

            if (elements.size() != other.elements.size()) {
                return false;
            } else if (elements.size() > 0 && !elements.equals(other.elements)) {
                return false;
            }

            return true;
        }
    }

    class Document {
        Node baseNode;
        Map<String, String> xmlProperties;

        public Document() {
            xmlProperties = new HashMap<>();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Document)) {
                return false;
            }
            Document other = (Document) obj;
            if (!xmlProperties.equals(other.xmlProperties)) {
                return false;
            }

            if (!baseNode.equals(other.baseNode)) {
                return false;
            }

            return true;
        }
    }

    public XMLParser() {
        this.document = new Document();
    }

    class LineParser {
        FileInputStream fis;
        Character prev;

        public LineParser(FileInputStream fis) {
            this.fis = fis;
        }

        public String getLine() throws IOException {
            String cur = "";
            Character first = null;

            if (prev == null) {
                while (first == null && fis.available() > 0) {
                    char c = (char) fis.read();
                    if (c != ' ' && c != '\n') {
                        first = c;
                        cur += c;
                        break;
                    }
                }
            } else {
                first = prev;
                cur += prev;
                prev = null;
            }

            while (fis.available() > 0) {
                char c = (char) fis.read();
                if (first == '<') {
                    cur += c;
                    if (c == '>') {
                        break;
                    }
                } else {
                    if (c != '<') {
                        cur += c;
                    } else {
                        prev = '<';
                        break;
                    }
                }
            }

            if (fis.available() > 0) {
                return cur;
            } else {
                return null;
            }
        }
    }

    enum TAG {
        XML,
        STARTED,
        ENDED,
        TEXT,
        COMMENT,
    }

    public Document parseFormattedXMLFile(String filePath) {
        Stack<Node> nodeStack = new Stack<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                sCurrentLine = sCurrentLine.trim();
                while (sCurrentLine.length() > 0) {
                    Node node = new Node();
                    switch (tagType(sCurrentLine)) {
                        case XML:
                            sCurrentLine = sCurrentLine.substring(2);
                            sCurrentLine = getNode(sCurrentLine, node);
                            document.xmlProperties.putAll(node.attributeMap);
                            break;

                        case COMMENT:
                            if (sCurrentLine.startsWith("<!--<")) {
                                sCurrentLine = sCurrentLine.replace("<!--", "");
                            } else {
                                sCurrentLine = sCurrentLine.replace("<!-", "");
                            }
                            node.isComment = true;
                        case STARTED:
                            sCurrentLine = sCurrentLine.substring(1);
                            sCurrentLine = getNode(sCurrentLine, node);
                            if (nodeStack.size() == 0) {
                                document.baseNode = node;
                            } else {
                                nodeStack.peek().elements.add(node);
                            }
                            nodeStack.push(node);
                            break;

                        case ENDED:
                            sCurrentLine = sCurrentLine.substring(2);
                            sCurrentLine = sCurrentLine.trim();
                            int firstEnd = sCurrentLine.indexOf(">");
                            String tag = sCurrentLine.substring(0, firstEnd);
                            sCurrentLine = sCurrentLine.substring(firstEnd + 1);
                            Node pop = nodeStack.pop();
                            if (!pop.tag.equals(tag) && !(pop.tag + "--").equals(tag)) {
                                throw new RuntimeException("wrong closing tag");
                            }
                            if (pop.isComment) {
                                sCurrentLine = sCurrentLine.replace("-->", "");
                                sCurrentLine = sCurrentLine.replace("--", "");
                            }
                            break;

                        case TEXT:
                            sCurrentLine = sCurrentLine.trim();
                            int firstStart = sCurrentLine.indexOf("<");
                            nodeStack.peek().text = sCurrentLine.substring(0, firstStart);
                            sCurrentLine = sCurrentLine.substring(firstStart);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("exception: " + e.getMessage());
        }


        return document;
    }

    public Document parseUnformattedXMLFile(String filePath) {
        Stack<Node> nodeStack = new Stack<>();

        try (FileInputStream fis = new FileInputStream(filePath)) {
            String sCurrentLine = "";
            LineParser lineParser = new LineParser(fis);

            while ((sCurrentLine = lineParser.getLine()) != null) {

                while (sCurrentLine.length() > 0) {
                    Node node = new Node();
                    switch (tagType(sCurrentLine)) {
                        case XML:
                            sCurrentLine = sCurrentLine.substring(2);
                            sCurrentLine = getNode(sCurrentLine, node);
                            document.xmlProperties.putAll(node.attributeMap);
                            break;

                        case COMMENT:
                            if (sCurrentLine.startsWith("<!--<")) {
                                sCurrentLine = sCurrentLine.replace("<!--", "");
                            } else {
                                sCurrentLine = sCurrentLine.replace("<!-", "");
                            }
                            node.isComment = true;
                        case STARTED:
                            sCurrentLine = sCurrentLine.substring(1);
                            sCurrentLine = getNode(sCurrentLine, node);
                            if (nodeStack.size() == 0) {
                                document.baseNode = node;
                            } else {
                                nodeStack.peek().elements.add(node);
                            }
                            nodeStack.push(node);
                            break;

                        case ENDED:
                            sCurrentLine = sCurrentLine.substring(2);
                            sCurrentLine = sCurrentLine.trim();
                            int firstEnd = sCurrentLine.indexOf(">");
                            String tag = sCurrentLine.substring(0, firstEnd);
                            sCurrentLine = sCurrentLine.substring(firstEnd + 1);
                            Node pop = nodeStack.pop();
                            if (!pop.tag.equals(tag) && !(pop.tag + "--").equals(tag)) {
                                throw new RuntimeException("wrong closing tag");
                            }
                            if (pop.isComment) {
                                sCurrentLine = sCurrentLine.replace("-->", "");
                                sCurrentLine = sCurrentLine.replace("--", "");
                            }
                            break;

                        case TEXT:
                            sCurrentLine = sCurrentLine.trim();
                            int firstStart = sCurrentLine.indexOf("<");
                            if (firstStart > 0) {
                                nodeStack.peek().text = sCurrentLine.substring(0, firstStart);
                                sCurrentLine = sCurrentLine.substring(firstStart);
                            } else {
                                nodeStack.peek().text = sCurrentLine;
                                sCurrentLine = "";
                            }
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("exception: " + e.getMessage());
        }


        return document;
    }

    private String getNode(String sCurrentLine, Node node) {
        sCurrentLine = sCurrentLine.trim();

        int firstSpace = sCurrentLine.indexOf(" ");
        int firstClose = sCurrentLine.indexOf(">");

        String tag = sCurrentLine.substring(0, firstClose);
        String attrString = sCurrentLine.substring(firstClose + 1).trim();

        if (firstSpace > 0 && firstSpace < firstClose) {
            tag = sCurrentLine.substring(0, firstSpace);
            attrString = sCurrentLine.substring(firstSpace + 1).trim();
        }
        node.tag = tag;

        if (firstSpace > 0 && firstSpace < firstClose) {
            while (attrString.length() > 0 && attrString.charAt(0) != '?' && attrString.charAt(0) != '>') {
                int equalTo = attrString.indexOf("=");
                String attrName = attrString.substring(0, equalTo).trim();
                attrString = attrString.substring(equalTo + 1).trim();

                char start = attrString.charAt(0);
                attrString = attrString.substring(1);
                int quote = attrString.indexOf(start);
                String attrValue = attrString.substring(0, quote);

                node.attributeMap.put(attrName, attrValue);
                attrString = attrString.substring(quote + 1).trim();
            }

            while (attrString.length() > 0 && (attrString.charAt(0) == '?' || attrString.charAt(0) == '>')) {
                attrString = attrString.substring(1);
            }
        }

        return attrString;
    }

    private TAG tagType(String sCurrentLine) {
        if (sCurrentLine.length() > 1) {
            if (sCurrentLine.startsWith("</")) {
                return TAG.ENDED;
            } else if (sCurrentLine.startsWith("<?xml")) {
                return TAG.XML;
            } else if (sCurrentLine.startsWith("<!--")) {
                return TAG.COMMENT;
            } else if (sCurrentLine.startsWith("<")) {
                return TAG.STARTED;
            } else {
                return TAG.TEXT;
            }
        }

        throw new RuntimeException("failed");
    }

    public void writeToFile(String filePath) throws IOException {
        Set<Integer> visitedNodes = new HashSet<>();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))
        ) {
            bw.write("<?xml" + writeAttributeMap(document.xmlProperties) + "?>\n");
            Stack<Node> stk = new Stack<>();
            if (document.baseNode != null) {
                stk.push(document.baseNode);
                visitedNodes.add(document.baseNode.id);
                writeStartNodeTag(bw, document.baseNode, 0);

                while (stk.size() > 0) {
                    Node node = stk.peek();

                    boolean found = false;
                    for (Node n : node.elements) {
                        if (!visitedNodes.contains(n.id)) {
                            visitedNodes.add(n.id);
                            writeStartNodeTag(bw, n, stk.size());
                            stk.push(n);
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        continue;
                    }

                    writeEndNodeTag(bw, node, stk.size() - 1);
                    stk.pop();
                }
            }
        }
    }

    private void writeEndNodeTag(BufferedWriter bw, Node node, int tabs) throws IOException {
        String str = "";
        if (node.elements.size() == 0) {
            str += node.text;
        } else {
            while (tabs > 0) {
                str += "\t";
                tabs--;
            }
        }
        str += "</" + node.tag + ">";
        if (node.isComment) {
            str += "-->";
        }
        str += "\n";

        bw.write(str);
    }

    private void writeStartNodeTag(BufferedWriter bw, Node node, int tabs) throws IOException {
        String str = "";
        while (tabs > 0) {
            str += "\t";
            tabs--;
        }

        if (node.isComment) {
            str += "<!--";
        }
        str += "<" + node.tag + writeAttributeMap(node.attributeMap) + ">";
        if (node.elements.size() > 0) {
            str += "\n";
        }
        bw.write(str);
    }

    private String writeAttributeMap(Map<String, String> xmlProperties) {
        String str = "";
        for (String key : xmlProperties.keySet()) {
            str += " " + key + " = \"" + xmlProperties.get(key) + "\"";
        }

        return str;
    }

    public static void main(String[] args) throws IOException {
        XMLParser xmlParser1 = new XMLParser();
        Document document1 = xmlParser1.parseFormattedXMLFile("/Users/adarsh.sharma/code/test/src/main/java/practice.algo/xmlParser/sample1.xml");

//        XMLParser xmlParser2 = new XMLParser();
//        Document document2 = xmlParser2.parseUnformattedXMLFile("/Users/adarsh.sharma/code/test/src/main/java/practice.algo/xmlParser/sample1_nonewline.xml");

        xmlParser1.writeToFile("/Users/adarsh.sharma/code/test/src/main/java/practice.algo/xmlParser/sample1_generated.xml");

        System.out.println("done");
    }
}
