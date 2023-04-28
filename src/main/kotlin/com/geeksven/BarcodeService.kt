package com.geeksven

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.oned.EAN13Writer
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.util.Base64
import javax.enterprise.context.ApplicationScoped
import javax.imageio.ImageIO

@ApplicationScoped
class BarcodeService {

    fun generateEAN13BarcodeImage(barcodeText: String, width: Int, height: Int): ByteArray {
        val barcodeWriter = EAN13Writer()
        val bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, width, height)
        return toByteArray(MatrixToImageWriter.toBufferedImage(bitMatrix), "png")
    }

    fun generateEAN13BarcodeB64Image(barcodeText: String, width: Int, height: Int): String {
        val barcodeWriter = EAN13Writer()
        val bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, width, height)
        return String(Base64.getEncoder().encode(toByteArray(MatrixToImageWriter.toBufferedImage(bitMatrix), "png")))
    }

    fun toByteArray(bi: BufferedImage, format: String): ByteArray {
        val baos = ByteArrayOutputStream()
        ImageIO.write(bi, format, baos)
        return baos.toByteArray()
    }
}