package com.zhangwy.sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@SuppressWarnings({"unused", "FieldCanBeLocal", "WeakerAccess", "SameParameterValue"})
public class Statistics {
    private static long trackTimeStart = 0;
    private static long trackTimeEnd = 0;
    private static final String TAG = "BaseMethodHook";
    private static final String METHOD_AFTER = "afterHookedMethod";
    private static final String METHOD_BEFORE = "beforeHookedMethod";
    private static final String METHOD_MESSAGE_AFTER = "afterMessageHookedMethod";
    private static final String METHOD_MESSAGE_BEFORE = "beforeMessageHookedMethod";
    private static final String METHOD_STACKTRACE = "StackTraceHookedMethod";
    private static final String TRACK_START = "@trackStart@";
    private static final String TRACK_END = "@trackEnd@";
    private static HashMap<Integer, TrackMessage> trackMessages = new HashMap<>();
    private static HashMap<String, HashCountMap> hashCountMap = new HashMap<>();
    private static Writer mWriter;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("输入文件名&回车:");
        String path = reader.readLine();
        System.out.print("正在分析数据...");
        System.out.println("分析结果：");
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        File outFile = new File(file.getParent(), TimeUtil.getCurrentDate(TimeUtil.PATTERN_S) + ".txt");
        read(file);
        openFile(outFile);
        String trackTimeSStart = TimeUtil.dateMilliSecond2String(trackTimeStart, TimeUtil.PATTERN_DATE);
        String trackTimeSEnd = TimeUtil.dateMilliSecond2String(trackTimeEnd, TimeUtil.PATTERN_DATE);
        long timeLong = trackTimeEnd - trackTimeStart;
        String timeString = TimeUtil.dateMilliSecond2String(timeLong, TimeUtil.PATTERN_TIME);
        println(String.format("时间：%s--%s = %s", trackTimeSStart, trackTimeSEnd, timeString));
        for (String key: hashCountMap.keySet()) {
            if (key != null && hashCountMap.containsKey(key)) {
                println(key);
                println(hashCountMap.get(key).toString());
            }
        }

