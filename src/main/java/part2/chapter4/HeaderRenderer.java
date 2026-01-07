package part2.chapter4;

public class HeaderRenderer implements Renderer {
    @Override
    public String render(Message message) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>")
                .append(message.getHeader())
                .append("</h1>");
        return sb.toString();
    }
}
