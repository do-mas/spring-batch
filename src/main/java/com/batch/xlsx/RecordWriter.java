package com.batch.xlsx;


import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.transform.FieldSet;

import java.util.List;

public class RecordWriter implements ItemWriter<FieldSet> {

    @Override
    public void write(List<? extends FieldSet> list) throws Exception {
//        System.out.println("writing total records: " + list.size());
    }
}
