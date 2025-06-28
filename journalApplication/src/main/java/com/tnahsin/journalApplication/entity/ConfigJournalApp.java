package com.tnahsin.journalApplication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "config_journal_app")
@NoArgsConstructor
public class ConfigJournalApp {


    private String key;
    private String value;


}