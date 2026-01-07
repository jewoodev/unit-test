package part2.chapter4;

public class FooterRenderer implements Renderer {
    @Override
    public String render(Message message) {
        StringBuilder sb = new StringBuilder();
        sb.append("<hr>")
                .append(message.getFooter())
                .append("</hr>");
        return sb.toString();
    }
}
