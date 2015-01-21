package com.chaojidaogou.taskcenter.domain;

/**
 * Created by Administrator on 2015/1/16.
 */
public enum TaskTypeEnum {
    EXAM(0, "考试"), ARTICLE(1, "文章"), LESSON(2, "课程"), RESEARCH(3, "调研"), VOICEFEEDBACK(4, "语音反馈");

    private final Integer code;
    private final String name;

    TaskTypeEnum(int code, String name) {
        this.name = name;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private TaskTypeEnum fromCode(Integer code) {
        for (TaskTypeEnum typeEnum : values()) {
            if (code.equals(typeEnum.getCode())) return typeEnum;
        }
        return null;
    }
}
