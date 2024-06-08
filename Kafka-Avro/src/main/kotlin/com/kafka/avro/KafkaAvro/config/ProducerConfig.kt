package com.kafka.avro.KafkaAvro.config

import com.kafka.avro.KafkaAvro.entity.Request
import com.kafka.avro.KafkaAvro.service.ProducerServ
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig
import io.confluent.kafka.serializers.KafkaAvroSerializer
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class ProducerConfig {

    @Value("\${group_Id}")
    private val groupId : String = ""

//    @Value("\${brokers}")
    private val broker : String = "localhost:9092"

    @Bean
    fun producerFactory(): ProducerFactory<String, GenericRecord>
    {
        var props:HashMap<String,Any> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG]=broker
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG]=StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG]=KafkaAvroSerializer::class.java
        props[AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG] = "http://localhost:8081";
//        props["value.converter.schema.registry.url"]="http://localhost:8081"
//        props["producer.type"] = "sync"
        //return DefaultKafkaProducerFactory(props,StringSerializer(),KafkaAvroSerializer()) as ProducerFactory<String, Request>
        return DefaultKafkaProducerFactory(props)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, GenericRecord>
    {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun sender():ProducerServ
    {
        return ProducerServ()
    }


}