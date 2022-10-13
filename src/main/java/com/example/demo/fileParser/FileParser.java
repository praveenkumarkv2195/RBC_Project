package com.example.demo.fileParser;

import com.example.demo.model.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FileParser {
    private static HashMap<Integer,String> valuesMap = new HashMap<Integer,String>(){{
        put(0,"quarter");
        put(1,"stock");
        put(2,"date");
        put(3,"open");
        put(4,"high");
        put(5,"low");
        put(6,"close");
        put(7,"volume");
        put(8,"percentChangePrice");
        put(9,"percentChangeVolumeOverLastWk");
        put(10,"previousWeeksVolume");
        put(11,"nextWeeksOpen");
        put(12,"nextWeeksClose");
        put(13,"percentChangeNextWeeksPrice");
        put(14,"daysToNextDividend");
        put(15,"percentReturnNextDividend");

    }};

        public static List<Stock> parseFile(InputStream stream) throws Exception{
            Scanner in = new Scanner(stream);
            List<Stock> stocks = new ArrayList<>();
            while(in.hasNextLine()){
               String line =  in.nextLine();
               String[] values = line.split(",");
               JSONObject objectJson = new JSONObject();
               for(int i=0;i<values.length;i++){
                    objectJson.put(valuesMap.get(i),values[i]);
               }
               ObjectMapper mapper = new ObjectMapper();
               Stock stockValue =  mapper.readValue(objectJson.toString(),Stock.class);
               stocks.add(stockValue);
            }
            return stocks;
        }
}
