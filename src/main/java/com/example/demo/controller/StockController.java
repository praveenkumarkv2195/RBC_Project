package com.example.demo.controller;

import com.example.demo.fileParser.FileParser;
import com.example.demo.model.Stock;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/stock-data")
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("")
    public List<Stock> getStocks(){
        return stockService.listAllStock();
    }

    @PostMapping("/")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock){
        stockService.saveStock(stock);
        return new ResponseEntity<Stock>(stock, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Stock>> getStock(@PathVariable String name){
        List<Stock> stocks = stockService.getStockByName(name);
        return new ResponseEntity<List<Stock>>(stocks,HttpStatus.OK);
    }

    @PostMapping("/bulk-insert")
    public void bulkInsert(@RequestParam("file") MultipartFile file) throws Exception{
        if(file!=null&&file.getInputStream()!=null){
            List<Stock> stocks = FileParser.parseFile(file.getInputStream());
            stockService.saveStocks(stocks);
        }
    }
}
