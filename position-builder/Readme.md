--- 
spring: 
  profiles: default  
  cloud: 
    stream:       
      kafka: 
        binder:
          brokers: localhost:9092
          zkNodes: localhost:2181
          autoCreateTopics: true
        streams:
          timeWindow.length: 500
          binder:
            serdeError: logAndContinue 
            configuration:
              default:
                key.serde: org.apache.kafka.common.serialization.Serdes$LongSerde 
                value.serde: org.mocr.poc.reactive.positionbuilder.serde.MovementSerde
              
      bindings: 
        input:
          destination: movements 
          content-type: application/json  
          group: movementsConsumerGroup     
        output: 
          contentType: application/json
          destination: positions