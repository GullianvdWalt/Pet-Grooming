/*
 * Created By Gullian Van Der Walt 10/09/2020
 * 
 * Last Update : 2020/09/11, 06:40

   Class for using SQL functions in JPA SQL

 */
package com.pg.pet_grooming;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class SqlFunctionsMetadataBuilderContributor
        implements MetadataBuilderContributor {

    @Override
    public void contribute(MetadataBuilder mb) {
        mb.applySqlFunction("group_concat",
                new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }

}

