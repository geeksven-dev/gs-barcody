package com.geeksven

import io.smallrye.mutiny.Uni
import org.jboss.resteasy.reactive.RestQuery
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.ws.rs.DefaultValue
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("/barcodes")
class BarcodeResource(private val barcodeService: BarcodeService) {

    private val logger: Logger = LoggerFactory.getLogger(BarcodeResource::class.java)

    @GET
    @Produces("image/png")
    @Path("/ean13/{barcode}")
    fun getEAN13Barcode(
        barcode: String,
        @RestQuery @DefaultValue("300") width: Int,
        @RestQuery @DefaultValue("150") height: Int
    ): Uni<ByteArray> = Uni.createFrom().item(
        barcodeService.generateEAN13BarcodeImage(barcode, width, height)
            .also { logger.info("barcode requested: $barcode / ($width by $height)") })

    @GET
    @Produces("text/plain")
    @Path("/ean13b64/{barcode}")
    fun getEAN13BarcodeB64(
        barcode: String,
        @RestQuery @DefaultValue("300") width: Int,
        @RestQuery @DefaultValue("150") height: Int
    ): Uni<String> = Uni.createFrom().item(
        barcodeService.generateEAN13BarcodeB64Image(barcode, width, height)
            .also { logger.info("barcode requested: $barcode / ($width by $height)") })

}