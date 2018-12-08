package libai.wxapp.clear.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {
    private Integer tId;

    private String tName;

    private Integer tScore;

    private String tText;

    private Integer tStatus;

    private String tUser;

    private String tReceiver;


}