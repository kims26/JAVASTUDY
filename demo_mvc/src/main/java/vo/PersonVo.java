package vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonVo {
    
    String name;
    int    age;
    String addr;

    public PersonVo() {
        super();
    }

    public PersonVo(String name, int age, String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }

    


}
