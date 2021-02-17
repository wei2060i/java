package com.nettydemo.beans.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wei
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMessage extends Model<ChatMessage> {

    private static final long serialVersionUID=1L;

    private Long id;

    /**
     * 发送人
     */
    private Long sendUid;

    /**
     * 接受人
     */
    private Long acceptUid;

    /**
     * 信息内容
     */
    private String msg;

    /**
     * 是否签收
     */
    private Boolean signFlag;

    /**
     * 发送信息时间
     */
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
