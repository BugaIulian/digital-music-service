package com.dms.demo.services.dalle;

import com.dms.demo.exceptions.dalle.AlbumCoverCreationException;
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
        try {
            OpenAiService service = new OpenAiService(openAIAPI);
            CreateImageRequest request = CreateImageRequest.builder()
                    .prompt(prompt)
                    .build();
            return service.createImage(request).getData().get(0).getUrl();
        } catch (AlbumCoverCreationException e) {
            throw new AlbumCoverCreationException("Failed to create album cover due to connection issue.");
        }
    }
}