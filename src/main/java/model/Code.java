package model;

import java.util.Objects;

public class Code {
    private String value;
    private Long userId;
    private Long goodId;

    public Code(String value, Long userId, Long goodId) {
        this.value = value;
        this.userId = userId;
        this.goodId = goodId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    @Override
    public String toString() {
        return "Code{" +
                "value='" + value + '\'' +
                ", userId=" + userId +
                ", goodId=" + goodId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code = (Code) o;
        return Objects.equals(value, code.value) &&
                Objects.equals(userId, code.userId) &&
                Objects.equals(goodId, code.goodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, userId, goodId);
    }
}
