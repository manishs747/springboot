package com.base.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

public class Utility {

    private static final Logger logger = LoggerFactory
            .getLogger(Utility.class);


    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final ObjectMapper objectMapperv2 = new ObjectMapper();
    public static final String NEWRELIC_IGNORE = "com.newrelic.agent.IGNORE";



    /**
     * php equivalent of preg_match
     *
     * @param pattern
     * @param content
     * @return
     */
    public static boolean pregMatch(String pattern, String content) {
        return content.matches(pattern);
    }

    public static String getValidString(String string) {
        return string.replace("\"", "").trim();
    }

    public static boolean isValidMobileNumber(String mobile) {
        if (mobile == null || (mobile.length() != 10))
            return false;
        else return pregMatch("^[1-9][0-9]{9}", mobile);
    }

    public static boolean isValidUserName(String name) {
        if (name == null)
            return false;
        return pregMatch("[-a-zA-Z.\\ ]+", name);
    }



    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * Php Equivalent of json_encode
     *
     * @param o
     * @return
     */
    public static String jsonEncode(Object o) throws JsonProcessingException {
        if (o == null)
            return "";
        return objectMapper.writeValueAsString(o);
    }

    public static String jsonEncode2(Object o) {
        try {
            return jsonEncode(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String jsonEncode3(Object o) {
        try {
            if (o == null)
                return "";
            return objectMapperv2.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> T getFirst(List<T> list) {
        if (isEmpty(list))
            return null;
        else return list.get(0);
    }

    public static int toInt(boolean b) {
        return b ? 1 : 0;
    }

    public static Integer toInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static short toSrt(boolean b) {
        return b ? (short) 1 : (short) 0;
    }

    public static boolean toBoolean(short a) {
        return a == 1;
    }


    public static boolean toBoolean(int a) {
        return a == 1;
    }

    public static boolean toBoolean(String str) {
        if(Utility.isEmpty(str)) return false;

        return !"FALSE".equalsIgnoreCase(str);
    }


    public static Object jsonDecode(String s, Class<?> c) {
        Object u = null;
        if (s != null && !s.equals("")) {
            try {
                u = objectMapper.reader(c).readValue(s);
            } catch (Exception e) {
                logger.error("error while converting json to class", e);
            }
        }
        return u;
    }

    public static <T> T jsonDecode2(String s, Class<?> clazz) throws IOException {
        if (Utility.isEmpty(s)) return null;
        JavaType classType = objectMapper.getTypeFactory().constructType(clazz);
        return objectMapper.reader(classType)
                .readValue(s);
    }

    public static <T> T jsonDecode3(String s, Class<?> clazz) {
        try {
            return jsonDecode2(s, clazz);
        } catch (IOException e) {
            logger.error("error while decoding json", e);
        }
        return null;
    }

    public static <T> T jsonDecode4(String s, Class<?> clazz) {
        try {
            if (Utility.isEmpty(s)) return null;
            JavaType classType = objectMapperv2.getTypeFactory().constructType(clazz);
            return objectMapperv2.reader(classType)
                    .readValue(s);
        } catch (IOException e) {
            logger.error("error while decoding json", e);
        }
        return null;
    }

    public static <T> T jsonDecodeAsList(String s, Class<?> clazz) throws IOException {
        if (Utility.isEmpty(s)) return null;

        return objectMapper.readValue(s, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isLong(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isEmpty(String s) {
        return s == null || "".equals(s.trim());
    }

    public static boolean isEmpty(Collection l) {
        return l == null || l.isEmpty();
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> List<T> setToList(Set<T> s) {
        if (s == null || s.isEmpty())
            return null;
        else return new ArrayList<>(s);
    }

    public static boolean isLatValid(String lat){
        return isDouble(lat) && (Double.parseDouble(lat) <=90 && Double.parseDouble(lat) >=-90);
    }

    public static boolean isLngValid(String lng){
        return isDouble(lng) && (Double.parseDouble(lng) <=180 && Double.parseDouble(lng) >=-180);
    }

    /**
     * Get Request object
     * Bad design.Need to support php
     *
     * @return
     */
    public static HttpServletRequest getCurrentRequest() {
        final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        final HttpServletRequest request = attr.getRequest();
        return request;
    }

    public static boolean isCurrentRequestFromWeb(String userAgent) {
        if (userAgent == null) {
            return true;
        } else {
            return !(userAgent.equals("Swiggy-Android") || (userAgent.equals("Swiggy-iOS")));
        }
    }

    public static long getEpochTime(String time) {
        Date temp = null;
        try {
            temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            return temp.getTime();
        } catch (ParseException e) {
            logger.error("date parsing error", e);
        }
        return Long.MIN_VALUE;
    }



    /**
     *
     */
    public static HashMap<String, Object> jsonToMap(String jsonString) throws IOException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);

        TypeReference<HashMap<String, Object>> typeRef
                = new TypeReference<HashMap<String, Object>>() {
        };

        return mapper.readValue(jsonString, typeRef);
    }

    /**
     * Add days to Date
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Timestamp getTimestamp(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        cal.set(Calendar.MILLISECOND, 0);
        return new java.sql.Timestamp(date.getTime());
    }

    public static Integer getInt(String value) {
        return getInt(value,0);
    }

    public static Integer getInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Long getLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return 0l;
        }
    }

    public static Float getFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            return 0f;
        }
    }

    public static Double getDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0d;
        }
    }



    public static Boolean getBoolean(String value) {
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            return false;
        }
    }

    public static String getString(Object obj) {
        return (obj == null) ? "" : String.valueOf(obj);
    }




    public static String getRequestPath() {
        HttpServletRequest request = getCurrentRequest();
        String context = request.getContextPath();
        String fullURL = request.getRequestURI();
        String path = fullURL.substring(fullURL.indexOf(context) + context.length());
        return path;
    }

    public static <T> T convertValue(Object o, Class<T> clazz) {
        return objectMapper.convertValue(o, clazz);
    }




    private static String getDateOrdinal(LocalDateTime dateTime) {
        int date = dateTime.getDayOfMonth();
        String dateEnd = "th";
        if (date == 1 || date == 21 || date == 31) {
            dateEnd = "st";
        } else if (date == 2 || date == 22) {
            dateEnd = "nd";
        } else if (date == 3 || date == 23) {
            dateEnd = "rd";
        }
        return dateEnd;
    }

    private static String getOpenTimeHours(int openHourMin) {
        int hours = openHourMin / 100; //since both are ints, you get an int
        int minutes = openHourMin % 100;
        String minutesString = String.valueOf(minutes == 0 ? new String() :
                (minutes / 10 == 0 ? ":0" + String.valueOf(minutes) :
                        (":" + String.valueOf(minutes))));
        String timePeriod = " am";
        if (hours >= 12) {
            hours = hours > 12 ? hours - 12 : hours;
            timePeriod = " pm";
            if (hours == 12 && minutes == 0) {
                timePeriod = " noon";
            }
        }
        return String.valueOf(hours) + minutesString + timePeriod;
    }

    public static boolean containsKey(Set t, Object k) {
        if (t == null)
            return false;
        return t.contains(k);
    }

    public static boolean containsKey(Map t, Object k) {
        if (t == null)
            return false;
        return t.containsKey(k);
    }

    public static <T> Set<T> toSet(List<T> values) {
        if (isEmpty(values))
            return null;
        else return new HashSet<T>(values);
    }


    public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toList());
    }

    public static <T, U> List<U> allMapValueToList(Map<T, U> m) {
        if (m == null || m.size() == 0)
            return null;
        List<U> l = new ArrayList<>();
        for (Map.Entry<T, U> k : m.entrySet()) {
            l.add(k.getValue());
        }
        return l;
    }

    public static <T> List<T> getAMinusB(List<T> A, List<T> B) {
        if (A == null)
            return new ArrayList<>();
        if (B == null)
            return A;
        Map<T, Integer> countMapA = countMap(A);
        if (countMapA == null) return null;
        Map<T, Integer> countMapB = countMap(B);
        if (countMapB == null) return A;
        for (T b : B) {
            if (countMapA.containsKey(b)) {
                int count = countMapA.get(b);
                if (count == 0)
                    countMapA.remove(b);
                else countMapA.put(b, count - 1);
            }
        }
        List<T> aminusb = new ArrayList<>();
        for (Map.Entry<T, Integer> entry : countMapA.entrySet())
            for (int i = 0; i < entry.getValue(); i++)
                aminusb.add(entry.getKey());
        return aminusb;
    }

    public static <T> List<T> getUniqueList(List<T> t) {
        HashSet hs = new HashSet();
        hs.addAll(t);
        List<T> temp = new ArrayList<>();
        temp.addAll(hs);
        return temp;
    }

    public static <T> Map<T, Integer> countMap(List<T> A) {
        if (isEmpty(A))
            return new HashMap<>();
        Map<T, Integer> countMap = new HashMap<>();
        for (T a : A) {
            if (countMap.containsKey(a)) {
                int count = countMap.get(a);
                countMap.put(a, ++count);
            } else countMap.put(a, 1);
        }
        return countMap;
    }




    public static String findCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime());
    }

    public static int findCurrentHHMM() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return Utility.getInt(new SimpleDateFormat("HHmm", Locale.ENGLISH).format(date.getTime()));
    }

    public static String getDeviceId() {
        final HttpServletRequest request = getCurrentRequest();
        if (request.getHeader("deviceId") == null) {
            return null;
        }
        return String.valueOf(request.getHeader("deviceId"));
    }

    public static String getTid() {
        final HttpServletRequest request = getCurrentRequest();
        if (request.getHeader("tid") == null) {
            return null;
        }
        return String.valueOf(request.getHeader("tid"));
    }

    public static String getSwUid() {
        final HttpServletRequest request = getCurrentRequest();
        if (request.getAttribute("swuid") == null) {
            return null;
        }
        return String.valueOf(request.getAttribute("swuid"));
    }





    public static String toString(Serializable s) {
        if (s != null) {
            return s.toString();
        }
        return "";
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static long minutesBetween(long start, long end) {

        long diffInMilliseconds = Math.abs(end - start);

        return TimeUnit.MILLISECONDS.toMinutes(diffInMilliseconds);
    }

    public static long convertLocalDateTimeToEpochTime(LocalDateTime ldt) {

        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());

        return output.getTime();
    }

    /**
     * Gets the current time slot according to analytics settings
     * 0000 - 0700 : 0
     * 0700 - 1130 : 1
     * 1130 - 1600 : 2
     * 1600 - 1900 : 3
     * 1900 - 2359 : 4
     *
     * @return
     */
    public static Integer getCurrentSlot() {
        Date curDate = new Date();
        return getSlot(curDate);
    }

    public static Integer getSlot(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Integer curHour = calendar.get(Calendar.HOUR_OF_DAY);
        Integer curMinute = calendar.get(Calendar.MINUTE);

        Integer curHourMinute = curHour * 100 + curMinute;
        if (curHourMinute >= 0 && curHourMinute < 700) {
            return 0;
        } else if (curHourMinute >= 700 && curHourMinute < 1130) {
            return 1;
        } else if (curHourMinute >= 1130 && curHourMinute < 1600) {
            return 2;
        } else if (curHourMinute >= 1600 && curHourMinute < 1900) {
            return 3;
        } else {
            return 4;
        }
    }

    public static float getDaysDelta(String orderDate, String pattern) {
        LocalDateTime timeNow = LocalDateTime.now();
        return differenceInDays(LocalDateTime.parse(orderDate, DateTimeFormatter.ofPattern(pattern)),
                timeNow);
    }

    private static int differenceInDays(LocalDateTime start, LocalDateTime end) {
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();
        if (start.toLocalTime().isAfter(end.toLocalTime())) {
            startDate = startDate.plusDays(1);
        }
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static float roundWithDecimals(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return (float) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
    }

    public static boolean isSameMonth(Calendar first, Calendar second) {
        return first.get(Calendar.MONTH) == second.get(Calendar.MONTH) &&
                first.get(Calendar.YEAR) == second.get(Calendar.YEAR);
    }

    //Convert String date of yyyy-MM-dd format to TimeStamp
    public static Timestamp getTimesStampFromStringDate(String date, LocalTime localTime) {
        if (localTime == null) {
            localTime = LocalTime.MIN;
        }
        Timestamp timestamp = null;
        if (!StringUtils.hasText(date)) {
            return timestamp;
        }
        try {
            timestamp = Timestamp.valueOf(LocalDateTime.of(LocalDate.parse(date), localTime));
        } catch (Exception e) {
            logger.error("Unable to get TimeStamp for Date: {}", date);
        }
        return timestamp;
    }

    public static String getDateStringFromTimestamp(Timestamp timestamp, String format) {
        String date = "0000-00-00 00:00:00";
        if (!StringUtils.hasText(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.format(timestamp);
        } catch (Exception e) {
            logger.error("Unable to get Date from Timestamp: {}", timestamp);
        }
        return date;
    }





    //trim all not null string data member of object
    public static Object trimStringValues(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value != null) {
                    if (value instanceof String) {
                        String trimmed = (String) value;
                        field.set(obj, trimmed.trim());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static double roundWithDecimals(double number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        double tmp = number * pow;
        return (double) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
    }

    /**
     * php Equivalent of remove_special_chars
     *
     * @param value            whose special chars have to be removed
     * @param allowSpecialChar flag for allowing some special char in special situations, like / and \ is allowed when address is stored,
     *                         Pass false for not allowing the special char
     * @return result string after removing special char
     */
    public static String removeSpecialChars(String value, Boolean allowSpecialChar) {
        if (value != null) {
            value = value.trim();
            if (allowSpecialChar) {
                value = value.replaceAll("[\\+\\^\\[\\]:;*?~=<>|'\"]", "");
            } else {
                value = value.replaceAll("[\\-\\+\\.\\^\\[\\]\\/:,;*?~=<>|'\"]", "");
            }
        }
        return value;
    }

    public static String replaceFromCameltoSnake(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.replaceAll("([^_A-Z])([A-Z])", "$1_$2").toLowerCase();
    }




    public static boolean validateLatLongString(String latLong) {
        return validateLatLongString(latLong, ",");
    }

    public static boolean validateLatLongString(String latLong, String splitterRegex) {
        String[] split = latLong.split(splitterRegex);
        if (split.length != 2)
            return false;
        if (split[0] == null || split[1] == null)
            return false;
        try {
            Float.parseFloat(split[0]);
            Float.parseFloat(split[1]);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }



    public static void ignoreCurrentRequestNPM() {
        HttpServletRequest currentRequest = Utility.getCurrentRequest();
        if (currentRequest != null) {
            currentRequest.setAttribute(NEWRELIC_IGNORE, true);
        }
    }

    public static String encodeBase64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    public static String decodeBase64(String encodedString) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
            return new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid base 64 string {}", encodedString);
            return encodedString;
        }
    }
}
