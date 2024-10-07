package com.service.microservice.batch.excute.step;

import com.service.microservice.batch.constant.BatchConstant;
import com.service.microservice.batch.constant.BatchMapping;
import com.service.microservice.batch.response.BatchExcel;
import com.service.microservice.batch.response.BatchResponse;
import com.service.microservice.batch.response.ConvertBatch;
import com.service.microservice.batch.response.ListBatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Slf4j
@Configuration
public class BatchProcess implements ItemProcessor<String, ListBatch> {

    @Override
    public ListBatch process(String item) {
        log.info("=== processing ===");

        try {
            String a = item;
            InputStream inputStream = new FileInputStream("C:\\Users\\AiDenPhung\\Desktop\\header1.xlsx");
            List<BatchResponse> list = readDataExcel(inputStream);
            log.info("==== size: {}", list.size());
        } catch (FileNotFoundException e) {
            log.error("File {} ", e.getMessage());
        }

        return null;
    }


    private List<BatchResponse> readDataExcel(InputStream inputStream) {
        List<BatchResponse> batchRes = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            for (Sheet sheet : workbook) {
                Row headerRow = sheet.getRow(0);
                String partnerName = getBatchHeaderName(headerRow);

                if (!Objects.equals(partnerName, "")) {
                    // Get map with key is column name (excel) and value is fieldName of Entity
                    Map<String, String> mapColumnToField = BatchMapping.mapping.get(partnerName);

                    // Map with column name (EXCEL file) and index of row (EXCEL file)
                    Map<String, Integer> mapColumnToIndex = new HashMap<>();
                    if (headerRow.cellIterator().hasNext()) {
                        for (int index = 0; index < headerRow.getLastCellNum(); index++) {
                            headerRow.getCell(index).setCellType(CellType.STRING);
                            String columnName = headerRow.getCell(index).getStringCellValue().trim();
                            mapColumnToIndex.put(columnName, index);
                        }
                    }

                    // Mapping data to partnerTransaction
                    sheet.removeRow(headerRow);
                    for (Row row : sheet) {
                        BatchExcel batchExcel = new BatchExcel();
                        PropertyAccessor propertyAccessor = PropertyAccessorFactory.forBeanPropertyAccess(batchExcel);
                        for (String columnName : mapColumnToIndex.keySet()) {
                            if (mapColumnToField.containsKey(columnName)) {
                                String data = "";
                                if (row.getCell(mapColumnToIndex.get(columnName)) != null) {
                                    row.getCell(mapColumnToIndex.get(columnName)).setCellType(CellType.STRING);
                                    data = row.getCell(mapColumnToIndex.get(columnName)).getStringCellValue();
                                }
                                String fieldName = mapColumnToField.get(columnName);
                                propertyAccessor.setPropertyValue(fieldName, data);
                            }
                        }
                        BatchResponse partnerTransaction = ConvertBatch.convertData(batchExcel);
                        log.info("BatchExcelConverter: {}", partnerTransaction);
                        batchRes.add(partnerTransaction);
                    }
                }
            }
        } catch (IOException e) {
            log.error("InputStream is not valid", e);
        }
        return batchRes;
    }

    private List<String> getColumnsName(Row headerRow) {
        if (headerRow == null) {
            return new ArrayList<>();
        }
        List<String> fileColumns = new ArrayList<>();
        Iterator<Cell> cellIterator = headerRow.cellIterator();

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            fileColumns.add(cell.getStringCellValue().trim());
        }
        return fileColumns;
    }

    private String getBatchHeaderName(Row headerRow) {
        List<String> columnNames = getColumnsName(headerRow);

        // Get partnerName based on template with column names
        for (String headerName : BatchConstant.getList().keySet()) {
            if (BatchConstant.getList().get(headerName).equals(columnNames)) {
                return headerName;
            }
        }
        return "";
    }

}
