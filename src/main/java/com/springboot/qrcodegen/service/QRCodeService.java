package com.springboot.qrcodegen.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class QRCodeService {

    public byte[] generateQRCodeWithLogo(String text, String logoPath) throws WriterException, IOException {
        int size = 250;
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size, hints);

        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // Load the logo image
        BufferedImage logoImage = ImageIO.read(getClass().getResourceAsStream(logoPath));

        // Scale the logo image
        int logoWidth = qrImage.getWidth() / 5;
        int logoHeight = qrImage.getHeight() / 5;
        Image scaledLogoImage = logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);

        // Calculate the logo position
        int centerX = (qrImage.getWidth() - logoWidth) / 2;
        int centerY = (qrImage.getHeight() - logoHeight) / 2;

        // Combine the QR code and the logo
        Graphics2D g = qrImage.createGraphics();
        g.drawImage(scaledLogoImage, centerX, centerY, logoWidth, logoHeight, null);

        // Draw a white border around the logo
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(5));
        g.draw(new RoundRectangle2D.Float(centerX, centerY, logoWidth, logoHeight, 10, 10));

        g.dispose();

        // Convert to byte array
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }
}