        List<TrackMessage> values = new ArrayList<>(trackMessages.values());
        Collections.sort(values);
        for (TrackMessage message : values) {
            print("hashCode：");
            print(String.valueOf(message.hashCode()));
            print(" |》|》|》|》count：");
            println(String.valueOf(message.number));
            println(message.getMessage());
        }
        System.out.println("分析结果End");
        closeFile();
    }

    private static void openFile(File file) {
        try {
            mWriter = new BufferedWriter(new FileWriter(file, true), 1024);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void print(String string) {
        System.out.print(string);
        if (mWriter == null) {
            return;
        }
        try {
            mWriter.write(string);
            mWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void println(String string) {
        System.out.println(string);
        if (mWriter == null) {
            return;
        }
        try {
            mWriter.write(string);
            mWriter.write("\n");
            mWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void closeFile() {
        if (mWriter == null) {
            return;
        }
        try {
            mWriter.flush();
            mWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void read(File file) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);//通过字节流获取
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split("#");//0:time;1:type;2:tag;3:methodKey;4:method
                if (array.length != 5) {
                    continue;
                }
                String time = array[0];
                long timeLong = TimeUtil.dateString2Long(time, TimeUtil.PATTERN_DATE);
                if (trackTimeStart == 0) {
                    trackTimeStart = timeLong;
                } else if (trackTimeStart > timeLong) {
                    trackTimeStart = timeLong;
                }
                if (trackTimeEnd == 0) {
                    trackTimeEnd = timeLong;
                } else if (trackTimeEnd < timeLong) {
                    trackTimeEnd = timeLong;
                }

                switch (array[3]) {
                    case METHOD_STACKTRACE: {
                        if (TRACK_START.equals(array[4])) {
                            readTrack(br);
                        }
                        break;
                    }
                    case METHOD_AFTER:
                    case METHOD_BEFORE:
                    case METHOD_MESSAGE_AFTER:
                    case METHOD_MESSAGE_BEFORE: {
                        if (!hashCountMap.containsKey(array[3])) {
                            hashCountMap.put(array[3], new HashCountMap());
                        }
                        HashCountMap countMap = hashCountMap.get(array[3]);
                        countMap.plus(array[4]);
                        break;
                    }
                }

            }
            br.close();
            isr.close();
            fis.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                //
            }
        }
    }

    private static void readTrack(BufferedReader reader) throws IOException {
        String line;
        TrackMessage message = new TrackMessage();
        while ((line = reader.readLine()) != null) {
            if (TRACK_END.equals(line)) {
                break;
            }
            message.putValue(line);
        }
        int hashCode = message.hashCode();
        TrackMessage oldMessage;
        if (trackMessages.containsKey(hashCode) && (oldMessage = trackMessages.get(hashCode)) != null) {
            oldMessage.plus();
        } else {
            message.plus();
            trackMessages.put(hashCode, message);
//            hashListMap.put(message.key, message);
        }
    }

    private static final int TRACK_LINES = 10;
    private static class TrackMessage implements Comparable<TrackMessage> {
        private String key = "";
        private List<String> lines = new ArrayList<>();
        private StringBuffer message = new StringBuffer();
        private int number = 0;

        private void putValue(String value) {
            message.append(value).append('\n');
            if (lines.size() > TRACK_LINES) {
                return;
            }
            if (isEmpty(this.key)) {
                this.key = value;
            } else {
                lines.add(value);
            }
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + hashCode(key);
            for (String line: lines) {
                result = 31 * result + hashCode(line);
            }
            return result;
        }

        private int hashCode(String string) {
            return string == null ? 0 : string.hashCode();
        }

        private String getMessage() {
            return message.toString();
        }

        private void plus() {
            this.number++;
        }

        @Override
        public int compareTo(TrackMessage other) {
            if (other == null || other == this) {
                return 0;
            }
            String keyO = other.key;
            keyO = keyO.substring(0, keyO.indexOf('('));
            String keyThis = this.key;
            keyThis = keyThis.substring(0, keyThis.indexOf('('));
            int compare = keyO.compareTo(keyThis);
            return compare != 0 ? compare : other.number - this.number;
        }
    }

    private static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static class HashListMap extends HashMap<String, List<TrackMessage>> {
        public void put(String trackKey, TrackMessage message) {
            List<TrackMessage> array = this.get(trackKey);
            if (array == null) {
                array = new ArrayList<>();
                this.put(trackKey, array);
            }
            array.add(message);
        }

        public List<TrackMessage> allValues() {
            List<TrackMessage> array = new ArrayList<>();
            for (List<TrackMessage> messages: this.values()) {
                if (messages == null) {
                    continue;
                }
                array.addAll(messages);
            }
            return array;
        }
    }

    public static class HashCountMap extends HashMap<String, Integer> {
        @Override
        public Integer put(String key, Integer value) {
            Integer old = this.get(key);
            if (old == null) {
                return super.put(key, value);
            } else {
                return super.put(key, value + old);
            }
        }

        public Integer plus(String key) {
            return this.put(key, 1);
        }
    }

    public static class TimeUtil {
        public static final String PATTERN_MONTH = "yyyy-MM";
        public static final String PATTERN_DAY2Y = "yy-MM-dd";
        public static final String PATTERN_DAY4Y = "yyyy-MM-dd";
        public static final String PATTERN_TIME = "HH:mm:ss";
        public static final String PATTERN_MMSS = "mm:ss";
        public static final String PATTERN_S = "yyyyMMddHHmmss";
        public static final String PATTERN_MS = "yyyyMMddHHmmssSSS";
        public static final String PATTERN_DATE = "yyyy-MM-dd HH:mm:ss";
        public static final String PATTERN_DATE_MS = "yyyy-MM-dd HH:mm:ss.SSS";
        public static final String PATTERN_WEEHOURS = "yyyy-MM-dd 00:00:00";

        public static String getCurrentDate(String pattern) {
            return date2String(new Date(), pattern);
        }

        public static String changeDatePattern(String timeString, String srcPattern, String destPattern) {
            try {
                Date dt = dateString2Date(timeString, srcPattern);
                return date2String(dt, destPattern);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
            return "";
        }

        public static Date dateString2Date(String timeString, String pattern) throws ParseException {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern, Locale.getDefault());
            return dateFormatter.parse(timeString);
        }

        public static Date dateLong2Date(long milliseconds) {
            return new Date(milliseconds);
        }

        public static long dateString2Long(String date, String pattern) {
            try {
                return dateString2Date(date, pattern).getTime();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return 0;
        }

        public static String date2String(Date date, String pattern) {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
            return sdf.format(date);
        }

        public static String dateSecond2String(long seconds, String pattern) {
            return dateMilliSecond2String(seconds * 1000, pattern);
        }

        public static String dateMilliSecond2String(long milliseconds, String pattern) {
            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
            format.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            return format.format(milliseconds);
        }
    }
}
