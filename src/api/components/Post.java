package api.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Post implements PostInterface{

    private int id;
    private int likes;
    private String text;
    private String attachment;

    public Post(int id, int likes, String text) {
        this.id = id;
        this.likes = likes;
        this.text = text;
    }

    public Post(int id, int likes, String text, String attachment) {
        this(id, likes, text);
        this.attachment = attachment;
    }

    public int getId() {
        return this.id;
    }

    public int getLikes() {
        return this.likes;
    }

    public String getText() {
        return this.text;
    }

    public String getAttachmentLink() {
        return attachment;
    }

    public boolean hasAttachment() {
        return this.attachment != null;
    }

    public static void sortByLikes(ArrayList<PostInterface> posts) {
        Collections.sort(posts, Comparator.comparingInt(o -> -o.getLikes()));
    }

    public static void slice(ArrayList<PostInterface> posts, int count) {

        count = posts.size() < count ? posts.size() : count;

        ArrayList<PostInterface> temp = new ArrayList<>(posts.subList(0, count));
        posts.clear();
        posts.addAll(temp);
    }
}
