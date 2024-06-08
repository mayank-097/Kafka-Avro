package com.kafka.avro.KafkaAvro.service

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord
import com.kafka.avro.KafkaAvro.entity.Request
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class ProducerServ {

    private val logger: Logger = LoggerFactory.getLogger(ProducerServ::class.java)
    private val TOPIC = "AvroTopic"

    @Autowired
    private val kafkaTemplate : KafkaTemplate<String, GenericRecord>? = null

    var userSchema : String = "{\"name\" : \"Request\","+
    "\"type\" : \"record\","+
    "\"namespace\" : \"example.avro\","+
    "\"fields\" : [{\"name\" : \"requestId\" , \"type\" : \"string\"},"+
    "{\"name\" : \"requestUserName\" , \"type\" : \"string\"},"+
    "{\"name\" : \"requestPriority\" , \"type\" : \"string\"},"+
    "{\"name\" : \"requestMessageType\" , \"type\" : \"string\"},"+
    "{\"name\" : \"requestChannelType\" , \"type\" : \"string\"},"+
    "{\"name\" : \"requestMessage\" , \"type\" : \"string\"}]}"

    var parser : Schema.Parser = Schema.Parser()
    var schema :Schema = parser.parse(userSchema)
    var avroRecord : GenericRecord = GenericData.Record(schema)



    fun produceMessage(conRequest:Request):String
    {
        logger.info("Producing message $conRequest")
        val future = kafkaTemplate?.send(TOPIC,avroRecord)
        future?.get()
        return("Producing message $conRequest")

    }
}