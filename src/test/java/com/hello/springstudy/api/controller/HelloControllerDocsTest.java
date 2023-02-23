package com.hello.springstudy.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureRestDocs
//@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(HelloController.class)
public class HelloControllerDocsTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  protected RestDocumentationResultHandler restDocs;

  @Autowired
  protected ObjectMapper objectMapper;

  @BeforeEach
  void setUp(
          final WebApplicationContext context,
          final RestDocumentationContextProvider provider
  ) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context) //@AutoConfigureMockMvc
            .apply(documentationConfiguration(provider)) //@AutoConfigureRestDocs
            .alwaysDo(restDocs)
            .build();
  }

  @Test
  void helloRestDocs() throws Exception {
    mockMvc.perform(get("/hello-eunjiA"))
            .andExpect(status().isOk())
            .andDo(
                    restDocs.document(
                            responseFields(
                                    fieldWithPath("status").type(JsonFieldType.STRING).description("응답 상태"),
                                    fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터")
                            ).andWithPrefix("data.",
                                    fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                                    fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                    fieldWithPath("role").description("담당")
                            )
                    )
            );
  }

  @TestConfiguration
  static class RestDocsConfiguration {

    @Bean
    public RestDocumentationResultHandler write() {
      return MockMvcRestDocumentation.document(
              "{class-name}/{method-name}",
              Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
              Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
      );
    }
  }
}
