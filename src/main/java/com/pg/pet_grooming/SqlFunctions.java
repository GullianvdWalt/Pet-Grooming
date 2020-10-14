/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.pet_grooming;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Gullian
 */
public class SqlFunctions implements MetadataBuilderContributor {

    @Override
    public void contribute(MetadataBuilder metaDataBuilder) {
        metaDataBuilder.applySqlFunction("group_concat",
                new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }

}

