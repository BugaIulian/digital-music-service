package com.dms.demo.services.dalle;

import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DalleServiceImpl implements DalleService {

    @Value("${openai.api.key}")
    private String openAIAPI;

    @Override
    public String createAlbumCover(String prompt) {
        OpenAiService service = new OpenAiService(openAIAPI);
        CreateImageRequest request = CreateImageRequest.builder()
                .prompt(prompt)
                .build();
        return service.createImage(request).getData().get(0).getUrl();
    }
}