package com.example.demo.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Train
 */
public class Train {

    private String[] components = { "(", ")", "@", "O", "=", "D", "_", "|", "/", "\\", "I", "H", "-", "[", "]", "A",
            "Y", "~", " " };
    private List<HashMap<Integer, String[]>> lineLsit = new ArrayList<>();

    public void paint(int fnum) {
        StringBuffer buff = new StringBuffer();
        for (HashMap<Integer, String[]> line : lineLsit) {
            List<Integer> lineNum = new ArrayList<>();
            for (int i = 0; i < 200; i++) {
                lineNum.add(18);
            }
            if (line != null) {
                for (Object key : line.keySet()) {
                    for (String numStr : line.get(key)) {
                        if (numStr != null && numStr.contains("-")) {
                            String[] temp = numStr.split("-");
                            for (int i = Integer.valueOf(temp[0]); i <= Integer.valueOf(temp[1]); i++) {
                                lineNum.set(i + fnum, (Integer) key);
                            }
                        } else if (numStr != null) {
                            lineNum.set(Integer.valueOf(numStr) + fnum, (Integer) key);
                        }
                    }
                }
            }
            int i = 0;
            for (Integer num : lineNum) {
                // if(num != 18){
                // System.out.print(i + ":" + num + " ");
                // }
                // i++;
                // System.out.print(components[num]);
                buff.append(components[num]);
            }
            buff.append("\n");
            // System.out.println();
        }
        System.out.println(buff.toString());
    }

