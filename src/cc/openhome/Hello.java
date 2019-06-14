package cc.openhome;

import java.util.*;

public class Hello {
    private Map<String, String> messages;

    public Hello() {
        messages = new HashMap<>();
        messages.put("caterpillar", "Hello");
        messages.put("Justin", "Welcome");
        messages.put("momor", "Hi");
    }

    public String doHello(String user) {
        return String.format("%s, %s!", messages.get(user), user);
    }
}