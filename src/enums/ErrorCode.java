package enums;

public enum ErrorCode {
    EndGame(0),
    Success(1),
    TryAgain(-1);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
