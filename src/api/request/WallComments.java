package api.request;

import api.components.Comment;
import api.components.Post;
import api.components.PostInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WallComments extends Entity {

    private PostInterface post;
    private Group group;

    private ArrayList<PostInterface> comments;

    public WallComments(Group group, PostInterface post) throws IOException {
        this.group = group;
        this.post = post;

        HashMap params = new HashMap<>();
        params.put("owner_id", group.getGid() * -1);
        params.put("post_id", post.getId());
        params.put("need_likes", 1);

        this.init("wall.getComments", params);

        this.request.makeRequest();

        if (this.request.getError() != null) {
            return;
        }

        this.setComments();


    }

    public void setComments() {
        this.comments = new ArrayList();

        JSONArray comments = (new JSONObject(this.request.getResponse()))
                .getJSONArray("response");

        comments.remove(0);

        for (int i = 0; i < comments.length(); i++) {
            JSONObject item = comments.getJSONObject(i);

            Comment comment = new Comment(
                    item.getInt("cid"),
                    item.getJSONObject("likes").getInt("count"),
                    item.getInt("uid"),
                    item.getString("text").replace("<br>", "\n")
            );

            this.comments.add(comment);

            Post.sortByLikes(this.comments);
            Post.slice(this.comments, 3);

        }

    }

    public ArrayList<PostInterface> getComments() {
        return comments;
    }
}
