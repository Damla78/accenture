package com.accenture.flight.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface LoadService {
    static final Logger logger = LoggerFactory.getLogger(LoadService.class);
    static String COMMA_DELIMITER = ",";
    static String LINE_DELIMITER = "\n";
    static String QUOTE = "\"";
    static String EMPTY = "";

    default File getFile(String filePath) throws FileNotFoundException {
        try {
            return ResourceUtils.getFile(filePath);
        } catch (FileNotFoundException ex) {
            logger.error("Filepath is not resolved: " + filePath + " " + ex);
            throw new FileNotFoundException("Filepath is not resolved: " + filePath + " " + ex);
        }
    }

    default void retrieveDataInsideQuote(ArrayList<String> resultList, int firstInd, String[] lineDivededQuote) {
        //9090,"26AR","small_airport","Fly ""N"" K Airport",35.2154998779,-91.807800293,400,"NA","US","US-AR","Searcy","no","26AR",,"26AR",,,
        //Double quote
        int lastRecordedIndex = resultList.size() - 1;
        if (firstInd % 2 == 0 && !lineDivededQuote[firstInd - 1].contains(COMMA_DELIMITER)) {
            resultList.set(lastRecordedIndex, resultList.get(lastRecordedIndex) + "\"");//+ lineDivededQuote[firstInd] + "\""
        } else if (firstInd % 2 == 1 && !lineDivededQuote[firstInd - 1].contains(COMMA_DELIMITER)) {
            resultList.set(lastRecordedIndex, resultList.get(lastRecordedIndex) + lineDivededQuote[firstInd]);//+ lineDivededQuote[firstInd] + "\""
        } else {
            resultList.add(lineDivededQuote[firstInd]);
        }
    }

    default void retrieveDataBeforeQuote(ArrayList<String> resultList, int firstInd, String[] lineDivededQuote) {
        if (!lineDivededQuote[firstInd].equals(COMMA_DELIMITER)) {
            for (int secInd = 0; secInd < lineDivededQuote[firstInd].length() - 1; secInd++) {
                if (lineDivededQuote[firstInd].charAt(secInd) == ',' && lineDivededQuote[firstInd].charAt(secInd + 1) == ',') {//for ,,
                    resultList.add("");
                } else {
                    int nextDelimeter = lineDivededQuote[firstInd].indexOf(",", secInd + 1);
                    if (nextDelimeter == -1) {//for ,123
                        resultList.add(lineDivededQuote[firstInd].substring(secInd + 1, lineDivededQuote[firstInd].length()));
                        break;
                    } else if (lineDivededQuote[firstInd].charAt(secInd) == ',') {//for ,123, or ,123,56
                        resultList.add(lineDivededQuote[firstInd].substring(secInd + 1, nextDelimeter));
                        secInd = nextDelimeter - 1;
                    } else {//for 123,
                        resultList.add(lineDivededQuote[firstInd].substring(secInd, nextDelimeter));
                        secInd = nextDelimeter - 1;
                    }
                }
            }

        }
    }

    default ArrayList<String> divideLine(String line) {
        //6734,"03OI","heliport","Cleveland Clinic, Marymount Hospital Heliport",41.420312,-81.599552,890,"NA","US","US-OH","Garfield Heights","no","03OI",,"03OI",,,
        //COMMA_DELIMITER is use inside quote. It must be differentiated than other commas...
        String[] lineDivededQuote = line.split(QUOTE);
        ArrayList<String> resultList = new ArrayList<String>();
        if (!line.startsWith("\"id\"")) {// skip first line
            for (int firstInd = 0; firstInd < lineDivededQuote.length; firstInd++) {
                if (firstInd % 2 == 0 && lineDivededQuote[firstInd].contains(COMMA_DELIMITER)) {
                    retrieveDataBeforeQuote(resultList, firstInd, lineDivededQuote);
                } else {
                    retrieveDataInsideQuote(resultList, firstInd, lineDivededQuote);
                }
            }
        }
        return resultList;
    }

    void recordDatas() throws IOException;
}
