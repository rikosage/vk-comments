package api.request;

import api.components.Request;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Request class for getting group or public page information
 */
public class Group extends Entity {

    private String name;
    private int gid;

    public Group(String domain) throws IOException {

        HashMap params = new HashMap<>();
        params.put("group_id", domain);

        this.init("groups.getById", params);

        this.request.makeRequest();

        if (this.request.getError() != null) {
            return;
        }

        JSONObject json = (new JSONObject(this.request.getResponse()))
                .getJSONArray("response")
                .getJSONObject(0);

        this.name = json.getString("name");
        this.gid = json.getInt("gid");
    }

    /**
     * Get GID of group or public page
     *
     * @return GID
     * @throws IOException Exception
     */
    public int getGid() {
        return this.gid;
    }

    /**
     * Get name of group or public page
     *
     * @return Name
     * @throws IOException Exception
     */
    public String getName() {
        return this.name;
    }


}
