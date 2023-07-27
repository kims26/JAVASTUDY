package vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter 
@Getter
@ToString
//@Data : @NoArgsConstructor + @AllArgsConstructor +
//        @Setter + @Getter  + @ToString()
public class PersonVo {
    
    //@NoArgsConstructor
    public PersonVo() {
    }

    //@AllArgsConstructor   : 가급적이면 사용하지 말것 
    public PersonVo(String name, int age, String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }
    
    String name;
    int    age;
    String addr;
   

}
