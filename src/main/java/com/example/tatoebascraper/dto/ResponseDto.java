package com.example.tatoebascraper.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {
    String fromLang;
    String toLang;
}
