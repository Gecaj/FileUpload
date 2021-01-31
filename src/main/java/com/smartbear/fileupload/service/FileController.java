package com.smartbear.fileupload.service;

import com.smartbear.fileupload.dto.SaveFileDto;
import com.smartbear.fileupload.dto.SearchByTagsResponseDto;
import com.smartbear.fileupload.facade.FileFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileFacade fileFacade;

    @PostMapping("/file")
    public UUID createFile(@RequestBody SaveFileDto saveFileDto) {
        return fileFacade.addFile(saveFileDto);
    }

    @GetMapping("/files/{tag_search_query}/{page}")
    public SearchByTagsResponseDto findFilesByTags(@PathVariable String tag_search_query, @PathVariable int page) {
        return fileFacade.findFilesByTags(tag_search_query, page);
    }
}
