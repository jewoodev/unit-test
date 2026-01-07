package part2.chapter4;

public class BodyRenderer implements Renderer {
    @Override
    public String render(Message message) {
        StringBuilder sb = new StringBuilder();
        sb.append("<b>")
                .append(message.getBody())
                .append("</b>");
        return sb.toString();
    }
}
