package hello.advanced.trace;

import java.util.UUID;

public class TraceId {
    // TraceId: 트랜잭션ID와 깊이를 표현하는 Level을 묶어서 TraceId라는 개념을 만듦
    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private TraceId createNextId() {
        // level을 주기 위하여 설정, id는 동일하고 level만 하나 더 추가된다
        return new TraceId(id, level + 1);
    }

    private TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
