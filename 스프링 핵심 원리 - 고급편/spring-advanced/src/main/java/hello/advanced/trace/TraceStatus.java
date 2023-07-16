package hello.advanced.trace;

public class TraceStatus {
    private TraceId traceId; // 트랜잭션ID와 Level을 가짐
    private Long startTimeMs; // 시작시간만 알면 얼마나 걸린지 전체 수행 시간 계산 가능
    private String message; // 시작 시 사용한 메시지, 종료 시에도 동일한 메시지 사용

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public void setTraceId(TraceId traceId) {
        this.traceId = traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public void setStartTimeMs(Long startTimeMs) {
        this.startTimeMs = startTimeMs;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
