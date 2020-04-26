package com.company.solvingAlgorithmicProblems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Task 02
 *
 * We have a JSON file and need access to all its members (keys, values) easily
 * This problem stores whole json in structured way
 */
public class JSONParser {

    public static void main(String[] args) throws Exception {
        int i, j, k;

        // овде ќе го сместиме влезниот JSON стринг
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Acer-PC\\IdeaProjects\\AdvancedAlgorithms\\src\\com\\company\\solvingAlgorithmicProblems\\data.txt"));

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
            sb.append(' '); // додаваме празно место наместо нова линија
        }

        br.close();

        String res = sb.toString();
        JSON json = new JSON(res);

        System.out.println("Тестирање:");
        System.out.println(json.jsonObject.pairs.get("\"name\"").jsonPrimitive.value);
        System.out.println(json.jsonObject.pairs.get("\"properties\"").jsonObject.pairs.get("\"stock\"").jsonObject.pairs.get("\"type\"").jsonPrimitive.value);
        System.out.println(json.jsonObject.pairs.get("\"people\"").jsonArray.array.get(2).jsonObject.pairs.get("\"name\"").jsonPrimitive.value);

    }
}

class JSON {

    JSONObject jsonObject;
    JSONPrimitive jsonPrimitive;
    JSONArray jsonArray;

    JSON(String text) {

        text = text.trim();
        if (text.charAt(0) == '{') {

            jsonObject = new JSONObject(text);
        } else if (text.charAt(0) == '[') {
            jsonArray = new JSONArray(text);
        } else {
            jsonPrimitive = new JSONPrimitive(text);
        }

    }
}

class JSONPrimitive {

    String value;

    JSONPrimitive(String text) {
        value = text.trim();
        System.out.println("Created JSONPrimitive: " + value);
    }
}

class JSONUtilities {

    static boolean isSpecialCharacter(char c) {
        if ((c == '{') || (c == '}') || (c == '[') || (c == ']') || (c == ':') || (c == ',')) {
            return true;
        }
        return false;
    }

    static int findFirstOccurence(String text, int from, char c) {
        int i;
        int N = text.length();

        for (i = from; i < N; i++) {
            if (text.charAt(i) == c) {
                return i;
            }
        }

        return -1;
    }

    static boolean isStartNestingCharacter(char c) {
        if ((c == '{') || (c == '[')) {
            return true;
        }
        return false;
    }

    static boolean isEndNestingCharacter(char c) {
        if ((c == '}') || (c == ']')) {
            return true;
        }
        return false;
    }

    static int findNextJSONText(String text) {
        int i;

        if (text.equals("")) {
            return -1;
        }

        int N = text.length();

        if (!isSpecialCharacter(text.charAt(0))) {

            for (i = 0; i < N; i++) {
                if (isSpecialCharacter(text.charAt(i))) {
                    return i;
                }
            }
            return N;
        }

        Stack<Character> s = new Stack<Character>();
        s.add(text.charAt(0));

        for (i = 1; i < N; i++) {

            if (isStartNestingCharacter(text.charAt(i))) {
                s.add(text.charAt(i));
                //System.out.println("ADDED: "+text.charAt(i));
            } else if (isEndNestingCharacter(text.charAt(i))) {
                s.pop();
                //System.out.println("REMOVED: "+text.charAt(i));
                if (s.isEmpty()) {
                    return i + 1;
                }
            }

        }

        return -1;
    }
}

class JSONObject {

    HashMap<String, JSON> pairs;

    JSONObject(String text) {

        pairs = new HashMap<String, JSON>();
        text = text.trim();

        //Get rid of outermost brackets "{" and "}"
        text = text.substring(1, text.length() - 1).trim();
        int pos = 0;

        System.out.println("Created JSONObject: " + text);

        while (true) {

            int m1 = JSONUtilities.findFirstOccurence(text, pos, ':');

            String key = text.substring(pos, m1).trim();

            //Get rid of the key we already fetched
            text = text.substring(m1 + 1).trim();
            int m2 = JSONUtilities.findNextJSONText(text);

            System.out.println("ADDED key: " + key);
            pairs.put(key, new JSON(text.substring(0, m2)));

            int m3 = JSONUtilities.findFirstOccurence(text, m2, ',');
            if (m3 == -1) {
                return;
            }

            pos = m3 + 1;
        }


    }
}

class JSONArray {

    ArrayList<JSON> array;

    JSONArray(String text) {

        array = new ArrayList<JSON>();
        text = text.trim();
        text = text.substring(1, text.length() - 1).trim();
        int pos = 0;

        System.out.println("Created JSONArray: " + text);

        while (true) {

            text = text.substring(pos + 1).trim();
            int m2 = JSONUtilities.findNextJSONText(text);

            array.add(new JSON(text.substring(0, m2)));

            int m3 = JSONUtilities.findFirstOccurence(text, m2, ',');

            if (m3 == -1) {
                return;
            }

            pos = m3 + 1;
        }


    }
}