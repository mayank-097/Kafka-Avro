package com.kafka.avro.KafkaAvro.config

import com.kafka.avro.KafkaAvro.entity.Request
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

@Configuration
class ConsumerConfig {

    @Value("\${group_Id}")
    private val groupId : String = ""

    @Value("\${brokers}")
    private val broker : String = ""

    @Bean
    fun consumeFactory(): ConsumerFactory<String, Request>
    {
        var prop:HashMap<String,Any> = HashMap()
        prop[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = broker
        prop[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        prop[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        prop[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        prop[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = KafkaAvroDeserializer::class.java
        prop[AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG]="http://localhost:8081"

        //prop[AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG] = "http://localhost:8081";


        return DefaultKafkaConsumerFactory(prop)

    }
    @Bean
    fun kafkaListenerContainerFactory() : KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Request>>
    {
        var factory : ConcurrentKafkaListenerContainerFactory<String, Request> = ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory= consumeFactory()
        return factory
    }

//    @Bean
//    fun receiver(): Reciever{
//        return Reciever()
//    }
}