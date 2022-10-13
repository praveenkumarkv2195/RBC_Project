package com.example.demo.service;

import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> listAllStock(){
        return stockRepository.findAll();
    }

    public void saveStock(Stock stock){
        stockRepository.save(stock);
    }

    public List<Stock> getStockByName(String name){
        Stock stock = new Stock();
        stock.setStock(name);
        return stockRepository.findAll(Example.of(stock));
    }

    public void saveStocks(List<Stock> stocks){
        stockRepository.saveAll(stocks);
    }
}
