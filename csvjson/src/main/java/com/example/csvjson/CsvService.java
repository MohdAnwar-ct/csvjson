package com.example.csvjson;

import com.example.csvjson.model.Record;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    // Method to parse CSV and convert to JSON (List of Records)
    public List<Record> parseCsvToJson(String filePath) throws IOException {
        List<Record> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> lines = reader.readAll();
            for (String[] line : lines) {
                Record record = new Record();
                record.setColumn1(line[0]);
                record.setColumn2(line[1]);
                // Set other fields if necessary
                records.add(record);
            }
        } catch (CsvException e) {
            e.printStackTrace();  // Handle the exception (logging or custom handling)
        }
        return records;
    }

    // Method to append JSON data to CSV file
    public void appendJsonToCsv(String filePath, List<Record> records) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
            for (Record record : records) {
                String[] line = {record.getColumn1(), record.getColumn2()}; // Add other columns if necessary
                writer.writeNext(line);
            }
        }
    }
}
