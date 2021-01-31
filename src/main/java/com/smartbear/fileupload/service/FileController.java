package com.smartbear.fileupload.service;

import com.smartbear.fileupload.dto.FileDto;
import com.smartbear.fileupload.facade.FileFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileFacade fileFacade;

    @PostMapping("/file")
    public UUID createFile(@RequestBody FileDto fileDto) {
        return fileFacade.addFile(fileDto);
    }
}
