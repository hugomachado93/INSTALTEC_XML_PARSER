package com.project.xmlparser.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

//@Configuration
//class MessageConfig(@Autowired val danfeAdapter: DanfeAdapter) {
//
////    @Bean
////    fun messageChannelAdapter(
////            @Qualifier("pubsubInputChannel") inputChannel: MessageChannel,
////            pubSubTemplate: PubSubTemplate): PubSubInboundChannelAdapter {
////
////        val adapter = PubSubInboundChannelAdapter(
////                pubSubTemplate, "fileXmlSignature")
////        adapter.outputChannel = inputChannel
////        adapter.ackMode = AckMode.MANUAL
//////        adapter.payloadType = Person::class.java
////
////        return adapter
////    }
////
////    @Bean
////    fun pubsubInputChannel() = DirectChannel()
//
////    @ServiceActivator(inputChannel = "pubsubInputChannel")
////    fun messageReceiver(fileByte: Array<Byte>, @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) message: BasicAcknowledgeablePubsubMessage) {
////        message.ack()
////        danfeAdapter.deserializeDanfeXml(fileByte)
////    }
//
//}