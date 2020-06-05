package cn.quyf.demo.gof.builder;

/**
 * @author quyf
 * @date 2020/5/25 11:28
 * @desc TODO
 **/
public class BuilderDemo {

    public static void main(String[] args) {
        String name = "aaa";
        String add = "nnn";
        long begin = System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            User userExample = User.builder()
                    .id(1)
                    .name(name)
                    .address(add)
                    .build();

        }

        System.out.println("builder take:"+(System.currentTimeMillis()-begin));

        begin = System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            Person p = new Person();
            p.setId(1);
            p.setName(name);
            p.setAddress(add);

        }

        System.out.println("constructor take:"+(System.currentTimeMillis()-begin));

    }

}
