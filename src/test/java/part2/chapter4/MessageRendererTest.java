package part2.chapter4;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageRendererTest {
    @Test
    void usesCorrectSubRenderers() {
        var sut = new MessageRenderer();

        List<Renderer> subRenderers = sut.getSubRenderers();

        assertThat(3).isEqualTo(subRenderers.size());
        assertThat(subRenderers.get(0) instanceof HeaderRenderer).isTrue();
        assertThat(subRenderers.get(1) instanceof BodyRenderer).isTrue();
        assertThat(subRenderers.get(2) instanceof FooterRenderer).isTrue();
    }

    @Test
    void isImplementedCorrectly() {
        // given
        Path path = Paths.get("src/main/java/part2/chapter4/MessageRenderer.java");

        String sourceCode = null;
        try {
            sourceCode = Files.readString(path);
        } catch (IOException e) {

        }

        // then
        assertThat(sourceCode).isEqualTo(
                """
                        package part2.chapter4;
                        
                        import java.util.List;
                        
                        public class MessageRenderer implements Renderer {
                            private final List<Renderer> subRenderers;
                        
                            public MessageRenderer() {
                                this.subRenderers = List.of(
                                        new HeaderRenderer(),
                                        new BodyRenderer(),
                                        new FooterRenderer()
                                );
                            }
                        
                            public List<Renderer> getSubRenderers() {
                                return this.subRenderers;
                            }
                        
                            @Override
                            public String render(Message message) {
                                return this.subRenderers.stream()
                                        .map(renderer -> renderer.render(message))
                                        .reduce("", (a, b) -> a + b);
                            }
                        }
                        """
        );
    }

    @Test
    void renderingAMessage() {
        var sut = new MessageRenderer();
        var message = new Message(
                "h",
                "b",
                "f"
        );

        String html = sut.render(message);
        assertThat(html).isEqualTo("<h1>h</h1><b>b</b><hr>f</hr>");
    }
}