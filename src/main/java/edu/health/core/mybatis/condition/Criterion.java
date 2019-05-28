package edu.health.core.mybatis.condition;

import java.util.List;

/**
 * 查询条件
 *
 * @author 执笔
 * @date 2018/12/5 10:35
 */
public class Criterion {
    private String condition;

    private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    Criterion(String condition) {
        this.condition = condition;
        this.noValue = true;
    }

    Criterion(String condition, Object value) {
        this.condition = condition;
        this.value = value;
        if (value instanceof List<?>) {
            this.listValue = true;
        } else {
            this.singleValue = true;
        }
    }

    Criterion(String condition, Object value, Object secondValue) {
        this.condition = condition;
        this.value = value;
        this.secondValue = secondValue;
        this.betweenValue = true;
    }


    public String getCondition() {
        return condition;
    }

    public Object getValue() {
        return value;
    }

    public Object getSecondValue() {
        return secondValue;
    }

    public boolean isNoValue() {
        return noValue;
    }

    public boolean isSingleValue() {
        return singleValue;
    }

    public boolean isBetweenValue() {
        return betweenValue;
    }

    public boolean isListValue() {
        return listValue;
    }
}