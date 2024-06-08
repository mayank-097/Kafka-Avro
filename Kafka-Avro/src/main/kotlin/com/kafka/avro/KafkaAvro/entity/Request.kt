package com.kafka.avro.KafkaAvro.entity

data class Request (
    var requestId : String,
    var requestUserName : String,
    var requestPriority : String,
    var requestMessageType : String,
    var requestChannelType : String,
    var requestMessage : String
        ){

}