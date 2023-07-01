package com.jimmy.studyspring.uploadingfiles;

import com.jimmy.studyspring.uploadingfiles.storage.StorageFileNotFoundException;
import com.jimmy.studyspring.uploadingfiles.storage.StorageService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(FileUploadController.class)
public class FileUploadTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StorageService storageService;

    @Test
    public void shouldListAllFiles() throws Exception {
        // given
        given(storageService.loadAll())
                .willReturn(Stream.of(Paths.get("first.txt"), Paths.get("second.txt")));

        // when
        ResultActions actions =
                mvc.perform(get("/"));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(model().attribute("files",
                        Matchers.contains("http://localhost/files/first.txt",
                                "http://localhost/files/second.txt")));
    }

    @Test
    public void shouldSaveUploadedFile() throws Exception {
        // given
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file", "test.txt", "text/plain", "Spring Framework".getBytes());

        // when
        ResultActions actions =
                mvc.perform(multipart("/")
                        .file(multipartFile));

        // then
        actions
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/"));

        then(storageService).should().store(multipartFile);
    }

    @Test
    public void should404WhenMissingFile() throws Exception {
        // given
        given(storageService.loadAsResource("test.txt"))
                .willThrow(StorageFileNotFoundException.class);

        // when
        ResultActions actions =
                mvc.perform(get("/files/test.txt"));

        // then
        actions
                .andExpect(status().isNotFound());
    }

}
