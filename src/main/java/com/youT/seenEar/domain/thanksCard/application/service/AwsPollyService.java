package com.youT.seenEar.domain.thanksCard.application.service;


import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.*;
import com.youT.seenEar.domain.thanksCard.application.port.in.AwsPollyUseCase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@AllArgsConstructor
@Slf4j
public class AwsPollyService  implements AwsPollyUseCase {
    private AmazonPollyClient pollyClient;

    private S3MultipartService s3MultipartService;

    private final String dirName = "seenEar";

    private final String voiceId = "Seoyeon";


    @Override
    public String getTTS(String text) {

        try{
            //get the audio stream
            InputStream speechStream = synthesize(text, OutputFormat.Mp3);

            //create an MP3 file
            File mp3File = createMP3File(speechStream);
            log.info(mp3File.getName());

            return s3MultipartService.upload(mp3File,dirName);
        }catch (Exception e){
            log.info(e.getMessage());
        }

        return null;

    }

    private InputStream synthesize(String text, OutputFormat format) throws IOException {
        SynthesizeSpeechRequest synthReq =
                new SynthesizeSpeechRequest().withText(text).withVoiceId(voiceId)
                        .withOutputFormat(format);
        SynthesizeSpeechResult synthRes = pollyClient.synthesizeSpeech(synthReq);

        return synthRes.getAudioStream();
    }

    private static File createMP3File(InputStream inputStream) throws Exception {

        // InputStream을 사용하여 MP3 파일 생성
        File mp3File = File.createTempFile("output", ".mp3");
        try (FileOutputStream fos = new FileOutputStream(mp3File)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        return mp3File;
    }

}
