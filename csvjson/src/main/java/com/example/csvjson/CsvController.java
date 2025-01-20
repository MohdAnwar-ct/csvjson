package com.example.csvjson;

import com.example.csvjson.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/csv")
public class CsvController {

    @Autowired
    private CsvService csvService;

    // Endpoint to parse CSV to JSON
    @PostMapping("/parse")
    public List<Record> parseCsv(@RequestParam String filePath) throws IOException {
        return csvService.parseCsvToJson(filePath);
    }

    // Endpoint to append JSON data to CSV
    @PostMapping("/append")
    public String appendJson(@RequestParam String filePath, @RequestBody List<Record> records) throws IOException {
        csvService.appendJsonToCsv(filePath, records);
        return "CSV file updated successfully";
    }
}
