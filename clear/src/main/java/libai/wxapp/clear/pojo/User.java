package libai.wxapp.clear.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;

    private String name;

    private String passworld;
    private Integer score;

    private Integer getnum;

    private Integer postnum;
}