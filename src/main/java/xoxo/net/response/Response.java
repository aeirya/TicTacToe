package xoxo.net.response;

import com.google.gson.Gson;

public class Response {
    public final NetResponse type;
    public final String body;

    public Response(byte[] data) {
        final String context = new String(data);
        final Response response = new Gson().fromJson(context, Response.class);
        this.type = response.type;
        this.body = response.body;
    }

    public Response(NetResponse type, String body) {
        this.type = type;
        this.body = body;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public byte[] getBytes() {
        return this.toString().getBytes();
    }
}