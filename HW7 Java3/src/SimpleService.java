@Service(name = "mySimpleService")
public class SimpleService {
    @Init
    public void initService() {
        System.out.println(this.getClass().getName() + " - initService()");
    }

    public void foo() {
        System.out.println(this.getClass().getName() + " - foo()");
    }
}
