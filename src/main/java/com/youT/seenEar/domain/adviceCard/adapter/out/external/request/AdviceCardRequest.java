package com.youT.seenEar.domain.adviceCard.adapter.out.external.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youT.seenEar.domain.adviceCard.domain.AdviceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class AdviceCardRequest {

    private AdviceType adviceType;
    private MultipartFile multipartFile;

}
