public class WikiPage {
    private final String url;
    private final String title;
    private final StringBuilder content;

    public WikiPage(String url, String title, StringBuilder content) {
        this.url = url;
        this.title = title;
        this.content = content;
    }

    public String toString() {
        return "WikiPage: " +
                "\nPhoto Url: " + url +
                "\nTitle: " + title +
                "\nContent: " +
                "\n" + content;
    }
}
