package com.kafka.avro.KafkaAvro.controller

import com.kafka.avro.KafkaAvro.entity.Request
import com.kafka.avro.KafkaAvro.service.ProducerServ
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/Kafka/Avro")
class kafkaAvroController(
    private val producerServ: ProducerServ
) {

    @PostMapping("/produce")
    fun produce(@RequestBody request: Request):String
    {
        return producerServ.produceMessage(request)
    }
}