    public void clear() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            buffer.append("\n");
        }
        System.out.println(buffer.toString());

        // System.out.print("\033[H\033[2J");  
        // System.out.flush();  
    }

    public void move() throws IOException, InterruptedException {
        for (int i = 100; i > 20; i = i - 5) {
            
            clear();
            // Runtime.getRuntime().exec("cls");
            initTrain();
            paint(i);
            Thread.sleep(100);
            clear();
            // Runtime.getRuntime().exec("cls");
            setBottom();
            paint(i);
            Thread.sleep(100);
            clear();
            initTrain();
            setRight();
            paint(i);
            Thread.sleep(100);
        }
    }


    public void setBottom() {
        HashMap<Integer, String[]> line15 = lineLsit.get(14);
        HashMap<Integer, String[]> line16 = lineLsit.get(15);
        String[] cp8ps = line16.get(6);
        List<String> temp3List = new ArrayList<>();
        List<String> temp4List = new ArrayList<>();
        for (String colNum : Arrays.copyOfRange(cp8ps, 1, 5)) {
            if (colNum.contains("-")) {
                String start = colNum.split("-")[0];
                temp3List.add(start);
            }
        }
        for (int i = 0; i < temp3List.size() - 1; i++) {
            temp4List.add((Integer.valueOf(temp3List.get(i)) + 1) + "-" + (Integer.valueOf(temp3List.get(i + 1)) - 1));
        }

        line15.remove(3);
        line15.put(4, Arrays.copyOfRange(line15.get(4), 0, 2));
        line15.put(7, concat(concat(line15.get(7),Arrays.copyOfRange(line16.get(8), 1, 5)),Arrays.copyOfRange(line16.get(9), 1, 5)));
        line16.put(3, temp3List.toArray(new String[0]));
        line16.put(4, temp4List.toArray(new String[0]));
        line16.put(6, concat(Arrays.copyOfRange(line16.get(6), 0, 1), Arrays.copyOfRange(line16.get(6), 5, 10)));
        line16.put(8, concat(Arrays.copyOfRange(line16.get(8), 0, 1), Arrays.copyOfRange(line16.get(8), 4, 10)));
        line16.put(9, concat(Arrays.copyOfRange(line16.get(9), 0, 2), Arrays.copyOfRange(line16.get(9), 6, 10)));
        lineLsit.set(15, line16);
        lineLsit.set(14, line15);
    }

    public void setRight(){
        HashMap<Integer, String[]> line15 = lineLsit.get(14);
        HashMap<Integer, String[]> line16 = lineLsit.get(15);
        String[] cp4ps = line15.get(4);
        String[] temp3Array = Arrays.copyOfRange(line16.get(8), 1, 5);
        List<String> temp4List = new ArrayList<>();
        for (int i = 0; i < temp3Array.length - 1; i++) {
            // temp4List.add((Integer.valueOf(temp3Array[i]) + 1) + "-" + (Integer.valueOf(temp3Array[i]) - 1));
            Arrays.fill(cp4ps, 2+i, 3+i, (Integer.valueOf(temp3Array[i]) + 1) + "-" + (Integer.valueOf(temp3Array[i+1]) - 1));
        }

        // Arrays.fill(cp4ps, 2, 5, temp4List.toArray(new String[0]));
        line15.put(3, temp3Array);
        line15.put(4, cp4ps);

    }

    private <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public void initTrain() {
        lineLsit.clear();
        // line1
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(0, new String[] { "24", "30", "36", "42", "48" });
                put(1, new String[] { "27", "33", "38", "44", "49" });
                put(2, new String[] { "31-32", "43", "55", "56", "67", "79" });
                put(3, new String[] { "61", "73", "84" });
            }
        });
        // line2
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(0, new String[] { "19" });
                put(1, new String[] { "23" });
                put(2, new String[] { "20-22" });
            }
        });
        // line3
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(0, new String[] { "14" });
                put(1, new String[] { "20" });
            }
        });
        // line4
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(0, new String[] { "10" });
                put(1, new String[] { "16" });
            }
        });
        // line5
        lineLsit.add(null);
        // line6
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(0, new String[] { "8" });
                put(1, new String[] { "13" });
            }
        });
        // line7
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(4, new String[] { "5-8" });
                put(6, new String[] { "17-25", "44-57" });
            }
        });
        // line8
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(6, new String[] { "1", "4", "9-15", "27-29", "33-39", "43-44", "46-55" });
                put(5, new String[] { "2" });
                put(7, new String[] { "5", "8", "45", "56" });
                put(8, new String[] { "16" });
                put(9, new String[] { "26" });
                put(10, new String[] { "30", "32" });
                put(4, new String[] { "40-42" });
            }
        });
        // line9
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(7, new String[] { "2", "11", "30", "32", "45", "54" });
                put(0, new String[] { "3" });
                put(6, new String[] { "4", "17-25", "46-48", "50-52", "61-79" });
                put(1, new String[] { "5" });
                put(12, new String[] { "6-8" });
                put(11, new String[] { "15" });
                put(9, new String[] { "16" });
                put(8, new String[] { "26" });
                put(4, new String[] { "44" });
            }
        });
        // line10
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(7, new String[] { "8", "11", "18", "21", "30", "32", "45", "46", "48", "50", "52", "54", "61" });
                put(11, new String[] { "15" });
                put(9, new String[] { "80" });
                put(6, new String[] { "47", "51", "60", "81-86" });
                put(15, new String[] { "87" });
                put(8, new String[] { "2" });
            }
        });
        // line11
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(7, new String[] { "2", "8", "11", "18", "45", "54", "59", "87" });
                put(11, new String[] { "15" });
                put(6, new String[] { "19-20", "48-50" });
                put(12, new String[] { "21-44" });
                put(13, new String[] { "47" });
                put(14, new String[] { "51" });
                put(4, new String[] { "58" });
            }
        });
        // line12
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(7, new String[] { "2", "11", "21", "45", "54", "59", "87" });
                put(6, new String[] { "3-10", "12-14", "16-17", "19-20", "22-27", "35-44" });
                put(11, new String[] { "15" });
                put(8, new String[] { "18", "28" });
                put(13, new String[] { "29", "31" });
                put(14, new String[] { "30", "32" });
                put(17, new String[] { "33" });
                put(9, new String[] { "34" });
                put(12, new String[] { "58" });
            }
        });
        // line13
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(7, new String[] { "2", "5", "9", "45", "54", "59", "87" });
                put(8, new String[] { "3" });
                put(12, new String[] { "10-20" });
                put(10, new String[] { "21", "27" });
                put(6, new String[] { "22-26", "55-58", "60-86", "88" });
                put(13, new String[] { "29", "31", "34" });
                put(14, new String[] { "30", "32", "35" });
                put(5, new String[] { "38" });
                put(4, new String[] { "46-53" });
            }
        });
        // line14
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(8, new String[] { "2", "17", "23", "29" });
                put(4, new String[] { "4", "10" });
                put(7, new String[] { "5", "9", "54", "57", "87" });
                put(3, new String[] { "7" });
                put(12, new String[] { "11" });
                put(17, new String[] { "12-13", "18-19", "24-25", "30-31" });
                put(9, new String[] { "14", "20", "26", "32" });
                put(6, new String[] { "0-1", "34-37", "39-53", "55-56", "58-86", "88" });
                put(16, new String[] { "38" });
            }
        });
        // line15
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(7, new String[] { "1", "5", "9", "33", "60", "70", "73", "83" });
                put(8, new String[] { "2", "39", "45" });
                put(12, new String[] { "3" });
                put(4, new String[] { "4", "10", "12-16", "18-22", "24-28" });
                put(6, new String[] { "6-8", "34-38", "42-44", "61", "63-64", "66-67", "69", "74", "76-77", "79-80",
                        "82" });
                put(3, new String[] { "11", "17", "23", "29" });
                put(17, new String[] { "40" });
                put(9, new String[] { "41" });
                put(5, new String[] { "62", "65", "68", "75", "78", "81" });
            }
        });
        // line16
        lineLsit.add(new HashMap<Integer, String[]>() {
            private static final long serialVersionUID = 1L;

            {
                put(9, new String[] { "2", "11", "17", "23", "29", "39", "61", "67", "74", "80" });
                put(6, new String[] { "3", "12-13", "18-19", "24-25", "30-31", "40", "62", "68", "75", "81" });
                put(8, new String[] { "4", "14", "20", "26", "32", "41", "63", "69", "76", "82" });
            }
        });
    }
}
