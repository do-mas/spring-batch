package com.batch.xlsx;

import exc.RowMapper;
import exc.support.rowset.RowSet;

public class FieldMapper implements RowMapper<String> {

    @Override
    public String mapRow(RowSet rs) throws Exception {
        try {
//            System.out.println("Reader mapping " + rs.getColumnValue(0) + " " + rs.getColumnValue(1));
        } catch (Exception e) {
//            System.out.println("Reader");
            return null;
        }
        return rs.getColumnValue(0).toString();
    }

}
