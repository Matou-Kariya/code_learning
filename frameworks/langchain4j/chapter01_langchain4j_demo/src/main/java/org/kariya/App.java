package org.kariya;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class App {

    public static void main(String[] args) {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .apiKey(System.getenv("API_KEY_DASHSCOPE")).modelName("qwen3-max").logRequests(true).logResponses(true).build();
        // 调用chat方法交互
        String result = model.chat("介绍下河海大学");
        System.out.println(result);
    }
}
