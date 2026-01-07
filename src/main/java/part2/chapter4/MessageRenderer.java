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
