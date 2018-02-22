package api.request;

import api.components.Post;
import api.components.PostInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Wall extends Entity {

    private Group group;

    private ArrayList<PostInterface> posts;

    public Wall(Group group) throws IOException {
        this.group = group;

        HashMap params = new HashMap<>();
        params.put("owner_id", group.getGid() * -1);

        this.init("wall.get", params);

        this.request.makeRequest();

        if (this.request.getError() != null) {
            return;
        }

        this.setPosts();


    }

    public void setPosts() {
        this.posts = new ArrayList();

        JSONArray posts = (new JSONObject(this.request.getResponse()))
                .getJSONArray("response");

        posts.remove(0);

        for (int i = 0; i < posts.length(); i++) {
            JSONObject item = posts.getJSONObject(i);

            String attach;

            try {
                attach = item.getJSONObject("attachment").getJSONObject("photo").getString("src_big");
            } catch (JSONException e) {
                attach = null;
            }

            Post post = new Post(
                item.getInt("id"),
                item.getJSONObject("likes").getInt("count"),
                item.getString("text").replace("<br>", "\n"),
                attach
            );


            this.posts.add(post);
        }


        Post.sortByLikes(this.posts);
        Post.slice(this.posts, 3);

    }

    public ArrayList<PostInterface> getPosts() {
        return posts;
    }
}
