package api.components;

public class Comment extends Post implements PostInterface {

    private int name;

    public Comment(int id, int likes, int name, String text) {
        super(id, likes, text);
        this.name = name;
    }

    public int getName() {
        return this.name;
    }
}
